package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.ConnectionMenu;
import fr.pizzeria.model.Client;

public class ConnecterClientOptionMenu extends AbstractOptionMenu {

	private static final String CONNECTION_LIBELLE_MENU = "Se connecter";
	private static final String CONNECTION_MSG = "Connection";

	public ConnecterClientOptionMenu(Scanner scanner, IDaoFactory daoFact) {
		super(CONNECTION_LIBELLE_MENU, scanner, daoFact);
	}

	@Override
	public boolean execute() {
		IClientDao clientDao = daoFact.createClientDao();
	
		boolean continuer = true;
		System.out.println(CONNECTION_MSG);
		Client newClient = saisieCredentials(sc,clientDao);
		try {
			clientDao.saveClient(newClient);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if (newClient != null) {

			ConnectionMenu menu = new ConnectionMenu(sc, newClient, daoFact);
			menu.afficher();

		}

		return continuer;
	}

	private Client saisieCredentials(Scanner sc, IClientDao clientDao) {
		
		System.out.println(MENU_MSG_SAISIE_EMAIL);
		String email = sc.next();
		System.out.println(MENU_MSG_SAISIE_PASSWORD);
		String password = DigestUtils.md5Hex(sc.next());

		return clientDao.connect(email,password);
	}

}
