package fr.pizzeria.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.pizzeria.dao.ILivreurDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Livreur;

public class LivreurDaoJPAImpl implements ILivreurDao {

	private EntityManagerFactory entityFacto;

	public LivreurDaoJPAImpl(EntityManagerFactory entityFacto) {
		this.entityFacto = entityFacto;
	}

	@Override
	public List<Livreur> findAddLivreurs() {
		EntityManager em = entityFacto.createEntityManager();
		TypedQuery<Livreur> query = em.createQuery("select l from Livreur l", Livreur.class);
		List<Livreur> livreurs = query.getResultList();
		em.close();
		return livreurs;
	}

	@Override
	public Livreur getLivreurByNom(String nom) throws DaoException {
		EntityManager em = entityFacto.createEntityManager();
		Livreur l = null;
		try{
			l=em.createQuery("select l from Livreur l where l.nom = :nom", Livreur.class).setParameter("nom", nom)
			.getSingleResult();
		}catch(PersistenceException e){
			throw new DaoException("ce nom n'existe pas");
		}finally{
			em.close();
		}
		return l;
	}

}
