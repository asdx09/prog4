package model;

public class Food {

    private final Long id;
    private String restaurant;
    private String name;
    private Integer price;

    public Food(Long id, String restaurant, String name, Integer price) {
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

}
