package hu.pte.mik.prog4.zh1.model;

import hu.pte.mik.prog4.zh1.xml.ZH1Element;
import hu.pte.mik.prog4.zh1.xml.ZH1Serializable;

import java.util.Objects;
@ZH1Serializable
public class Food {

    private Long id;
    private String restaurantName;
    private String foodName;
    @ZH1Element(text="PRICE_FT")
    private String price;

    public Food() {
    }

    public Food(Long id, String restaurantName, String foodName, String price) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        Food food = (Food) object;
        return Objects.equals(id, food.id) && Objects.equals(restaurantName, food.restaurantName) && Objects.equals(foodName, food.foodName) && Objects.equals(price, food.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(restaurantName);
        result = 31 * result + Objects.hashCode(foodName);
        result = 31 * result + Objects.hashCode(price);
        return result;
    }
}
