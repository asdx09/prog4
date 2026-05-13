package hu.pte.mik.prog4.zh2.service;

import hu.pte.mik.prog4.generated.ws.Food;
import hu.pte.mik.prog4.generated.ws.FoodDataRequest;
import hu.pte.mik.prog4.generated.ws.FoodDataResponse;
import hu.pte.mik.prog4.generated.ws.FoodDataService;
import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.repository.FoodRepository;

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

        FoodDataResponse response = port.getFoodData(request);

        if(response != null)
        {
            return response.getPortion();
        }
        throw new RuntimeException("Dögölj meg!");
    }

}
