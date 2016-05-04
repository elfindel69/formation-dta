package filesystem;

/**
 * 
 */
public abstract class Node {
	protected IVisitor visitor;
	/**
	 * Default constructor
	 */
	public Node() {
	}

	/**
	 * 
	 */
	protected int id;

	/**
	 * 
	 */
	protected int size;

	/**
	 * 
	 */
	protected String name;

	/**
	 * 
	 */
	protected User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param Visitor
	 *            visiteur
	 */
	abstract public void accepteVisitor(IVisitor visitor);

	public VisiteurTaille getVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

}