package Services.ProprietateServices;

import Entities.Proprietate.Apartament;
import Repository.ProprietateRepository.ApartamentRepository;
import Utils.QueryOutcome;

public class ApartamentServices
{
    public QueryOutcome addApartament(Apartament apartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.addApartament(apartament);
    }
}
