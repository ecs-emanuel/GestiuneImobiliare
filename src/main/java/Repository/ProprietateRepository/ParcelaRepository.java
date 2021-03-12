package Repository.ProprietateRepository;

import Entities.Proprietate.Parcela;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParcelaRepository
{
    public Pair<Parcela, QueryOutcome> getParcela(int indexParcela)
    {
        Parcela parcela = new Parcela();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(parcela, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM parcele\n" +
            "WHERE indexParcela = %d",
            indexParcela
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    parcela.setIndexParcela(indexParcela);
                    parcela.setSuprafataParcela(resultSet.getInt(2));
                    parcela.setHasApa(resultSet.getBoolean(3));
                    parcela.setHasGaz(resultSet.getBoolean(4));
                    parcela.setHasElectricitate(resultSet.getBoolean(5));
                    parcela.sethasCanalizare(resultSet.getBoolean(6));

                    return new Pair<>(parcela, QueryOutcome.SUCCESS);
                }
                return new Pair<>(parcela, QueryOutcome.EMPTY);
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
        return new Pair<>(parcela, QueryOutcome.ERROR);
    }
}
