package Repository.LocatieRepository;

import Entities.Locatie.Comuna;
import Entities.Locatie.Locatie;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatieRepository
{
    public Pair<Locatie, QueryOutcome> addLocatie(Locatie locatie)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return new Pair<>(locatie, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "INSERT INTO locatii(judetLocatie, orasLocatie, cartierLocatie, comunaLocatie, satLocatie, denumireLocatie) VALUES\n" +
            "(%d, %d, %d, %d, %d, '%s')",
            locatie.getJudetLocatie().getIndexJudet(), locatie.getOrasLocatie().getIndexOras(), locatie.getCartierLocatie().getIndexCartier(),
            locatie.getComunaLocatie().getIndexComuna(), locatie.getSatLocatie().getIndexSat(), locatie.getDenumireLocatie()
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlScript, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                if (resultSet.first())
                {
                    locatie.setIndexLocatie(resultSet.getInt(1));
                    return new Pair<>(locatie, QueryOutcome.SUCCESS);
                }
                return new Pair<>(locatie, QueryOutcome.EMPTY);
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
        return new Pair<>(locatie, QueryOutcome.ERROR);
    }
}
