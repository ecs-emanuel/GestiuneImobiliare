package Repository.LocatieRepository;

import Entities.Locatie.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatieRepository
{
    public Pair<Locatie, QueryOutcome> getLocatie(int indexLocatie)
    {
        Locatie locatie = new Locatie();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(locatie, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM locatii\n" +
            "WHERE indexLocatie = %d",
            indexLocatie
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    locatie.setIndexLocatie(indexLocatie);

                    int indexJudet = resultSet.getInt(2);
                    JudetRepository judetRepository = new JudetRepository();
                    Pair<Judet, QueryOutcome> queryOutcomePairJudet = judetRepository.getJudet(indexJudet);
                    QueryOutcome queryOutcome = queryOutcomePairJudet.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(locatie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    locatie.setJudetLocatie(queryOutcomePairJudet.getKey());

                    int indexOras = resultSet.getInt(3);
                    int indexComuna = resultSet.getInt(5);

                    // locatie has oras instead of comuna
                    if (indexOras > 0)
                    {
                        OrasRepository orasRepository = new OrasRepository();
                        Pair<Oras, QueryOutcome> queryOutcomePairOras = orasRepository.getOras(indexOras);
                        queryOutcome = queryOutcomePairOras.getValue();

                        if (queryOutcome != QueryOutcome.SUCCESS)
                        {
                            return new Pair<>(locatie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                        }

                        locatie.setOrasLocatie(queryOutcomePairOras.getKey());

                        int indexCartier = resultSet.getInt(4);
                        CartierRepository cartierRepository = new CartierRepository();
                        Pair<Cartier, QueryOutcome> queryOutcomePairCartier = cartierRepository.getCartier(indexCartier);
                        queryOutcome = queryOutcomePairCartier.getValue();

                        if (queryOutcome != QueryOutcome.SUCCESS)
                        {
                            return new Pair<>(locatie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                        }

                        locatie.setCartierLocatie(queryOutcomePairCartier.getKey());
                    }
                    else if (indexComuna > 0)
                    {
                        ComunaRepository comunaRepository = new ComunaRepository();
                        Pair<Comuna, QueryOutcome> queryOutcomePairComuna = comunaRepository.getComuna(indexComuna);
                        queryOutcome = queryOutcomePairComuna.getValue();

                        if (queryOutcome != QueryOutcome.SUCCESS)
                        {
                            return new Pair<>(locatie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                        }

                        locatie.setComunaLocatie(queryOutcomePairComuna.getKey());

                        int indexSat = resultSet.getInt(6);
                        SatRepository satRepository = new SatRepository();
                        Pair<Sat, QueryOutcome> queryOutcomePairSat = satRepository.getSat(indexSat);
                        queryOutcome = queryOutcomePairSat.getValue();

                        if (queryOutcome != QueryOutcome.SUCCESS)
                        {
                            return new Pair<>(locatie, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                        }

                        locatie.setSatLocatie(queryOutcomePairSat.getKey());
                    }
                    else
                    {
                        return new Pair<>(locatie, QueryOutcome.CORRUPT);
                    }

                    locatie.setDenumireLocatie(resultSet.getString(7));
                    return new Pair<>(locatie, QueryOutcome.SUCCESS);
                }
                return new Pair<>(locatie, QueryOutcome.EMPTY);
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
        return new Pair<>(locatie, QueryOutcome.ERROR);
    }

    public QueryOutcome addLocatie(Locatie locatie)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        String sqlScript = String.format
        (
            "INSERT INTO locatii(judetLocatie, orasLocatie, cartierLocatie, comunaLocatie, satLocatie, denumireLocatie) VALUES\n" +
            "(%d, %d, %d, %d, %d, '%s')",
            locatie.getJudetLocatie().getIndexJudet(), locatie.getOrasLocatie().getIndexOras(), locatie.getCartierLocatie().getIndexCartier(),
            locatie.getComunaLocatie().getIndexComuna(), locatie.getSatLocatie().getIndexSat(), locatie.getDenumireLocatie()
        );

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sqlScript);
            return QueryOutcome.SUCCESS;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return QueryOutcome.ERROR;
    }
}
