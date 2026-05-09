package hu.pte.mik.prog4.zh2.ws;

import jakarta.jws.WebService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@WebService(endpointInterface = "hu.pte.mik.prog4.zh2.ws.FoodDataService", serviceName = "food", targetNamespace = "hu.pte.mik.prog4.zh2.ws.soap")
public class SoapFoodDataService implements FoodDataService {

    private final Map<String, Long> map = new HashMap<>();

    @Override
    public FoodDataResponse getFoodData(FoodDataRequest request) {
        return new FoodDataResponse(request.getFoodId(), this.map.computeIfAbsent(request.getFoodId(), foodId -> ThreadLocalRandom.current()
                .nextLong(0, 100)));
    }

}
