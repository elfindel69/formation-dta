package facto;

public class Main {

	public static void main(String[] args) {
		IFileNameParser p = FileNameParserFactory.getParser("Windows");
		String filename = p.parseFile("C:\\Windows\\hello.dll");
		System.out.println(filename);
		IFileNameParser p2 = FileNameParserFactory.getParser("Linux");
		String filename2 = p2.parseFile("/user/share/hello.rc");
		System.out.println(filename2);
	}
	


}
