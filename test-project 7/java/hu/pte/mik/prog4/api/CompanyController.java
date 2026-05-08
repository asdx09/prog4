package hu.pte.mik.prog4.api;

import hu.pte.mik.prog4.model.Company;
import hu.pte.mik.prog4.model.CompanyCreationRequest;
import hu.pte.mik.prog4.model.CompanyUpdateRequest;
import hu.pte.mik.prog4.service.CompanyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController() {
        this.companyService = new CompanyService();
    }

    @GET
    public Response listAll() {
        List<Company> companies = this.companyService.listAll();

        return Response.status(Response.Status.OK)
                .entity(companies).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Optional<Company> company = this.companyService.listAll()
                .stream()
                .filter(c -> Objects.equals(id, c.getId()))
                .findFirst();

        if(company.isPresent()) {
            return Response.status(Response.Status.OK)
                    .entity(company.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/payment")
    public Response pay(@PathParam("id") Long id) {
        Optional<Company> company = this.companyService.listAll()
                .stream()
                .filter(c -> Objects.equals(id, c.getId()))
                .findFirst();

        if(company.isPresent()) {
            this.companyService.pay(company.get());
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response create(CompanyCreationRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setTaxNumber(request.getTaxNumber());

        Company cmp = this.companyService.create(company);
        if(cmp != null) {
            return Response.status(Response.Status.CREATED)
                    .entity(cmp).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    public Response update(CompanyUpdateRequest request) {
        Company company = new Company();
        company.setId(request.getId());
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setTaxNumber(request.getTaxNumber());

        Company cmp = this.companyService.update(company);
        if(cmp != null) {
            return Response.status(Response.Status.ACCEPTED)
                    .entity(cmp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
