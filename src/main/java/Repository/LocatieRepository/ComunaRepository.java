package Repository.LocatieRepository;

import Entities.Locatie.Judet;
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

public class ComunaRepository
{
    public Pair<Comuna, QueryOutcome> getComuna(int indexComuna)
    {
        Comuna comuna = new Comuna();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(comuna, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM comune\n" +
            "WHERE indexComuna = %d",
            indexComuna
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    comuna.setIndexComuna(indexComuna);
                    comuna.setDenumireComuna(resultSet.getString(2));

                    return new Pair<>(comuna, QueryOutcome.SUCCESS);
                }

                return new Pair<>(comuna, QueryOutcome.EMPTY);
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
        return new Pair<>(comuna, QueryOutcome.ERROR);
    }

    public Pair<List<Comuna>, QueryOutcome> getListaComune(Judet judet)
    {
        List<Comuna> listaComune = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaComune, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM comune " +
            "WHERE judetComuna = '%s'",
            judet.getIndexJudet()
        );

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

                if (listaComune.size() <= 0)
                {
                    return new Pair<>(listaComune, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaComune, QueryOutcome.SUCCESS);
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

        return new Pair<>(listaComune, QueryOutcome.ERROR);
    }

    public Pair<Boolean, QueryOutcome> isComunaInDatabase(Comuna comuna)
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
            "FROM comune\n" +
            "WHERE indexComuna = %d",
            comuna.getIndexComuna()
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
