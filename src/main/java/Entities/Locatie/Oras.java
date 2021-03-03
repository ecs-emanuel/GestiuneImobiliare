package Entities.Locatie;

public class Oras extends Judet
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

    public Oras(int indexJudet, String denumireJudet, int indexOras, String denumireOras)
    {
        super(indexJudet, denumireJudet);
        this.indexOras = indexOras;
        this.denumireOras = denumireOras;
    }

    public Oras(int indexLocatie, String denumireLocatie, int indexJudet, String denumireJudet, int indexOras, String denumireOras)
    {
        super(indexLocatie, denumireLocatie, indexJudet, denumireJudet);
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

    @Override
    public boolean hasInformationComplete()
    {
        return super.hasInformationComplete() && indexOras > 0 && !denumireOras.isEmpty();
    }
}
