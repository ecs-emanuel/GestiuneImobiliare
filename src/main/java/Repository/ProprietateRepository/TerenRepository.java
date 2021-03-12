package Repository.ProprietateRepository;

import Components.DispozitieConstructie;
import Components.DispozitieTeren;
import Components.StructuraConstructie;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Proprietate.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public QueryOutcome modTeren(Teren oldTeren, Teren newTeren)
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

            // update locatie
            String sqlScript1 = "";
            Locatie oldLocatie = oldTeren.getLocatieProprietate();
            Locatie newLocatie = newTeren.getLocatieProprietate();

            if (newLocatie.getOrasLocatie() != null)
            {
                sqlScript1 = String.format
                (
                "UPDATE locatii SET\n" +
                "judetLocatie = %d,\n" +
                "orasLocatie = %d,\n" +
                "cartierLocatie = %d,\n" +
                "comunaLocatie = NULL,\n" +
                "satLocatie = NULL,\n" +
                "denumireLocatie = '%s'\n" +
                "WHERE indexLocatie = %d",
                newLocatie.getJudetLocatie().getIndexJudet(),
                newLocatie.getOrasLocatie().getIndexOras(), newLocatie.getCartierLocatie().getIndexCartier(),
                newLocatie.getDenumireLocatie(),
                oldLocatie.getIndexLocatie()
                );
            }
            else
            {
                sqlScript1 = String.format
                (
                "UPDATE locatii SET\n" +
                "judetLocatie = %d,\n" +
                "orasLocatie = NULL,\n" +
                "cartierLocatie = NULL,\n" +
                "comunaLocatie = %d,\n" +
                "satLocatie = %d,\n" +
                "denumireLocatie = '%s'\n" +
                "WHERE indexLocatie = %d",
                newLocatie.getJudetLocatie().getIndexJudet(),
                newLocatie.getComunaLocatie().getIndexComuna(), newLocatie.getSatLocatie().getIndexSat(),
                newLocatie.getDenumireLocatie(),
                oldLocatie.getIndexLocatie()
                );
            }

            statement.addBatch(sqlScript1);

            // update proprietate
            String sqlScript2 = String.format
            (
                "UPDATE proprietati SET\n" +
                "titluProprietate = '%s',\n" +
                "descriereProprietate = '%s',\n" +
                "pretProprietate = %d,\n" +
                "proprietarProprietate = %d,\n" +
                "dispozitieProprietate = '%s'\n" +
                "WHERE indexProprietate = %d",
                newTeren.getTitluProprietate(), newTeren.getDescriereProprietate(), newTeren.getPretProprietate(),
                newTeren.getProprietarProprietate().getIndexClient(), newTeren.getDispozitieProprietate().name(),
                oldTeren.getIndexProprietate()
            );

            statement.addBatch(sqlScript2);

            // update parcela
            Parcela oldParcela = oldTeren.getParcelaTeren();
            Parcela newParcela = newTeren.getParcelaTeren();

            String sqlScript3 = String.format
            (
                "UPDATE parcele SET\n" +
                "suprafataParcela = %d,\n" +
                "hasApa = %b,\n" +
                "hasGaz = %b,\n" +
                "hasElectricitate = %b,\n" +
                "hasCanalizare = %b\n" +
                "WHERE indexParcela = %d",
                newParcela.getSuprafataParcela(),
                newParcela.hasApa(), newParcela.hasGaz(),
                newParcela.hasElectricitate(), newParcela.hasCanalizare(),
                oldParcela.getIndexParcela()
            );

            statement.addBatch(sqlScript3);

            // update teren
            String sqlScript4 = String.format
            (
                "UPDATE terenuri SET\n" +
                "dispozitieTeren = '%s'\n" +
                "WHERE indexTeren = %d",
                newTeren.getDispozitieTeren().name(), oldTeren.getIndexTeren()
            );

            statement.addBatch(sqlScript4);

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

    public QueryOutcome delTeren(Teren teren)
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

            int totalScripts = 4;
            String[] sqlScripts = new String[totalScripts];

            sqlScripts[0] = String.format
            (
                "DELETE FROM terenuri\n" +
                "WHERE indexTeren = %d",
                teren.getIndexTeren()
            );

            sqlScripts[1] = String.format
            (
                "DELETE FROM proprietati\n" +
                "WHERE indexProprietate = %d",
                teren.getIndexProprietate()
            );

            sqlScripts[2] = String.format
            (
                "DELETE FROM locatii\n" +
                "WHERE indexLocatie = %d",
                teren.getLocatieProprietate().getIndexLocatie()
            );

            sqlScripts[3] = String.format
            (
                "DELETE FROM parcele\n" +
                "WHERE indexParcela = %d",
                teren.getParcelaTeren().getIndexParcela()
            );

            for (String sqlScript : sqlScripts)
            {
                statement.addBatch(sqlScript);
            }

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

    public Pair<List<Teren>, QueryOutcome> getListaTerenuri()
    {
        List<Teren> listaTerenuri = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaTerenuri, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT indexTeren\n" +
            "FROM terenuri"
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    int indexTeren = resultSet.getInt(1);
                    Pair<Teren, QueryOutcome> queryOutcomePairTeren = getTeren(indexTeren);
                    QueryOutcome queryOutcome = queryOutcomePairTeren.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(listaTerenuri, QueryOutcome.CORRUPT);
                    }

                    listaTerenuri.add(queryOutcomePairTeren.getKey());
                }

                if (listaTerenuri.size() <= 0)
                {
                    return new Pair<>(listaTerenuri, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaTerenuri, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaTerenuri, QueryOutcome.ERROR);
    }

    public Pair<List<Teren>, QueryOutcome> getListaTerenuri(Agent agent)
    {
        List<Teren> listaTerenuri = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaTerenuri, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT indexTeren\n" +
            "FROM terenuri\n" +
            "JOIN proprietati\n" +
            "ON proprietateTeren = indexProprietate\n" +
            "WHERE agentProprietate = %d",
            agent.getIndexAgent()
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    int indexTeren = resultSet.getInt(1);
                    Pair<Teren, QueryOutcome> queryOutcomePairTeren = getTeren(indexTeren);
                    QueryOutcome queryOutcome = queryOutcomePairTeren.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(listaTerenuri, QueryOutcome.CORRUPT);
                    }

                    listaTerenuri.add(queryOutcomePairTeren.getKey());
                }

                if (listaTerenuri.size() <= 0)
                {
                    return new Pair<>(listaTerenuri, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaTerenuri, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaTerenuri, QueryOutcome.ERROR);
    }
}
