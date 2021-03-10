package Services.ProprietateServices;

import Entities.Proprietate.Proprietate;
import Repository.ProprietateRepository.ProprietateRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class ProprietateServices
{
    public Pair<Proprietate, QueryOutcome> getProprietate(int indexProprietate)
    {
        ProprietateRepository proprietateRepository = new ProprietateRepository();
        return proprietateRepository.getProprietate(indexProprietate);
    }
}
