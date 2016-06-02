package fr.pizzeria.dao.livreur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.pizzeria.dao.AbstractLivreurDaoTest;
import fr.pizzeria.dao.ILivreurDao;

public class LivreurDaoJPARepoImplTest extends AbstractLivreurDaoTest {
	@Autowired
	public void setLivreurDao(@Qualifier("livreurDaoJPARepoImpl") ILivreurDao livreurDao) {
		this.livreurDao = livreurDao;
	}
}
