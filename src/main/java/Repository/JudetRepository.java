package Repository;

import Entities.Locatie.Judet;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JudetRepository
{
    public List<Judet> getListaJudete()
    {
        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM judete"
        );

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return null;
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                List<Judet> listaJudete = new ArrayList<>();

                while (resultset.next())
                {
                    Judet judet = new Judet();
                    judet.setIndexJudet(resultset.getInt(1));
                    judet.setDenumireJudet(resultset.getString(2));
                    listaJudete.add(judet);
                }

                return listaJudete;
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
        return null;
    }
}
