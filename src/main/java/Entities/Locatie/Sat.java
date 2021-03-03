package Entities.Locatie;

public class Sat
{
    private int indexSat;
    private String denumireSat;

    public Sat()
    {
    }

    public Sat(int indexSat, String denumireSat)
    {
        this.indexSat = indexSat;
        this.denumireSat = denumireSat;
    }

    public int getIndexSat()
    {
        return indexSat;
    }

    public void setIndexSat(int indexSat)
    {
        this.indexSat = indexSat;
    }

    public String getDenumireSat()
    {
        return denumireSat;
    }

    public void setDenumireSat(String denumireSat)
    {
        this.denumireSat = denumireSat;
    }
}
