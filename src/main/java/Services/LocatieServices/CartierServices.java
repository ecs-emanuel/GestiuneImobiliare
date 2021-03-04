package Services.LocatieServices;

import Entities.Locatie.Cartier;
import Entities.Locatie.Oras;
import Repository.LocatieRepository.CartierRepository;

import java.util.List;

public class CartierServices
{
    public List<Cartier> getListaCartiere(Oras oras)
    {
        CartierRepository cartierRepository = new CartierRepository();
        return cartierRepository.getListaCartiere(oras);
    }
}
