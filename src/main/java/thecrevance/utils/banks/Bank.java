package thecrevance.utils.banks;

/**
 * Created by Greg on 31/10/17.
 */
public class Bank {
    private int id;
    private String name;
    private String slug;
    private String code;

    public Bank() {
    }

    public Bank(int id, String name, String slug, String code) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
