package facto.interfaces;

public interface IFactory {
	IFileNameParser CreateParseFileName();
    ICountFolders CreateCountFolder();
}
