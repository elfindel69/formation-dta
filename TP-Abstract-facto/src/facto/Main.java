package facto;

import facto.interfaces.IFactory;
import facto.interfaces.IFileNameParser;

public class Main {

	public static void main(String[] args) {
		IFactory f = FactoryFactory.buildFactory(TypesOS.WINDOWS); //on demande a construire une factory windows
		IFileNameParser p = f.CreateParseFileName();
		String filename = p.parseFile("C:\\Windows\\hello.dll");
		System.out.println(filename);
		IFactory f2 = FactoryFactory.buildFactory(TypesOS.LINUX);
		IFileNameParser p2 = f2.CreateParseFileName();
		String filename2 = p2.parseFile("/user/share/hello.rc");
		System.out.println(filename2);
	}
	
    
    
  

}
