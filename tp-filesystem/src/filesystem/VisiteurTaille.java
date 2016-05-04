package filesystem;

public class VisiteurTaille implements IVisitor {

	@Override
	public int visiteFichier(Node noeud) {
		return noeud.getSize();

	}

	@Override
	public int visiteRepertoire(Node noeud) {
		int size = 0;
		Repertoire root = (Repertoire) noeud;
		size += noeud.getSize();
		for (Node n:root.getNodes()){
			if (n instanceof Fichier){
				size += n.getSize();
			}else{
				size +=visiteRepertoire(n);
			}
		}
		return size;
		
	}

}
