package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;

@Stateless
public class ClientService {
	@PersistenceContext(unitName = "pizzeria-db")
	private EntityManager em;

	public List<Client> findAll(){
		TypedQuery<Client> query = em.createQuery("Select c from Client c", Client.class);
		return query.getResultList();
	}

	public void save(Client newClient) throws DaoException {
		try {
			em.persist(newClient);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}

	}

	public void update(Client newClient) throws DaoException {
		Client oldClient = getObjectById(newClient.getId());
		oldClient.setNom(newClient.getNom());
		oldClient.setPrenom(newClient.getPrenom());
		oldClient.setEmail(newClient.getEmail());
		oldClient.setPassword(newClient.getPassword());

	}
	
	public void delete(Integer id) throws DaoException {
		Client oldCommande = getObjectById(id);
		em.remove(oldCommande);

	}

	protected Client getObjectById(Integer id) throws DaoException {
		Client oldClient = em.find(Client.class, id);
		if(oldClient==null){
			throw new DaoException("code non trouvé!");
		}
		return oldClient;
	}



}
