package facto.linux;

import facto.interfaces.ICountFolders;

public class CountFoldersLinux implements ICountFolders {

	@Override
	public int countFolders(String path) {
		int cpt=0;
		for(char c : path.toCharArray()){
			if (c == '/'){
				cpt++;
			}
					
		}
        return cpt;
	}
	

}
