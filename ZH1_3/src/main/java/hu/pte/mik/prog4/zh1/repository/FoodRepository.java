package hu.pte.mik.prog4.zh1.repository;

import hu.pte.mik.prog4.zh1.model.Food;
import hu.pte.mik.prog4.zh1.util.IdProvider;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodRepository {

    private static final FoodRepository INSTANCE = new FoodRepository();

    private final Map<Long, Food> storage;
    private final IdProvider idProvider = IdProvider.getInstance();

    private FoodRepository() {
        this.storage = Stream.of(new Food(this.idProvider.getNewId(), "Finom étterem", "Rántott hús", "2500"),
                                     new Food(this.idProvider.getNewId(), "Finom étterem", "Marhapörkölt", "3000"),
                                     new Food(this.idProvider.getNewId(), "Finomabb étterem", "Húsleves", "1800"),
                                     new Food(this.idProvider.getNewId(), "Finomabb étterem", "Lasagne", "3000"),
                                     new Food(this.idProvider.getNewId(), "Gyors kajálda", "Hamburger", "2900"))
                             .collect(Collectors.toMap(Food::getId, Function.identity()));
    }

    public static FoodRepository getInstance() {
        return INSTANCE;
    }

    public Food findById(Long id) {
        return this.storage.get(id);
    }

    public List<Food> findAll() {
        return List.copyOf(this.storage.values());
    }

    public Food create(String restaurantName, String foodName, String price) {
        var id = this.idProvider.getNewId();
        return this.storage.put(id, new Food(id, restaurantName, foodName, price));
    }

    public Food delete(Long id) {
        return this.storage.remove(id);
    }

}
