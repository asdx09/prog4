package hu.pte.mik.prog4.zh2.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface FoodDataService {

    @WebMethod
    FoodDataResponse getFoodData(FoodDataRequest request);

}
