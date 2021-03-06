package Services.LocatieServices;

import Entities.Locatie.Judet;
import Repository.LocatieRepository.JudetRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class JudetServices
{
    public Pair<List<Judet>, QueryOutcome> getListaJudete()
    {
        JudetRepository judetRepository = new JudetRepository();
        return judetRepository.getListaJudete();
    }
}
