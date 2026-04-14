package repository;

import model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FoodRepository {
    private static final List<Food> foods = new ArrayList<>();
    private final AtomicLong id = new AtomicLong(1L);

    public void AddFood(String restaurant, String name, Integer price)
    {
        Food _food = new Food(id.getAndIncrement(),restaurant,name,price);
        foods.add(_food);
    }

    public Food GetFood(Long id)
    {
        return foods.stream()
                .filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    public void DeleteFood(Long id)
    {
        foods.removeIf(f -> f.getId().equals(id));
    }

    public List<Food> GetAll()
    {
        return foods;
    }


}
