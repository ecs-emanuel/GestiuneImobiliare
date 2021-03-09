package Entities.Proprietate;

import Components.DispozitieProprietate;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;

import java.sql.Date;

public class Casa extends Proprietate
{
    private int indexCasa;
    Constructie constructieCasa;

    public Casa()
    {
    }

    public Casa(int indexCasa, Constructie constructieCasa)
    {
        this.indexCasa = indexCasa;
        this.constructieCasa = constructieCasa;
    }

    public Casa(int indexProprietate, String titluProprietate, String descriereProprietate, int pretProprietate,
                Locatie locatieProprietate, Client proprietarProprietate, Agent agentProprietate,
                DispozitieProprietate dispozitieProprietate, Date dataProprietate,
                int indexCasa, Constructie constructieCasa)
    {
        super(indexProprietate, titluProprietate, descriereProprietate, pretProprietate, locatieProprietate, proprietarProprietate, agentProprietate, dispozitieProprietate, dataProprietate);
        this.indexCasa = indexCasa;
        this.constructieCasa = constructieCasa;
    }

    public int getIndexCasa()
    {
        return indexCasa;
    }

    public void setIndexCasa(int indexCasa)
    {
        this.indexCasa = indexCasa;
    }

    public Constructie getConstructieCasa()
    {
        return constructieCasa;
    }

    public void setConstructieCasa(Constructie constructieCasa)
    {
        this.constructieCasa = constructieCasa;
    }
}
