package Services.LocatieServices;

import Entities.Locatie.Comuna;
import Entities.Locatie.Judet;
import Repository.LocatieRepository.ComunaRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class ComunaServices
{
    public Pair<List<Comuna>, QueryOutcome> getListaComune(Judet judet)
    {
        ComunaRepository comunaRepository = new ComunaRepository();
        return comunaRepository.getListaComune(judet);
    }

    public Pair<Boolean, QueryOutcome> isComunaInDatabase(Comuna comuna)
    {
        ComunaRepository comunaRepository = new ComunaRepository();
        return comunaRepository.isComunaInDatabase(comuna);
    }
}
