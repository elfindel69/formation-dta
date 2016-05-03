package facto;

import facto.interfaces.ICountFolders;
import facto.interfaces.IFactory;
import facto.interfaces.IFileNameParser;

public class Main {

	public static void main(String[] args) {
		IFactory f = FactoryFactory.buildFactory(TypesOS.WINDOWS); //on demande a construire une factory windows
		IFileNameParser p = f.CreateParseFileName();
		final String windowsPath = "C:\\Windows\\hello.dll";
		System.out.println("Windows");
		System.out.println(windowsPath);
		String filename = p.parseFile(windowsPath);
		System.out.println(filename);
		ICountFolders c1 = f.CreateCountFolder();
		int cpt = c1.countFolders(windowsPath);
		System.out.println(cpt);
		
		IFactory f2 = FactoryFactory.buildFactory(TypesOS.LINUX);
		IFileNameParser p2 = f2.CreateParseFileName();
		final String linuxPath = "/user/share/hello.rc";
		System.out.println('\n'+"Linux");
		System.out.println(linuxPath);
		String filename2 = p2.parseFile(linuxPath);
		System.out.println(filename2);
		ICountFolders c2 = f2.CreateCountFolder();
		int cpt2 = c2.countFolders(linuxPath);
		System.out.println(cpt2);
	}
	
    
    
  

}
