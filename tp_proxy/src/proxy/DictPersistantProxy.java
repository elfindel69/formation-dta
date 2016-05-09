package proxy;

import java.util.HashMap;

public class DictPersistantProxy<T> implements IDictPersistant<T>{
	private HashMap<String,T> mapDico = new HashMap<>();
	private DictPersistantImpl<T> dict= new DictPersistantImpl<>();
	@Override
	public boolean ajoute(String cle, T objet) {
		boolean retour = dict.ajoute(cle, objet);
		if (retour){
			mapDico.put(cle, objet);
		}
		return retour;
	}

	@Override
	public T get(String cle) {
		T retour = null;
		if (mapDico.containsKey(cle)){
			System.out.println("Clé existante: "+cle);
			retour= mapDico.get(cle);
		}else{
			System.out.println("Ajout clé: "+cle);
			retour=dict.get(cle);
			if(retour != null){
				mapDico.put(cle, retour);
			}
		}
		return retour;
	}

}
