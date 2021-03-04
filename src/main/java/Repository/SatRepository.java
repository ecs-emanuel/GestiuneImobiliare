package Repository;

import Entities.Locatie.Sat;
import Entities.Locatie.Comuna;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SatRepository
{
    public List<Sat> getListaSate(Comuna comuna)
    {
        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM sate " +
            "WHERE comunaSat = '%s'",
            comuna.getIndexComuna()
        );

        List<Sat> listaSate = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return listaSate;
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                while (resultset.next())
                {
                    Sat sat = new Sat();
                    sat.setIndexSat(resultset.getInt(1));
                    sat.setDenumireSat(resultset.getString(2));
                    listaSate.add(sat);
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
        return listaSate;
    }
}
