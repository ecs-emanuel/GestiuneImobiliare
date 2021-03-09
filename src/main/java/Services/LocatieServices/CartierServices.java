package Services.LocatieServices;

import Entities.Locatie.Cartier;
import Entities.Locatie.Oras;
import Repository.LocatieRepository.CartierRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class CartierServices
{
    public Pair<Cartier, QueryOutcome> getCartier(int indexCartier)
    {
        CartierRepository cartierRepository = new CartierRepository();
        return cartierRepository.getCartier(indexCartier);
    }

    public Pair<List<Cartier>, QueryOutcome> getListaCartiere(Oras oras)
    {
        CartierRepository cartierRepository = new CartierRepository();
        return cartierRepository.getListaCartiere(oras);
    }

    public Pair<Boolean, QueryOutcome> isCartierInDatabase(Cartier cartier)
    {
        CartierRepository cartierRepository = new CartierRepository();
        return cartierRepository.isCartierInDatabase(cartier);
    }
}
