package Repository;

import Entities.Programare;
import Utils.QueryOutcome;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramareRepository
{
    public QueryOutcome addProgramare(Programare programare)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        String sqlScript = String.format
        (
            "INSERT INTO programari(dataProgramare, agentProgramare, clientProgramare) VALUES\n" +
            "('%s', %d, %d)",
            programare.getData(), programare.getAgent().getIndexAgent(), programare.getClient().getIndexClient()
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
