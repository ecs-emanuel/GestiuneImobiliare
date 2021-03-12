package Repository.PersoanaRepository;

import Entities.Locatie.Locatie;
import Entities.Persoana.Persoana;
import Repository.DatabaseRepository;
import Repository.LocatieRepository.LocatieRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersoanaRepository
{
    public Pair<Persoana, QueryOutcome> getPersoana(int indexPersoana)
    {
        Persoana persoana = new Persoana();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(persoana, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM persoane\n" +
            "WHERE indexPersoana = %d",
            indexPersoana
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    persoana.setIndexPersoana(indexPersoana);
                    persoana.setNumePersoana(resultSet.getString(2));
                    persoana.setPrenumePersoana(resultSet.getString(3));
                    persoana.setTelefonPersoana(resultSet.getString(4));
                    persoana.setEmailPersoana(resultSet.getString(5));

                    int indexLocatie = resultSet.getInt(6);

                    LocatieRepository locatieRepository = new LocatieRepository();
                    Pair<Locatie, QueryOutcome> queryOutcomePairLocatie = locatieRepository.getLocatie(indexLocatie);
                    QueryOutcome queryOutcome = queryOutcomePairLocatie.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(persoana, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    persoana.setDomiciliuPersoana(queryOutcomePairLocatie.getKey());

                    return new Pair<>(persoana, QueryOutcome.SUCCESS);
                }
                return new Pair<>(persoana, QueryOutcome.EMPTY);
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
        return new Pair<>(persoana, QueryOutcome.ERROR);
    }
}
