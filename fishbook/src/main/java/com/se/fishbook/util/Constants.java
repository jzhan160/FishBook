package com.se.fishbook.util;

import java.util.HashMap;
import java.util.Map;

/**
 * constant
 */
public class Constants {
     public static Map<String,String> mapOptions = new HashMap<String,String>();
     public static final String CURRENT_USER = "user";
     public static final String SUCCESS = "success";
     public static final String ERROR = "error";
     
 	 public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");
 	 public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator");
 	 public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator");
 	 
     //public static final String AVATAR_PATH = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+SF_FILE_SEPARATOR+"images"+SF_FILE_SEPARATOR+"avatar";
     
     public static final String AVATAR_PATH  = "head";//photograph
}
