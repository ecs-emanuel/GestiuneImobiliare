package Entities.Proprietate;

public class Compartimentare
{
    private int indexCompartimentare;

    private int openspace;
    private int living;
    private int dormitor;
    private int dressing;
    private int bucatarie;
    private int debara;
    private int baie;
    private int hol;

    private int mansarda;
    private int balcon;
    private int terasa;
    private int gradina;
    private int parcare;
    private int garaj;
    private int boxa;
    private int pod;

    public Compartimentare()
    {
    }

    public Compartimentare(int indexCompartimentare, int openspace, int living, int dormitor, int dressing, int bucatarie, int debara, int baie, int hol, int mansarda, int balcon, int terasa, int gradina, int parcare, int garaj, int boxa, int pod)
    {
        this.indexCompartimentare = indexCompartimentare;
        this.openspace = openspace;
        this.living = living;
        this.dormitor = dormitor;
        this.dressing = dressing;
        this.bucatarie = bucatarie;
        this.debara = debara;
        this.baie = baie;
        this.hol = hol;
        this.mansarda = mansarda;
        this.balcon = balcon;
        this.terasa = terasa;
        this.gradina = gradina;
        this.parcare = parcare;
        this.garaj = garaj;
        this.boxa = boxa;
        this.pod = pod;
    }

    public int getIndexCompartimentare()
    {
        return indexCompartimentare;
    }

    public void setIndexCompartimentare(int indexCompartimentare)
    {
        this.indexCompartimentare = indexCompartimentare;
    }

    public int getOpenspace()
    {
        return openspace;
    }

    public void setOpenspace(int openspace)
    {
        this.openspace = openspace;
    }

    public int getLiving()
    {
        return living;
    }

    public void setLiving(int living)
    {
        this.living = living;
    }

    public int getDormitor()
    {
        return dormitor;
    }

    public void setDormitor(int dormitor)
    {
        this.dormitor = dormitor;
    }

    public int getDressing()
    {
        return dressing;
    }

    public void setDressing(int dressing)
    {
        this.dressing = dressing;
    }

    public int getBucatarie()
    {
        return bucatarie;
    }

    public void setBucatarie(int bucatarie)
    {
        this.bucatarie = bucatarie;
    }

    public int getDebara()
    {
        return debara;
    }

    public void setDebara(int debara)
    {
        this.debara = debara;
    }

    public int getBaie()
    {
        return baie;
    }

    public void setBaie(int baie)
    {
        this.baie = baie;
    }

    public int getHol()
    {
        return hol;
    }

    public void setHol(int hol)
    {
        this.hol = hol;
    }

    public int getMansarda()
    {
        return mansarda;
    }

    public void setMansarda(int mansarda)
    {
        this.mansarda = mansarda;
    }

    public int getBalcon()
    {
        return balcon;
    }

    public void setBalcon(int balcon)
    {
        this.balcon = balcon;
    }

    public int getTerasa()
    {
        return terasa;
    }

    public void setTerasa(int terasa)
    {
        this.terasa = terasa;
    }

    public int getGradina()
    {
        return gradina;
    }

    public void setGradina(int gradina)
    {
        this.gradina = gradina;
    }

    public int getParcare()
    {
        return parcare;
    }

    public void setParcare(int parcare)
    {
        this.parcare = parcare;
    }

    public int getGaraj()
    {
        return garaj;
    }

    public void setGaraj(int garaj)
    {
        this.garaj = garaj;
    }

    public int getBoxa()
    {
        return boxa;
    }

    public void setBoxa(int boxa)
    {
        this.boxa = boxa;
    }

    public int getPod()
    {
        return pod;
    }

    public void setPod(int pod)
    {
        this.pod = pod;
    }
}