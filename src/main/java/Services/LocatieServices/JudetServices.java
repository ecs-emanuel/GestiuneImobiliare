package Services.LocatieServices;

import Entities.Locatie.Judet;
import Repository.LocatieRepository.JudetRepository;

import java.util.List;

public class JudetServices
{
    public List<Judet> getListaJudete()
    {
        JudetRepository judetRepository = new JudetRepository();
        return judetRepository.getListaJudete();
    }
}
