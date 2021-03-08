package Services.PersoanaServices;

import Entities.Persoana.Client;
import Repository.PersoanaRepository.ClientRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.management.Query;
import java.util.List;

public class ClientServices
{
    public QueryOutcome addClient(Client client)
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.addClient(client);
    }

    public Pair<List<Client>, QueryOutcome> getListaClienti()
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.getListaClienti();
    }
}