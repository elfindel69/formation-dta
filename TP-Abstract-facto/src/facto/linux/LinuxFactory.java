package facto.linux;

import facto.interfaces.ICountFolders;
import facto.interfaces.IFactory;
import facto.interfaces.IFileNameParser;

public class LinuxFactory implements IFactory {

	@Override
	public IFileNameParser CreateParseFileName() {
		return new ParseFileNameLinux();
	}

	@Override
	public ICountFolders CreateCountFolder() {
		return new CountFoldersLinux();
	}

}
