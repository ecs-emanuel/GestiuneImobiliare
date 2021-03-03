package Entities.Persoana;

import Entities.Locatie.Locatie;

public class Client extends Persoana
{
    private int indexClient;

    public Client()
    {
    }

    public Client(int indexClient)
    {
        this.indexClient = indexClient;
    }

    public Client(int indexPersoana, String numePersoana, String prenumePersoana, String telefonPersoana, String emailPersoana, Locatie domiciliuPersoana, int indexClient)
    {
        super(indexPersoana, numePersoana, prenumePersoana, telefonPersoana, emailPersoana, domiciliuPersoana);
        this.indexClient = indexClient;
    }

    public int getIndexClient()
    {
        return indexClient;
    }

    public void setIndexClient(int indexClient)
    {
        this.indexClient = indexClient;
    }

}
