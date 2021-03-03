package Entities.Locatie;

public class Judet extends Locatie
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

    public Judet(int indexLocatie, String denumireLocatie, int indexJudet, String denumireJudet)
    {
        super(indexLocatie, denumireLocatie);
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

    @Override
    public boolean hasInformationComplete()
    {
        return super.hasInformationComplete() && indexJudet > 0 && !denumireJudet.isEmpty();
    }
}

