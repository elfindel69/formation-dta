package proxy;

public class Main {

	public static void main(String[] args) {
		IDictPersistant<String> dico = new DictPersistantImpl<>();
		dico.ajoute("1", "Test 1");
		dico.ajoute("2", "Test 2");
		dico.ajoute("3", "Test 3");
		dico.ajoute("4", "Test 4");
		dico.ajoute("5", "Test 5");
		System.out.println("sans proxy");
		System.out.println(dico.get("1"));
		System.out.println(dico.get("2"));
		System.out.println(dico.get("3"));
		System.out.println("\n avec proxy");
		dico = new DictPersistantProxy<>();
		dico.ajoute("6", "Test 6");
		System.out.println(dico.get("1"));
		System.out.println(dico.get("5"));
		System.out.println(dico.get("6"));
		dico.ajoute("7", "Test 7");
		System.out.println(dico.get("7"));
		
	}

}
