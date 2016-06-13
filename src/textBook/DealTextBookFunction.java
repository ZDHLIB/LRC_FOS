package textBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.sun.org.apache.xerces.internal.util.URI;

import corpus.CorpusBean;

import net.lrc.db.Mysql;

public class DealTextBookFunction {

	private static Logger logger = Logger.getLogger(CorpusBean.class);
	
	public List<String> getTextBookPublisher(){

		List<String> publisherList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct publisher from textbook";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				publisherList.add(resultSet.getString("publisher"));
			}
			return publisherList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return publisherList;
		
	}
	
	public List<String> getTextDetailPublisher(){

		List<String> publisherList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct publisher from textbook where publisher_j in (select publisher from texts)";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				publisherList.add(resultSet.getString("publisher"));
			}
			return publisherList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return publisherList;
		
	}
	
	public List<String> getTextBookPeriod(){

		List<String> gradeList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct period from textbook";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				gradeList.add(resultSet.getString("period"));
			}
			return gradeList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return gradeList;
	}

	public List<String> getTextDetailPeriod(){

		List<String> gradeList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct period from textbook where period_j in (select period from texts)";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				gradeList.add(resultSet.getString("period"));
			}
			return gradeList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return gradeList;
	}
	
	public List<String> getTextBookVolume(){

		List<String> volumeList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct volume from textbook";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				volumeList.add(resultSet.getString("volume"));
			}
			return volumeList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return volumeList;
	}
	

	public List<String> getTextDetailVolume(){

		List<String> volumeList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct volume from textbook where volume_j in (select volume from texts)";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				volumeList.add(resultSet.getString("volume"));
			}
			return volumeList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return volumeList;
	}
	
	public List<String> getTextBookLanguage(){

		List<String> languageList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct languages from textbook";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				languageList.add(resultSet.getString("languages"));
			}
			return languageList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return languageList;
	}
	

	public List<String> getTextDetailLanguage(){

		List<String> languageList = new ArrayList<String>();
		try{
			ResultSet resultSet = null;
			Mysql mysql = new Mysql();
			String sql = "select distinct languages from textbook where languages_j in (select languages from texts)";
			logger.info(sql);
			resultSet = mysql.executeQuery(sql);
			while (resultSet.next()) {
				languageList.add(resultSet.getString("languages"));
			}
			return languageList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return languageList;
	}
	
	public String [] parseAllPath(String finalAllPath, String mytext){
		try{
			String tempPath = "";
			tempPath = finalAllPath.replace(mytext, "");
			String [] allPath = tempPath.split(",");
			System.out.println("allPath:-------------------"+allPath[0]);
			return allPath;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String preparePath(String [] allPath, String fileName ){
		try{
			for( int i = 0; i < allPath.length; i++ ){
				if( new String(fileName.getBytes(),"utf-8").equals(new String(allPath[i].substring(allPath[i].lastIndexOf("\\")+1, allPath[i].length()).getBytes(), "utf-8" )) ){
					return allPath[i];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * copy file
	 */
	public void CopyFile(String oldPath, String newFoldPath, String fileName){
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			File newfolder = new File(newFoldPath);
			File newfile = new File(newFoldPath+"\\"+fileName);
			if( !newfolder.exists() ){
				newfolder.mkdirs();
			}
			if( !newfile.exists() ){
				newfile.createNewFile();
			}
			
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newfile);
				byte[] buffer = new byte[4444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}
	}
	
	/*
	 * 提取文件的路径和文件里的信息插入数据库中
	 */
	public void insertTextDetailtoSQL(String folderPath, String fileName ) {
		try{
			String filePath = (folderPath +"\\" + fileName).replace("\\", "\\\\");
			String title = "";
			String IDoftext = "";
			String author = "";
			String source = "";
			String translatefrom = "";
			String form = "";
			String typeoftext = "";
			String domain = "";
			
			folderPath = folderPath.substring(folderPath.indexOf("textDetail\\")+11,folderPath.length());
			System.out.println("folderPath:"+folderPath);
			String folderPathSplit [] = folderPath.split("\\\\");
			Mysql mysql = new Mysql();
			
			for( int i = 0; i < folderPathSplit.length; i++ ){
				System.out.print(folderPathSplit[i]+" ");
			}
			
			SAXBuilder builder = new SAXBuilder();
    		InputStream file = new FileInputStream(filePath);
    		Document doc = builder.build(file);// 获得文档对象
    		Element root = doc.getRootElement();// 获得根节点
			title = root.getChildText("title");
			IDoftext = root.getChildText("IDoftext");
			author = root.getChildText("author");
			source = root.getChildText("source");
			translatefrom = root.getChildText("translatefrom");
			form = root.getChildText("form");
			typeoftext = root.getChildText("typeoftext");
			domain = root.getChildText("domain");
			
			String sql = "insert into texts(title,IDoftext," +
			"author,sourcefrom,translatefrom,form," +
			"typeoftext,domain,content,languages," +
			"textstandard,classtype,publisher,subject," +
			"period,characters,volume,tag) values('" + 
			title + "','"+ 
			IDoftext +"','"+
			author +"','" +
			source +"','" +
			translatefrom +"','" +
			form +"','" +
			typeoftext +"','" +
			domain +"','" +
			filePath +"','" +
			folderPathSplit[0] +"','" +
			folderPathSplit[1] +"','" +
			folderPathSplit[2] +"','" +
			folderPathSplit[3] +"','" +
			folderPathSplit[4] +"','" +
			folderPathSplit[5] +"','" +
			folderPathSplit[6] +"','" +
			folderPathSplit[7] +"','" +
			folderPathSplit[8] +"')";
			
			System.out.println(sql);
			logger.info(sql);
			mysql.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
}
