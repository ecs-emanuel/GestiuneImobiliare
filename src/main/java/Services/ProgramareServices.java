package Services;

import Entities.Persoana.Agent;
import Entities.Programare;
import Repository.ProgramareRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class ProgramareServices
{
    public QueryOutcome addProgramare(Programare programare)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.addProgramare(programare);
    }

    public QueryOutcome delProgramare(Programare programare)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.delProgramare(programare);
    }

    public Pair<List<Programare>, QueryOutcome> getListaProgramari(Agent agent, boolean istoricComplet)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.getListaProgramari(agent, istoricComplet);
    }
}
