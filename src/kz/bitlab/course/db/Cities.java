package kz.bitlab.course.db;

public class Cities {
    private Long id;
    private String name;
    private Countries country;


    public Cities(Long id, String name, Countries country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Cities(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }
}
