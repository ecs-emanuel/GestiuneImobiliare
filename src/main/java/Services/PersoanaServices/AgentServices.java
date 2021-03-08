package Services.PersoanaServices;

import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import Repository.PersoanaRepository.AgentRepository;
import javafx.util.Pair;

public class AgentServices
{
    public Pair<Agent, QueryOutcome> getAgent(User user)
    {
        AgentRepository agentRepository = new AgentRepository();
        return agentRepository.getAgent(user);
    }
}
