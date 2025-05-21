package services;

import entities.Employee;
import persistence.EmployeeDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/employees")
public class EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") Integer id) {
        Employee employee = employeeDAO.find(id);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
        return Response.ok(employee).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee) {
        employeeDAO.persist(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") Integer id, Employee updatedEmployee) {
        Employee existingEmployee = employeeDAO.find(id);
        if (existingEmployee == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }

        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());

        employeeDAO.update(existingEmployee);

        return Response.ok(existingEmployee).build();
    }
}
