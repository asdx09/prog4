package hu.pte.mik.prog4.zh2.api.controller;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.service.FoodService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/food")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodController {
    private final FoodService foodService;
    public FoodController() {this.foodService = new FoodService();}

    @POST
    @Path("/save")
    public Response save(FoodEntity food)
    {
        FoodEntity result = foodService.save(food.getId(),food.getRestaurantName(),food.getFoodName(),food.getPrice());
        if(result != null)
        {
            return Response.status(Response.Status.OK).entity(result).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response findAll()
    {
        List<FoodEntity> result = foodService.findAll();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("/find/{id}")
    public Response findById(@PathParam("id")long id)
    {
        FoodEntity result = foodService.findById(id);
        if(result != null)
        {
            return Response.status(Response.Status.OK).entity(result).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/portion/{id}")
    public Response getFoodPortion(@PathParam("id")String id)
    {
        long result = foodService.getFoodPortion(id);
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
