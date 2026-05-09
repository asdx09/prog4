package hu.pte.mik.prog4.zh2.ws;

import java.util.Objects;

public class FoodDataRequest {

    private String foodId;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FoodDataRequest that = (FoodDataRequest) o;
        return Objects.equals(foodId, that.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(foodId);
    }
}
