package com.xiezl.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static Properties urlProperties;
	static {
		if (urlProperties==null) {
			loadUrlProperties();
		}
	}
	
	
	/**
	 * 初始化url.properties配置文件
	 */
	public static void loadUrlProperties(){
		urlProperties = new Properties();
		try {
			urlProperties.load(PropertiesUtil.class.getResourceAsStream("/url.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(PropertiesUtil.urlProperties.getProperty("login.url"));
	}
}
