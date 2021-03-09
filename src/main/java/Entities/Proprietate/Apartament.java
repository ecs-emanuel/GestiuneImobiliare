package Entities.Proprietate;

import Components.DispozitieProprietate;
import Components.EtajApartament;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;

import java.sql.Date;

public class Apartament extends Proprietate
{
    private int indexApartament;
    private EtajApartament etajApartament;
    Constructie constructieApartament;

    public Apartament()
    {
    }

    public Apartament(int indexApartament, EtajApartament etajApartament, Constructie constructieApartament)
    {
        this.indexApartament = indexApartament;
        this.etajApartament = etajApartament;
        this.constructieApartament = constructieApartament;
    }

    public Apartament(int indexProprietate, String titluProprietate, String descriereProprietate, int pretProprietate,
                      Locatie locatieProprietate, Client proprietarProprietate, Agent agentProprietate,
                      DispozitieProprietate dispozitieProprietate, Date dataProprietate,
                      int indexApartament, EtajApartament etajApartament, Constructie constructieApartament)
    {
        super(indexProprietate, titluProprietate, descriereProprietate, pretProprietate, locatieProprietate, proprietarProprietate, agentProprietate, dispozitieProprietate, dataProprietate);
        this.indexApartament = indexApartament;
        this.etajApartament = etajApartament;
        this.constructieApartament = constructieApartament;
    }

    public int getIndexApartament()
    {
        return indexApartament;
    }

    public void setIndexApartament(int indexApartament)
    {
        this.indexApartament = indexApartament;
    }

    public EtajApartament getEtajApartament()
    {
        return etajApartament;
    }

    public void setEtajApartament(EtajApartament etajApartament)
    {
        this.etajApartament = etajApartament;
    }

    public Constructie getConstructieApartament()
    {
        return constructieApartament;
    }

    public void setConstructieApartament(Constructie constructieApartament)
    {
        this.constructieApartament = constructieApartament;
    }
}
