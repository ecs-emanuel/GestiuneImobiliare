package Repository.LocatieRepository;

import Entities.Locatie.Judet;
import Entities.Locatie.Oras;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrasRepository
{
    public Pair<Oras, QueryOutcome> getOras(int indexOras)
    {
        Oras oras = new Oras();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(oras, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM orase\n" +
            "WHERE indexOras = %d",
            indexOras
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    oras.setIndexOras(indexOras);
                    oras.setDenumireOras(resultSet.getString(2));

                    return new Pair<>(oras, QueryOutcome.SUCCESS);
                }

                return new Pair<>(oras, QueryOutcome.EMPTY);
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
        return new Pair<>(oras, QueryOutcome.ERROR);
    }

    public Pair<List<Oras>, QueryOutcome> getListaOrase(Judet judet)
    {
        List<Oras> listaOrase = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaOrase, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM orase " +
            "WHERE judetOras = '%s'",
            judet.getIndexJudet()
        );

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

                if (listaOrase.size() <= 0)
                {
                    return new Pair<>(listaOrase, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaOrase, QueryOutcome.SUCCESS);
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

        return new Pair<>(listaOrase, QueryOutcome.ERROR);
    }

    public Pair<Boolean, QueryOutcome> isOrasInDatabase(Oras oras)
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
            "FROM orase\n" +
            "WHERE indexOras = %d",
            oras.getIndexOras()
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
