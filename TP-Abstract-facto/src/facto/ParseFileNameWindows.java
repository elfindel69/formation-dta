package facto;

public class ParseFileNameWindows implements IFileNameParser {

	@Override
	public String parseFile(String path) {
		//index est l'endroit où se situe, dans la String path, la dernière

		//apparition du caractère \
		int index = path.lastIndexOf("\\");

		//On construit une String qui ne contient que la partie située à droite

		//du dernier caractère \

		String r = path.substring(index+1);
		return r;
	}

}
