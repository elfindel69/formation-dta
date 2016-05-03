package facto.windows;

import facto.interfaces.ICountFolders;

public class CountFolderWindows implements ICountFolders {

	@Override
	public int countFolders(String path) {
		int cpt=0;
		for(char c : path.toCharArray()){
			if (c == '\\'){
				cpt++;
			}
					
		}
        return cpt;
	}

}
