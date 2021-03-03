package Entities;

import Entities.Persoana.Agent;
import Entities.Persoana.Client;

import java.sql.Date;

public class Programare
{
    private int indexProgramare;
    private Date data;
    private Agent agent;
    private Client client;

    public Programare()
    {
    }

    public Programare(int indexProgramare, Date data, Agent agent, Client client)
    {
        this.indexProgramare = indexProgramare;
        this.data = data;
        this.agent = agent;
        this.client = client;
    }

    public int getIndexProgramare()
    {
        return indexProgramare;
    }

    public void setIndexProgramare(int indexProgramare)
    {
        this.indexProgramare = indexProgramare;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Agent getAgent()
    {
        return agent;
    }

    public void setAgent(Agent agent)
    {
        this.agent = agent;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
}
