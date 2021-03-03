package Entities.Proprietate;

import Components.DispozitieProprietate;
import Components.DispozitieTeren;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;

import java.sql.Date;

public class Teren extends Proprietate
{
    private int indexTeren;
    private DispozitieTeren dispozitieTeren;
    Parcela parcelaTeren;

    public Teren()
    {
    }

    public Teren(int indexTeren, DispozitieTeren dispozitieTeren, Parcela parcelaTeren)
    {
        this.indexTeren = indexTeren;
        this.dispozitieTeren = dispozitieTeren;
        this.parcelaTeren = parcelaTeren;
    }

    public Teren(int indexProprietate, String titluProprietate, String descriereProprietate, int pretProprietate,
                 Locatie locatieProprietate, Client proprietarProprietate, Agent agentProprietate,
                 DispozitieProprietate dispozitieProprietate, Date dataProprietate,
                 int indexTeren, DispozitieTeren dispozitieTeren, Parcela parcelaTeren)
    {
        super(indexProprietate, titluProprietate, descriereProprietate, pretProprietate, locatieProprietate, proprietarProprietate, agentProprietate, dispozitieProprietate, dataProprietate);
        this.indexTeren = indexTeren;
        this.dispozitieTeren = dispozitieTeren;
        this.parcelaTeren = parcelaTeren;
    }

    public int getIndexTeren()
    {
        return indexTeren;
    }

    public void setIndexTeren(int indexTeren)
    {
        this.indexTeren = indexTeren;
    }

    public DispozitieTeren getDispozitieTeren()
    {
        return dispozitieTeren;
    }

    public void setDispozitieTeren(DispozitieTeren dispozitieTeren)
    {
        this.dispozitieTeren = dispozitieTeren;
    }

    public Parcela getParcelaTeren()
    {
        return parcelaTeren;
    }

    public void setParcelaTeren(Parcela parcelaTeren)
    {
        this.parcelaTeren = parcelaTeren;
    }
}
