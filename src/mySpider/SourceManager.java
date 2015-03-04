package mySpider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JavaBeans.DiseaseBean;

public class SourceManager {
	/*
	 * 对网页源码正则处理
	 */
	public static DiseaseBean getDiseaseFromRaw(String source) {
		// 存放爬取的内容
		DiseaseBean diseaseBean = new DiseaseBean();
		
		// 存放目标正则式列表
		List<String> targetList = new ArrayList<String>();
		targetList.add("<title>(.*?)_.*?</title>");	// name
		targetList.add("<meta name=\"Description\" content=\"(.*?)...\"");	// description
		targetList.add("catalog=\"true\" name=\"STAT_ONCLICK_UNSUBMIT_CATALOG\" onclick=\"return false;\">(.*?)<");	// 目录
		
		// 详细信息 --> 无法获取全部，这个正则式需要从新写//////////////////////////
//		targetList.add("(<span class=\"headline-content\">.*?</span></h2>)(<div class=\"para\">(.*?)</div>)");	
//		targetList.add("<span class=\"headline-content\">(.*?)</span></h2>");
		// 详细信息太复杂，需要用多个正则分别查找
		List<String> CDREList = new ArrayList<String>();
		// 第一个添加：提取第一层目录
		CDREList.add("<span class=\"headline-content\">(.*?)</span></h2>");
		// 第二个添加：提取第二层目录
		CDREList.add("<span class=\"headline-content\">(.*?)</span></h3>");
		// 第三个添加：提取总目录
		CDREList.add("<span class=\"headline-content\">(.*?)</span></h[0-9]>");
		
		// 提取 name
		diseaseBean.name = findByRE(source, targetList.get(0));
		// 提取 description
		diseaseBean.description = findByRE(source, targetList.get(1));
		// 提取目录
		diseaseBean.content = findContentByRE(source, targetList.get(2));
		// 提取详细信息，和目录一一对应
		diseaseBean.contentDetail = findContentDetailByRE(source, CDREList);
		
		// 输出此次抓取的页面信息
//		System.out.println(diseaseBean);
		return diseaseBean;
	} 
	/*
	 * 返回列表，用于 contentDetail 的查询
	 */
	public static List<String> findContentDetailByRE(String source, List<String> target) {
		List<String> resultList = new ArrayList<String>();
		// 一层目录
		Pattern pattern1 = Pattern.compile(target.get(0));
		// 二层目录
		Pattern pattern2 = Pattern.compile(target.get(1)); 
		// 总目录
		Pattern patternTotal = Pattern.compile(target.get(2));
		
		Matcher matcher = patternTotal.matcher(source);
		while (matcher.find()) {
			resultList.add(matcher.group(1) + "---");
			//System.out.println(matcher.groupCount());
		}
		return resultList;
	}
	
	/*
	 * 返回列表，用于 content(目录) 的查询
	 */
	public static List<String> findContentByRE(String source, String target) {
		List<String> resultList = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(target);
		Matcher matcher = pattern.matcher(source);
		
		while (matcher.find()) {
			resultList.add(matcher.group(1));
			//System.out.println(matcher.groupCount());
		}
		
		return resultList;
	}
	
	/*
	 * 返回 String，用于 name & description 的查询
	 */
	public static String findByRE(String source, String target) {
		
		StringBuilder sb = new StringBuilder();
		
		// 正则表达式的编译表示
		Pattern pattern = Pattern.compile(target);
		// 根据正则处理字符串
		Matcher matcher = pattern.matcher(source);

		while (matcher.find()) {
			if (! matcher.group(1).equals("")) {	// 去掉空行
				sb.append(matcher.group(1));
				//System.out.println("---> " + matcher.group(1));
			}
		}
		return sb.toString();
	}
}
