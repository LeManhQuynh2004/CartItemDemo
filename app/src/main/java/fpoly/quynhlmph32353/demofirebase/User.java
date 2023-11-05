package fpoly.quynhlmph32353.demofirebase;

import java.io.Serializable;

public class User implements Serializable {
    private String key;
    private String username;
    private String password;
    public User(){

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public User(String key) {
        this.key = key;
    }
}
