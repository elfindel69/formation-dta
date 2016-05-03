package facto;

public class ParseFileNameWindows implements IFileNameParser {

	@Override
	public String parseFile(String path) {
		//index est l'endroit o� se situe, dans la String path, la derni�re

		//apparition du caract�re \
		int index = path.lastIndexOf("\\");

		//On construit une String qui ne contient que la partie situ�e � droite

		//du dernier caract�re \

		String r = path.substring(index+1);
		return r;
	}

}
