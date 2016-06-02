package fr.pizzeria.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AbstractClientDaoTest {

	private static final int NB_INITIAL_CLIENT = 2;
	protected IClientDao clientDao;

	@Test
	public void testfindAllClients() throws DaoException {
		List<Client> Clients = clientDao.findAllClients();
		assertEquals(NB_INITIAL_CLIENT, Clients.size());
	}

	@Test
	public void testSaveClient() throws DaoException {
		Client Client = new Client("AA", "BB", "a@a.a", "aa");
		clientDao.saveClient(Client);
		List<Client> Clients = clientDao.findAllClients();
		assertEquals(NB_INITIAL_CLIENT + 1, Clients.size());
		Client clientResult = clientDao.findByNom("BB");
		assertEquals("BB", clientResult.getNom());
	}

	@Test
	public void testUpdateClient() throws DaoException {
		Client oneClient = clientDao.findByNom("Dalton");
		oneClient.setNom("Dalton 2");
		clientDao.updateClient(2, oneClient);

		Client clientResult = clientDao.findOne(2);
		assertEquals("Dalton 2", clientResult.getNom());
	}

	@Test
	public void testDeleteClient() throws DaoException {
		clientDao.deleteClient(2);
		List<Client> Clients = clientDao.findAllClients();
		assertEquals(NB_INITIAL_CLIENT - 1, Clients.size());
	}

}
