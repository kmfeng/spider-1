package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Common {
	
	/*
	 * urlList 存放需要爬取的连接的列表
	 */
	private static List<String> fukeList = new ArrayList<String>(
			Arrays.asList( 
					"http://baike.baidu.com/view/28164.htm",
					"http://baike.baidu.com/view/1197445.htm",
					"http://baike.baidu.com/view/2968900.htm",
					"http://baike.baidu.com/view/3216125.htm",
					"http://baike.baidu.com/view/136705.htm",
					"http://baike.baidu.com/link?url=FCrR2luU2PMFRT4R-uiir_EPmqumuKh2Eb1mzeOrl4s932l1zAXVzdSVFuy-rQLixDUpSl9lJI3M51g8hjaAW_#2"
					));
	
	private static List<String> pifuList = new ArrayList<String>(
			Arrays.asList("http://baike.baidu.com/view/771426.htm",
					"http://baike.baidu.com/view/3381227.htm",
					"http://baike.baidu.com/view/138234.htm?fromtitle=%E8%B6%B3%E8%97%93&fromid=4499545&type=syn",
					"http://baike.baidu.com/view/171760.htm?fromtitle=%E6%89%8B%E8%97%93&fromid=9685701&type=syn",
					"http://baike.baidu.com/view/1001.htm",
					"http://baike.baidu.com/view/4318139.htm"));
	
	private static List<String> szList = new ArrayList<String>(
			Arrays.asList("http://baike.baidu.com/view/102216.htm", // 阴道炎
					"http://baike.baidu.com/view/826362.htm", // 阴囊湿疹
					"http://baike.baidu.com/view/289204.htm?fromtitle=%E8%82%9B%E9%97%A8%E6%B9%BF%E7%96%B9&fromid=799967&type=syn",	// 肛门湿疹
					"http://baike.baidu.com/view/865301.htm",	// 龟头炎
					"http://baike.baidu.com/view/81718.htm"	//尿道炎
					));
	
	private static List<String> sexList = new ArrayList<String>(
			Arrays.asList("http://baike.baidu.com/view/26104.htm",	// 淋病
					"http://baike.baidu.com/view/23940.htm"		// 梅毒
					));
	
	
	/*
	 * 对外提供的方法
	 */
	public static List<String> getFukeList() {
		return fukeList;
	}
	
	public static List<String> getPifuList() {
		return pifuList;
	}
	
	public static List<String> getSZList() {
		return szList;
	}
	
	public static List<String> getSexList() {
		return sexList;
	}
}
