package Services.LocatieServices;

import Entities.Locatie.Judet;
import Repository.LocatieRepository.JudetRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class JudetServices
{
    public Pair<Judet, QueryOutcome> getJudet(int indexJudet)
    {
        JudetRepository judetRepository = new JudetRepository();
        return judetRepository.getJudet(indexJudet);
    }

    public Pair<List<Judet>, QueryOutcome> getListaJudete()
    {
        JudetRepository judetRepository = new JudetRepository();
        return judetRepository.getListaJudete();
    }

    public Pair<Boolean, QueryOutcome> isJudetInDatabase(Judet judet)
    {
        JudetRepository judetRepository = new JudetRepository();
        return judetRepository.isJudetInDatabase(judet);
    }
}
