package Repository.ProprietateRepository;

import Components.EtajApartament;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Proprietate.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApartamentRepository
{
    public QueryOutcome addApartament(Apartament apartament)
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
            Locatie locatie = apartament.getLocatieProprietate();

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
                apartament.getTitluProprietate(), apartament.getDescriereProprietate(), apartament.getPretProprietate(),
                apartament.getProprietarProprietate().getIndexClient(), apartament.getAgentProprietate().getIndexAgent(),
                apartament.getDispozitieProprietate().name(), apartament.getDataProprietate()
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
            Constructie constructie = apartament.getConstructieApartament();
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
                "INSERT INTO apartamente (etajApartament, constructieApartament, proprietateApartament) VALUES\n" +
                "('%s', LAST_INSERT_ID(), %d)",
                apartament.getEtajApartament().name(), indexProprietate
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

    public QueryOutcome modApartament(Apartament oldApartament, Apartament newApartament)
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
            Locatie oldLocatie = oldApartament.getLocatieProprietate();
            Locatie newLocatie = newApartament.getLocatieProprietate();

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
                newApartament.getTitluProprietate(), newApartament.getDescriereProprietate(), newApartament.getPretProprietate(),
                newApartament.getProprietarProprietate().getIndexClient(), newApartament.getDispozitieProprietate().name(),
                oldApartament.getIndexProprietate()
            );

            statement.addBatch(sqlScript2);

            // constructie
            Constructie oldConstructie = oldApartament.getConstructieApartament();
            Constructie newConstructie = newApartament.getConstructieApartament();

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

            // update apartament
            String sqlScript6 = String.format
            (
                "UPDATE apartamente SET\n" +
                "etajApartament = '%s'\n" +
                "WHERE indexApartament = %d",
                newApartament.getEtajApartament().name(), oldApartament.getIndexApartament()
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

    public QueryOutcome delApartament(Apartament apartament)
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
                "DELETE FROM apartamente\n" +
                "WHERE indexApartament = %d",
                apartament.getIndexApartament()
            );

            sqlScripts[1] = String.format
            (
                "DELETE FROM proprietati\n" +
                "WHERE indexProprietate = %d",
                apartament.getIndexProprietate()
            );

            sqlScripts[2] = String.format
            (
                "DELETE FROM locatii\n" +
                "WHERE indexLocatie = %d",
                apartament.getLocatieProprietate().getIndexLocatie()
            );

            Constructie constructie = apartament.getConstructieApartament();

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

    public Pair<Apartament, QueryOutcome> getApartament(int indexApartament)
    {
        Apartament apartament = new Apartament();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(apartament, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM apartamente\n" +
            "WHERE indexApartament = %d",
            indexApartament
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    apartament.setIndexApartament(indexApartament);
                    apartament.setEtajApartament(EtajApartament.valueOf(resultSet.getString(2)));

                    int indexConstructie = resultSet.getInt(3);
                    ConstructieRepository constructieRepository = new ConstructieRepository();
                    Pair<Constructie, QueryOutcome> queryOutcomePairConstructie = constructieRepository.getConstructie(indexConstructie);
                    QueryOutcome queryOutcome = queryOutcomePairConstructie.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(apartament, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    apartament.setConstructieApartament(queryOutcomePairConstructie.getKey());

                    int indexProprietate = resultSet.getInt(4);
                    ProprietateRepository proprietateRepository = new ProprietateRepository();
                    Pair<Proprietate, QueryOutcome> queryOutcomePairProprietate = proprietateRepository.getProprietate(indexProprietate);
                    queryOutcome = queryOutcomePairProprietate.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(apartament, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    Proprietate proprietate = queryOutcomePairProprietate.getKey();

                    apartament.setIndexProprietate(proprietate.getIndexProprietate());
                    apartament.setTitluProprietate(proprietate.getTitluProprietate());
                    apartament.setDescriereProprietate(proprietate.getDescriereProprietate());
                    apartament.setPretProprietate(proprietate.getPretProprietate());
                    apartament.setLocatieProprietate(proprietate.getLocatieProprietate());
                    apartament.setProprietarProprietate(proprietate.getProprietarProprietate());
                    apartament.setAgentProprietate(proprietate.getAgentProprietate());
                    apartament.setDispozitieProprietate(proprietate.getDispozitieProprietate());
                    apartament.setDataProprietate(proprietate.getDataProprietate());

                    return new Pair<>(apartament, QueryOutcome.SUCCESS);
                }
                return new Pair<>(apartament, QueryOutcome.EMPTY);
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
        return new Pair<>(apartament, QueryOutcome.ERROR);
    }

    public Pair<List<Apartament>, QueryOutcome> getListaApartamente()
    {
        List<Apartament> listaApartamente = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaApartamente, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT indexApartament\n" +
            "FROM apartamente"
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    int indexApartament = resultSet.getInt(1);
                    Pair<Apartament, QueryOutcome> queryOutcomePairApartament = getApartament(indexApartament);
                    QueryOutcome queryOutcome = queryOutcomePairApartament.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(listaApartamente, QueryOutcome.CORRUPT);
                    }

                    listaApartamente.add(queryOutcomePairApartament.getKey());
                }

                if (listaApartamente.size() <= 0)
                {
                    return new Pair<>(listaApartamente, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaApartamente, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaApartamente, QueryOutcome.ERROR);
    }

    public Pair<List<Apartament>, QueryOutcome> getListaApartamente(Agent agent)
    {
        List<Apartament> listaApartamente = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaApartamente, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT indexApartament\n" +
            "FROM apartamente\n" +
            "JOIN proprietati\n" +
            "ON proprietateApartament = indexProprietate\n" +
            "WHERE agentProprietate = %d",
            agent.getIndexAgent()
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    int indexApartament = resultSet.getInt(1);
                    Pair<Apartament, QueryOutcome> queryOutcomePairApartament = getApartament(indexApartament);
                    QueryOutcome queryOutcome = queryOutcomePairApartament.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(listaApartamente, QueryOutcome.CORRUPT);
                    }

                    listaApartamente.add(queryOutcomePairApartament.getKey());
                }

                if (listaApartamente.size() <= 0)
                {
                    return new Pair<>(listaApartamente, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaApartamente, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaApartamente, QueryOutcome.ERROR);
    }
}
