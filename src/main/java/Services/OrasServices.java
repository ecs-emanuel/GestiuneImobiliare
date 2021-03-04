package Services;

import Entities.Locatie.Judet;
import Entities.Locatie.Oras;
import Repository.OrasRepository;

import java.util.List;

public class OrasServices
{
    public List<Oras> getListaOrase(Judet judet)
    {
        OrasRepository orasRepository = new OrasRepository();
        return orasRepository.getListaOrase(judet);
    }
}
