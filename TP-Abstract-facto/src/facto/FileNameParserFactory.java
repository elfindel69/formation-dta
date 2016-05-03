package facto;

public class FileNameParserFactory {
	static IFileNameParser getParser(String os){
		IFileNameParser p = null;
		switch(os){
		case "Windows" : p = new ParseFileNameWindows();
		break;
		case "Linux" : p = new ParseFileNameLinux();
		break;
		}
		return p;
		
	}
}
