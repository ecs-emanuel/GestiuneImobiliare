package Repository.PersoanaRepository;

import Utils.QueryOutcome;
import Entities.Persoana.Client;

public class ClientRepository
{
    private QueryOutcome queryOutcome;

    public static void main(String[] args)
    {
        ClientRepository obj = new ClientRepository();
        Client client = new Client();
        obj.addClient(client);

    }

    public void addClient(Client client)
    {

    }


}
