package Services.ProprietateServices;

import Entities.Proprietate.Teren;
import Repository.ProprietateRepository.TerenRepository;
import Utils.QueryOutcome;

public class TerenServices
{
    public QueryOutcome addTeren(Teren teren)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.addTeren(teren);
    }
}
