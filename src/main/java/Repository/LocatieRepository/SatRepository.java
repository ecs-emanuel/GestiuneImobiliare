package Repository.LocatieRepository;

import Entities.Locatie.Judet;
import Entities.Locatie.Sat;
import Entities.Locatie.Comuna;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SatRepository
{
    public Pair<List<Sat>, QueryOutcome> getListaSate(Comuna comuna)
    {
        List<Sat> listaSate = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaSate, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM sate " +
            "WHERE comunaSat = '%s'",
            comuna.getIndexComuna()
        );

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

                if (listaSate.size() <= 0)
                {
                    return new Pair<>(listaSate, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaSate, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaSate, QueryOutcome.ERROR);
    }

    public Pair<Boolean, QueryOutcome> isSatInDatabase(Sat sat)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(null, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM sate\n" +
            "WHERE indexSat = %d",
            sat.getIndexSat()
        );

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                if (resultset.first())
                {
                    return new Pair<>(true, QueryOutcome.SUCCESS);
                }
                return new Pair<>(false, QueryOutcome.EMPTY);
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
        return new Pair<>(null, QueryOutcome.ERROR);
    }
}
