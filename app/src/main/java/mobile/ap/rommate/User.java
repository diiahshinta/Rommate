package mobile.ap.rommate;

/**
 * Created by Danang Yuanto on 6/17/2017.
 */

public class User  {
    String userNama;
    String userEmail;
    String userUsername;
    String userPassword;

    public User(){

    }


    public User(String userNama, String userEmail, String userUsername, String userPassword) {
        this.userNama = userNama;
        this.userEmail = userEmail;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
    }

    public String getUserNama() {
        return userNama;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
