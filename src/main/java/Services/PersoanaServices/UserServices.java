package Services.PersoanaServices;

import Utils.QueryOutcome;
import Entities.Persoana.User;
import Repository.PersoanaRepository.UserRepository;
import javafx.util.Pair;

public class UserServices
{
    public Pair<User, QueryOutcome> authenticate(User user)
    {
        UserRepository userRepository = new UserRepository();
        return userRepository.authenticate(user);
    }
}
