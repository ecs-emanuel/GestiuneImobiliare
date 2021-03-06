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
    public Pair<List<Oras>, QueryOutcome> getListaOrase(Judet judet)
    {
        List<Oras> listaOrase = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

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

        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return new Pair<>(listaOrase, QueryOutcome.ERROR);
    }

    public Pair<Boolean, QueryOutcome> isOrasInDatabase(Oras oras)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return new Pair<>(null, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT * " +
            "FROM orase " +
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
