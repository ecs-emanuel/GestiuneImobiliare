package Repository.LocatieRepository;

import Entities.Locatie.Judet;
import Repository.DatabaseRepository;
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
    public Pair<List<Judet>, QueryOutcome> getListaJudete()
    {
        List<Judet> listaJudete = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaJudete, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM judete"
        );

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                while (resultset.next())
                {
                    Judet judet = new Judet();
                    judet.setIndexJudet(resultset.getInt(1));
                    judet.setDenumireJudet(resultset.getString(2));
                    listaJudete.add(judet);
                }

                if (listaJudete.size() <= 0)
                {
                    return new Pair<>(listaJudete, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaJudete, QueryOutcome.SUCCESS);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return new Pair<>(listaJudete, QueryOutcome.ERROR);
    }

    public Pair<Boolean, QueryOutcome> isJudetInDatabase(Judet judet)
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
            "FROM judete\n" +
            "WHERE indexJudet = %d",
            judet.getIndexJudet()
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
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return new Pair<>(null, QueryOutcome.ERROR);
    }
}
