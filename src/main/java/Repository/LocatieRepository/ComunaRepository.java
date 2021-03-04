package Repository.LocatieRepository;

import Entities.Locatie.Judet;
import Entities.Locatie.Comuna;
import Repository.DatabaseRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComunaRepository
{
    public List<Comuna> getListaComune(Judet judet)
    {
        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM comune " +
            "WHERE judetComuna = '%s'",
            judet.getIndexJudet()
        );

        List<Comuna> listaComune = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return listaComune;
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                while (resultset.next())
                {
                    Comuna comuna = new Comuna();
                    comuna.setIndexComuna(resultset.getInt(1));
                    comuna.setDenumireComuna(resultset.getString(2));
                    listaComune.add(comuna);
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
        return listaComune;
    }
}
