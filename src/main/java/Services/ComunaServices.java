package Services;

import Entities.Locatie.Comuna;
import Entities.Locatie.Judet;
import Repository.ComunaRepository;

import java.util.List;

public class ComunaServices
{
    public List<Comuna> getListaComune(Judet judet)
    {
        ComunaRepository comunaRepository = new ComunaRepository();
        return comunaRepository.getListaComune(judet);
    }
}
