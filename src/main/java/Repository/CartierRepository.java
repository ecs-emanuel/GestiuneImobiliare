package Repository;

import Entities.Locatie.Judet;
import Entities.Locatie.Cartier;
import Entities.Locatie.Oras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartierRepository
{
    public List<Cartier> getListaCartiere(Oras oras)
    {
        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM cartiere " +
            "WHERE orasCartier = '%s'",
            oras.getIndexOras()
        );

        List<Cartier> listaCartiere = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return listaCartiere;
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                while (resultset.next())
                {
                    Cartier cartier = new Cartier();
                    cartier.setIndexCartier(resultset.getInt(1));
                    cartier.setDenumireCartier(resultset.getString(2));
                    listaCartiere.add(cartier);
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
        return listaCartiere;
    }
}
