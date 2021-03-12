package Services.ProprietateServices;

import Entities.Persoana.Agent;
import Entities.Proprietate.Apartament;
import Repository.ProprietateRepository.ApartamentRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class ApartamentServices
{
    public QueryOutcome addApartament(Apartament apartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.addApartament(apartament);
    }

    public QueryOutcome modApartament(Apartament oldApartament, Apartament newApartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.modApartament(oldApartament, newApartament);
    }

    public QueryOutcome delApartament(Apartament apartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.delApartament(apartament);
    }

    public Pair<Apartament, QueryOutcome> getApartament(int indexApartament)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.getApartament(indexApartament);
    }

    public Pair<List<Apartament>, QueryOutcome> getListaApartamente()
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.getListaApartamente();
    }

    public Pair<List<Apartament>, QueryOutcome> getListaApartamente(Agent agent)
    {
        ApartamentRepository apartamentRepository = new ApartamentRepository();
        return apartamentRepository.getListaApartamente(agent);
    }
}
