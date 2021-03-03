package Services;

import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import Repository.AgentRepository;

public class AgentServices
{
    private QueryOutcome queryOutcome;

    public Agent getAgent(User user)
    {
        AgentRepository agentRepository = new AgentRepository();
        Agent agent = agentRepository.getAgent(user);
        this.queryOutcome = agentRepository.getQueryOutcome();
        return agent;
    }

    public QueryOutcome getQueryOutcome()
    {
        return this.queryOutcome;
    }
}
