package Services.ProprietateServices;

import Entities.Proprietate.Constructie;
import Repository.ProprietateRepository.ConstructieRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class ConstructieServices
{
    public Pair<Constructie, QueryOutcome> getConstructie(int indexConstructie)
    {
        ConstructieRepository constructieRepository = new ConstructieRepository();
        return constructieRepository.getConstructie(indexConstructie);
    }
}
