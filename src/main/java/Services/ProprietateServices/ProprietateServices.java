package Services.ProprietateServices;

import Entities.Persoana.Agent;
import Entities.Proprietate.Proprietate;
import Repository.ProprietateRepository.ProprietateRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.List;

public class ProprietateServices
{
    public Pair<Proprietate, QueryOutcome> getProprietate(int indexProprietate)
    {
        ProprietateRepository proprietateRepository = new ProprietateRepository();
        return proprietateRepository.getProprietate(indexProprietate);
    }

    /* deprecated - old code - not to be used
    public Pair<List<Proprietate>, QueryOutcome> getListaProprietati()
    {
        ProprietateRepository proprietateRepository = new ProprietateRepository();
        return proprietateRepository.getListaProprietati();
    }

    public Pair<List<Proprietate>, QueryOutcome> getListaProprietati(Agent agent)
    {
        ProprietateRepository proprietateRepository = new ProprietateRepository();
        return proprietateRepository.getListaProprietati();
    }*/
}
