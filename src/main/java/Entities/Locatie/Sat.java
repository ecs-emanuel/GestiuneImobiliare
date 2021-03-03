package Entities.Locatie;

public class Sat extends Comuna
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

    public Sat(int indexComuna, String denumireComuna, int indexSat, String denumireSat)
    {
        super(indexComuna, denumireComuna);
        this.indexSat = indexSat;
        this.denumireSat = denumireSat;
    }

    public Sat(int indexJudet, String denumireJudet, int indexComuna, String denumireComuna, int indexSat, String denumireSat)
    {
        super(indexJudet, denumireJudet, indexComuna, denumireComuna);
        this.indexSat = indexSat;
        this.denumireSat = denumireSat;
    }

    public Sat(int indexLocatie, String denumireLocatie, int indexJudet, String denumireJudet, int indexComuna, String denumireComuna, int indexSat, String denumireSat)
    {
        super(indexLocatie, denumireLocatie, indexJudet, denumireJudet, indexComuna, denumireComuna);
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

    @Override
    public boolean hasInformationComplete()
    {
        return super.hasInformationComplete() && indexSat > 0 && !denumireSat.isEmpty();
    }
}
