package de.csw.fub.util;

import java.io.File;

import de.csw.fub.Task;

public class ProjectUtil {
	 public static String getProjectDir() {
			String parentFile = Task.class.getResource("/").getFile()
					.toString();
			File serverDir = (new File(parentFile)).getParentFile().getParentFile();
			parentFile = serverDir.getAbsolutePath().replaceAll("%20", " ");
			return (new StringBuilder(String.valueOf(parentFile.replaceAll("%20",
					" ")))).append(File.separator).toString();
		}
	 
	 public static String getHTSDBPath(){
		 return ProjectUtil.getProjectDir()+"conf"+File.separator+"hts.xml";
	 }
	 
	 
	 public static String getWFSDBPath(){
		  return ProjectUtil.getProjectDir()+"conf"+File.separator+"workflows.xml";
	 }

//	public static String getXSLTPath() {
//		// TODO Auto-generated method stub
//		 return ProjectUtil.getProjectDir()+"conf"+File.separator+"provaInterface2HTML.xslt";
//	}

	public static String getExceptionsPath() {
		// TODO Auto-generated method stub
		return ProjectUtil.getProjectDir()+"conf"+File.separator+"exceptions.xml";
	}
	
	public static String getConfigPath() {
		// TODO Auto-generated method stub
		return ProjectUtil.getProjectDir()+"conf"+File.separator+"server.xml";
	}
	
}
