package benoProjet.Repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import benoProjet.entities.User;

@Stateless
public class UserRepository {
	
	@SuppressWarnings("unused")
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
	@SuppressWarnings("unused")
	private static final String PARAM_EMAIL = "email";
	
	@PersistenceContext(unitName = "beno")
	private EntityManager entityManager;
	
	public void addUser(User user){
		entityManager.persist(user);
	}
	
	public void deleteUser(String email){
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		User user = (User) requete.getSingleResult();
		entityManager.remove(user);
	}
	
	public User findUserbyEmail(String email){
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		User user = (User) requete.getSingleResult();
		return user;
	}
	
	public void update(User u){
		if(entityManager.find(User.class, u.getId())==null)
			throw new IllegalArgumentException("Unknown user");
		entityManager.merge(u);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllTheUsers(){
		Query requete = entityManager.createNativeQuery("select * from User where hierachie='Etudiant'", User.class);
		List<User> user = requete.getResultList();
		return user;
		//return entityManager.createNativeQuery("select * from User", User.class).getResultList();
	}
	
	public Response login(String email, String mdp,String hierachie) {
		Query requete = entityManager.createNativeQuery("select * from User where email='" + email + "' AND mdp='" + mdp + "' AND hierachie='" + hierachie + "'", User.class);
		System.out.println("email : " + email + " mdp : " +mdp + " hierachie : " +hierachie);
		User user = null;
		try{
			user = (User) requete.getSingleResult();
		}catch(Exception e){
			System.out.println("not found");
			return Response.status(Status.NOT_FOUND).build();
		}
		
		if (user.getMdp().equals(mdp) && user.getEmail().equals(email) && user.getHierachie().equals(hierachie)){
			System.out.println("mot de passe--> mdp " + mdp + " mot de passe user.getMdp() : " + user.getMdp());
			ObjectMapper m = new ObjectMapper();
			try {
				return Response.ok(m.writeValueAsString(user), MediaType.APPLICATION_JSON).build();
			} catch (JsonProcessingException e) {
				System.out.println("zzzzzzzzzz" + e.getMessage());
				return Response.ok("{\"error \": \"Error JSON Processing "+e.getMessage()+"\"}",MediaType.APPLICATION_JSON).build();
			}
			
		}
		return Response.ok("{}", MediaType.APPLICATION_JSON).build();
	}
	
	
}
