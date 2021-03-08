package Services.LocatieServices;

import Entities.Locatie.Judet;
import Entities.Locatie.Oras;
import Repository.LocatieRepository.OrasRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class OrasServices
{
    public Pair<List<Oras>, QueryOutcome> getListaOrase(Judet judet)
    {
        OrasRepository orasRepository = new OrasRepository();
        return orasRepository.getListaOrase(judet);
    }

    public Pair<Boolean, QueryOutcome> isOrasInDatabase(Oras oras)
    {
        OrasRepository orasRepository = new OrasRepository();
        return orasRepository.isOrasInDatabase(oras);
    }
}
