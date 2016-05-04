package filesystem;

/**
 * 
 */
public class Fichier extends Node {

	/**
     * Default constructor
     */
    public Fichier() {
    }

	public Fichier(String nom, int size) {
		this.name = nom;
		this.size = size;
	}
	
	@Override
	public void accepteVisitor(IVisitor visitor) {
		this.visitor = visitor;
		
	}

}