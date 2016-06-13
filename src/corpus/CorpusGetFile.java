package corpus;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorpusGetFile {

	/**
	 * 功能： 求目录及此目录下的所有文件的名称(绝对路径名) 参数： filePathName 必须是一个文件夹的路径,如：D:\html
	 * 返回值：(1) null 表示无任何文件或出错 (2) String[] 文件名数组(至少包含一个文件名)
	 */

	public String[] getAllFileName_2(String filePathName) {
		System.out.println(filePathName);
		/*
		String regex = "\\";
		Pattern pattern = Pattern.compile(regex);
		Matcher Match = pattern.matcher(filePathName);
		filePathName = Match.replaceAll("\\"+"\\");*/
		
		System.out.println(filePathName);
		String fileNamesStr = getFileNameStr(new File(filePathName));
		return fileNamesStr.split("\r\n");
	}

	private String getFileNameStr(File file) {
		// 判断文件是否存在
		if (file.exists()) {
			if (!file.isFile()) {
				File[] fl = file.listFiles();
				String tmp = "";
				for (File f : fl)
					tmp += getFileNameStr(f) + "\r\n";
				return tmp;
			} else {
				return file.getAbsolutePath();
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return null;
		}
	}
}
