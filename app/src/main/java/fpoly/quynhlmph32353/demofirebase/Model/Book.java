package fpoly.quynhlmph32353.demofirebase.Model;

public class Book {
        private String id;
        private String title;
        private String genre;
        private int publicationYear;
        private String authorId;

        public Book() {

        }



        public Book(String id, String title, String genre, int publicationYear, String authorId) {
            this.id = id;
            this.title = title;
            this.genre = genre;
            this.publicationYear = publicationYear;
            this.authorId = authorId;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
