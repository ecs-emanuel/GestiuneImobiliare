package Entities.Locatie;

public class Locatie
{
    private int indexLocatie;

    private Judet judetLocatie;
    private String denumireLocatie;

    public Locatie()
    {
    }

    public Locatie(int indexLocatie, String denumireLocatie)
    {
        this.indexLocatie = indexLocatie;
        this.denumireLocatie = denumireLocatie;
    }

    public int getIndexLocatie()
    {
        return indexLocatie;
    }

    public void setIndexLocatie(int indexLocatie)
    {
        this.indexLocatie = indexLocatie;
    }

    public String getDenumireLocatie()
    {
        return denumireLocatie;
    }

    public void setDenumireLocatie(String denumireLocatie)
    {
        this.denumireLocatie = denumireLocatie;
    }

    public boolean hasInformationComplete()
    {
        return indexLocatie > 0 && !denumireLocatie.isEmpty();
    }
}

