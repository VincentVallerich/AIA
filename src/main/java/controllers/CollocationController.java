package controllers;

import dao.ColocationDao;
import dao.ServiceDao;
import dao.UserDao;
import model.Colocation;
import model.Service;
import model.User;
import provider.ColocationDaoProvider;
import provider.ServiceDaoProvider;
import provider.UserDaoProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("colocation")
public class CollocationController {
    private final ColocationDao colocationDao = ColocationDaoProvider.getColocationDao();
    private final ServiceDao serviceDao = ServiceDaoProvider.getServiceDao();
    private final UserDao userDao = UserDaoProvider.getUserDao();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{colocationId}")
    public Response getCollocationById(@PathParam("colocationId") long colocationId) {
        Optional<Colocation> colocation = colocationDao.findById(colocationId);
        return colocation.isPresent()
                ? Response.ok().entity(colocation.get()).build()
                : Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{colocationId}")
    public Response getCollocationUsers(@PathParam("colocationId") long colocationId) {
        List<User> users = colocationDao.getUsers(colocationId);
        return !users.isEmpty()
                ? Response.ok().entity(users).build()
                : Response.noContent().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{colocationId}/services")
    public Response putService(@PathParam("colocationId") long colocationId, @PathParam("service") Service service) {
        colocationDao.addService(colocationId, service);
        if (serviceDao.findById(service.getId()).isPresent())
            return Response.ok().build();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{colocationId}/users")
    public Response putUser(@PathParam("colocationId") long colocationId, @PathParam("user") User user) {
        colocationDao.addUsers(colocationId, user);
        if (userDao.findById(user.getId()).isPresent())
            return Response.ok().build();
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{colocationId}/{userId}")
    public Response delete(@PathParam("colocationId") long colocationId, @PathParam("userId") long userId) {
        long adminId = colocationDao.getAdminId(colocationId);
        if (adminId == userId) {
            colocationDao.delete(colocationId);
            return Response.ok().build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
