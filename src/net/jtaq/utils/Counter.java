package net.jtaq.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class Counter extends Object {
private String currentRecord = null;//保存文本的变量
private BufferedReader file; //BufferedReader对象，用于读取文件数据
private String path;//文件完整路径名
public Counter() {
}
 

public String ReadFile() throws FileNotFoundException
{
   File temp=new File(path);
	if(!temp.exists()) {
			try {
//		创建PrintWriter对象，用于写入数据到文件中
		PrintWriter pw = new PrintWriter(new FileOutputStream(path));
//		用文本格式打印整数Writestr
		pw.println(1);
//		清除PrintWriter对象
		pw.close();
		} catch(IOException e) {
//		错误处理
		System.out.println("创建文件错误"+e.getMessage());
		} 
	}
//创建新的BufferedReadelr对象
file = new BufferedReader(new FileReader(path));
String returnStr =null;
try
{
//读取一行数据并保存到currentRecord变量中
currentRecord = file.readLine();
}
catch (IOException e)
{//错误处理
System.out.println("读取数据错误.");
}
if (currentRecord == null)
//如果文件为空
returnStr = "没有任何记录";
else
{//文件不为空
returnStr =currentRecord;
}
//返回读取文件的数据
return returnStr;
}

public void WriteFile(String counter) throws FileNotFoundException
{
 
//将counter转换为int类型并加一
int Writestr = Integer.parseInt(counter)+1;
try {
//创建PrintWriter对象，用于写入数据到文件中
PrintWriter pw = new PrintWriter(new FileOutputStream(path));
//用文本格式打印整数Writestr
pw.println(Writestr);
//清除PrintWriter对象
pw.close();
} catch(IOException e) {
//错误处理
System.out.println("写入文件错误"+e.getMessage());
}
}


/**
 * @return the file
 */
public BufferedReader getFile() {
	return file;
}


/**
 * @param file the file to set
 */
public void setFile(BufferedReader file) {
	this.file = file;
}


/**
 * @return the path
 */
public String getPath() {
	return path;
}


/**
 * @param path the path to set
 */
public void setPath(String path) {
	this.path = path;
}

}  