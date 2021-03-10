package Repository.ProprietateRepository;

import Components.DispozitieTeren;
import Entities.Locatie.Locatie;
import Entities.Proprietate.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
