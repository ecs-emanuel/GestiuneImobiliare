package Services.LocatieServices;

import Entities.Locatie.Comuna;
import Entities.Locatie.Sat;
import Repository.LocatieRepository.SatRepository;

import java.util.List;

public class SatServices
{
    public List<Sat> getListaSate(Comuna comuna)
    {
        SatRepository satRepository = new SatRepository();
        return satRepository.getListaSate(comuna);
    }
}
