package Repository.LocatieRepository;

import Entities.Locatie.Cartier;
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

public class CartierRepository
{
    public Pair<Cartier, QueryOutcome> getCartier(int indexCartier)
    {
        Cartier cartier = new Cartier();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(cartier, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM cartiere\n" +
            "WHERE indexCartier = %d",
            indexCartier
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    cartier.setIndexCartier(indexCartier);
                    cartier.setDenumireCartier(resultSet.getString(2));

                    return new Pair<>(cartier, QueryOutcome.SUCCESS);
                }

                return new Pair<>(cartier, QueryOutcome.EMPTY);
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
        return new Pair<>(cartier, QueryOutcome.ERROR);
    }

    public Pair<List<Cartier>, QueryOutcome> getListaCartiere(Oras oras)
    {
        List<Cartier> listaCartiere = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaCartiere, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM cartiere " +
            "WHERE orasCartier = '%s'",
            oras.getIndexOras()
        );

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

                if (listaCartiere.size() <= 0)
                {
                    return new Pair<>(listaCartiere, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaCartiere, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaCartiere, QueryOutcome.ERROR);
    }

    public Pair<Boolean, QueryOutcome> isCartierInDatabase(Cartier cartier)
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
            "FROM cartiere\n" +
            "WHERE indexCartier = %d",
            cartier.getIndexCartier()
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
