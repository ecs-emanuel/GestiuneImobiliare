package Repository.ProprietateRepository;

import Components.DispozitieTeren;
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

public class CasaRepository
{
    public QueryOutcome addCasa(Casa casa)
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

            // add locatie
            String sqlScript1 = "";
            Locatie locatie = casa.getLocatieProprietate();

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
                casa.getTitluProprietate(), casa.getDescriereProprietate(), casa.getPretProprietate(),
                casa.getProprietarProprietate().getIndexClient(), casa.getAgentProprietate().getIndexAgent(),
                casa.getDispozitieProprietate().name(), casa.getDataProprietate()
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

            // add parcela
            Constructie constructie = casa.getConstructieCasa();
            Parcela parcela = constructie.getParcelaConstructie();

            String sqlScript3 = String.format
            (
                "INSERT INTO parcele (suprafataParcela, hasApa, hasGaz, hasElectricitate, hasCanalizare) VALUES\n" +
                "(%d, %b, %b, %b, %b);",
                parcela.getSuprafataParcela(), parcela.hasApa(), parcela.hasGaz(), parcela.hasElectricitate(), parcela.hasCanalizare()
            );

            statement.executeUpdate(sqlScript3, Statement.RETURN_GENERATED_KEYS);

            // retrieve indexParcela
            int indexParcela = 0;

            try (ResultSet resultSet = statement.getGeneratedKeys())
            {
                if (resultSet.first())
                {
                    indexParcela = resultSet.getInt(1);
                }
            }

            // add compartimentare
            Compartimentare compartimentare = constructie.getCompartimentareConstructie();

            String sqlScript4 = String.format
            (
                "INSERT INTO compartimentari(openSpace, living, dormitor, dressing,\n" +
                "bucatarie, debara, baie, hol, mansarda, balcon, terasa, gradina, parcare, garaj, boxa, pod) VALUES\n" +
                "(%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d)",
                compartimentare.getOpenspace(), compartimentare.getLiving(),
                compartimentare.getDormitor(), compartimentare.getDressing(),
                compartimentare.getBucatarie(), compartimentare.getDebara(),
                compartimentare.getBaie(), compartimentare.getHol(),
                compartimentare.getMansarda(), compartimentare.getBalcon(),
                compartimentare.getTerasa(), compartimentare.getGradina(),
                compartimentare.getParcare(), compartimentare.getGaraj(),
                compartimentare.getBoxa(), compartimentare.getPod()
            );

            statement.addBatch(sqlScript4);

            String sqlScript5 = String.format
            (
                "INSERT INTO constructii (suprafataUtilizabila, suprafataConstructie,\n" +
                "inaltimeConstructie, anConstructie, structuraConstructie,\n" +
                "dispozitieActuala, dispozitiePredare,\n" +
                "compartimentareConstructie, parcelaConstructie) VALUES\n" +
                "(%d, %d, %d, %d, '%s', '%s', '%s', LAST_INSERT_ID(), %d)",
                constructie.getSuprafataUtilizabila(), constructie.getSuprafataConstructie(),
                constructie.getInaltimeConstructie(), constructie.getAnConstructie(), constructie.getStructuraConstructie().name(),
                constructie.getDispozitieActuala().name(), constructie.getDispozitiePredare().name(), indexParcela
            );

            statement.addBatch(sqlScript5);

            String sqlScript6 = String.format
            (
                "INSERT INTO casute (constructieCasa, proprietateCasa) VALUES\n" +
                "(LAST_INSERT_ID(), %d)",
                indexProprietate
            );

            statement.addBatch(sqlScript6);
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

    public QueryOutcome modCasa(Casa oldCasa, Casa newCasa)
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
            Locatie oldLocatie = oldCasa.getLocatieProprietate();
            Locatie newLocatie = newCasa.getLocatieProprietate();

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
                newCasa.getTitluProprietate(), newCasa.getDescriereProprietate(), newCasa.getPretProprietate(),
                newCasa.getProprietarProprietate().getIndexClient(), newCasa.getDispozitieProprietate().name(),
                oldCasa.getIndexProprietate()
            );

            statement.addBatch(sqlScript2);

            // constructie
            Constructie oldConstructie = oldCasa.getConstructieCasa();
            Constructie newConstructie = newCasa.getConstructieCasa();

            // update parcela
            Parcela oldParcela = oldConstructie.getParcelaConstructie();
            Parcela newParcela = newConstructie.getParcelaConstructie();

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

            // update compartimentare
            Compartimentare oldCompartimentare = oldConstructie.getCompartimentareConstructie();
            Compartimentare newCompartimentare = newConstructie.getCompartimentareConstructie();

            String sqlScript4 = String.format
            (
                "UPDATE compartimentari SET\n" +
                "openspace = %d, living = %d, dormitor = %d, dressing = %d,\n" +
                "bucatarie = %d, debara = %d, baie = %d, hol = %d,\n" +
                "mansarda = %d, balcon = %d, terasa = %d, gradina = %d,\n" +
                "parcare = %d, garaj = %d, boxa = %d, pod = %d\n" +
                "WHERE indexCompartimentare = %d",
                newCompartimentare.getOpenspace(), newCompartimentare.getLiving(),
                newCompartimentare.getDormitor(), newCompartimentare.getDressing(),
                newCompartimentare.getBucatarie(), newCompartimentare.getDebara(),
                newCompartimentare.getBaie(), newCompartimentare.getHol(),
                newCompartimentare.getMansarda(), newCompartimentare.getBalcon(),
                newCompartimentare.getTerasa(), newCompartimentare.getGradina(),
                newCompartimentare.getParcare(), newCompartimentare.getGaraj(),
                newCompartimentare.getBoxa(), newCompartimentare.getPod(),
                oldCompartimentare.getIndexCompartimentare()
            );

            statement.addBatch(sqlScript4);

            // update constructie
            String sqlScript5 = String.format
            (
                "UPDATE constructii SET\n" +
                "suprafataUtilizabila = %d, suprafataConstructie = %d,\n" +
                "inaltimeConstructie = %d, anConstructie = %d, structuraConstructie = '%s',\n" +
                "dispozitieActuala = '%s', dispozitiePredare = '%s'\n" +
                "WHERE indexConstructie = %d",
                newConstructie.getSuprafataUtilizabila(), newConstructie.getSuprafataConstructie(),
                newConstructie.getInaltimeConstructie(), newConstructie.getAnConstructie(), newConstructie.getStructuraConstructie().name(),
                newConstructie.getDispozitieActuala().name(), newConstructie.getDispozitiePredare().name(),
                oldConstructie.getIndexConstructie()
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

    public QueryOutcome delCasa(Casa casa)
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

            int totalScripts = 6;
            String[] sqlScripts = new String[totalScripts];

            sqlScripts[0] = String.format
            (
                "DELETE FROM casute\n" +
                "WHERE indexCasa = %d",
                casa.getIndexCasa()
            );

            sqlScripts[1] = String.format
            (
                "DELETE FROM proprietati\n" +
                "WHERE indexProprietate = %d",
                casa.getIndexProprietate()
            );

            sqlScripts[2] = String.format
            (
                "DELETE FROM locatii\n" +
                "WHERE indexLocatie = %d",
                casa.getLocatieProprietate().getIndexLocatie()
            );

            Constructie constructie = casa.getConstructieCasa();

            sqlScripts[3] = String.format
            (
                "DELETE FROM constructii\n" +
                "WHERE indexConstructie = %d",
                constructie.getIndexConstructie()
            );

            sqlScripts[4] = String.format
            (
                "DELETE FROM compartimentari\n" +
                "WHERE indexCompartimentare = %d",
                constructie.getCompartimentareConstructie().getIndexCompartimentare()
            );

            sqlScripts[5] = String.format
            (
                "DELETE FROM parcele\n" +
                "WHERE indexParcela = %d",
                constructie.getParcelaConstructie().getIndexParcela()
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

    public Pair<Casa, QueryOutcome> getCasa(int indexCasa)
    {
        Casa casa = new Casa();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(casa, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM casute\n" +
            "WHERE indexCasa = %d",
            indexCasa
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    casa.setIndexCasa(indexCasa);

                    int indexConstructie = resultSet.getInt(2);
                    ConstructieRepository constructieRepository = new ConstructieRepository();
                    Pair<Constructie, QueryOutcome> queryOutcomePairConstructie = constructieRepository.getConstructie(indexConstructie);
                    QueryOutcome queryOutcome = queryOutcomePairConstructie.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(casa, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    casa.setConstructieCasa(queryOutcomePairConstructie.getKey());

                    int indexProprietate = resultSet.getInt(3);
                    ProprietateRepository proprietateRepository = new ProprietateRepository();
                    Pair<Proprietate, QueryOutcome> queryOutcomePairProprietate = proprietateRepository.getProprietate(indexProprietate);
                    queryOutcome = queryOutcomePairProprietate.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(casa, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    Proprietate proprietate = queryOutcomePairProprietate.getKey();

                    casa.setIndexProprietate(proprietate.getIndexProprietate());
                    casa.setTitluProprietate(proprietate.getTitluProprietate());
                    casa.setDescriereProprietate(proprietate.getDescriereProprietate());
                    casa.setPretProprietate(proprietate.getPretProprietate());
                    casa.setLocatieProprietate(proprietate.getLocatieProprietate());
                    casa.setProprietarProprietate(proprietate.getProprietarProprietate());
                    casa.setAgentProprietate(proprietate.getAgentProprietate());
                    casa.setDispozitieProprietate(proprietate.getDispozitieProprietate());
                    casa.setDataProprietate(proprietate.getDataProprietate());

                    return new Pair<>(casa, QueryOutcome.SUCCESS);
                }
                return new Pair<>(casa, QueryOutcome.EMPTY);
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
        return new Pair<>(casa, QueryOutcome.ERROR);
    }

    public Pair<List<Casa>, QueryOutcome> getListaCase()
    {
        List<Casa> listaCase = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaCase, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT indexCasa\n" +
            "FROM casute"
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    int indexCasa = resultSet.getInt(1);
                    Pair<Casa, QueryOutcome> queryOutcomePairCasa = getCasa(indexCasa);
                    QueryOutcome queryOutcome = queryOutcomePairCasa.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(listaCase, QueryOutcome.CORRUPT);
                    }

                    listaCase.add(queryOutcomePairCasa.getKey());
                }

                if (listaCase.size() <= 0)
                {
                    return new Pair<>(listaCase, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaCase, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaCase, QueryOutcome.ERROR);
    }

    public Pair<List<Casa>, QueryOutcome> getListaCase(Agent agent)
    {
        List<Casa> listaCase = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaCase, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT indexCasa\n" +
            "FROM casute\n" +
            "JOIN proprietati\n" +
            "ON proprietateCasa = indexProprietate\n" +
            "WHERE agentProprietate = %d",
            agent.getIndexAgent()
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    int indexCasa = resultSet.getInt(1);
                    Pair<Casa, QueryOutcome> queryOutcomePairCasa = getCasa(indexCasa);
                    QueryOutcome queryOutcome = queryOutcomePairCasa.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(listaCase, QueryOutcome.CORRUPT);
                    }

                    listaCase.add(queryOutcomePairCasa.getKey());
                }

                if (listaCase.size() <= 0)
                {
                    return new Pair<>(listaCase, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaCase, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaCase, QueryOutcome.ERROR);
    }
}
