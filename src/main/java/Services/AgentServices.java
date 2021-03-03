package Services;

import Repository.UserRepository;
import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import Repository.AgentRepository;
import javafx.util.Pair;

public class AgentServices
{
    public Pair<Agent, QueryOutcome> getAgent(User user)
    {
        AgentRepository agentRepository = new AgentRepository();
        return agentRepository.getAggent(user);
    }
}
