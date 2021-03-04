package Services;

import Entities.Locatie.Comuna;
import Entities.Locatie.Sat;
import Repository.SatRepository;

import java.util.List;

public class SatServices
{
    public List<Sat> getListaSate(Comuna comuna)
    {
        SatRepository satRepository = new SatRepository();
        return satRepository.getListaSate(comuna);
    }
}
