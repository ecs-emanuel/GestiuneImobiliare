package Services.PersoanaServices;

import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import Repository.PersoanaRepository.AgentRepository;
import javafx.util.Pair;

import java.util.List;

public class AgentServices
{
    public Pair<Agent, QueryOutcome> getAgent(int indexAgent)
    {
        AgentRepository agentRepository = new AgentRepository();
        return agentRepository.getAgent(indexAgent);
    }

    public Pair<Agent, QueryOutcome> getAgent(User user)
    {
        AgentRepository agentRepository = new AgentRepository();
        return agentRepository.getAgent(user);
    }

    public Pair<List<Agent>, QueryOutcome> getListaAgenti()
    {
        AgentRepository agentRepository = new AgentRepository();
        return agentRepository.getListaAgenti();
    }
}
