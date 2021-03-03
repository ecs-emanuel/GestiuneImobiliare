package Entities.Persoana;

public class User
{
    private int indexUser;
    private String nameUser;
    private String passUser;
    private boolean isAdminUser;

    public User()
    {
    }

    public User(int indexUser, String nameUser, String passUser, boolean isAdminUser)
    {
        this.indexUser = indexUser;
        this.nameUser = nameUser;
        this.passUser = passUser;
        this.isAdminUser = isAdminUser;
    }

    public int getIndexUser()
    {
        return indexUser;
    }

    public void setIndexUser(int indexUser)
    {
        this.indexUser = indexUser;
    }

    public String getNameUser()
    {
        return nameUser;
    }

    public void setNameUser(String nameUser)
    {
        this.nameUser = nameUser;
    }

    public String getPassUser()
    {
        return passUser;
    }

    public void setPassUser(String passUser)
    {
        this.passUser = passUser;
    }

    public boolean isAdminUser()
    {
        return isAdminUser;
    }

    public void setAdminUser(boolean adminUser)
    {
        isAdminUser = adminUser;
    }
}