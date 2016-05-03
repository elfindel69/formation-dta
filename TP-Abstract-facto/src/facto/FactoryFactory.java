package facto;

import facto.interfaces.IFactory;
import facto.linux.LinuxFactory;
import facto.windows.WindowsFactory;

public class FactoryFactory {
	public static IFactory buildFactory(TypesOS os)
    {
        switch(os)
        {        
            case WINDOWS:
                return new WindowsFactory();
            
            case LINUX:
                return new LinuxFactory();
            
            default:
                return null;
        }
    }
}
