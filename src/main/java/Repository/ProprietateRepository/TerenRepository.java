package Repository.ProprietateRepository;

import Components.DispozitieConstructie;
import Components.DispozitieTeren;
import Components.StructuraConstructie;
import Entities.Locatie.Locatie;
import Entities.Proprietate.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TerenRepository
{
    public QueryOutcome addTeren(Teren teren)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        try (Statement statement = connection.createStatement())
        {
            connection.setAutoCommit(false);

            String sqlScript1 = null;
            Locatie locatie = teren.getLocatieProprietate();

            // add locatie
            if (locatie.getOrasLocatie() != null)
            {
                sqlScript1 = String.format
                (
                    "INSERT INTO locatii (judetLocatie, orasLocatie, cartierLocatie, denumireLocatie) VALUES\n" +
                    "(%d, %d, %d, '%s');\n",
                    locatie.getJudetLocatie().getIndexJudet(),
                    locatie.getOrasLocatie().getIndexOras(), locatie.getCartierLocatie().getIndexCartier(), locatie.getDenumireLocatie()
                );
            }
            else
            {
                sqlScript1 = String.format
                (
                    "INSERT INTO locatii (judetLocatie, comunaLocatie, satLocatie, denumireLocatie) VALUES\n" +
                    "(%d, %d, %d, '%s');\n",
                    locatie.getJudetLocatie().getIndexJudet(),
                    locatie.getComunaLocatie().getIndexComuna(), locatie.getSatLocatie().getIndexSat(), locatie.getDenumireLocatie()
                );
            }

            statement.executeUpdate(sqlScript1);

            // add proprietate
            String sqlScript2 = String.format
            (
                "INSERT INTO proprietati (titluProprietate, descriereProprietate, pretProprietate,\n" +
                "locatieProprietate, proprietarProprietate, agentProprietate,\n" +
                "dispozitieProprietate, dataProprietate) VALUES\n" +
                "('%s', '%s', %d, LAST_INSERT_ID(), %d, %d, '%s', '%s');",
                teren.getTitluProprietate(), teren.getDescriereProprietate(), teren.getPretProprietate(),
                teren.getProprietarProprietate().getIndexClient(), teren.getAgentProprietate().getIndexAgent(),
                teren.getDispozitieProprietate().name(), teren.getDataProprietate()
            );

            statement.executeUpdate(sqlScript2, Statement.RETURN_GENERATED_KEYS);

            // retrieve indexProprietate for later use
            int indexProprietate = 0;
            try (ResultSet resultSet = statement.getGeneratedKeys())
            {
                if (resultSet.first())
                {
                    indexProprietate = resultSet.getInt(1);
                }
            }

            System.out.println(indexProprietate);

            Parcela parcela = teren.getParcelaTeren();

            // add parcela
            String sqlScript4 = String.format
            (
                "INSERT INTO parcele (suprafataParcela, hasApa, hasGaz, hasElectricitate, hasCanalizare) VALUES\n" +
                "(%d, %b, %b, %b, %b);",
                parcela.getSuprafataParcela(), parcela.hasApa(), parcela.hasGaz(), parcela.hasElectricitate(), parcela.hasCanalizare()
            );

            statement.addBatch(sqlScript4);

            // add teren
            String sqlScript5 = String.format
            (
                "INSERT INTO terenuri(dispozitieTeren, parcelaTeren, proprietateTeren) VALUES\n" +
                "('%s', LAST_INSERT_ID(), %d)",
                teren.getDispozitieTeren().name(), indexProprietate
            );

            statement.addBatch(sqlScript5);
            statement.executeBatch();
            connection.commit();

            return QueryOutcome.SUCCESS;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return QueryOutcome.ERROR;
    }

    public Pair<Teren, QueryOutcome> getTeren(int indexTeren)
    {
        Teren teren = new Teren();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(teren, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM terenuri\n" +
            "WHERE indexTeren = %d",
            indexTeren
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    teren.setIndexTeren(indexTeren);
                    teren.setDispozitieTeren(DispozitieTeren.valueOf(resultSet.getString(2)));

                    int indexParcela = resultSet.getInt(3);
                    ParcelaRepository parcelaRepository = new ParcelaRepository();
                    Pair<Parcela, QueryOutcome> queryOutcomePairParcela = parcelaRepository.getParcela(indexParcela);
                    QueryOutcome queryOutcome = queryOutcomePairParcela.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(teren, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    teren.setParcelaTeren(queryOutcomePairParcela.getKey());

                    int indexProprietate = resultSet.getInt(4);
                    ProprietateRepository proprietateRepository = new ProprietateRepository();
                    Pair<Proprietate, QueryOutcome> queryOutcomePairProprietate = proprietateRepository.getProprietate(indexProprietate);
                    queryOutcome = queryOutcomePairProprietate.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(teren, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    Proprietate proprietate = queryOutcomePairProprietate.getKey();

                    teren.setIndexProprietate(proprietate.getIndexProprietate());
                    teren.setTitluProprietate(proprietate.getTitluProprietate());
                    teren.setDescriereProprietate(proprietate.getDescriereProprietate());
                    teren.setPretProprietate(proprietate.getPretProprietate());
                    teren.setLocatieProprietate(proprietate.getLocatieProprietate());
                    teren.setProprietarProprietate(proprietate.getProprietarProprietate());
                    teren.setAgentProprietate(proprietate.getAgentProprietate());
                    teren.setDispozitieProprietate(proprietate.getDispozitieProprietate());
                    teren.setDataProprietate(proprietate.getDataProprietate());

                    return new Pair<>(teren, QueryOutcome.SUCCESS);
                }
                return new Pair<>(teren, QueryOutcome.EMPTY);
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
        return new Pair<>(teren, QueryOutcome.ERROR);
    }
}
