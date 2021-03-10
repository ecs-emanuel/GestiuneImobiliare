package Services.ProprietateServices;

import Entities.Proprietate.Apartament;
import Repository.ProprietateRepository.ApartamentRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class ApartamentServices
{
    public QueryOutcome addApartament(Apartament apartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.addApartament(apartament);
    }

    public Pair<Apartament, QueryOutcome> getApartament(int indexApartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.getApartament(indexApartament);
    }
}
