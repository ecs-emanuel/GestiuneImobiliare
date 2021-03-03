package Entities.Persoana;

import Entities.Locatie.Locatie;

public class Agent extends Persoana
{
    private int indexAgent;
    private User userAgent;

    public Agent()
    {
    }

    public Agent(int indexAgent, User userAgent)
    {
        this.indexAgent = indexAgent;
        this.userAgent = userAgent;
    }

    public Agent(int indexPersoana, String numePersoana, String prenumePersoana, String telefonPersoana, String emailPersoana, Locatie domiciliuPersoana, int indexAgent, User userAgent)
    {
        super(indexPersoana, numePersoana, prenumePersoana, telefonPersoana, emailPersoana, domiciliuPersoana);
        this.indexAgent = indexAgent;
        this.userAgent = userAgent;
    }

    public int getIndexAgent()
    {
        return indexAgent;
    }

    public void setIndexAgent(int indexAgent)
    {
        this.indexAgent = indexAgent;
    }

    public User getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(User userAgent)
    {
        this.userAgent = userAgent;
    }
}
