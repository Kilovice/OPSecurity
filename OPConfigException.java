package dev.kilovice.opsecurity.main;

public class OPConfigException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String path;
	public OPConfigException()
	{
		super();
	}
	
	public OPConfigException(String message, String path)
	{
		super(message);
		this.path = path;
	}
	
	public OPConfigException(String message, Throwable cause)
	{
	super(message, cause);	
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage()  + " for path: ['" + path + "']";
	}
	
	public String getPath()
	{
		return path;
	}
}
