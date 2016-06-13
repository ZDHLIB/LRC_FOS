package net.lrc.model;

public class Master {
	private int id;
	private String workexperience;
	private String studyexperience;
	private String research;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	public String getStudyexperience() {
		return studyexperience;
	}

	public void setStudyexperience(String studyexperience) {
		this.studyexperience = studyexperience;
	}

	public String getWorkexperience() {
		return workexperience;
	}

	public void setWorkexperience(String workexperience) {
		this.workexperience = workexperience;
	}
}
