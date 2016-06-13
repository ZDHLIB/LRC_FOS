package net.lrc.model;

import java.util.Date;

public class News {
	private Integer id;
	private String newsTitle;
	private String newsContext;
	private Integer count;
	private Date addTime;
	private String author;
	private String images;

	public News() {
	}

	public News(Integer id, String newsTitle, String newsContext) {
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContext = newsContext;
	}

	public News(Integer id, String newsTitle, String newsContext,
			Integer count, Date addTime, String author, String images) {
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContext = newsContext;
		this.count = count;
		this.addTime = addTime;
		this.author = author;
		this.images = images;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContext() {
		return this.newsContext;
	}

	public void setNewsContext(String newsContext) {
		this.newsContext = newsContext;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}
}