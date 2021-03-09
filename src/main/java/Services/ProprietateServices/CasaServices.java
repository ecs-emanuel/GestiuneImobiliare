package Services.ProprietateServices;

import Entities.Proprietate.Casa;
import Repository.ProprietateRepository.CasaRepository;
import Utils.QueryOutcome;

public class CasaServices
{
    public QueryOutcome addCasa(Casa casa)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.addCasa(casa);
    }
}
