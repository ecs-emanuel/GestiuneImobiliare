package Services.PersoanaServices;

import Entities.Persoana.Client;
import Repository.PersoanaRepository.ClientRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;
import java.util.List;

public class ClientServices
{
    public QueryOutcome addClient(Client client)
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.addClient(client);
    }

    public Pair<Client, QueryOutcome> getClient(int indexClient)
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.getClient(indexClient);
    }

    public Pair<List<Client>, QueryOutcome> getListaClienti()
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.getListaClienti();
    }
}
