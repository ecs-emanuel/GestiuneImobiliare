package Services;

import Entities.Persoana.Agent;
import Entities.Persoana.User;
import Services.PersoanaServices.AgentServices;
import Services.PersoanaServices.UserServices;
import Utils.QueryMessage;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class LoginServices
{
    public Pair<Agent, QueryMessage> signIn(User user)
    {
        return authenticateUser(user);
    }

    private Pair<Agent, QueryMessage> authenticateUser(User user)
    {
        UserServices userServices = new UserServices();
        Pair<User, QueryOutcome> userQueryOutcomePair = userServices.authenticate(user);

        switch (userQueryOutcomePair.getValue())
        {
            case OFFLINE: return new Pair<>(null, QueryMessage.DATABASE_OFFLINE);
            case ERROR: return new Pair<>(null, QueryMessage.DATABASE_ERROR);
            case EMPTY: return new Pair<>(null, QueryMessage.ACCOUNT_WRONG);
            case SUCCESS: return retrieveAgent(user);
        }
        return new Pair<>(null, QueryMessage.DATABASE_ERROR);
    }

    private Pair<Agent, QueryMessage> retrieveAgent(User user)
    {
        AgentServices agentServices = new AgentServices();
        Pair<Agent, QueryOutcome> agentQueryOutcomePair = agentServices.getAgent(user);

        switch (agentQueryOutcomePair.getValue())
        {
            case OFFLINE: return new Pair<>(null, QueryMessage.DATABASE_OFFLINE);
            case ERROR: return new Pair<>(null, QueryMessage.DATABASE_ERROR);
            case EMPTY: return new Pair<>(null, QueryMessage.ACCOUNT_CURRUPTED);
            case SUCCESS: return new Pair<>(agentQueryOutcomePair.getKey(), QueryMessage.QUERY_SUCCESS);
        }
        return new Pair<>(null, QueryMessage.DATABASE_ERROR);
    }
}
