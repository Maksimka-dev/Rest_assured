package data;

import model.User;

import java.util.Arrays;
import java.util.List;

public class UsersData {
    public static List<User> listUser = Arrays.asList(
            (new User(11,"Max","Maksim","Petrov","h@mail.ru","123","654",0)),
            (new User(15,"Alf","Egor","Petrov","h@mail.ru","123","654",0))
    );
}
