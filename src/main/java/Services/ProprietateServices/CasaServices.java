package Services.ProprietateServices;

import Entities.Proprietate.Casa;
import Entities.Proprietate.Teren;
import Repository.ProprietateRepository.CasaRepository;
import Repository.ProprietateRepository.TerenRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class CasaServices
{
    public QueryOutcome addCasa(Casa casa)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.addCasa(casa);
    }

    public Pair<Casa, QueryOutcome> getCasa(int indexCasa)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.getCasa(indexCasa);
    }
}
