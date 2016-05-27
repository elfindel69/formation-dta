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
public class CommandeService {
	@PersistenceContext(unitName = "pizzeria-db")
	private EntityManager em;

	public List<Commande> findAllCommandes() {
		TypedQuery<Commande> query = em.createQuery("Select c from Commande c", Commande.class);
		return query.getResultList();
	}

	public void saveCommande(Commande newCommande) throws DaoException {
		try {
			em.persist(newCommande);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}

	}

	public void updateCommande(Commande newCommande) throws DaoException {
		Commande oldCommande = getCommandeById(newCommande.getId());
		oldCommande.setNoCommande(newCommande.getNoCommande());
		oldCommande.setDateCommande(newCommande.getDateCommande());
		oldCommande.setStatut(newCommande.getStatut());
		oldCommande.setClient(newCommande.getClient());
		oldCommande.setLivreur(newCommande.getLivreur());
		// TODO liste des pizzas
	}

	public void deleteCommande(Integer id) throws DaoException {
		Commande oldCommande = getCommandeById(id);
		em.remove(oldCommande);

	}

	private Commande getCommandeById(Integer id) throws DaoException {
		Commande oldCommande = em.find(Commande.class, id);
		if (oldCommande == null) {
			throw new DaoException("code non trouvé!");
		}
		return oldCommande;
	}

}
