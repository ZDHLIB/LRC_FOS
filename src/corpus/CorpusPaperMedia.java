package corpus;

public class CorpusPaperMedia 
{
	private int type_id;
	private String type;
	private String PaperAbbreviation;
	
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
	
	public String getType_PaperAbbre() {
		return(PaperAbbreviation);
	}
	
	public void setType_PaperAbbre(String PaperAbbreviation) {
		this.PaperAbbreviation = PaperAbbreviation;
	}
}
