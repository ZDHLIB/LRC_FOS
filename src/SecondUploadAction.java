import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import corpus.CorpusBean;
public class SecondUploadAction extends ActionSupport {
	private String Filedata;
	private String Filename;
	public String execute() throws Exception {
		
		
		CorpusBean corpusBean = new CorpusBean();
		//System.out.println("path is ： "+getFiledata());
		if (!corpusBean.checkCorpus(getFilename())){
			return ERROR;
		}
			
		File directory = new File("corpusUpload");
		if (!directory.exists())
			directory.mkdir();		
	
		FileInputStream fis = new FileInputStream(getFiledata());
		double corpusSize = (double) ((fis.available()) / (double) (1024));
		NumberFormat numberFormat = NumberFormat.getNumberInstance();// ����һ����ݸ�ʽ����
		numberFormat.setMaximumFractionDigits(2);// ����С��������ʾ����ĿΪ2
		String tempSize = numberFormat.format(corpusSize);// ����numberFormat��format����,��ʽ��corpusSize,����һ���ַ����
		String size = tempSize + "K";
		FileOutputStream fos = new FileOutputStream(directory + File.separator
				+ getFilename());
		byte[] buffer = new byte[1024];
		int length;
		while ((length = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, length);
		}
		fis.close();
		fos.close();

		
		
		System.out.println("come to action");
		return SUCCESS;
	}
	public String getFiledata() {
		return Filedata;
	}
	public void setFiledata(String filedata) {
		Filedata = filedata;
	}
	public String getFilename() {
		return Filename;
	}
	public void setFilename(String filename) {
		Filename = filename;
	}
}
