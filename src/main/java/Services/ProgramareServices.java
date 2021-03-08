package Services;

import Entities.Programare;
import Repository.ProgramareRepository;
import Utils.QueryOutcome;

public class ProgramareServices
{
    public QueryOutcome addProgramare(Programare programare)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.addProgramare(programare);
    }
}
