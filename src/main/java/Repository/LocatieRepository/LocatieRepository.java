package Repository.LocatieRepository;

import Entities.Locatie.Comuna;
import Entities.Locatie.Locatie;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatieRepository
{
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
