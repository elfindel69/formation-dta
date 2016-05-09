package personne;

/**
 * 
 */
public class Actif extends EtatPersonne {

	/**
	 * Default constructor
	 */
	public Actif(String nom) {
		super(nom);
	}

	@Override
	public void ajouterPointsRetraite(int points) {
		this.ptsRetraite += points;
		
	}

}