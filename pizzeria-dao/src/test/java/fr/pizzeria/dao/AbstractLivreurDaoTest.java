package fr.pizzeria.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Livreur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AbstractLivreurDaoTest {

	private static final int NB_INITIAL_CLIENT = 2;
	protected ILivreurDao livreurDao;

	@Test
	public void testfindAllLivreurs() throws DaoException {
		List<Livreur> Livreurs = livreurDao.findAllLivreurs();
		assertEquals(NB_INITIAL_CLIENT, Livreurs.size());
	}

	@Test
	public void testSaveLivreur() throws DaoException {
		Livreur Livreur = new Livreur("AA", "BB");
		livreurDao.saveLivreur(Livreur);
		List<Livreur> Livreurs = livreurDao.findAllLivreurs();
		assertEquals(NB_INITIAL_CLIENT + 1, Livreurs.size());
		Livreur livreuResult = livreurDao.findByNom("BB");
		assertEquals("BB", livreuResult.getNom());
	}

	@Test
	public void testUpdateLivreur() throws DaoException {
		Livreur oneLivreur = livreurDao.findByNom("Simpson");
		oneLivreur.setNom("Simpson 2");
		livreurDao.updateLivreur(2, oneLivreur);

		Livreur livreuResult = livreurDao.findOne(2);
		assertEquals("Simpson 2", livreuResult.getNom());
	}

	@Test
	public void testDeleteLivreur() throws DaoException {
		livreurDao.deleteLivreur(2);
		List<Livreur> livreurs = livreurDao.findAllLivreurs();
		assertEquals(NB_INITIAL_CLIENT - 1, livreurs.size());
	}

}
