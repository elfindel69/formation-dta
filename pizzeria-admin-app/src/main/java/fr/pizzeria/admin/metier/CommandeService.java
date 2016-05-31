package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Commande;

@Stateless
public class CommandeService{
	
	@PersistenceContext(unitName = "pizzeria-db")
	protected EntityManager em;

	public List<Commande> findAll() {
		TypedQuery<Commande> query = em.createQuery("Select c from Commande c", Commande.class);
		return query.getResultList();
	}

	public void save(Commande newCommande) throws DaoException {
		try {
			em.persist(newCommande);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}

	}

	public void update(Commande newCommande) throws DaoException {
		Commande oldCommande = getObjectById(newCommande.getId());
		oldCommande.setNoCommande(newCommande.getNoCommande());
		oldCommande.setDateCommande(newCommande.getDateCommande());
		oldCommande.setStatut(newCommande.getStatut());
		oldCommande.setClient(newCommande.getClient());
		oldCommande.setLivreur(newCommande.getLivreur());
		// TODO liste des pizzas
	}
	
	public void delete(Integer id) throws DaoException {
		Commande oldCommande = getObjectById(id);
		em.remove(oldCommande);

	}

	protected Commande getObjectById(Integer id) throws DaoException {
		Commande oldCommande = em.find(Commande.class, id);
		if (oldCommande == null) {
			throw new DaoException("code non trouvé!");
		}
		return oldCommande;
	}

}
