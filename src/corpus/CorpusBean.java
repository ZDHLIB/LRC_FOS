package corpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import corpus.CorpusBean;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.apache.log4j.Logger;

import textBook.Textbook;
import textBook.Textdetail;

import net.lrc.db.Mysql;
import net.lrc.util.AbstractPage;

//工具包 

//dom4j包 


public class CorpusBean extends AbstractPage {
	private int id;
	private int type_id;
	private int quality;
	private int test;
	private String origin;
	private String title;
	private String author;
	private String date;
	private String language;
	private String url;
	private String lastModifyTime;
	private String size;
	private String remarks;
	private String tag;
	private String type;
	public final static int MESSAGE_PAGE_SIZE = 15;
	private static Logger logger = Logger.getLogger(CorpusBean.class);

	public CorpusBean() {
	}

	public CorpusBean(int id, String origin, String title, String author,
			String date, String language, String url, String lastModifyTime,
			String size, int type_id, int quality, int test, String remarks,
			String tag, String type) {
		this.id = id;
		this.origin = origin;
		this.title = title;
		this.author = author;
		this.date = date;
		this.language = language;
		this.url = url;
		this.lastModifyTime = lastModifyTime;
		this.size = size;
		this.type_id = type_id;
		this.quality = quality;
		this.test = test;
		this.remarks = remarks;
		this.tag = tag;
		this.type = type;
	}

	public String getSize() {
		return (size);
	}

	public String getRemarks() {
		return (remarks);
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrigin() {
		return (origin);
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTitle() {
		return (title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return (author);
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return (date);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return (url);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType_id() {
		return (type_id);
	}

	public String getLastModifyTime() {
		return (lastModifyTime);
	}

	public int getId() {
		return (id);
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getTest() {
		return (test);
	}

	public void setTset(int test) {
		this.test = test;
	}

	public int getQuality() {
		return (quality);
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String getType_tag() {
		return (tag);
	}

	public void setType_tag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return (type);
	}

	public void setType(String type) {
		this.type = type;
	}

	//2013-6-18, added by zhongdunhao 
	public List<Textbook>getAlltextbook() {
		List<Textbook> alltextbook = new ArrayList<Textbook>();
		ResultSet resultSet = null;
		try {
			Mysql mysql = new Mysql();
			String sql = "select * from textbook order by id";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			Textbook textbook = null;

			while (resultSet.next()) {
				textbook = new Textbook();
				textbook.setId(resultSet.getInt("id"));
				textbook.setName(resultSet.getString("name"));
				textbook.setChiefeditor(resultSet.getString("chiefeditor"));
				textbook.setAssociateeditor(resultSet.getString("associateeditor"));
				textbook.setPublisher(resultSet.getString("publisher"));
				textbook.setPublishtime(resultSet.getString("publishtime"));
				textbook.setCopyright(resultSet.getString("copyright"));
				textbook.setCurriculumstandard(resultSet.getString("curriculumstandard"));
				textbook.setTypeofcurriculum(resultSet.getString("typeofcurriculum"));
				textbook.setTypeofcurriculum(resultSet.getString("typeofcurriculum_j"));
				textbook.setSubject(resultSet.getString("subject"));
				textbook.setSubject(resultSet.getString("subject_j"));
				textbook.setTextclass(resultSet.getString("class"));
				textbook.setTextclass(resultSet.getString("class_j"));
				textbook.setPeriod(resultSet.getString("period"));
				textbook.setPeriod(resultSet.getString("period_j"));
				textbook.setGrade(resultSet.getString("grade"));
				textbook.setVolume(resultSet.getString("volume"));
				textbook.setVolume(resultSet.getString("volume_j"));
				textbook.setLanguage(resultSet.getString("language"));
				textbook.setLanguage(resultSet.getString("language_j"));
				textbook.setWords(resultSet.getString("words"));
				alltextbook.add(textbook);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		setPagesize(MESSAGE_PAGE_SIZE);
		return (alltextbook);
	}
	
	//2013-6-18, added by zhongdunhao 
	public List<Textbook>getTextbook(String publisher,String period,String volume,String language) {
		List<Textbook> alltextbook = new ArrayList<Textbook>();
		ResultSet resultSet = null;
		try {
			Mysql mysql = new Mysql();
			String sql = "select * from textbook order by id where publisher='"+publisher+"'period='"+period+"'volume='"+volume+"'language='"+language+"'";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			Textbook textbook = null;

			while (resultSet.next()) {
				textbook = new Textbook();
				textbook.setId(resultSet.getInt("id"));
				textbook.setName(resultSet.getString("name"));
				textbook.setChiefeditor(resultSet.getString("chiefeditor"));
				textbook.setAssociateeditor(resultSet.getString("associateeditor"));
				textbook.setPublisher(resultSet.getString("publisher"));
				textbook.setPublishtime(resultSet.getString("publishtime"));
				textbook.setCopyright(resultSet.getString("copyright"));
				textbook.setCurriculumstandard(resultSet.getString("curriculumstandard"));
				textbook.setTypeofcurriculum(resultSet.getString("typeofcurriculum"));
				textbook.setTypeofcurriculum(resultSet.getString("typeofcurriculum_j"));
				textbook.setSubject(resultSet.getString("subject"));
				textbook.setSubject(resultSet.getString("subject_j"));
				textbook.setTextclass(resultSet.getString("class"));
				textbook.setTextclass(resultSet.getString("class_j"));
				textbook.setPeriod(resultSet.getString("period"));
				textbook.setPeriod(resultSet.getString("period_j"));
				textbook.setGrade(resultSet.getString("grade"));
				textbook.setVolume(resultSet.getString("volume"));
				textbook.setVolume(resultSet.getString("volume_j"));
				textbook.setLanguage(resultSet.getString("language"));
				textbook.setLanguage(resultSet.getString("language_j"));
				textbook.setWords(resultSet.getString("words"));
				alltextbook.add(textbook);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		setPagesize(MESSAGE_PAGE_SIZE);
		return (alltextbook);
	}
	
	//2013-6-20, added by zhongdunhao; read the xml to upload the textbook
	//2013-7-10, modified by zhongdunhao; added some parameters including *_j,;
	public void uploadTextbook(String textbookPath) {
		List<String> pathList = new ArrayList<String>();
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
		System.out.println(textbookPath);
		try{
			Mysql mysql = new Mysql();
    		pathList = getAllFilePath(textbookPath,pathList);
    		for( int i = 0; i < pathList.size(); i++ ){
    			System.out.println(pathList.get(i));
    			SAXBuilder builder = new SAXBuilder();
        		InputStream file = new FileInputStream(pathList.get(i));
        		Document doc = builder.build(file);// 获得文档对象
        		Element root = doc.getRootElement();// 获得根节点
        		
        		bookname = root.getChildText("bookname");
        		chiefeditor = root.getChildText("chiefeditor");
        		associateeditor = root.getChildText("associateeditor");
        		publisher = root.getChildText("publisher");
        		publisher_j = root.getChildText("publisher_j");
        		publishtime = root.getChildText("publishtime");
        		copyright = root.getChildText("copyright");
        		curriculumstandard = root.getChildText("curriculumstandard");
        		typeofcurriculum = root.getChildText("typeofcurriculum");
        		typeofcurriculum_j = root.getChildText("typeofcurriculum_j");
        		subject = root.getChildText("subject");
        		subject_j = root.getChildText("subject_j");
        		textclass = root.getChildText("class");
        		textclass_j = root.getChildText("class_j");
        		period = root.getChildText("period");
        		period_j = root.getChildText("period_j");
        		grade = root.getChildText("grade");
        		volume = root.getChildText("volume");
        		volume_j = root.getChildText("volume_j");
        		language = root.getChildText("language");
        		language_j = root.getChildText("language_j");
        		words = root.getChildText("words");
        		
    			String sql = "insert into textbook(bookname,chiefeditor,associateeditor," +
    					"publisher,publisher_j,publishtime,copyright,curriculumstandard," +
    					"typeofcurriculum,typeofcurriculum_j,subject,subject_j," +
    					"class,class_j,period,period_j,grade,volume,volume_j,languages,languages_j," +
    					"words) values('" + 
    					bookname + "','"+ 
    					chiefeditor +"','"+
    					associateeditor +"','" +
    					publisher +"','" +
    					publisher_j +"','" +
    					publishtime +"','" +
    					copyright +"','" +
    					curriculumstandard +"','" +
    					typeofcurriculum +"','" +
    					typeofcurriculum_j +"','" +
    					subject +"','" +
    					subject_j +"','" +
    					textclass +"','" +
    					textclass_j +"','" +
    					period +"','" +
    					period_j +"','" +
    					grade +"','" +
    					volume +"','" +
    					volume_j +"','" +
    					language +"','" +
    					language_j +"','" +
    					words +"')";
    			
    			System.out.println(sql);
    			logger.info(sql);
    			mysql.executeUpdate(sql);
    		}
    	}catch( Exception e){
    		e.printStackTrace();
    	}
	}
	
	/*
	 * @2013-6-20, added by zhongdunhao
	 * @function: Get all files' path under a directory's path
	 */
	public List<String> getAllFilePath(String dirPath, List<String> pathList) {
		
		File file = new File(dirPath);
		
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				pathList.add(file.getAbsolutePath()); 
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					getAllFilePath(files[i].getAbsolutePath(),pathList); // 把每个文件 用这个方法进行迭代
				}
			}
		} else {
			System.out.println("文件不存在！" + '\n');
		}
		return pathList;
	}
	
	//added by zhongdunhao 2013-6-24 delete upload textbook
	public void deleteUploadTextbook(String [] value) {
		try{
			Mysql mysql = new Mysql();
			
			for( int i = 0; i < value.length; i++ ){
				String sql = "delete from textbook where id=" + Integer.valueOf(value[i]);
				logger.info(sql);
				mysql.executeUpdate(sql);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteUploadTextdetail(String [] value) {
		try{
			Mysql mysql = new Mysql();
			ResultSet resultSet = null;
			for( int i = 0; i < value.length; i++ ){
				
				String sql = "select content from texts where id=" + Integer.valueOf(value[i]);
				logger.info(sql);
				resultSet = mysql.executeQuery(sql);
				while (resultSet.next()) {
					String fileName = "";
					String deleteFileName = "";
					String path = resultSet.getString("content");
					System.out.println("content path is :"+path);
					deleteFileName = path.substring(path.lastIndexOf("\\")+1, path.length());
					String folderPath = path.substring(0, path.lastIndexOf("\\"));
					File file = new File(folderPath);
					File files [] = file.listFiles();
					for( int j = 0; j < files.length; j++ ){
						fileName = files[j].getName();
						System.out.println("fileName:"+fileName);
						if( fileName.equals(deleteFileName) ){
							files[j].delete();
							System.out.println("delete succese");
						} else {
							System.out.println("delete false");							
						}
					}
					/*
					if( file.exists() ){
						file.delete();
						System.out.println("delete succese");
					} else{
						System.out.println("delete false");
					}*/
				}
				
				sql = "delete from texts where id=" + Integer.valueOf(value[i]);
				logger.info(sql);
				mysql.executeUpdate(sql);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//added by zhongdunhao 2013-7-4 upload texts
	public void uploadTexts() {
		
	}
	
	
	// 获取所有的语料资源种类
	public List<CorpusCategory> getAllCategory() {
		List<CorpusCategory> allCategory = new ArrayList<CorpusCategory>();
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_category order by type_id";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			CorpusCategory corpusCategory = null;

			while (resultSet.next()) {
				corpusCategory = new CorpusCategory();
				corpusCategory.setType_id(resultSet.getInt("type_id"));
				corpusCategory.setType(resultSet.getString("type"));
				allCategory.add(corpusCategory);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (allCategory);
	}

	// 是否加工过
	public List<CorpusCategorytag> getAllCategorytag() {
		List<CorpusCategorytag> allCategorytag = new ArrayList<CorpusCategorytag>();
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from temp order by type_id";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			CorpusCategorytag corpusCategorytag = null;

			while (resultSet.next()) {
				corpusCategorytag = new CorpusCategorytag();
				corpusCategorytag.setType_id(resultSet.getInt("type_id"));
				corpusCategorytag.setType(resultSet.getString("type"));
				allCategorytag.add(corpusCategorytag);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (allCategorytag);
	}

	// 语料种类
	public List<CorpusLanguage> getAllLanguage() {
		List<CorpusLanguage> allCategory = new ArrayList<CorpusLanguage>();
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_language order by type_id";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			CorpusLanguage corpusLanguage = null;

			while (resultSet.next()) {
				corpusLanguage = new CorpusLanguage();
				corpusLanguage.setType_id(resultSet.getInt("type_id"));
				corpusLanguage.setType(resultSet.getString("type"));
				allCategory.add(corpusLanguage);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (allCategory);
	}

	// 网络媒体
	public List<CorpusNetMedia> getAllNetMedia() {
		List<CorpusNetMedia> allCategory = new ArrayList<CorpusNetMedia>();
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_origin_netmedia order by type_id";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			CorpusNetMedia corpusOriginNetMedia = null;

			while (resultSet.next()) {
				corpusOriginNetMedia = new CorpusNetMedia();
				corpusOriginNetMedia.setType_id(resultSet.getInt("type_id"));
				corpusOriginNetMedia.setType(resultSet.getString("type"));
				corpusOriginNetMedia.setType_NetAbbre(resultSet
						.getString("NetAbbreviation"));
				allCategory.add(corpusOriginNetMedia);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (allCategory);
	}

	// 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喎褰ㄧ喊澶嬪缁炬悂鏁撻弬銈嗗婵帡鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	public List<CorpusPaperMedia> getAllPaperMedia() {
		List<CorpusPaperMedia> allCategory = new ArrayList<CorpusPaperMedia>();
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_origin_papermedia order by type_id";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			CorpusPaperMedia corpusOriginPaperMedia = null;

			while (resultSet.next()) {
				corpusOriginPaperMedia = new CorpusPaperMedia();
				corpusOriginPaperMedia.setType_id(resultSet.getInt("type_id"));
				corpusOriginPaperMedia.setType(resultSet.getString("type"));
				corpusOriginPaperMedia.setType_PaperAbbre(resultSet
						.getString("PaperAbbreviation"));
				allCategory.add(corpusOriginPaperMedia);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (allCategory);
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗濠ф劙鏁撻弬銈嗗闁跨喐鏋婚幏锟�
	public CorpusCategory getCategory(int type_id) {
		CorpusCategory corpusCategory = null;
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_category where type_id="
					+ type_id;
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusCategory = new CorpusCategory();
				corpusCategory.setType_id(resultSet.getInt("type_id"));
				corpusCategory.setType(resultSet.getString("type"));
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (corpusCategory);
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喓娈曟潏鐐闁跨喐鏋婚幏鐑芥晸閿燂拷
	public boolean addCorpusCategory(String type) {
		type = type.trim();
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "insert into corpus_category(type) values('" + type
					+ "')";
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閿燂拷
	public boolean addCorpusLanguage(String type) {
		type = type.trim();
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "insert into corpus_language(type) values('" + type
					+ "')";
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喓娈曟潏鐐濮婁即鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閻偅鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻敓锟�
	public boolean addCorpusOriginNetMedia(String type, String NetAbbreviation) {
		type = type.trim();
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "insert into corpus_origin_netmedia(type,NetAbbreviation) values('"
					+ type + "','" + NetAbbreviation + "')";
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閿燂拷
	public boolean addCorpusOriginPaperMedia(String type,
			String PaperAbbreviation) {
		type = type.trim();
		boolean flag = false;
		try {
			Mysql mysql = new Mysql();
			String sql = "insert into corpus_origin_papermedia(type,PaperAbbreviation) values('"
					+ type + "','" + PaperAbbreviation + "')";
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 閸掔娀鏁撻弬銈嗗閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗濠ф劙鏁撻弬銈嗗闁跨喐鏋婚幏锟�
	public boolean deleteCorpusCategory(int type_id) {
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "delete from corpus_category where type_id=" + type_id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 閸掔娀鏁撻弬銈嗗閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏锟�
	public boolean deleteCorpusLanguage(int type_id) {
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "delete from corpus_language where type_id=" + type_id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 閸掔娀鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹烽攱瀵氶柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚规刊鎺楁晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
	public boolean deleteCorpusNetMedia(int type_id) {
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "delete from corpus_origin_netmedia where type_id="
					+ type_id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	// 閸掔娀鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹烽攱瀵氶柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚规刊鎺楁晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
	public boolean deleteCorpusPaperMedia(int type_id) {
		boolean flag = false;

		try {
			Mysql mysql = new Mysql();
			String sql = "delete from corpus_origin_papermedia where type_id="
					+ type_id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return (flag);
	}

	@SuppressWarnings("unchecked")
	public void setResult(String offset, String filepath) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_details order by lastModifyTime desc,id desc";
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				result.add(corpusBean);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}
	
	
	//added by zhongdunhao 2013-6-24
	public void setResultUploadTextbook(String offset, String filepath, String query){
		setPagesize(25);
		result = new ArrayList();
		//String query = "select * FROM " + table + " order by id desc";
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				Textbook textbook = new Textbook(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getString(10), resultSet.getString(11), 
						resultSet.getString(12), resultSet.getString(13), 
						resultSet.getString(14), resultSet.getString(15), 
						resultSet.getString(16), resultSet.getString(17),
						resultSet.getString(18), resultSet.getString(19),
						resultSet.getString(20), resultSet.getString(21),
						resultSet.getString(22), resultSet.getString(23));
				result.add(textbook);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}
	
	public void setResultUploadTextdetail(String offset, String filepath, String query){
		setPagesize(25);
		result = new ArrayList();
		//String query = "select * FROM " + table + " order by id desc";
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				Textdetail textdetail = new Textdetail(resultSet.getInt(24),
						resultSet.getString(25), resultSet.getString(26),
						resultSet.getString(27), resultSet.getString(28),
						resultSet.getString(29), resultSet.getString(30),
						resultSet.getString(31), resultSet.getString(32),
						resultSet.getString(33), resultSet.getString(21), 
						resultSet.getString(9), resultSet.getString(14), 
						resultSet.getString(5), resultSet.getString(12), 
						resultSet.getString(16), resultSet.getString(10),
						resultSet.getString(19), resultSet.getString(42));
				result.add(textdetail);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}
	

	public void setResult(String offset, String filepath, String table) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM " + table
				+ " order by lastModifyTime desc,id desc";
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				result.add(corpusBean);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	public void setResult(String offset, String filepath, String table, int p) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();

		String query = "select * FROM " + table + " where quality = " + p
				+ " order by lastModifyTime desc,id desc,test desc";
		logger.info(query);
		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				result.add(corpusBean);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void setResultNetMedia(String offset, String filepath) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_origin_netmedia order by type_id";
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				CorpusNetMedia corpusNetMedia = new CorpusNetMedia();
				corpusNetMedia.setType_id(resultSet.getInt(1));
				corpusNetMedia.setType(resultSet.getString(2));
				corpusNetMedia.setType_NetAbbre(resultSet.getString(3));
				result.add(corpusNetMedia);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void setResultPaperMedia(String offset, String filepath) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_origin_papermedia order by type_id";
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				CorpusPaperMedia corpusPaperMedia = new CorpusPaperMedia();
				corpusPaperMedia.setType_id(resultSet.getInt(1));
				corpusPaperMedia.setType(resultSet.getString(2));
				corpusPaperMedia.setType_PaperAbbre(resultSet.getString(3));
				result.add(corpusPaperMedia);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	// 检索语料资源，但语料搜索结果加了一定的限制
	@SuppressWarnings("unchecked")
	public void searchCorpus1(String relationship, String offset,
			String filepath, String category, String language, String date,
			String date2, String origin, String title, String author) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_details where";

		if (relationship.equals("与")) {
			query += " 1=1";
			if (!(category.equals("-1"))) {
				query += " and type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " and language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " and date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " and date='" + date + "'";
				} else {
					query += " and date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " and origin='" + origin + "'";
			}

			if (title != "") {
				query += " and title='" + title + "'";
			}

			if (author != "") {
				query += " and author='" + author + "'";
			}
			query += " order by date";
		} else if (relationship.equals("或")) {
			query += " 1=2";
			if (!(category.equals("-1"))) {
				query += " or type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " or language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " or date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " or date='" + date + "'";
				} else {
					query += " or date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " or origin='" + origin + "'";
			}

			if (title != "") {
				query += " or title='" + title + "'";
			}

			if (author != "") {
				query += " or author='" + author + "'";
			}
			query += " order by date";
		}
		logger.info(query);

		try {
			ResultSet resultSet = myQuery1(query, offset, filepath);

			while (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				result.add(corpusBean);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	public void searchCorpus2(String relationship, String offset,
			String filepath, String category, String language, String date,
			String date2, String origin, String title, String author,
			String factors) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_details where";
		String querypart = "";
		int factorInt = Integer.valueOf(factors);
		System.out.println(factorInt);
		switch (factorInt) {
		case 8:
			querypart = "1=1";
			break;
		case 931:
			querypart = "test = 1 and tag = 1";
			break;
		case 921:
			querypart = "test = 0 and tag = 1";
			break;
		case 920:
			querypart = "test = 0 and tag = 0";
			break;
		case 930:
			querypart = "test = 1 and tag = 0";
			break;
			
		case 791:
			querypart = "quality = 3 and tag = 1";
			break;
		case 691:
			querypart = "quality = 2 and tag = 1";
			break;	
		case 591:
			querypart = "quality = 1 and tag = 1";
			break;
		case 491:
			querypart = "quality = 0 and tag = 1";
			break;
		case 790:
			querypart = "quality = 3 and tag = 0";
			break;
		case 690:
			querypart = "quality = 2 and tag = 0";
			break;
		case 590:
			querypart = "quality = 1 and tag = 0";
			break;
		case 490:
			querypart = "quality = 0 and tag = 0";
			break;
			
		case 739:
			querypart = "quality = 3 and test = 1";
			break;
		case 639:
			querypart = "quality = 2 and test = 1";
			break;
		case 539:
			querypart = "quality = 1 and test = 1";
			break;
		case 439:
			querypart = "quality = 0 and test = 1";
			break;
			
		case 729:
			querypart = "quality = 3 and test = 0";
			break;
		case 629:
			querypart = "quality = 2 and test = 0";
			break;
		case 529:
			querypart = "quality = 1 and test = 0";
			break;
		case 429:
			querypart = "quality = 0 and test = 0";
			break;	
						
		case 991:
			querypart = "tag = 1";
			break;
		case 990:
			querypart = "tag = 0";
			break;
		case 799:
			querypart = "quality = 3";
			break;
		case 699:
			querypart = "quality = 2";
			break;
		case 599:
			querypart = "quality = 1";
			break;
		case 499:
			querypart = "quality = 0";
			break;
		case 999:
			querypart = "1=1";
			break;
	
		case 731:
			querypart = "quality = 3 and test = 1 and tag = 1";
			break;
		case 631:
			querypart = "quality = 2 and test = 1 and tag = 1";
			break;
		case 531:
			querypart = "quality = 1 and test = 1 and tag = 1";
			break;
		case 431:
			querypart = "quality = 0 and test = 1 and tag = 1";
			break;

		case 721:
			querypart = "quality = 3 and test = 0 and tag = 1";
			break;
		case 621:
			querypart = "quality = 2 and test = 0 and tag = 1";
			break;
		case 521:
			querypart = "quality = 1 and test = 0 and tag = 1";
			break;
		case 421:
			querypart = "quality = 0 and test = 0 and tag = 1";
			break;

		case 730:
			querypart = "quality = 3 and test = 1 and tag = 0";
			break;
		case 630:
			querypart = "quality = 2 and test = 1 and tag = 0";
			break;
		case 530:
			querypart = "quality = 1 and test = 1 and tag = 0";
			break;
		case 430:
			querypart = "quality = 0 and test = 1 and tag = 0";
			break;

		case 720:
			querypart = "quality = 3 and test = 0 and tag = 0";
			break;
		case 620:
			querypart = "quality = 2 and test = 0 and tag = 0";
			break;
		case 520:
			querypart = "quality = 1 and test = 0 and tag = 0";
			break;
		case 420:
			querypart = "quality = 0 and test = 0 and tag = 0";
			break;

		default:
			querypart = "1=1";
			break;
		}

		query += " 1=1";
		if (!(category.equals("-1"))) {
			query += "and type_id=" + Integer.parseInt(category);
		}

		if (!(language.equals("----"))) {
			query += " and language='" + language + "'";
		}

		if (!date2.equals("0000-00-00")) {
			if (date.length() == 4)
				date += "-00-00";
			else if (date.length() == 7)
				date += "-00";
			if (date2.length() == 4)
				date2 += "-00-00";
			else if (date2.length() == 7)
				date2 += "-00";
			query += " and date between '" + date + "' and '" + date2 + "'";
		} else if (!(date.equals("0000-00-00"))) {
			if (date.length() == 10) {
				query += " and date='" + date + "'";
			} else {
				query += " and date like '" + date + "%'";
			}
		}

		if( origin != null ) {
			if (origin.equals("全部语料")) {
				query += " and 1=1";
				
			} else {
				query += " and origin='" + origin + "'";
			}
		}

		if (title != "") {
			query += " and title='" + title + "'";
		}

		if (author != "") {
			query += " and author='" + author + "'";
		}
		query = query + " and " + querypart;
		query += " order by date";

		logger.info(query);
		System.out.println(query);
		try {
			ResultSet resultSet = myQuery2(query, offset, filepath);

			while (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				result.add(corpusBean);
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	public List<CorpusBean> searchCorpus3(String idAll, String title1,
			String title2, String number) {
		int tempNumber = 0;
		int n = Integer.valueOf(number);
		if (title1 != "" && title2 == "") {
			tempNumber = (n * Integer.valueOf(title1)) / 100;
		} else if (title2 != "" && title1 == "") {
			tempNumber = Integer.valueOf(title2);
		} else {
			tempNumber = n;
		}

		System.out.println("aaaaaaaaaaaaaa" + tempNumber);

		int m = (int) (Math.random() * (n - tempNumber));

		String idAl[] = new String[200000];
		String id[] = new String[tempNumber];
		for (int i = 0; i < idAl.length; i++) {
			idAl[i] = new String();
		}
		for (int i = 0; i < id.length; i++) {
			id[i] = new String();
		}

		int begin = 0, j = 0;
		for (j = 0; j < n - 1; j++) {
			begin = idAll.indexOf("-");
			idAl[j] = idAll.substring(0, begin);
			idAll = idAll.substring(begin + 1, idAll.length());
		}
		idAl[j] = idAll;

		System.out.println("bbbbbbbbbbbbbbbbbbb" + m);
		System.out.println("bbbbbbbbbbbbbbbbbbb" + tempNumber);

		List<CorpusBean> sk = new ArrayList<CorpusBean>();
		int i = 0;
		for (i = m, j = 0; j < tempNumber; i++, j++) {
			id[j] = idAl[i];
			CorpusBean temp = new CorpusBean();

			try {
				temp = temp.getCorpusBean(Integer.valueOf(id[j]));
				sk.add(temp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return sk;

	}

	public void searchCorpus4(String relationship, String offset,
			String filepath, String category, String language, String date,
			String date2, String origin, String title, String author,
			String factors) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_details where";
		String querypart = "";
		int factorInt = Integer.valueOf(factors);
		switch (factorInt) {
		case 0:
			querypart = "1=1";
			break;
		case 1:
			querypart = "quality = 1";
			break;
		case 2:
			querypart = "quality = 0";
			break;
		case 3:
			querypart = "test = 1";
			break;
		case 4:
			querypart = "test = 0";
			break;
		case 12:
			querypart = "1=1";
			break;
		case 13:
			querypart = "quality = 1 and test = 1";
			break;
		case 14:
			querypart = "quality = 1 and test = 0";
			break;
		case 23:
			querypart = "quality = 0 and test = 1";
			break;
		case 24:
			querypart = "quality = 0 and test = 0";
			break;
		case 34:
			querypart = "1=1";
			break;
		case 123:
			querypart = "test = 1";
			break;
		case 124:
			querypart = "test = 0";
			break;
		case 234:
			querypart = "quality = 0";
			break;
		case 134:
			querypart = "quality = 1";
			break;
		case 1234:
			querypart = "1=1";
			break;
		default:
			querypart = "1=1";
			break;
		}

		if (relationship.equals("与")) {
			query += " 1=1";
			if (!(category.equals("-1"))) {
				query += " and type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " and language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " and date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " and date='" + date + "'";
				} else {
					query += " and date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " and origin='" + origin + "'";
			}

			if (title != "") {
				query += " and title='" + title + "'";
			}

			if (author != "") {
				query += " and author='" + author + "'";
			}
			query = query + " and " + querypart;
			query += " order by date";
		} else if (relationship.equals("或")) {
			query += " 1=2";
			if (!(category.equals("-1"))) {
				query += " or type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " or language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " or date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " or date='" + date + "'";
				} else {
					query += " or date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " or origin='" + origin + "'";
			}

			if (title != "") {
				query += " or title='" + title + "'";
			}

			if (author != "") {
				query += " or author='" + author + "'";
			}
			query = query + " or " + querypart;

			query += " order by date";
		}
		logger.info(query);

		try {
			ResultSet resultSet = myQuery3(query, offset, filepath);

			while (resultSet.next()) {
				String temp = resultSet.getString(3).substring(
						resultSet.getString(3).indexOf("-") + 1,
						resultSet.getString(3).length());
				if (temp.indexOf("-") == -1) {
					CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
							resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5),
							resultSet.getString(6), resultSet.getString(7),
							resultSet.getString(8), resultSet.getString(9),
							resultSet.getInt(10), resultSet.getInt(11),
							resultSet.getInt(12), resultSet.getString(13),
							resultSet.getString(14), resultSet.getString(15));
					result.add(corpusBean);
				}
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	public void searchCorpus5(String relationship, String offset,
			String filepath, String category, String language, String date,
			String date2, String origin, String title, String author,
			String factors) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_details where";
		String querypart = "";
		int factorInt = Integer.valueOf(factors);
		switch (factorInt) {
		case 0:
			querypart = "1=1";
			break;
		case 1:
			querypart = "quality = 1";
			break;
		case 2:
			querypart = "quality = 0";
			break;
		case 3:
			querypart = "test = 1";
			break;
		case 4:
			querypart = "test = 0";
			break;
		case 12:
			querypart = "1=1";
			break;
		case 13:
			querypart = "quality = 1 and test = 1";
			break;
		case 14:
			querypart = "quality = 1 and test = 0";
			break;
		case 23:
			querypart = "quality = 0 and test = 1";
			break;
		case 24:
			querypart = "quality = 0 and test = 0";
			break;
		case 34:
			querypart = "1=1";
			break;
		case 123:
			querypart = "test = 1";
			break;
		case 124:
			querypart = "test = 0";
			break;
		case 234:
			querypart = "quality = 0";
			break;
		case 134:
			querypart = "quality = 1";
			break;
		case 1234:
			querypart = "1=1";
			break;
		default:
			querypart = "1=1";
			break;
		}

		if (relationship.equals("与")) {
			query += " 1=1";
			if (!(category.equals("-1"))) {
				query += " and type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " and language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " and date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " and date='" + date + "'";
				} else {
					query += " and date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " and origin='" + origin + "'";
			}

			if (title != "") {
				query += " and title='" + title + "'";
			}

			if (author != "") {
				query += " and author='" + author + "'";
			}
			query = query + " and " + querypart;
			query += " order by date";
		} else if (relationship.equals("或")) {
			query += " 1=2";
			if (!(category.equals("-1"))) {
				query += " or type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " or language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " or date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " or date='" + date + "'";
				} else {
					query += " or date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " or origin='" + origin + "'";
			}

			if (title != "") {
				query += " or title='" + title + "'";
			}

			if (author != "") {
				query += " or author='" + author + "'";
			}
			query = query + " or " + querypart;

			query += " order by date";
		}
		logger.info(query);

		try {
			ResultSet resultSet = myQuery4(query, offset, filepath);

			while (resultSet.next()) {
				String temp = resultSet.getString(3).substring(
						resultSet.getString(3).indexOf("-") + 1,
						resultSet.getString(3).length());
				if (temp.indexOf("-") != -1) {
					CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
							resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5),
							resultSet.getString(6), resultSet.getString(7),
							resultSet.getString(8), resultSet.getString(9),
							resultSet.getInt(10), resultSet.getInt(11),
							resultSet.getInt(12), resultSet.getString(13),
							resultSet.getString(14), resultSet.getString(15));
					result.add(corpusBean);

				}
			}

			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏閿嬬爱
	@SuppressWarnings("unchecked")
	public void searchCorpus(String relationship, String offset,
			String filepath, String category, String language, String date,
			String date2, String origin, String title, String author) {
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM corpus_details where";

		if (relationship.equals("与")) {
			query += " 1=1";
			if (!(category.equals("-1"))) {
				query += " and type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " and language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " and date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " and date='" + date + "'";
				} else {
					query += " and date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " and origin='" + origin + "'";
			}

			if (title != "") {
				query += " and title='" + title + "'";
			}

			if (author != "") {
				query += " and author='" + author + "'";
			}
			query += " order by date";
		} else if (relationship.equals("或")) {
			query += " 1=2";
			if (!(category.equals("-1"))) {
				query += " or type_id=" + Integer.parseInt(category);
			}

			if (!(language.equals("----"))) {
				query += " or language='" + language + "'";
			}

			if (!date2.equals("0000-00-00")) {
				if (date.length() == 4)
					date += "-00-00";
				else if (date.length() == 7)
					date += "-00";
				if (date2.length() == 4)
					date2 += "-00-00";
				else if (date2.length() == 7)
					date2 += "-00";
				query += " or date between '" + date + "' and '" + date2 + "'";
			} else if (!(date.equals("0000-00-00"))) {
				if (date.length() == 10) {
					query += " or date='" + date + "'";
				} else {
					query += " or date like '" + date + "%'";
				}
			}

			if (origin != null) {
				query += " or origin='" + origin + "'";
			}

			if (title != "") {
				query += " or title='" + title + "'";
			}

			if (author != "") {
				query += " or author='" + author + "'";
			}
			query += " order by date";
		}
		logger.info(query);

		try {
			ResultSet resultSet = myQuery(query, offset, filepath);

			while (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				result.add(corpusBean);
			}
			resultSet.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	// 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏宄板闁跨喐鏋婚幏鐑芥晸鏉堝啫灏呴幏鐑芥晸閸欘偆娈戠涵閿嬪瑜版洟鏁撻弬銈嗗闁跨喐鏋婚幏锟�
	public int getNumberOfTotalCorpus() throws Exception {
		Mysql mysql = new Mysql();
		int count = 0;

		try {
			String sql = "select count(*) count from corpus_details order by id desc";
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (Exception e) {
			logger.info(e);
		}

		return (count);
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风兘鏁撻弬銈嗗缂佸棝鏁撻弬銈嗗閹拷
	public CorpusBean getCorpusBean(int id) throws Exception {
		Mysql mysql = new Mysql();

		try {
			String sql = "select * from corpus_details where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				return (corpusBean);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}

	// 閺嶏繝鏁撻弬銈嗗闁跨喐鏋婚幏閿嬪闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻惃鍡嫹
	public boolean checkCorpus(String title) {
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select url from corpus_details";
			logger.info(sql);

			resultSet = mysql.executeQuery(sql);

			System.out.println("&&&&&&&&&&&&&&&1 " + title);
			String tempurl = null;

			while (resultSet.next()) {
				tempurl = resultSet.getString("url");
				int begin = 0;
				begin = tempurl.lastIndexOf("/");

				tempurl = tempurl.substring(begin + 1, tempurl.length());

				if (tempurl.equals(title)) {
					return (false);
				}
			}

		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		try {
			Mysql mysql = new Mysql();

			String sql1 = "select url from temps ";

			logger.info(sql1);

			resultSet1 = mysql.executeQuery(sql1);
			System.out.println("&&&&&&&&&&&&&&&2 " + title);
			String tempurl = null;
			while (resultSet1.next()) {
				tempurl = resultSet1.getString("url");
				int begin = 0;
				begin = tempurl.lastIndexOf("/");

				tempurl = tempurl.substring(begin + 1, tempurl.length());

				if (tempurl.equals(title)) {
					return (false);
				}
			}

		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		return (true);
	}

	// 閺嶏繝鏁撻弬銈嗗闁跨喐鏋婚幏閿嬪闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨噦鎷�
	public boolean checkCorpusLanguage(String corpusLanguage) {
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_language where type='"
					+ corpusLanguage + "'";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				return (false);
			}
		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		return (true);
	}

	// 閺嶏繝鏁撻弬銈嗗闁跨喐鏋婚幏閿嬪闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻惃鍡氭彧閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閻偅鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻敓锟�
	public boolean checkCorpusNetMedia(String corpusNetMedia,
			String NetAbbreviation) {
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_origin_netmedia where type='"
					+ corpusNetMedia + "','" + NetAbbreviation + "'";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				return (false);
			}
		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		return (true);
	}

	// 閺嶏繝鏁撻弬銈嗗闁跨喐鏋婚幏閿嬪闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻惃鍡氭彧閹烽攱顤嬮柨鐔活敎閺傘倖瀚归柨鐔虹叓閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨噦鎷�
	public boolean checkCorpusPaperMedia(String corpusPaperMedia,
			String PaperAbbreviation) {
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_origin_papermedia where type='"
					+ corpusPaperMedia + "','" + PaperAbbreviation + "'";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				return (false);
			}
		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		return (true);
	}

	// 閺嶏繝鏁撻弬銈嗗闁跨喐鏋婚幏閿嬪闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻惃鍡氭彧閹风兘鏁撻弬銈嗗闁跨噦鎷�
	public boolean checkCorpusCategory(String corpusCategory) {
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from corpus_category where type='"
					+ corpusCategory + "'";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				return (false);
			}
		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		return (true);
	}

	public boolean checkCorpustag(String tag) {
		ResultSet resultSet = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from temps where tag='" + tag + "'";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				return (false);
			}
		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
		}
		return (true);
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喓娈曢敓锟�
	public boolean addCorpus(String origin, String title, String author,
			String date, String language, String url, String lastModifyTime,
			String size, int type_id, String tag, String type, int quality)
			throws Exception {
		title = title.trim();
		author = author.trim();
		try {
			Mysql mysql = new Mysql();
			String sql = "insert into corpus_details(origin,title,author,date,language,url,lastModifyTime,size,type_id,quality,test,remarks,tag,type) "
					+ "values('"
					+ origin
					+ "','"
					+ title
					+ "','"
					+ author
					+ "','"
					+ date
					+ "','"
					+ language
					+ "','"
					+ url
					+ "','"
					+ lastModifyTime
					+ "','"
					+ size
					+ "','"
					+ type_id
					+ "','"
					+ quality
					+ "','"
					+ 0 + "','','" + tag + "','" + type + "')";
			logger.info(sql);
			mysql.executeUpdate(sql);
			return (true);
		} catch (Exception ex) {
			logger.info(ex);
			throw ex;
		}
	}
	
	public boolean addCorpus2(String origin,String title,
			String date, String language, String url, String lastModifyTime,
			String size, int type_id, String tag, String type, int quality)
			throws Exception {

		try {
			Mysql mysql = new Mysql();
			String sql = "insert into corpus_details(origin,date,title,language,url,lastModifyTime,size,type_id,quality,test,remarks,tag,type) "
					+ "values('"
					+ origin
					+ "','"
					+ date
					+ "','"
					+ title
					+ "','"
					+ language
					+ "','"
					+ url
					+ "','"
					+ lastModifyTime
					+ "','"
					+ size
					+ "','"
					+ type_id
					+ "','"
					+ quality
					+ "','"
					+ 0 + "','','" + tag + "','" + type + "')";
			logger.info(sql);
			mysql.executeUpdate(sql);
			return (true);
		} catch (Exception ex) {
			logger.info(ex);
			throw ex;
		}
	}

	public boolean addCorpus1(String origin, String title, String author,
			String date, String language, String url, String lastModifyTime,
			String size, int type_id, int quality) throws Exception {
		title = title.trim();
		author = author.trim();

		try {
			Mysql mysql = new Mysql();

			String sql = "insert into corpus_details(origin,title,author,date,language,url,lastModifyTime,size,type_id,quality,test,remarks) values('"
					+ origin
					+ "','"
					+ title
					+ "','"
					+ author
					+ "','"
					+ date
					+ "','"
					+ language
					+ "','"
					+ url
					+ "','"
					+ lastModifyTime
					+ "','" + size + "'," + type_id + "," + quality + ",0,'')";
			logger.info(sql);
			System.out.println("ddddddddddddddddddddddddddddddddd");
			mysql.executeUpdate(sql);
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			return (true);
		} catch (Exception ex) {
			logger.info(ex);
			throw ex;
		} finally {
		}
	}

	// 閸掔娀鏁撻弬銈嗗閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗濠э拷
	public boolean deleteCorpus(int id) throws Exception {
		try {
			Mysql mysql = new Mysql();
			String sql = "delete from corpus_details where id=" + id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			return (true);
		} catch (Exception ex) {
			logger.info(ex);
			throw ex;
		} finally {
		}
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风rl
	public String getCorpusURL(int id) {
		String corpusURL = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select url from corpus_details where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusURL = resultSet.getString("url");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusURL == null) {
			corpusURL = "#";
		}

		return (corpusURL);
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风agegory闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨噦鎷�
	public String getCorpusCategory(int type_id) {
		String corpusCategory = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select type from corpus_category where type_id="
					+ type_id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusCategory = resultSet.getString("type");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusCategory == null) {
			corpusCategory = "#";
		}

		return (corpusCategory);
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风ag闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶憴鎺戝殩閹峰嘲鐒婇柨鐔告灮閹风兘鏁撻敓锟�
	public String getCorpustag2(int id) {
		String corpusCategorytag = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select tag from corpus_details where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusCategorytag = resultSet.getString("tag");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusCategorytag == null) {
			corpusCategorytag = "#";
		}

		return (corpusCategorytag);
	}

	public String getCorpustype2(int id) {
		String corpusCategorytag = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select type from corpus_details where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusCategorytag = resultSet.getString("type");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusCategorytag == null) {
			corpusCategorytag = "#";
		}

		return (corpusCategorytag);
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐宓庢潏鐐闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风ag闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶憴鎺戝殩閹峰嘲鐒婇柨鐔告灮閹风兘鏁撻敓锟�
	public String getCorpustag(int id) {
		String corpusCategorytag = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select tag from temps where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusCategorytag = resultSet.getString("tag");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusCategorytag == null) {
			corpusCategorytag = "#";
		}

		return (corpusCategorytag);
	}

	public String getCorpustype(int id) {
		String corpusCategorytag = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select type from temps where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusCategorytag = resultSet.getString("type");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusCategorytag == null) {
			corpusCategorytag = "#";
		}

		return (corpusCategorytag);
	}

	// 闁跨喓娼鹃棃鈺傚閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸鏉堝啰顣幏鐑芥晸閺傘倖瀚归柨鐕傛嫹

	public void changCat(int id, int type_id, String type) throws Exception {
		CorpusBean CB = getCorpusBean(id);
		String URL = CB.getUrl();
		String url = URL;
		String newFileName = "";
		String category = null;
		if (type.equals(".xml")) { // 单独对xml文件做处理
			
			String typeID = String.valueOf(type_id);
			Document doc = null;
			try {
				File file = new File(URL);
				SAXBuilder builder = new SAXBuilder();
				doc = builder.build(file);
				Element foo = doc.getRootElement();

				Mysql mysql = new Mysql();
				String sql = "select * from corpus_category where type_id="
						+ type_id;
				logger.info(sql);
				ResultSet resultSet = mysql.executeQuery(sql);

				if (resultSet.next()) {
					category = resultSet.getString("type");
				}
				mysql.close();

				foo.getChild("nameofclassification").setText(
						category.replaceAll("\\s", ""));
				foo.getChild("codeofclassification").setText(
						typeID.replaceAll("\\s", ""));

				// 文件名格式：类别代号_网站简拼_分类可信度_语言代号_日期_处理时段_相似序号.xml
				// DataOfClassify.fileSourName的格式：0_qhzygbw_0_1_2008-12-30_16_2.xml
				String[] nameParts = URL.split("_"); // 取得原来的XML文件名
				nameParts[1] = typeID; // 修改类别代码
				nameParts[3] = String.valueOf(2); // 修改可信度值

				for (int i = 1; i < nameParts.length; i++) {
					nameParts[i - 1] = nameParts[i];
				}

				for (int i = 0; i < nameParts.length - 1; i++) {
					newFileName += nameParts[i] + "_";
				}
				newFileName = newFileName.substring(0, newFileName
						.lastIndexOf("_"));
				foo.getChild("filename").setText(newFileName); // xml中的文件名也要改变
				System.out.println(newFileName + "----1");

				// 覆盖存储（存储在原来的位置, 文件名中有些字段的值变了）
				file.delete(); // 删除原文件
				XMLOutputter outputter = new XMLOutputter();
				String regex = "corpusUpload/.*?//.*?xml";
				Pattern pattern = Pattern.compile(regex);
				Matcher Match = pattern.matcher(url);
				URL = Match.replaceFirst("corpusUpload/" + category + "//"
						+ newFileName);// 新路径
				foo.getChild("url").setText(URL); // 修改文件里显示的路径
				outputter.output(doc, new FileOutputStream(URL));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			FileInputStream input = null;
			FileOutputStream output = null;

			Mysql mysql = new Mysql();
			String sql = "select * from corpus_category where type_id="
					+ type_id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				category = resultSet.getString("type");
			}
			mysql.close();
			
			String regex = "corpusUpload/.*?//";
			Pattern pattern = Pattern.compile(regex);
			Matcher Match = pattern.matcher(url);
			URL = Match.replaceFirst("corpusUpload/" + category + "//");// 新路径
			try {
				input = new FileInputStream(CB.getUrl());
				output = new FileOutputStream(URL);
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
				File file = new File(CB.getUrl());
				file.delete();

			} catch (Exception e) {
				System.out.println("File   can't   be   read!");
				System.out.println("该文件正在被使用或创建");
			}

		}

		try {
			Mysql mysql = new Mysql();
			String sql = "update corpus_details set type_id=" + type_id
					+ " where id=" + id;

			logger.info(sql);
			mysql.executeUpdate(sql);
			sql = "update corpus_details set url='" + URL + "'  where id=" + id; // "select * from user_down where user_name = '"
																					// +
																					// name
																					// +
																					// "'";
			logger.info(sql);
			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风柉鏌楅柨鐕傛嫹
	public void changTest(int id) {
		try {
			Mysql mysql = new Mysql();
			String sql = "update corpus_details set quality=3,test=1 where id="
					+ id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	// baocun
	public void saveRemarks(int id, String remarks) {
		try {
			Mysql mysql = new Mysql();
			String sql = "update corpus_details set remarks=\"" + remarks
					+ "\" where id=" + id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	// 闁跨喓鐓导娆愬闁跨喕绶濇潏鐐闁跨喐宓庢潏鐐
	public boolean addTemps(String origin, String title, String author,
			String date, String language, String url, String lastModifyTime,
			String size, int type_id, String tag) throws Exception {
		title = title.trim();
		author = author.trim();
		if (type_id != 0) {
			try {
				Mysql mysql = new Mysql();
				String sql = "insert into temps(origin,title,author,date,language,url,lastModifyTime,size,type_id,quality,test,remarks,tag) values('"
						+ origin
						+ "','"
						+ title
						+ "','"
						+ author
						+ "','"
						+ date
						+ "','"
						+ language
						+ "','"
						+ url
						+ "','"
						+ lastModifyTime
						+ "','"
						+ size
						+ "','"
						+ type_id
						+ "','" + 1 + "','" + 0 + "',' ','" + tag + "')";
				logger.info(sql);
				mysql.executeUpdate(sql);
				return (true);
			} catch (Exception ex) {
				logger.info(ex);
				throw ex;
			} finally {
			}
		} else {
			try {
				Mysql mysql = new Mysql();
				String sql = "insert into temps(origin,title,author,date,language,url,lastModifyTime,size,type_id,quality,test,remarks,tag) values('"
						+ origin
						+ "','"
						+ title
						+ "','"
						+ author
						+ "','"
						+ date
						+ "','"
						+ language
						+ "','"
						+ url
						+ "','"
						+ lastModifyTime
						+ "','"
						+ size
						+ "','"
						+ type_id
						+ "','" + 0 + "','" + 0 + "',' ','" + tag + "')";
				logger.info(sql);
				mysql.executeUpdate(sql);
				return (true);
			} catch (Exception ex) {
				logger.info(ex);
				throw ex;
			} finally {
			}
		}
	}

	// 闁跨喐鏋婚幏宄板絿閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风rl*****************************
	public String getCorpusURLTemps(int id) {
		String corpusURL = null;

		try {
			Mysql mysql = new Mysql();
			String sql = "select url from temps where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				corpusURL = resultSet.getString("url");
			}

			mysql.close();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		if (corpusURL == null) {
			corpusURL = "#";
		}

		return (corpusURL);
	}

	// 閸掔娀鏁撻弬銈嗗閹稿洭鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗濠э拷*********************************************
	public boolean deleteCorpusTemps(int id) throws Exception {
		try {
			Mysql mysql = new Mysql();
			String sql = "delete from temps where id=" + id;
			logger.info(sql);
			mysql.executeUpdate(sql);
			return (true);
		} catch (Exception ex) {
			logger.info(ex);
			throw ex;
		} finally {
		}
	}

	// 闁跨喐鏋婚幏宄板絿闁跨喐宓庢潏鐐闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱绨柨鐔告灮閹风兘鏁撻弬銈嗗缂佸棝鏁撻弬銈嗗閹拷*******************************************************
	public CorpusBean getCorpusBeanTemps(int id) throws Exception {
		Mysql mysql = new Mysql();

		try {
			String sql = "select * from temps where id=" + id;
			logger.info(sql);
			ResultSet resultSet = mysql.executeQuery(sql);

			if (resultSet.next()) {
				CorpusBean corpusBean = new CorpusBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet
								.getInt(12), resultSet.getString(13), resultSet
								.getString(14), resultSet.getString(15));
				return (corpusBean);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
}
