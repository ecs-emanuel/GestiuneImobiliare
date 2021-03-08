package Entities.Locatie;

public class Cartier
{
    private int indexCartier;
    private String denumireCartier;

    public Cartier()
    {
    }

    public Cartier(int indexCartier, String denumireCartier)
    {
        this.indexCartier = indexCartier;
        this.denumireCartier = denumireCartier;
    }

    public int getIndexCartier()
    {
        return indexCartier;
    }

    public void setIndexCartier(int indexCartier)
    {
        this.indexCartier = indexCartier;
    }

    public String getDenumireCartier()
    {
        return denumireCartier;
    }

    public void setDenumireCartier(String denumireCartier)
    {
        this.denumireCartier = denumireCartier;
    }
}
