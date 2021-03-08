package Services.LocatieServices;

import Entities.Locatie.Comuna;
import Entities.Locatie.Sat;
import Repository.LocatieRepository.SatRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class SatServices
{
    public Pair<List<Sat>, QueryOutcome> getListaSate(Comuna comuna)
    {
        SatRepository satRepository = new SatRepository();
        return satRepository.getListaSate(comuna);
    }

    public Pair<Boolean, QueryOutcome> isSatInDatabase(Sat sat)
    {
        SatRepository satRepository = new SatRepository();
        return satRepository.isSatInDatabase(sat);
    }
}
