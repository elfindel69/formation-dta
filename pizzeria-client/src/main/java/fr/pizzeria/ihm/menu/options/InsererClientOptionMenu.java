package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Client;

public class InsererClientOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_CLIENT_MSG_OK = "Client ajouté ^^";
	private static final String AJOUTER_MSG = "Inscription";
	private static final String AJOUTER_LIBELLE_MENU = "S'inscrire";
	private static final String MENU_MSG_SAISIE_NOM = "nom...";
	private static final String MENU_MSG_SAISIE_PRENOM = "prenom...";

	/**
	 * Constructeur
	 * 
	 * @param sc
	 *            - scanner
	 * @param daoFact
	 *            - lien vers la DAO
	 */
	public InsererClientOptionMenu(Scanner sc, IDaoFactory daoFact) {
		super(AJOUTER_LIBELLE_MENU, sc, daoFact);
	}

	@Override
	public boolean execute() {
		System.out.println(AJOUTER_MSG);
		IClientDao clientDao = daoFact.createClientDao();
		Client newClient = saisieClient(sc);
		try {
			clientDao.saveClient(newClient);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(AJOUTER_CLIENT_MSG_OK);

		return true;
	}

	Client saisieClient(Scanner sc) {
		Client newClient = new Client();
		System.out.println(MENU_MSG_SAISIE_NOM);
		newClient.setNom(sc.next());
		System.out.println(MENU_MSG_SAISIE_PRENOM);
		newClient.setPrenom(sc.next());
		System.out.println(MENU_MSG_SAISIE_EMAIL);
		newClient.setEmail(sc.next());
		System.out.println(MENU_MSG_SAISIE_PASSWORD);
		newClient.setPassword(DigestUtils.md5Hex(sc.next()));
		return newClient;
	}
}
