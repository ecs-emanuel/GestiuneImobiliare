package Repository;

import Entities.Locatie.Judet;
import Entities.Locatie.Oras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrasRepository
{
    public List<Oras> getListaOrase(Judet judet)
    {
        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM orase " +
            "WHERE judetOras = '%s'",
            judet.getIndexJudet()
        );

        List<Oras> listaOrase = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return listaOrase;
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                while (resultset.next())
                {
                    Oras oras = new Oras();
                    oras.setIndexOras(resultset.getInt(1));
                    oras.setDenumireOras(resultset.getString(2));
                    listaOrase.add(oras);
                }
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return listaOrase;
    }
}
