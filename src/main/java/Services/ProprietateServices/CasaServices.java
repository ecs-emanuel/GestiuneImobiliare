package Services.ProprietateServices;

import Entities.Persoana.Agent;
import Entities.Proprietate.Apartament;
import Entities.Proprietate.Casa;
import Entities.Proprietate.Teren;
import Repository.ProprietateRepository.ApartamentRepository;
import Repository.ProprietateRepository.CasaRepository;
import Repository.ProprietateRepository.TerenRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class CasaServices
{
    public QueryOutcome addCasa(Casa casa)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.addCasa(casa);
    }

    public QueryOutcome delCasa(Casa casa)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.delCasa(casa);
    }

    public Pair<Casa, QueryOutcome> getCasa(int indexCasa)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.getCasa(indexCasa);
    }

    public Pair<List<Casa>, QueryOutcome> getListaCase()
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.getListaCase();
    }

    public Pair<List<Casa>, QueryOutcome> getListaCase(Agent agent)
    {
        CasaRepository casaRepository = new CasaRepository();
        return casaRepository.getListaCase(agent);
    }
}
