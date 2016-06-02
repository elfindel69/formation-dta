package fr.pizzeria.dao.livreur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.ILivreurDao;
import fr.pizzeria.dao.repository.ILivreurRepository;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Livreur;

@Repository
@Lazy
public class LivreurDaoJPARepoImpl implements ILivreurDao {

	@Autowired
	ILivreurRepository livreurRepo;
	@Override
	public List<Livreur> findAllLivreurs() throws DaoException {
		return livreurRepo.findAll();
	}

	@Override
	public void saveLivreur(Livreur newLivreur) throws DaoException {
		livreurRepo.save(newLivreur);

	}

	@Override
	@Transactional
	public void updateLivreur(int idLivreur, Livreur updateLivreur) throws DaoException {
		Livreur oldLivreur = livreurRepo.findOne(idLivreur);
		oldLivreur.setPrenom(updateLivreur.getPrenom());
		oldLivreur.setNom(updateLivreur.getNom());
	}

	@Override
	public void deleteLivreur(int idLivreur) throws DaoException {
		Livreur oldLivreur = livreurRepo.findOne(idLivreur);
		livreurRepo.delete(oldLivreur);
	}

	
	@Override
	public Livreur findByNom(String nom) {
	
		return livreurRepo.findByNom(nom);
	}
	
	@Override
	public Livreur findOne(int i) {
		return livreurRepo.findOne(i);
	}

}
