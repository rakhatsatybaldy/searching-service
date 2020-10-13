package kz.bitlab.course.db;

import java.util.Date;

public class Hotel {
    private Long id;
    private String name;
    private User author;
    private String description;
    private int stars;
    private int price;
    private Date adddedDate;

    public Hotel(){

    }

    public Hotel(Long id, String name, User author, String description, int stars, int price, Date adddedDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.stars = stars;
        this.price = price;
        this.adddedDate = adddedDate;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getAdddedDate() {
        return adddedDate;
    }

    public void setAdddedDate(Date adddedDate) {
        this.adddedDate = adddedDate;
    }
}
