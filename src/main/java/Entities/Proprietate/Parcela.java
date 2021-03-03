package Entities.Proprietate;

public class Parcela
{
    private int indexParcela;
    private int suprafataParcela;
    private boolean hasApa;
    private boolean hasGaz;
    private boolean hasElectricitate;
    private boolean hasCanalizare;

    public Parcela()
    {
    }

    public Parcela(int indexParcela, int suprafataParcela, boolean hasApa, boolean hasGaz, boolean hasElectricitate, boolean hasCanalizare)
    {
        this.indexParcela = indexParcela;
        this.suprafataParcela = suprafataParcela;
        this.hasApa = hasApa;
        this.hasGaz = hasGaz;
        this.hasElectricitate = hasElectricitate;
        this.hasCanalizare = hasCanalizare;
    }

    public int getIndexParcela()
    {
        return indexParcela;
    }

    public void setIndexParcela(int indexParcela)
    {
        this.indexParcela = indexParcela;
    }

    public int getSuprafataParcela()
    {
        return suprafataParcela;
    }

    public void setSuprafataParcela(int suprafataParcela)
    {
        this.suprafataParcela = suprafataParcela;
    }

    public boolean hasApa()
    {
        return hasApa;
    }

    public void setHasApa(boolean hasApa)
    {
        this.hasApa = hasApa;
    }

    public boolean hasGaz()
    {
        return hasGaz;
    }

    public void setHasGaz(boolean hasGaz)
    {
        this.hasGaz = hasGaz;
    }

    public boolean hasElectricitate()
    {
        return hasElectricitate;
    }

    public void setHasElectricitate(boolean hasElectricitate)
    {
        this.hasElectricitate = hasElectricitate;
    }

    public boolean hasCanalizare()
    {
        return hasCanalizare;
    }

    public void hasCanalizare(boolean hasCanalizare)
    {
        this.hasCanalizare = hasCanalizare;
    }
}
