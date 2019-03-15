package com.xiezl.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xiezl.po.pojo.Locator;

public class UILibraryUtil {
	public static Map<String, Map<String, Locator>> pageLocatorsMap= new HashMap<String, Map<String,Locator>>();
	
	static {
		readUILibrary();
	}
	
	private static void readUILibrary(){
		//创建解析器
		SAXReader reader = new SAXReader();
		//获取document对象
		try {
			Document document = reader.read(UILibraryUtil.class.getResourceAsStream("/UILibrary.xml"));
			//获取根元素
			Element root = document.getRootElement();
			//通过根元素去获取其他子元素
			List<Element> pageList = root.elements("page");
			for (Element pageElement : pageList) {
				String pageClass = pageElement.attributeValue("class");
				List<Element> locatorList = pageElement.elements("locator");
				Map<String, Locator> pageLocatorMap = new HashMap<String, Locator>();
				for (Element locatorElement : locatorList) {
					String by = locatorElement.attributeValue("by");
					String value = locatorElement.attributeValue("value");
					String desc = locatorElement.attributeValue("desc");
					Locator locator = new Locator(by, value, desc);
					pageLocatorMap.put(desc, locator);
				}
				pageLocatorsMap.put(pageClass, pageLocatorMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		UILibraryUtil.readUILibrary();
		Set<String> keys = pageLocatorsMap.keySet();
		for (String key : keys) {
			System.out.println("==========");
			System.out.println("外层key="+key);
			Map<String, Locator> map = pageLocatorsMap.get(key);
			Set<String> descs = map.keySet();
			for (String desc : descs) {
				System.out.println("内层key="+desc);
				System.out.println("内层value="+map.get(desc));
			}
			System.out.println("------------------");
		}
		
	}
	
}
