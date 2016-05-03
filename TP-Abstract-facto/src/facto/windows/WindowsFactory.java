package facto.windows;

import facto.interfaces.ICountFolders;
import facto.interfaces.IFactory;
import facto.interfaces.IFileNameParser;

public class WindowsFactory implements IFactory {

	@Override
	public IFileNameParser CreateParseFileName() {
		 return new ParseFileNameWindows();
	}

	@Override
	public ICountFolders CreateCountFolder() {
		return new CountFolderWindows();
	}

}
