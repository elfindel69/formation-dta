package facto;

public class FactoryFactory {
	public IFactory buildFactory(int factorytype)
    {
        switch(factorytype)
        {        
            case(0):
                return new WindowsFactory();
            
            case(1):
                return new LinuxFactory();
            
            default:
                return null;
        }
    }
}
