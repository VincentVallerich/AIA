package controllers;

import annotation.SigninNeeded;
import dao.ColocationDao;
import dao.ServiceDao;
import model.Service;
import provider.ColocationDaoProvider;
import provider.ServiceDaoProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("colocation/{colocationId}/service")
public class ServiceController {

    private final ServiceDao serviceDao = ServiceDaoProvider.getServiceDao();
    private final ColocationDao colocationDao = ColocationDaoProvider.getColocationDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServices() {
        return Response.ok().entity(serviceDao.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getServiceById(@PathParam("colocationId") long colocationId, @PathParam("id") long serviceId) {
        Optional<Service> service = colocationDao.getAService(colocationId, serviceId);
        return service.isPresent()
                ? Response.ok().entity(service.get()).build()
                : Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteServiceById(@PathParam("colocationId") long colocationId, @PathParam("id") long serviceId) {
        colocationDao.deleteService(colocationId, serviceId);
        return Response.ok().build();
    }
}
