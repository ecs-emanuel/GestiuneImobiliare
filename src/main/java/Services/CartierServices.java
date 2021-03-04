package Services;

import Entities.Locatie.Cartier;
import Entities.Locatie.Oras;
import Repository.CartierRepository;

import java.util.List;

public class CartierServices
{
    public List<Cartier> getListaCartiere(Oras oras)
    {
        CartierRepository cartierRepository = new CartierRepository();
        return cartierRepository.getListaCartiere(oras);
    }
}
