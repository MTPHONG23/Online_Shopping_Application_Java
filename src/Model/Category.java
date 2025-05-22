
package Model;

public class Category {
     private  Integer id;
     private String name;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNam() {
        return name;
    }

    public void setNam(String name) {
        this.name = name;
    }
}
