package filesystem;

public class VisiteurNombre implements IVisitor{

	@Override
	public int visiteFichier(Node noeud) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int visiteRepertoire(Node noeud) {
		int cpt = 0;
		Repertoire root = (Repertoire) noeud;
		cpt++;
		for (Node n:root.getNodes()){
			if (n instanceof Fichier){
				cpt++;
			}else{
				cpt +=visiteRepertoire(n);
			}
		}
		return cpt;
		
	}

}
