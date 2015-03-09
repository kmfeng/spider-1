package mySpider;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import JavaBeans.DiseaseBean;

public class SourceManagerByJsoup {
	public static DiseaseBean getDiseaseFromRaw(String html) {
		// DiseaseBean 用于存储解析得来的 html 内容
		DiseaseBean db = new DiseaseBean(); 
				
		// 根据网页格式的不同，name description 使用不同的解析函数
		if (isOrdinaryHtmlPage(html)) {	// 普通页面
			db.setName(getName(html));
			db.setDescription(getDesc(html));
			db.setContent(getContent(html));
			db.setContentDetail(getContentDetail(html));
		} else {	// 权威医学页面
			db.setName(getNameSpecial(html));
			db.setDescription(getDescSpecial(html));
			db.setContent(getContent(html));
			db.setContentDetail(getContentDetail(html));
		}

		return db;
	}
	/*
	 * 1. 普通 --> true
	 * 2. 权威医学 --> false
	 */
	private static boolean isOrdinaryHtmlPage(String html) {
		return ! html.contains("side-anchor");
	}


	private static String getName(String html) {
		String name = "";
		Document doc = Jsoup.parse(html);
//		Element nameEle = doc.getElementsByAttributeValue("class", "lemmaTitle").first();
		Element nameEle = doc.select("[class*=lemmaTitle]").first();
		if (nameEle != null) {
			name = nameEle.text().split(" ")[0];
		}
		
		System.out.println("getName +++>>> " + name);
		return name;
	}
	private static String getNameSpecial(String html) {
		String name = "";
		Document doc = Jsoup.parse(html);
//		Element nameEle = doc.getElementsByAttributeValue("class", "lemmaTitle").first();
		Element nameEle = doc.select("[class*=lemmaTitle]").first();
		if (nameEle != null) {
			name = nameEle.text().split(" ")[0];
		}
		
		System.out.println("getNameSpecial +++>>> " + name);
		return name;
	}

	private static String getDesc(String html) {
		String desc = "";
		Document doc = Jsoup.parse(html);
		
		Element descEle = doc.getElementsByAttributeValue("class", "card-summary-content").first();
		desc = descEle.text();
		
		System.out.println("getDesc +++>>> " + desc);
		return desc;
	}
	private static String getDescSpecial(String html) {
		String desc = "";
		Document doc = Jsoup.parse(html);
		
		Element descEle = doc.getElementsByAttributeValue("class", "desc").first();
		if (descEle == null) {
			descEle = doc.getElementsByAttributeValue("class", "card-summary-content").first();
		}
		desc = descEle.text();
		
		System.out.println("getDescSpecial +++>>> " + desc);
		return desc;
	}
	
	private static List<String> getContent(String html) {
		List<String> content = new ArrayList<String>();

		Document doc = Jsoup.parse(html);
		Element ele = doc.getElementById("catalog-holder-0");
//		Elements contentEle = ele.getElementsByAttributeValue("class", "lie4");
//		Elements contentEle = ele.select("[class*=lie]");
		Elements contentEle = ele.select("[class*=z-catalog]");
		for (Element e : contentEle) {
			content.add(e.text());
			System.out.println("getContent +++>>> " + e.text());
		}
		return content;
	}

	private static List<String> getContentDetail(String html) {
		// 存放每个目录项下面的内容，列表的长度与目录的数目相等
		List<String> contentDetail = new ArrayList<String>();

		Document doc = Jsoup.parse(html);
		//  整个 DOM 树
		Element all = doc.getElementById("lemmaContent-0");
		// 目录
		Elements content = all.getElementsByAttributeValue("class","headline-1");
		List<Integer> index = new ArrayList<Integer>();

		// 所有的 para 内容
		Elements details = all.getElementsByAttributeValue("class", "para");
		// System.out.println("\n");

		// 将目录节点在 all 中的序号记录
		for (Element e : content) {
			index.add(e.siblingIndex());
			// System.out.println(all.childNodeSize() + " --> " +
			// e.siblingIndex());
		}
		index.add(all.childNodeSize());
		// System.out.println("\n");

		int indexLength = index.size();
		//  算出每个目录下面的详细内容的数目
		List<Integer> sizeEachList = new ArrayList<Integer>();
		for (int j = 1; j < indexLength; ++j) {
			sizeEachList.add(index.get(j) - index.get(j - 1) - 2);
			System.out.println(index.get(j) - index.get(j - 1) - 2);
		}
		System.out.println("size --> " + sizeEachList.size() + "\n");

		// 开头添加 0
		// sizeEachList.add(0, 0); 
		// 末尾添加 detail 的容量
		sizeEachList.add(sizeEachList.size(), details.size() - 1); 
		int l = sizeEachList.size();

		int jj = 0;
		for (int i = 0; i < l - 1; ++i) {
//			System.out.println("----------------------------> " + sizeEachList.get(i));

			// sb 存放单个目录项下的内容
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < sizeEachList.get(i); ++j) {
				if (j+jj >= details.size()) {
					break;
				}
				sb.append(details.get(j + jj).text() + '\n');
				System.out.println("getContentDetail +++>>> " + "锟斤拷 " + i + "锟斤拷 ----> " + details.get(j + jj).text());
			}
			jj = jj + sizeEachList.get(i);
			// 将该目录项下的内容存入 List
			contentDetail.add(sb.toString());
//			System.out.println("contentDetail.get(" + i + ") --> " + contentDetail.get(i));
		}

		return contentDetail;
	}
}
