package benoProjet.Resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import benoProjet.Repository.UserRepository;
import benoProjet.entities.User;

@Path("/users")
public class UserResource {
	@EJB
	UserRepository userRepository;
		
	@POST
	@Path("/addUser")
	@Consumes("application/json")
	public void  addUser(User user){
		userRepository.addUser(user);
	}
	
	@GET
	@Path("/AllUser")
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> AllUser(){
		return userRepository.getAllTheUsers();
	}
	
	@GET
	@Path("/{email}")
	@Produces({MediaType.APPLICATION_JSON})
	public void getUser(@PathParam("email")String email){
		userRepository.findUserbyEmail(email);
	}
	
	@PUT
	@Path("/PutUser")
	@Consumes("application/json")
	public void PutUser(User user){
		userRepository.update(user);
	}
	
	@DELETE
	@Path("/{email}")
	@Produces({MediaType.APPLICATION_JSON})
	public void DeleteUser(@PathParam("email")String email){
		userRepository.deleteUser(email);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User u){
		System.out.println("<<<<>>>>> " + u.getEmail()+ " " +u.getMdp() + " " + u.getHierachie());
		return userRepository.login(u.getEmail(),u.getMdp(),u.getHierachie());
	}
}