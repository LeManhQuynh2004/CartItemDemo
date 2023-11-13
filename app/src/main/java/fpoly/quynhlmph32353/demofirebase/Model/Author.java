package fpoly.quynhlmph32353.demofirebase.Model;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String key;
    private String name;
    private int birthYear;
    private List<User> user;
    public Author() {

    }

    public Author(String id, String name, int birthYear) {
        this.key = id;
        this.name = name;
        this.birthYear = birthYear;
        this.user = new ArrayList<>();
    }

    public void addBook(User user) {
        this.user.add(user);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

}
