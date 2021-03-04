package Entities.Locatie;

public class Locatie
{
    private int indexLocatie;
    private Judet judetLocatie;
    private Oras orasLocatie;
    private Cartier cartierLocatie;
    private Comuna comunaLocatie;
    private Sat satLocatie;
    private String denumireLocatie;

    public Locatie()
    {
    }

    public Locatie(int indexLocatie, Judet judetLocatie, Oras orasLocatie, Cartier cartierLocatie, String denumireLocatie)
    {
        this.indexLocatie = indexLocatie;
        this.judetLocatie = judetLocatie;
        this.orasLocatie = orasLocatie;
        this.cartierLocatie = cartierLocatie;
        this.denumireLocatie = denumireLocatie;
    }

    public Locatie(int indexLocatie, Judet judetLocatie, Comuna comunaLocatie, Sat satLocatie, String denumireLocatie)
    {
        this.indexLocatie = indexLocatie;
        this.judetLocatie = judetLocatie;
        this.comunaLocatie = comunaLocatie;
        this.satLocatie = satLocatie;
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

    public Judet getJudetLocatie()
    {
        return judetLocatie;
    }

    public void setJudetLocatie(Judet judetLocatie)
    {
        this.judetLocatie = judetLocatie;
    }

    public Oras getOrasLocatie()
    {
        return orasLocatie;
    }

    public void setOrasLocatie(Oras orasLocatie)
    {
        this.orasLocatie = orasLocatie;
    }

    public Cartier getCartierLocatie()
    {
        return cartierLocatie;
    }

    public void setCartierLocatie(Cartier cartierLocatie)
    {
        this.cartierLocatie = cartierLocatie;
    }

    public Comuna getComunaLocatie()
    {
        return comunaLocatie;
    }

    public void setComunaLocatie(Comuna comunaLocatie)
    {
        this.comunaLocatie = comunaLocatie;
    }

    public Sat getSatLocatie()
    {
        return satLocatie;
    }

    public void setSatLocatie(Sat satLocatie)
    {
        this.satLocatie = satLocatie;
    }

    public String getDenumireLocatie()
    {
        return denumireLocatie;
    }

    public void setDenumireLocatie(String denumireLocatie)
    {
        this.denumireLocatie = denumireLocatie;
    }
}

