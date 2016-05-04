package proxy;

public interface IDictPersistant<T> {
	boolean ajoute(String cle, T objet);

	  T get(String cle);
}
