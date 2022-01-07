package controllers;

import dao.ServiceDao;
import model.Service;
import provider.ServiceDaoProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/services")
public class ServiceController {

    private ServiceDao dao = ServiceDaoProvider.getServiceDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServices() {
        return Response.ok().entity(dao.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getServiceById(@PathParam("id") long serviceId) {
        Service service = dao.findById(serviceId);
        return !service.equals(new Service())
                ? Response.ok().entity(service).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteServiceById(@PathParam("id"), long serviceId) {
        dao.delete(serviceId);
        return Response.ok().build();
    }
}
