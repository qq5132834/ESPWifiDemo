package BaseInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;


public class SDCardUtils {
	 
	private String path = "abc/";
	private String fileName = "smartLed.txt";
	
	public void SDCardUtils(){ 
		createFile();
		Log.e("SDCardUtils", fileName);
	}
	
	/**
	 * 文件中另取一行写入数据
	 * */
	public boolean writeFile(String ip){
		boolean st = true;
		try {
			createFile();
			List<String> list = this.readFile();
			Log.e("写入文件", ip);
			if(list.contains(ip)){
				return st;
			}
			if(list.size()>5){
				this.delFile();
			}
			this.createFile();
			ip = ip + "\r\n";
			FileOutputStream fs = new FileOutputStream(fileName,true);
			fs.write(ip.getBytes());
			fs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			st = false;
		}
		return st;
	}
	
	/**
	 * 文件按行读取文件中的数据
	 * */
	public List<String> readFile(){
		List<String> list = new ArrayList<String>();
		try {
			if(new File(fileName).exists()){
				Log.e("文件中获取到的IP地址为：", "yes");
			}else{
				Log.e("文件中获取到的IP地址为：", "no");
			}
			FileReader fr;
			fr = new FileReader("../"+fileName);
			BufferedReader br=new BufferedReader(fr);
	        String line="";
	        while ((line=br.readLine())!=null) {
	            //System.out.println(line);
	        	Log.e("文件中获取到的IP地址为：", line);
	        	list.add(line);
	        }
	        br.close();
	        fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} 
	
	/**
	 * 删除文件
	 * */
	private boolean delFile(){
		boolean st = false;
		File file = new File(fileName);
		if(file.exists()){
			st = file.delete();
			createFile();
		}
		return st;
	}
	
	private boolean createFile(){
		boolean st = true;
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		file = null;
		file = new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				st = false;
			}
		}
		return st;
	}
	
}