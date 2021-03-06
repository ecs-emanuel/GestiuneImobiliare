package Services.LocatieServices;

import Entities.Locatie.Locatie;
import Repository.LocatieRepository.LocatieRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class LocatieServices
{
    public Pair<Locatie, QueryOutcome> addLocatie(Locatie locatie)
    {
        LocatieRepository locatieRepository = new LocatieRepository();
        return locatieRepository.addLocatie(locatie);
    }
}
