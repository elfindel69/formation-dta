package personne;


import java.util.Observable;
import java.util.Observer;

public class ObserverImpl implements Observer {
	private Observable observable;
	private Personne personne;
	


	@Override
	public void update(Observable o, Object arg) {
		this.observable = o;
		this.setEtat((Personne) arg);
		System.out.println(personne.getPrenom()+' '+personne.getNom()+" "+personne.getEtat().getNom());
	}


	public Personne getEtat() {
		return personne;
	}


	public void setEtat(Personne arg) {
		this.personne = arg;
	}
}
