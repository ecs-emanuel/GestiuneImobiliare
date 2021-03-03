package Repository;

import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import javafx.util.Pair;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AgentRepository
{
    public Pair<Agent, QueryOutcome> getAggent(User user)
    {
        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM agenti " +
            "WHERE userAgent = %d",
            user.getIndexUser()
        );

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        // no connection / offline
        if (connection == null)
        {
            return new Pair<>(null, QueryOutcome.OFFLINE);
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                // user found in database
                if (resultset.first())
                {
                    Agent agent = new Agent();
                    agent.setIndexAgent(resultset.getInt(1));
                    agent.setUserAgent(user);
                    return new Pair<>(agent, QueryOutcome.SUCCESS);
                }

                // nothing found
                return new Pair<>(null, QueryOutcome.EMPTY);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        // if we reached this point, something went wrong
        return new Pair<>(null, QueryOutcome.ERROR);
    }
}
