package Entities.Locatie;

public class Comuna extends Judet
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

    public Comuna(int indexJudet, String denumireJudet, int indexComuna, String denumireComuna)
    {
        super(indexJudet, denumireJudet);
        this.indexComuna = indexComuna;
        this.denumireComuna = denumireComuna;
    }

    public Comuna(int indexLocatie, String denumireLocatie, int indexJudet, String denumireJudet, int indexComuna, String denumireComuna)
    {
        super(indexLocatie, denumireLocatie, indexJudet, denumireJudet);
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

    @Override
    public boolean hasInformationComplete()
    {
        return super.hasInformationComplete() && indexComuna > 0 && !denumireComuna.isEmpty();
    }
}
