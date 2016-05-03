package facto.linux;

import facto.interfaces.ICountFolders;

public class CountFoldersLinux implements ICountFolders {

	@Override
	public int countFolders(String path) {
		System.out.println("Je suis le Count Linux.");
        return 0;
	}
	

}
