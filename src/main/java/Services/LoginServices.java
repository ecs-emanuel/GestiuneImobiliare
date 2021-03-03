package Services;

import Utils.QueryMessage;
import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import Interface.HomeUI.HomeUI;

public class LoginServices
{
    private QueryOutcome queryOutcome;
    private QueryMessage queryMessage;

    public void SignIn(User user)
    {
        Agent agent = authenticateUser(user);

        if (agent != null)
        {
            HomeUI homeUI = new HomeUI();
            homeUI.displayInterface(agent);
        }
    }

    private Agent authenticateUser(User user)
    {
        UserServices userServices = new UserServices();
        user = userServices.authenticate(user);
        QueryOutcome userQueryOutcome = userServices.getQueryOutcome();

        switch (userQueryOutcome)
        {
            case SUCCESS:
                return retrieveAgent(user);

            case EMPTY:
                this.queryOutcome = userQueryOutcome;
                this.queryMessage = QueryMessage.ACCOUNT_WRONG;
                break;

            case ERROR:
                this.queryOutcome = userQueryOutcome;
                this.queryMessage = QueryMessage.DATABASE_ERROR;
                break;

            case OFFLINE:
                this.queryOutcome = userQueryOutcome;
                this.queryMessage = QueryMessage.DATABASE_OFFLINE;
                break;
        }
        return null;
    }

    private Agent retrieveAgent(User user)
    {
        AgentServices agentServices = new AgentServices();
        Agent agent = agentServices.getAgent(user);
        QueryOutcome agentQueryOutcome = agentServices.getQueryOutcome();

        switch (agentQueryOutcome)
        {
            case SUCCESS:
                this.queryOutcome = agentQueryOutcome;
                this.queryMessage = QueryMessage.QUERY_SUCCESS;
                return agent;

            case EMPTY:
                this.queryOutcome = agentQueryOutcome;
                this.queryMessage = QueryMessage.ACCOUNT_CURRUPTED;
                break;

            case ERROR:
                this.queryOutcome = agentQueryOutcome;
                this.queryMessage = QueryMessage.DATABASE_ERROR;
                break;

            case OFFLINE:
                this.queryOutcome = agentQueryOutcome;
                this.queryMessage = QueryMessage.DATABASE_OFFLINE;
                break;
        }
        return null;
    }

    public QueryOutcome getQueryOutcome()
    {
        return this.queryOutcome;
    }

    public QueryMessage getQueryMessage()
    {
        return this.queryMessage;
    }
}
