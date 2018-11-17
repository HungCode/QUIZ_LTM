package models;

import java.util.ArrayList;

/**
 *
 * @author hung
 */
public class DataCommunications {

    public static void connect() {

    }

    public static ArrayList<User> getListUSer() {
        ArrayList<User> listUSer = new ArrayList<>();
        listUSer.add(new User("hung", "111", 111));
        listUSer.add(new User("dao", "222", 222));
        listUSer.add(new User("va", "333", 333));
        listUSer.add(new User("uoc", "444", 444));
        return listUSer;
    }

    public User getUserInfor(String username) {
        //lay du lieu user co username = username

        return null;
    }
    public static User getUser(String username){
        ArrayList<User> list = getListUSer();
        for(User u : list){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    public static boolean checkUSer(String username,String pass){
        ArrayList<User> list = getListUSer();
        for(User u : list){
            if(u.getUsername().equals(username) && u.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
}
