package Services.PersoanaServices;

import Entities.Persoana.Persoana;
import Entities.Persoana.User;
import Repository.PersoanaRepository.PersoanaRepository;
import Repository.PersoanaRepository.UserRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class PersoanaServices
{
    public Pair<Persoana, QueryOutcome> getPersoana(int indexPersoana)
    {
        PersoanaRepository persoanaRepository = new PersoanaRepository();
        return persoanaRepository.getPersoana(indexPersoana);
    }
}
