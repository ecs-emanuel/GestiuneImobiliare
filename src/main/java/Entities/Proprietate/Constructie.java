package Entities.Proprietate;

import Components.DispozitieConstructie;
import Components.StructuraConstructie;

public class Constructie
{
    private int indexConstructie;
    private int suprafataUtilizabila;
    private int suprafataConstructie;
    private int inaltimeConstructie;
    private int anConstructie;
    private StructuraConstructie structuraConstructie;
    private DispozitieConstructie dispozitieActuala;
    private DispozitieConstructie dispozitiePredare;
    private Compartimentare compartimentareConstructie;
    private Parcela parcelaConstructie;

    public Constructie()
    {
    }

    public Constructie(int indexConstructie, int suprafataUtilizabila, int suprafataConstructie,
                       int inaltimeConstructie, int anConstructie, StructuraConstructie structuraConstructie,
                       DispozitieConstructie dispozitieActuala, DispozitieConstructie dispozitiePredare,
                       Compartimentare compartimentareConstructie, Parcela parcelaConstructie)
    {
        this.indexConstructie = indexConstructie;
        this.suprafataUtilizabila = suprafataUtilizabila;
        this.suprafataConstructie = suprafataConstructie;
        this.inaltimeConstructie = inaltimeConstructie;
        this.anConstructie = anConstructie;
        this.structuraConstructie = structuraConstructie;
        this.dispozitieActuala = dispozitieActuala;
        this.dispozitiePredare = dispozitiePredare;
        this.compartimentareConstructie = compartimentareConstructie;
        this.parcelaConstructie = parcelaConstructie;
    }

    public int getIndexConstructie()
    {
        return indexConstructie;
    }

    public void setIndexConstructie(int indexConstructie)
    {
        this.indexConstructie = indexConstructie;
    }

    public int getSuprafataUtilizabila()
    {
        return suprafataUtilizabila;
    }

    public void setSuprafataUtilizabila(int suprafataUtilizabila)
    {
        this.suprafataUtilizabila = suprafataUtilizabila;
    }

    public int getSuprafataConstructie()
    {
        return suprafataConstructie;
    }

    public void setSuprafataConstructie(int suprafataConstructie)
    {
        this.suprafataConstructie = suprafataConstructie;
    }

    public int getInaltimeConstructie()
    {
        return inaltimeConstructie;
    }

    public void setInaltimeConstructie(int inaltimeConstructie)
    {
        this.inaltimeConstructie = inaltimeConstructie;
    }

    public int getAnConstructie()
    {
        return anConstructie;
    }

    public void setAnConstructie(int anConstructie)
    {
        this.anConstructie = anConstructie;
    }

    public StructuraConstructie getStructuraConstructie()
    {
        return structuraConstructie;
    }

    public void setStructuraConstructie(StructuraConstructie structuraConstructie)
    {
        this.structuraConstructie = structuraConstructie;
    }

    public DispozitieConstructie getDispozitieActuala()
    {
        return dispozitieActuala;
    }

    public void setDispozitieActuala(DispozitieConstructie dispozitieActuala)
    {
        this.dispozitieActuala = dispozitieActuala;
    }

    public DispozitieConstructie getDispozitiePredare()
    {
        return dispozitiePredare;
    }

    public void setDispozitiePredare(DispozitieConstructie dispozitiePredare)
    {
        this.dispozitiePredare = dispozitiePredare;
    }

    public Compartimentare getCompartimentareConstructie()
    {
        return compartimentareConstructie;
    }

    public void setCompartimentareConstructie(Compartimentare compartimentareConstructie)
    {
        this.compartimentareConstructie = compartimentareConstructie;
    }

    public Parcela getParcelaConstructie()
    {
        return parcelaConstructie;
    }

    public void setParcelaConstructie(Parcela parcelaConstructie)
    {
        this.parcelaConstructie = parcelaConstructie;
    }
}

