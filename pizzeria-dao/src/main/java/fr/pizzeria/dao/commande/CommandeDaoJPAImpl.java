package fr.pizzeria.dao.commande;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.StatutCommande;

public class CommandeDaoJPAImpl implements ICommandeDao {
	private EntityManagerFactory entityFacto;

	public CommandeDaoJPAImpl(EntityManagerFactory em) {
		entityFacto = em;
	}

	@Override
	public void saveCommande(Commande cmd) throws DaoException {
		EntityManager em = entityFacto.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(cmd.getClient());
			em.merge(cmd);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}
		et.commit();
		em.close();

	}

	@Override
	public List<Commande> findAllCommandes(Client client) {
		EntityManager em = entityFacto.createEntityManager();
		TypedQuery<Commande> query = em.createQuery("select c from Commande c where c.client =:client", Commande.class)
				.setParameter("client", client);
		List<Commande> commandes = query.getResultList();
		em.close();
		return commandes;
	}

	@Override
	public List<Commande> findCommandesNonTraitees() {
		EntityManager em = entityFacto.createEntityManager();
		TypedQuery<Commande> query = em.createQuery("select c from Commande c where c.statut =:statut", Commande.class)
				.setParameter("statut", StatutCommande.NON_TRAITE);
		List<Commande> commandes = query.getResultList();
		em.close();
		return commandes;
	}

}
