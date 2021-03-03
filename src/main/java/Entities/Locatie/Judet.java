package Entities.Locatie;

public class Judet
{
    private int indexJudet;
    private String denumireJudet;

    public Judet()
    {
    }

    public Judet(int indexJudet, String denumireJudet)
    {
        this.indexJudet = indexJudet;
        this.denumireJudet = denumireJudet;
    }

    public int getIndexJudet()
    {
        return indexJudet;
    }

    public void setIndexJudet(int indexJudet)
    {
        this.indexJudet = indexJudet;
    }

    public String getDenumireJudet()
    {
        return denumireJudet;
    }

    public void setDenumireJudet(String denumireJudet)
    {
        this.denumireJudet = denumireJudet;
    }
}

