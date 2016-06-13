import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import corpus.CorpusBean;
public class UploadAction extends ActionSupport {
	private String Filedata;
	private String Filename;
	public String execute() throws Exception {
		/*
		CorpusBean corpusBean = new CorpusBean();
		if (!corpusBean.checkCorpus(getFilename())){
			return ERROR;
		}
   	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lastModifyTime = null;
        lastModifyTime = simpleDateFormat.format(new Date());
		String leibie = getFilename().substring(0, 2);
		int a =Integer.valueOf(leibie.substring(0));
		int b =Integer.valueOf(leibie.substring(1));
		int c =Integer.valueOf(leibie);
		if(a==0){
			c = b;
		}else {
			c = c;
		}		
		String corpusStoreFileRealPath ="corpusUpload" + "/" + corpusBean.getCorpusCategory(c);
		
		
		File directory = new File("corpusUpload");
		if (!directory.exists())
			directory.mkdir();		
		File directory1 = new File(corpusStoreFileRealPath);
		if (!directory1.exists())
			directory1.mkdir();
    	
		String temp = getFilename().substring(getFilename().indexOf("-")+1, getFilename().length());
		
		
		
		if(temp.indexOf("-") != -1){		
			FileInputStream fis = new FileInputStream(getFiledata());
			double corpusSize = (double) ((fis.available()) / (double) (1024));
	    	NumberFormat numberFormat = NumberFormat.getNumberInstance();// ����һ����ݸ�ʽ����
	    	numberFormat.setMaximumFractionDigits(2);// ����С��������ʾ����ĿΪ2
	    	String tempSize = numberFormat.format(corpusSize);// ����numberFormat��format����,��ʽ��corpusSize,����һ���ַ����
	    	String size = tempSize + "K";
			FileOutputStream fos = new FileOutputStream(directory1 + File.separator
					+ getFilename());
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			fis.close();
			fos.close();
			char laiyuan = getFilename().substring(3, 4).charAt(0);
			String time = getFilename().substring(6, 16);			
			String ly = null;
			switch(laiyuan){
				case '1': ly = "�ຣ����㲥��";break;
				case '2': ly = "�»�������Ƶ��";break;
				case '3': ly = "�й������ͨ";break;
				case '4': ly = "�й����������İ�";break;
				case '5': ly = "�й�������(��������İ�)";break;
				case '6': ly = "�й�����������";break;
				case '7': ly = "�й�������Ϣ����";break;
				default: ly = "δ֪";break;	
			}
			corpusStoreFileRealPath =directory1.toURL().toString()+"/"+getFilename();
			corpusStoreFileRealPath = corpusStoreFileRealPath.substring(corpusStoreFileRealPath.indexOf("/")+1, corpusStoreFileRealPath.length());
			corpusBean.addCorpus1(ly, getFilename(), "", time,
					"",corpusStoreFileRealPath, lastModifyTime, size, c,1);
			
		}else{
			FileInputStream fis = new FileInputStream(getFiledata());
			double corpusSize = (double) ((fis.available()) / (double) (1024));
	    	NumberFormat numberFormat = NumberFormat.getNumberInstance();// ����һ����ݸ�ʽ����
	    	numberFormat.setMaximumFractionDigits(2);// ����С��������ʾ����ĿΪ2
	    	String tempSize = numberFormat.format(corpusSize);// ����numberFormat��format����,��ʽ��corpusSize,����һ���ַ����
	    	String size = tempSize + "K";
			FileOutputStream fos = new FileOutputStream(directory1 + File.separator
					+ getFilename());
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			fis.close();
			fos.close();
			
			corpusStoreFileRealPath = directory1.toURL().toString()+"/"+getFilename();
			corpusStoreFileRealPath = corpusStoreFileRealPath.substring(corpusStoreFileRealPath.indexOf("/")+1, corpusStoreFileRealPath.length());
			corpusBean.addCorpus1("", getFilename(), "", "",
					"",corpusStoreFileRealPath, lastModifyTime, size, c,1);
			
		}	
		*/
		System.out.println("come to UploadAction");
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
