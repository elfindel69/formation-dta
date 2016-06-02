package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.pizzeria.dao.client.IClientDao;

public class ClientDaoJPARepoImplTest extends AbstractClientDaoTest {
	@Autowired
	public void setClientDao(@Qualifier("clientDaoJPARepoImpl") IClientDao clientDao) {
		this.clientDao = clientDao;
	}
}
