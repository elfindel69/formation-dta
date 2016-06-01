package fr.pizzeria.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

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
	public void updateClient(String codeClient, Client updateClient) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteClient(String codeClient) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public Client connect(Client newClient) {
		EntityManager em = entityFacto.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Client c = em
				.createQuery("select c from Client c where c.email = :email and c.password = :password", Client.class)
				.setParameter("email", newClient.getEmail()).setParameter("password", newClient.getPassword())
				.getSingleResult();
		et.commit();
		em.close();
		return c;
	}

}
