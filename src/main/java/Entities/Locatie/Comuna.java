package Entities.Locatie;

public class Comuna
{
    private int indexComuna;
    private String denumireComuna;

    public Comuna()
    {
    }

    public Comuna(int indexComuna, String denumireComuna)
    {
        this.indexComuna = indexComuna;
        this.denumireComuna = denumireComuna;
    }

    public int getIndexComuna()
    {
        return indexComuna;
    }

    public void setIndexComuna(int indexComuna)
    {
        this.indexComuna = indexComuna;
    }

    public String getDenumireComuna()
    {
        return denumireComuna;
    }

    public void setDenumireComuna(String denumireComuna)
    {
        this.denumireComuna = denumireComuna;
    }
}
