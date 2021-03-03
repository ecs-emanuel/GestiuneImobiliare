package Entities.Persoana;

import Entities.Locatie.Cartier;
import Entities.Locatie.Locatie;
import Entities.Locatie.Sat;

public class Persoana
{
    private int indexPersoana;
    private String numePersoana;
    private String prenumePersoana;
    private String telefonPersoana;
    private String emailPersoana;
    private Locatie domiciliuPersoana;

    public Persoana()
    {
    }

    public Persoana(int indexPersoana, String numePersoana, String prenumePersoana, String telefonPersoana, String emailPersoana, Locatie domiciliuPersoana)
    {
        this.indexPersoana = indexPersoana;
        this.numePersoana = numePersoana;
        this.prenumePersoana = prenumePersoana;
        this.telefonPersoana = telefonPersoana;
        this.emailPersoana = emailPersoana;
        this.domiciliuPersoana = domiciliuPersoana;
    }

    public int getIndexPersoana()
    {
        return indexPersoana;
    }

    public void setIndexPersoana(int indexPersoana)
    {
        this.indexPersoana = indexPersoana;
    }

    public String getNumePersoana()
    {
        return numePersoana;
    }

    public void setNumePersoana(String numePersoana)
    {
        this.numePersoana = numePersoana;
    }

    public String getPrenumePersoana()
    {
        return prenumePersoana;
    }

    public void setPrenumePersoana(String prenumePersoana)
    {
        this.prenumePersoana = prenumePersoana;
    }

    public String getTelefonPersoana()
    {
        return telefonPersoana;
    }

    public void setTelefonPersoana(String telefonPersoana)
    {
        this.telefonPersoana = telefonPersoana;
    }

    public String getEmailPersoana()
    {
        return emailPersoana;
    }

    public void setEmailPersoana(String emailPersoana)
    {
        this.emailPersoana = emailPersoana;
    }

    public Locatie getDomiciliuPersoana()
    {
        return domiciliuPersoana;
    }

    public void setDomiciliuPersoana(Locatie domiciliuPersoana)
    {
        this.domiciliuPersoana = domiciliuPersoana;
    }
}
