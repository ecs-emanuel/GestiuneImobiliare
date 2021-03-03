package Entities.Proprietate;

import Components.DispozitieProprietate;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;

import java.sql.Date;

public class Proprietate
{
    private int indexProprietate;
    String titluProprietate;
    String descriereProprietate;
    int pretProprietate;

    Locatie locatieProprietate;
    Client proprietarProprietate;
    Agent agentProprietate;

    DispozitieProprietate dispozitieProprietate;
    Date dataProprietate;

    public Proprietate()
    {
    }

    public Proprietate(int indexProprietate, String titluProprietate, String descriereProprietate, int pretProprietate,
                       Locatie locatieProprietate, Client proprietarProprietate, Agent agentProprietate,
                       DispozitieProprietate dispozitieProprietate, Date dataProprietate)
    {
        this.indexProprietate = indexProprietate;
        this.titluProprietate = titluProprietate;
        this.descriereProprietate = descriereProprietate;
        this.pretProprietate = pretProprietate;
        this.locatieProprietate = locatieProprietate;
        this.proprietarProprietate = proprietarProprietate;
        this.agentProprietate = agentProprietate;
        this.dispozitieProprietate = dispozitieProprietate;
        this.dataProprietate = dataProprietate;
    }

    public int getIndexProprietate()
    {
        return indexProprietate;
    }

    public void setIndexProprietate(int indexProprietate)
    {
        this.indexProprietate = indexProprietate;
    }

    public String getTitluProprietate()
    {
        return titluProprietate;
    }

    public void setTitluProprietate(String titluProprietate)
    {
        this.titluProprietate = titluProprietate;
    }

    public String getDescriereProprietate()
    {
        return descriereProprietate;
    }

    public void setDescriereProprietate(String descriereProprietate)
    {
        this.descriereProprietate = descriereProprietate;
    }

    public int getPretProprietate()
    {
        return pretProprietate;
    }

    public void setPretProprietate(int pretProprietate)
    {
        this.pretProprietate = pretProprietate;
    }

    public Locatie getLocatieProprietate()
    {
        return locatieProprietate;
    }

    public void setLocatieProprietate(Locatie locatieProprietate)
    {
        this.locatieProprietate = locatieProprietate;
    }

    public Client getProprietarProprietate()
    {
        return proprietarProprietate;
    }

    public void setProprietarProprietate(Client proprietarProprietate)
    {
        this.proprietarProprietate = proprietarProprietate;
    }

    public Agent getAgentProprietate()
    {
        return agentProprietate;
    }

    public void setAgentProprietate(Agent agentProprietate)
    {
        this.agentProprietate = agentProprietate;
    }

    public DispozitieProprietate getDispozitieProprietate()
    {
        return dispozitieProprietate;
    }

    public void setDispozitieProprietate(DispozitieProprietate dispozitieProprietate)
    {
        this.dispozitieProprietate = dispozitieProprietate;
    }

    public Date getDataProprietate()
    {
        return dataProprietate;
    }

    public void setDataProprietate(Date dataProprietate)
    {
        this.dataProprietate = dataProprietate;
    }
}
