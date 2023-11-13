package fpoly.quynhlmph32353.demofirebase.Model;

public class Post {
    private String key;
    private String title;
    private String content;
    private String user_key;

    public Post(String key, String title, String content, String user_key) {
        this.key = key;
        this.title = title;
        this.content = content;
        this.user_key = user_key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
}
