package Repository;

import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class AgentRepository
{
    private QueryOutcome queryOutcome;

    public Agent getAgent(User user)
    {
        String sqlScript = String.format(
        "SELECT * " +
        "FROM agenti " +
        "WHERE userAgent = %d",
        user.getIndexUser());

        DatabaseRepository databaseRepository = new DatabaseRepository();
        CachedRowSet rowset = databaseRepository.retrieveData(sqlScript);
        QueryOutcome result = databaseRepository.getQueryOutcome();

        if (result != result.SUCCESS)
        {
            this.queryOutcome = result;
            return null;
        }

        try
        {
            if (rowset.first())
            {
                Agent agent = new Agent();
                agent.setIndexAgent(rowset.getInt(1));
                agent.setUserAgent(user);
                this.queryOutcome = QueryOutcome.SUCCESS;
                return agent;
            }
            else
            {
                this.queryOutcome = QueryOutcome.EMPTY;
            }
        }
        catch (SQLException throwables)
        {
            this.queryOutcome = QueryOutcome.ERROR;
            throwables.printStackTrace();
        }
        return null;
    }

    public QueryOutcome getQueryOutcome()
    {
        return this.queryOutcome;
    }
}
