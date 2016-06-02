package fr.pizzeria.dao.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.pizzeria.dao.AbstractClientDaoTest;
import fr.pizzeria.dao.IClientDao;

public class ClientDaoJPARepoImplTest extends AbstractClientDaoTest {
	@Autowired
	public void setClientDao(@Qualifier("clientDaoJPARepoImpl") IClientDao clientDao) {
		this.clientDao = clientDao;
	}
}
