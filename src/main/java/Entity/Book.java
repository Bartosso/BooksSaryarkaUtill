package Entity;

/**
 * Created by Eshu on 17.07.2017.
 */
public class Book {
    private String name;
    private String file;

    public Book(String name, String file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }


}
