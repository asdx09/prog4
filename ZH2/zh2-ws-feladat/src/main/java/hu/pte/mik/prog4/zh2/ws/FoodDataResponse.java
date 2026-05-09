package hu.pte.mik.prog4.zh2.ws;

import java.util.Objects;

public class FoodDataResponse {

    private String id;
    private Long portion;

    public FoodDataResponse() {
    }

    public FoodDataResponse(String id, Long portion) {
        this.id = id;
        this.portion = portion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPortion() {
        return portion;
    }

    public void setPortion(Long portion) {
        this.portion = portion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FoodDataResponse that = (FoodDataResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(portion, that.portion);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(portion);
        return result;
    }
}
