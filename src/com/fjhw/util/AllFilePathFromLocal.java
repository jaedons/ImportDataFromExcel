package com.fjhw.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllFilePathFromLocal {
	  
	  
    public static List<String> getAllFiles(String filepath){
    	 File file = new File(filepath);  
         List<String> filelist = new ArrayList<String>();  
         List<String> list = getAllFiles(file, filelist );  
		return list;
    }
  
    static List<String> getAllFiles(File dir, List<String> filelist){
    	if(dir.isFile()){
    		filelist.add(dir.getAbsolutePath());
    		return filelist;
    	}
        File[] fs = dir.listFiles();  
        for (int i = 0; i < fs.length; i++) {  
            //ƥ��xls����xlsx��β���ļ�  
        	String path = fs[i].getAbsolutePath();
            if (path.matches(".*\\.xls")||path.matches(".*\\.xlsx")) {   
                filelist.add(path);  
            }
            //��Ϊ�ļ��У��͵���getAllFiles����  
            if (fs[i].isDirectory()) {  
                try {  
                    getAllFiles(fs[i], filelist);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return filelist;  
    }  

}
