package corpus;

public class CorpusNetMedia 
{
	private int type_id;
	private String type;
	private String NetAbbreviation;
	
	public String getType() 
	{
		return(type);
	}
	
	public void setType(String type) 
	{
		this.type=type;
	}
	
	public int getType_id() 
	{
		return(type_id);
	}
	
	public void setType_id(int type_id) 
	{
		this.type_id=type_id;
	}
	
	public String getType_NetAbbre() {
		return(NetAbbreviation);
	}
	
	public void setType_NetAbbre( String NetAbbreviation ) {
		this.NetAbbreviation = NetAbbreviation;
	}
}
