package Repository.ProprietateRepository;

import Entities.Proprietate.Compartimentare;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompartimentareRepository
{
    public Pair<Compartimentare, QueryOutcome> getCompartimentare(int indexCompartimentare)
    {
        Compartimentare compartimentare = new Compartimentare();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(compartimentare, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM compartimentari\n" +
            "WHERE indexCompartimentare = %d",
            indexCompartimentare
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    compartimentare.setIndexCompartimentare(indexCompartimentare);
                    compartimentare.setOpenspace(resultSet.getInt(2));
                    compartimentare.setLiving(resultSet.getInt(3));
                    compartimentare.setDormitor(resultSet.getInt(4));
                    compartimentare.setDressing(resultSet.getInt(5));
                    compartimentare.setBucatarie(resultSet.getInt(6));
                    compartimentare.setDebara(resultSet.getInt(7));
                    compartimentare.setBaie(resultSet.getInt(8));
                    compartimentare.setHol(resultSet.getInt(9));
                    compartimentare.setMansarda(resultSet.getInt(10));
                    compartimentare.setBalcon(resultSet.getInt(11));
                    compartimentare.setTerasa(resultSet.getInt(12));
                    compartimentare.setGradina(resultSet.getInt(13));
                    compartimentare.setParcare(resultSet.getInt(14));
                    compartimentare.setGaraj(resultSet.getInt(15));
                    compartimentare.setBoxa(resultSet.getInt(16));
                    compartimentare.setPod(resultSet.getInt(17));

                    return new Pair<>(compartimentare, QueryOutcome.SUCCESS);
                }
                return new Pair<>(compartimentare, QueryOutcome.EMPTY);
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
        return new Pair<>(compartimentare, QueryOutcome.ERROR);
    }
}
