package Entities.Locatie;

public class Cartier extends Oras
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

    public Cartier(int indexOras, String denumireOras, int indexCartier, String denumireCartier)
    {
        super(indexOras, denumireOras);
        this.indexCartier = indexCartier;
        this.denumireCartier = denumireCartier;
    }

    public Cartier(int indexJudet, String denumireJudet, int indexOras, String denumireOras, int indexCartier, String denumireCartier)
    {
        super(indexJudet, denumireJudet, indexOras, denumireOras);
        this.indexCartier = indexCartier;
        this.denumireCartier = denumireCartier;
    }

    public Cartier(int indexLocatie, String denumireLocatie, int indexJudet, String denumireJudet, int indexOras, String denumireOras, int indexCartier, String denumireCartier)
    {
        super(indexLocatie, denumireLocatie, indexJudet, denumireJudet, indexOras, denumireOras);
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

    @Override
    public boolean hasInformationComplete()
    {
        return super.hasInformationComplete() && indexCartier > 0 && !denumireCartier.isEmpty();
    }
}
