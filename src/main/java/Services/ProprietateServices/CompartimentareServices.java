package Services.ProprietateServices;

import Entities.Proprietate.Compartimentare;
import Repository.ProprietateRepository.CompartimentareRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class CompartimentareServices
{
    public Pair<Compartimentare, QueryOutcome> getCompartimentare(int indexCompartimentare)
    {
        CompartimentareRepository compartimentareRepository = new CompartimentareRepository();
        return compartimentareRepository.getCompartimentare(indexCompartimentare);
    }
}
