package filesystem;

/**
 * 
 */
public class FileSystem {
	/**
	 * 
	 */
	private Repertoire root;
	private IVisitor visitor;
	/**
	 * Default constructor
	 */
	public FileSystem(Repertoire root) {
		this.root = root;
	}

	public Repertoire getRoot() {
		return root;
	}

	public void setRoot(Repertoire root) {
		this.root = root;
	}

	
	void accepteVisiteur(IVisitor visitor){
		this.visitor = visitor;
	}

}