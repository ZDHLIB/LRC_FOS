package textBook;

public class Textdetail {
	private Integer id = 0;
	private String title = "";
	private String IDoftext = "";
	private String author = "";
	private String sourcefrom = "";
	private String translatefrom = "";
	private String form = "";
	private String typeoftext = "";
	private String domain = "";
	private String content = "";
	private String languages = "";
	private String textstandard = "";
	private String classtype = "";
	private String publisher = "";
	private String subject = "";
	private String period = "";
	private String charaters = "";
	private String volume = "";
	private String tag = "";
	
	public Textdetail(Integer id, String title, String iDoftext, String author,
			String sourcefrom, String translatefrom, String form,
			String typeoftext, String domain, String content, String languages,
			String textstandard, String classtype, String publisher,
			String subject, String period, String charaters, String volume,
			String tag) {
		super();
		this.id = id;
		this.title = title;
		IDoftext = iDoftext;
		this.author = author;
		this.sourcefrom = sourcefrom;
		this.translatefrom = translatefrom;
		this.form = form;
		this.typeoftext = typeoftext;
		this.domain = domain;
		this.content = content;
		this.languages = languages;
		this.textstandard = textstandard;
		this.classtype = classtype;
		this.publisher = publisher;
		this.subject = subject;
		this.period = period;
		this.charaters = charaters;
		this.volume = volume;
		this.tag = tag;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIDoftext() {
		return IDoftext;
	}
	public void setIDoftext(String iDoftext) {
		IDoftext = iDoftext;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSourcefrom() {
		return sourcefrom;
	}
	public void setSourcefrom(String sourcefrom) {
		this.sourcefrom = sourcefrom;
	}
	public String getTranslatefrom() {
		return translatefrom;
	}
	public void setTranslatefrom(String translatefrom) {
		this.translatefrom = translatefrom;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getTypeoftext() {
		return typeoftext;
	}
	public void setTypeoftext(String typeoftext) {
		this.typeoftext = typeoftext;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getTextstandard() {
		return textstandard;
	}
	public void setTextstandard(String textstandard) {
		this.textstandard = textstandard;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCharaters() {
		return charaters;
	}
	public void setCharaters(String charaters) {
		this.charaters = charaters;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
