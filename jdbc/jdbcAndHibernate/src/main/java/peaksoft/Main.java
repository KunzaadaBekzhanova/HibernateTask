package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
 //       userDaoHibernate.createUsersTable();

//        userDaoHibernate.saveUser("Nuskayim","Orozova",(byte) 24);
//        userDaoHibernate.saveUser("Kunzaada","Bekzhanova",(byte) 24);
//        userDaoHibernate.saveUser("Azat","Ibraev",(byte) 24);

//        userDaoHibernate.getAllUsers().forEach(System.out::println);

//        userDaoHibernate.removeUserById(2);

//        userDaoHibernate.cleanUsersTable();

//       userDaoHibernate.dropUsersTable();

        System.out.println(userDaoHibernate.existsByFirstName("Kunzaada"));

        userDaoHibernate.close();

    }


}
