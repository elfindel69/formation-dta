package personne;

public class Main {

	public static void main(String[] args) {
		Personne personne = new Personne();
		personne.setNom("toto");
		personne.setPrenom("titi");
		ObserverImpl observer = new ObserverImpl();
		personne.addObserver(observer);
		personne.setEtat(new Etudiant("Etudiant"));
		personne.notifyObservers(personne);
		personne.setEtat(new Actif("Actif"));
		personne.notifyObservers(personne);

	}

}
