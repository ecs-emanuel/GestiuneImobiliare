package Services.ProprietateServices;

import Entities.Persoana.Agent;
import Entities.Proprietate.Teren;
import Repository.ProprietateRepository.TerenRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class TerenServices
{
    public QueryOutcome addTeren(Teren teren)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.addTeren(teren);
    }

    public QueryOutcome delTeren(Teren teren)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.delTeren(teren);
    }

    public Pair<Teren, QueryOutcome> getTeren(int indexTeren)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.getTeren(indexTeren);
    }

    public Pair<List<Teren>, QueryOutcome> getListaTerenuri()
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.getListaTerenuri();
    }

    public Pair<List<Teren>, QueryOutcome> getListaTerenuri(Agent agent)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.getListaTerenuri(agent);
    }

}
