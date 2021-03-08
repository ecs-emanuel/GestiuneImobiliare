package Services.PersoanaServices;

import Entities.Persoana.Client;
import Repository.PersoanaRepository.ClientRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class ClientServices
{
    public Pair<Client, QueryOutcome> addClient(Client client)
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.addClient(client);
    }
}
