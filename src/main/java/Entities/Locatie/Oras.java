package Entities.Locatie;

public class Oras
{
    private int indexOras;
    private String denumireOras;

    public Oras()
    {
    }

    public Oras(int indexOras, String denumireOras)
    {
        this.indexOras = indexOras;
        this.denumireOras = denumireOras;
    }

    public int getIndexOras()
    {
        return indexOras;
    }

    public void setIndexOras(int indexOras)
    {
        this.indexOras = indexOras;
    }

    public String getDenumireOras()
    {
        return denumireOras;
    }

    public void setDenumireOras(String denumireOras)
    {
        this.denumireOras = denumireOras;
    }
}
