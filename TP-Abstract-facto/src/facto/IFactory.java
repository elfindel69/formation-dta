package facto;

public interface IFactory {
	IFileNameParser CreateParseFileName();
    ICountFolders CreateCountFolder();
}
