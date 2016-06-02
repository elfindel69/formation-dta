package fr.pizzeria.dao.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.repository.IClientRepository;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;

@Repository
@Lazy
public class ClientDaoJPARepoImpl implements IClientDao {

	@Autowired
	IClientRepository clientRepo;

	@Override
	public List<Client> findAllClients() throws DaoException {
		return clientRepo.findAll();
	}

	@Override
	public void saveClient(Client newClient) throws DaoException {
		clientRepo.save(newClient);

	}

	@Override
	@Transactional
	public void updateClient(int idClient, Client updateClient) throws DaoException {
		Client oldClient = clientRepo.findOne(idClient);
		oldClient.setPrenom(updateClient.getPrenom());
		oldClient.setNom(updateClient.getNom());
		oldClient.setEmail(updateClient.getEmail());
		oldClient.setPassword(updateClient.getPassword());

	}

	@Override
	public void deleteClient(int idClient) throws DaoException {
		Client oldClient = clientRepo.findOne(idClient);
		clientRepo.delete(oldClient);
	}

	@Override
	public Client connect(String email, String password) {
		return clientRepo.findByEmailAndPassword(email, password);
	}

	@Override
	public Client findByNom(String nom) {
	
		return clientRepo.findByNom(nom);
	}
	
	@Override
	public Client findOne(int i) {
		return clientRepo.findOne(i);
	}

}
