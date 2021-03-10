package Services.ProprietateServices;

import Entities.Proprietate.Teren;
import Repository.ProprietateRepository.TerenRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class TerenServices
{
    public QueryOutcome addTeren(Teren teren)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.addTeren(teren);
    }

    public Pair<Teren, QueryOutcome> getTeren(int indexTeren)
    {
        TerenRepository terenRepository = new TerenRepository();
        return terenRepository.getTeren(indexTeren);
    }
}
