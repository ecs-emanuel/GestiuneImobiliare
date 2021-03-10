package Repository.ProprietateRepository;

import Entities.Proprietate.Apartament;
import Entities.Proprietate.Proprietate;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProprietateRepository
{
    /*
    public Pair<List<Proprietate>, QueryOutcome> getListaProprietati()
    {
        List<Proprietate> listaProprietati = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaProprietati, QueryOutcome.OFFLINE);
        }

        String sqlScript1 = String.format
        (
            "SELECT ap.indexApartament, ap.etajApartament, \n" +
            "cons.indexConstructie, cons.suprafataUtilizabila, cons.suprafataConstructie,\n" +
            "cons.inaltimeConstructie, cons.anConstructie, cons.structuraConstructie,\n" +
            "cons.dispozitieActuala, cons.dispozitiePredare, \n" +
            "comp.*, parc.*,\n" +
            "prop.indexProprietate, prop.titluProprietate, prop.descriereProprietate, prop.pretProprietate,\n" +
            "prop.dispozitieProprietate, prop.dataProprietate,\n" +
            "loc1.indexLocatie, loc1.denumireLocatie,\n" +
            "jud1.*, \n" +
            "ors1.indexOras, ors1.denumireOras,\n" +
            "car1.indexCartier, car1.denumireCartier,\n" +
            "com1.indexComuna, com1.denumireComuna,\n" +
            "sat1.indexSat, sat1.denumireSat,\n" +
            "cl.indexClient, \n" +
            "pers1.indexPersoana, pers1.numePersoana, pers1.prenumePersoana, pers1.telefonPersoana, pers1.emailPersoana,\n" +
            "dom1.indexLocatie, dom1.denumireLocatie,\n" +
            "jud2.*,\n" +
            "ors2.indexOras, ors2.denumireOras,\n" +
            "car2.indexCartier, car2.denumireCartier,\n" +
            "com2.indexComuna, com2.denumireComuna,\n" +
            "sat2.indexSat, sat2.denumireSat,\n" +
            "ag.indexAgent, \n" +
            "us.indexUser, us.isAdminUser,\n" +
            "pers2.indexPersoana, pers2.numePersoana, pers2.prenumePersoana, pers2.telefonPersoana, pers2.emailPersoana,\n" +
            "dom2.indexLocatie, dom2.denumireLocatie,\n" +
            "jud3.*,\n" +
            "ors3.indexOras, ors3.denumireOras,\n" +
            "car3.indexCartier, car3.denumireCartier,\n" +
            "com3.indexComuna, com3.denumireComuna,\n" +
            "sat3.indexSat, sat3.denumireSat\n" +
            "FROM apartamente ap \n" +
            "JOIN constructii cons \n" +
            "ON ap.constructieApartament = cons.indexConstructie\n" +
            "JOIN compartimentari comp \n" +
            "ON cons.compartimentareConstructie = comp.indexCompartimentare\n" +
            "JOIN parcele parc \n" +
            "ON cons.parcelaConstructie = parc.indexParcela\n" +
            "JOIN proprietati prop \n" +
            "ON ap.proprietateApartament = prop.indexProprietate\n" +
            "JOIN locatii loc1\n" +
            "ON prop.locatieProprietate = loc1.indexLocatie\n" +
            "JOIN judete jud1 \n" +
            "ON loc1.judetLocatie = jud1.indexJudet\n" +
            "LEFT JOIN orase ors1\n" +
            "ON loc1.orasLocatie = ors1.indexOras\n" +
            "LEFT JOIN cartiere car1\n" +
            "ON loc1.cartierLocatie = car1.indexCartier\n" +
            "LEFT JOIN comune com1\n" +
            "ON loc1.comunaLocatie = com1.indexComuna\n" +
            "LEFT JOIN sate sat1\n" +
            "ON loc1.satLocatie = sat1.indexSat\n" +
            "JOIN clienti cl \n" +
            "ON prop.proprietarProprietate = cl.indexClient\n" +
            "JOIN persoane pers1\n" +
            "ON cl.persoanaClient = pers1.indexPersoana\n" +
            "JOIN locatii dom1\n" +
            "ON pers1.domiciliuPersoana = dom1.indexLocatie\n" +
            "JOIN judete jud2 \n" +
            "ON dom1.judetLocatie = jud2.indexJudet\n" +
            "LEFT JOIN orase ors2\n" +
            "ON dom1.orasLocatie = ors2.indexOras\n" +
            "LEFT JOIN cartiere car2\n" +
            "ON dom1.cartierLocatie = car2.indexCartier\n" +
            "LEFT JOIN comune com2\n" +
            "ON dom1.comunaLocatie = com2.indexComuna\n" +
            "LEFT JOIN sate sat2\n" +
            "ON dom1.satLocatie = sat2.indexSat\n" +
            "JOIN agenti ag \n" +
            "ON prop.agentProprietate = ag.indexAgent\n" +
            "JOIN useri us \n" +
            "ON ag.userAgent = us.indexUser\n" +
            "JOIN persoane pers2\n" +
            "ON ag.persoanaAgent = pers2.indexPersoana\n" +
            "JOIN locatii dom2\n" +
            "ON pers2.domiciliuPersoana = dom2.indexLocatie\n" +
            "JOIN judete jud3 \n" +
            "ON dom2.judetLocatie = jud3.indexJudet\n" +
            "LEFT JOIN orase ors3\n" +
            "ON dom2.orasLocatie = ors3.indexOras\n" +
            "LEFT JOIN cartiere car3\n" +
            "ON dom2.cartierLocatie = car3.indexCartier\n" +
            "LEFT JOIN comune com3\n" +
            "ON dom2.comunaLocatie = com3.indexComuna\n" +
            "LEFT JOIN sate sat3\n" +
            "ON dom2.satLocatie = sat3.indexSat"
        );

        String sqlScript2 = String.format
        (

        );
    }*/
}

