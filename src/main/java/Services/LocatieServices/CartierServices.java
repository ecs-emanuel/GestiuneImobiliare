package Services.LocatieServices;

import Entities.Locatie.Cartier;
import Entities.Locatie.Oras;
import Repository.LocatieRepository.CartierRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class CartierServices
{
    public Pair<List<Cartier>, QueryOutcome> getListaCartiere(Oras oras)
    {
        CartierRepository cartierRepository = new CartierRepository();
        return cartierRepository.getListaCartiere(oras);
    }
}
