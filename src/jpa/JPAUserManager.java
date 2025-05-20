package jpa;

import java.util.List;
import javax.persistence.*;
import bloodbank.ifaces.UserManager;
import bloodbank.db.pojos.*;

public class JPAUserManager implements UserManager{
	EntityManager em;
	
	public JPAUserManager() {
		em=Persistence.createEntityManagerFactory("bloodbank-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty()) {
			
			//TODO: VERIFY THESE ARE THE ROLES WE SHOULD DO.
			Role bloodbankWorker=new Role("bloodbankWorker");
			Role donationsWorker=new Role("donationsWorker");
			Role requestsWorker=new Role("requestsWorker");
			
			this.createRole(bloodbankWorker);
			this.createRole(donationsWorker);
			this.createRole(requestsWorker);
			
			}
			
			
		}
	
	
	public void close() {
		em.close();
	
	}
	
	@Override
	public void register(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	@Override
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();
	}
	
	@Override
	public void assignRole(User user,Role role) {
		em.getTransaction().begin();
		user.setRole(role);
		role.addUser(user);
		em.getTransaction().commit();
	}
	
	@Override
	public User login(String name,String password) {
		try {
			Query q=em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?",User.class);
			q.setParameter(1, name);
			q.setParameter(2,password);
			User user=(User)q.getSingleResult();
			return user;
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Role>getRoles(){
		Query q=em.createNativeQuery("SELECT * FROM roles",Role.class);
		List<Role>roles=(List<Role>)q.getResultList();
		return roles;
	}

	
	@Override
	public Role getRole(String name) {
		Query q=em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?",Role.class);
		q.setParameter(1, name);
		Role r=(Role)q.getSingleResult();
		return r;
	}
}
