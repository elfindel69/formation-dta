package personne;

/**
 * 
 */
public abstract class EtatPersonne {

    /**
     * Default constructor
     */
    public EtatPersonne(String nom) {
    	this.nom = nom;
    }

    /**
     * 
     */
    protected String nom;

    /**
     * 
     */
    protected int ptsRetraite;

    /**
     * @return
     */
    public String getNom() {
        // TODO implement here
        return nom;
    }

    /**
     * @param points
     */
    public abstract void ajouterPointsRetraite(int points);

}