package fr.pizzeria.dao.client;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;

public class ClientDaoJPAImpl implements IClientDao {

	private EntityManagerFactory entityFacto;

	public ClientDaoJPAImpl(EntityManagerFactory em) {
		this.entityFacto = em;
	}

	@Override
	public List<Client> findAllClients() throws DaoException {
		return null;
	}

	@Override
	public void saveClient(Client newClient) throws DaoException {
		EntityManager em = entityFacto.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(newClient);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}
		et.commit();
		em.close();

	}

	@Override
	public void updateClient(int codeClient, Client updateClient) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteClient(int codeClient) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public Client connect(String email, String password) {
		EntityManager em = entityFacto.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Client c = em
				.createQuery("select c from Client c where c.email = :email and c.password = :password", Client.class)
				.setParameter("email", email).setParameter("password", password)
				.getSingleResult();
		et.commit();
		em.close();
		return c;
	}

	@Override
	public Client findByNom(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findOne(int i) {
		EntityManager em = entityFacto.createEntityManager();
		Client c = em.find(Client.class, i);
		em.close();
		return c;
	}

}
