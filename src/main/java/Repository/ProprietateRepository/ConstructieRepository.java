package Repository.ProprietateRepository;

import Components.DispozitieConstructie;
import Components.StructuraConstructie;
import Entities.Proprietate.Compartimentare;
import Entities.Proprietate.Constructie;
import Entities.Proprietate.Parcela;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConstructieRepository
{
    public Pair<Constructie, QueryOutcome> getConstructie(int indexConstructie)
    {
        Constructie constructie = new Constructie();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(constructie, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM constructii\n" +
            "WHERE indexConstructie = %d",
            indexConstructie
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    constructie.setIndexConstructie(indexConstructie);
                    constructie.setSuprafataUtilizabila(resultSet.getInt(2));
                    constructie.setSuprafataConstructie(resultSet.getInt(3));
                    constructie.setInaltimeConstructie(resultSet.getInt(4));
                    constructie.setAnConstructie(resultSet.getInt(5));
                    constructie.setStructuraConstructie(StructuraConstructie.valueOf(resultSet.getString(6)));
                    constructie.setDispozitieActuala(DispozitieConstructie.valueOf(resultSet.getString(7)));
                    constructie.setDispozitiePredare(DispozitieConstructie.valueOf(resultSet.getString(8)));

                    int indexCompartimentare = resultSet.getInt(9);
                    CompartimentareRepository compartimentareRepository = new CompartimentareRepository();
                    Pair<Compartimentare, QueryOutcome> queryOutcomePairCompartimentare =
                    compartimentareRepository.getCompartimentare(indexCompartimentare);
                    QueryOutcome queryOutcome = queryOutcomePairCompartimentare.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(constructie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    constructie.setCompartimentareConstructie(queryOutcomePairCompartimentare.getKey());

                    int indexParcela = resultSet.getInt(10);
                    ParcelaRepository parcelaRepository = new ParcelaRepository();
                    Pair<Parcela, QueryOutcome> queryOutcomePairParcela = parcelaRepository.getParcela(indexParcela);
                    queryOutcome = queryOutcomePairParcela.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(constructie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    constructie.setParcelaConstructie(queryOutcomePairParcela.getKey());

                    return new Pair<>(constructie, QueryOutcome.SUCCESS);
                }
                return new Pair<>(constructie, QueryOutcome.EMPTY);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            databaseRepository.closeConnection(connection);
        }
        return new Pair<>(constructie, QueryOutcome.ERROR);
    }
}
