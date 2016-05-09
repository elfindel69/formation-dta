package filesystem;
import java.util.*;

/**
 * 
 */
public class Repertoire extends Node {

    /**
     * Default constructor
     */
    public Repertoire() {
    }



    public Repertoire(String nom) {
		this.name = nom;
	}



	public List<Node> getNodes() {
		return nodes;
	}



	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}



	/**
     * 
     */
    private List<Node> nodes = new ArrayList<>();

    /**
     * 
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * @param node 
     * 
     */
    public void removeNode(Node node) {
    	 nodes.remove(node);
    }

    @Override
   public void accepteVisitor(IVisitor visitor){
		this.visitor = visitor;
		for(Node n : nodes){
			n.accepteVisitor(visitor);
		}
	}
}