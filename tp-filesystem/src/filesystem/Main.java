package filesystem;

public class Main {

	public static void main(String[] args) {
		User user = new User("moi");
		Repertoire root = new Repertoire("/");
		FileSystem fs = new FileSystem(root);
		root.addNode(new Repertoire("/usr"));
		root.addNode(new Fichier("/usr/toto",10));
		Repertoire r1 = new Repertoire("/bin");
		root.addNode(r1);
		Repertoire r2 = new Repertoire("/bin/sh");
		Repertoire r3 = new Repertoire("/bin/null");
		r1.addNode(r2);
		r1.addNode(r3);
		r2.addNode(new Fichier("/usr/sh/sh.txt",10));
		VisiteurTaille vt = new VisiteurTaille();
		root.accepteVisitor(vt);
		System.out.println("taille "+vt.visiteRepertoire(root));
		VisiteurNombre vn = new VisiteurNombre();
		root.accepteVisitor(vn);
		System.out.println("nombre "+vn.visiteRepertoire(root));
	}

}
