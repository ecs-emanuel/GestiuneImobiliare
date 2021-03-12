package Services.PersoanaServices;

import Entities.Persoana.Client;
import Repository.PersoanaRepository.ClientRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.util.ArrayList;
import java.util.List;

public class ClientServices
{
    public QueryOutcome addClient(Client client)
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.addClient(client);
    }

    public QueryOutcome modClient(Client oldClient, Client newClient)
    {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.modClient(oldClient, newClient);
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

    public Pair<List<Client>, QueryOutcome> getListaClient(String text)
    {
        ClientRepository clientRepository = new ClientRepository();

        Pair<List<Client>, QueryOutcome> queryOutcomePairClient = clientRepository.getListaClienti();
        QueryOutcome queryOutcome = queryOutcomePairClient.getValue();

        if (queryOutcome != QueryOutcome.SUCCESS)
        {
            return queryOutcomePairClient;
        }

        List<Client> listaClienti = queryOutcomePairClient.getKey();
        List<Client> listaClientiFiltrata = new ArrayList<>();

        for (Client client : listaClienti)
        {
            if (client.getTelefonPersoana().contains(text))
            {
                listaClientiFiltrata.add(client);
            }
        }

        if (listaClientiFiltrata.size() <= 0)
        {
            return new Pair<>(listaClientiFiltrata, QueryOutcome.EMPTY);
        }

        return new Pair<>(listaClientiFiltrata, QueryOutcome.SUCCESS);
    }
}
