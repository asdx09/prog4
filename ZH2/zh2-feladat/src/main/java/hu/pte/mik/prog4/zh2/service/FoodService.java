package hu.pte.mik.prog4.zh2.service;

import hu.pte.mik.prog4.generated.ws.*;
import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.repository.FoodRepository;

import java.awt.*;
import java.util.List;

public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService() {
        this.foodRepository = new FoodRepository();
    }

    public List<FoodEntity> findAll() {
        return this.foodRepository.listAll();
    }

    public FoodEntity findById(Long id) {
        return this.foodRepository.findById(id);
    }

    public FoodEntity save(Long id, String restaurantName, String foodName, String price) {
        return this.foodRepository.save(new FoodEntity(id, restaurantName, foodName, price));
    }

    public long getFoodPortion(String foodId) {

        Food service = new Food();
        FoodDataService port = service.getSoapFoodDataServicePort();

        FoodDataRequest request = new FoodDataRequest();
        request.setFoodId(foodId);

        FoodDataResponse resp = port.getFoodData(request);

        if(resp != null)
        {
            return resp.getPortion();
        }
        throw new RuntimeException("GetFoodPortion error!");
    }

}
