package textBook;

public class Textbook {
	
	Integer id = 0;
	String bookname = "";
	String chiefeditor = "";
	String associateeditor = "";
	String publisher = "";
	String publisher_j = "";
	String publishtime = "";
	String copyright = "";
	String curriculumstandard = "";
	String typeofcurriculum = "";
	String typeofcurriculum_j = "";
	String subject = "";
	String subject_j = "";
	String textclass = "";
	String textclass_j = "";
	String period = "";
	String period_j = "";
	String grade = "";
	String volume = "";
	String volume_j = "";
	String language = "";
	String language_j = "";
	String words = "";
	
	public Textbook(){}
	
	public Textbook(int id, String name, String chiefeditor, String associateeditor,
			String publisher, String publisher_j, String publishtime, String copyright,	String curriculumstandard, 
			String typeofcurriculum, String typeofcurriculum_j, String subject, String subject_j, 
			String textclass, String textclass_j, String period, String period_j, String grade, 
			String volume, String volume_j, String language, String language_j, String words){
		
		this.id = id;
		this.bookname = name;
		this.chiefeditor = chiefeditor;
		this.associateeditor = associateeditor;
		this.publisher = publisher;
		this.publisher_j = publisher_j;
		this.publishtime = publishtime;
		this.copyright = copyright;
		this.curriculumstandard = curriculumstandard;
		this.typeofcurriculum = typeofcurriculum;
		this.typeofcurriculum_j = typeofcurriculum_j;
		this.subject = subject;
		this.subject_j = subject_j;
		this.textclass = textclass;
		this.textclass_j = textclass_j;
		this.period = period;
		this.period_j = period_j;
		this.grade = grade;
		this.volume = volume;
		this.volume_j = volume_j;
		this.language = language;
		this.language_j = language_j;
		this.words = words;
	}
	
	public int getId() 
	{
		return(id);
	}
	public void setId(int id) 
	{
		this.id=id;
	}
	
	
	public String getBookName() 
	{
		return(bookname);
	}	
	public void setName(String bookname) 
	{
		this.bookname=bookname;
	}

	
	public String getChiefeditor() 
	{
		return(chiefeditor);
	}	
	public void setChiefeditor(String chiefeditor) 
	{
		this.chiefeditor=chiefeditor;
	}

	
	public String getAssociateeditor() 
	{
		return(associateeditor);
	}	
	public void setAssociateeditor(String associateeditor) 
	{
		this.associateeditor=associateeditor;
	}

	
	public String getPublisher() 
	{
		return(publisher);
	}	
	public void setPublisher(String publisher) 
	{
		this.publisher=publisher;
	}

	
	public String getPublisher_j() 
	{
		return(publisher);
	}	
	public void setPublisher_j(String publisher_j) 
	{
		this.publisher_j=publisher_j;
	}
	
	public String getPublishtime() 
	{
		return(publishtime);
	}	
	public void setPublishtime(String publishtime) 
	{
		this.publisher=publishtime;
	}

	
	public String getCopyright() 
	{
		return(copyright);
	}	
	public void setCopyright(String copyright) 
	{
		this.copyright=copyright;
	}

	
	public String getCurriculumstandard() 
	{
		return(curriculumstandard);
	}	
	public void setCurriculumstandard(String curriculumstandard) 
	{
		this.curriculumstandard=curriculumstandard;
	}

	
	public String getTypeofcurriculum() 
	{
		return(typeofcurriculum);
	}	
	public void setTypeofcurriculum(String typeofcurriculum) 
	{
		this.typeofcurriculum=typeofcurriculum;
	}

	
	public String getTypeofcurriculum_j() 
	{
		return(typeofcurriculum_j);
	}	
	public void setTypeofcurriculum_j(String typeofcurriculum_j) 
	{
		this.typeofcurriculum_j=typeofcurriculum_j;
	}
	
	
	public String getSubject() 
	{
		return(subject);
	}	
	public void setSubject(String subject) 
	{
		this.subject=subject;
	}

	
	public String getSubject_j() 
	{
		return(subject_j);
	}	
	public void setSubject_j(String subject_j) 
	{
		this.subject_j=subject_j;
	}
	
	
	public String getTextclass() 
	{
		return(textclass);
	}	
	public void setTextclass(String textclass) 
	{
		this.textclass=textclass;
	}

	
	public String getTextclass_j() 
	{
		return(textclass_j);
	}	
	public void setTextclass_j(String textclass_j) 
	{
		this.textclass_j=textclass_j;
	}
	
	
	public String getPeriod() 
	{
		return(period);
	}	
	public void setPeriod(String period) 
	{
		this.period=period;
	}

	
	public String getPeriod_j() 
	{
		return(period_j);
	}	
	public void setPeriod_j(String period_j) 
	{
		this.period_j=period_j;
	}
	
	
	public String getGrade() 
	{
		return(grade);
	}	
	public void setGrade(String grade) 
	{
		this.grade=grade;
	}

	
	public String getVolume() 
	{
		return(volume);
	}	
	public void setVolume(String volume) 
	{
		this.volume=volume;
	}

	
	public String getVolume_j() 
	{
		return(volume_j);
	}	
	public void setVolume_j(String volume_j) 
	{
		this.volume_j=volume_j;
	}

	

	
	public String getLanguage() 
	{
		return(language);
	}	
	public void setLanguage(String language) 
	{
		this.language=language;
	}
	
	
	public String getLanguage_j() 
	{
		return(language_j);
	}	
	public void setLanguage_j(String language_j) 
	{
		this.language_j=language_j;
	}

	
	public String getWords() 
	{
		return(words);
	}	
	public void setWords(String words) 
	{
		this.words=words;
	}
}
