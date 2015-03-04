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
		// ��ŷ��ص� DiseaseBean ����
		DiseaseBean db = new DiseaseBean();
				
		// Ҫ����ҳ��Ĳ�ͬ��ѡ��ͬ�Ľ�������
		if (isOrdinaryHtmlPage(html)) {	// ��ͨҳ��
			db.name = getName(html);
			db.description = getDesc(html);
			db.content = getContent(html);
			db.contentDetail = getContentDetail(html);
		} else {	// Ȩ��ҽѧ���մ�������ƽ̨ҳ��
			db.name = getNameSpecial(html);
			db.description = getDescSpecial(html);
			db.content = getContent(html);
			db.contentDetail = getContentDetail(html);
		}

		return db;
	}
	/*
	 * �ж� html ҳ�����𣬸���Դ�����Ƿ��� side-anchor �ַ�����û�е�����ͨҳ��
	 * 1. ��ͨҳ�� --> true
	 * 2. Ȩ��ҽѧ���մ�������ƽ̨ҳ�� --> false
	 */
	private static boolean isOrdinaryHtmlPage(String html) {
		return ! html.contains("side-anchor");
	}

	/*
	 * ���� Name
	 */
	private static String getName(String html) {
		String name = "";
		Document doc = Jsoup.parse(html);
//		Element nameEle = doc.getElementsByAttributeValue("class", "lemmaTitle").first();
		// ��Ϊ html �Ĳ��淶����һ�� lemmaTitle ���þ��� class ��ֵ������ֻҪ���� lemmaTitle ������Ŀ
		Element nameEle = doc.select("[class*=lemmaTitle]").first();
		if (nameEle != null) {
			// ��Ϊ���� lemmaTitle ��String���ܲ�ֻ����Ŀ�������ÿո� spilt ֮��ȡ�����ַ���
			name = nameEle.text().split(" ")[0];
		}
		
		System.out.println("getName +++>>> " + name);
		return name;
	}
	private static String getNameSpecial(String html) {
		String name = "";
		Document doc = Jsoup.parse(html);
//		Element nameEle = doc.getElementsByAttributeValue("class", "lemmaTitle").first();
		// ��Ϊ html �Ĳ��淶����һ�� lemmaTitle ���þ��� class ��ֵ������ֻҪ���� lemmaTitle ������Ŀ
		Element nameEle = doc.select("[class*=lemmaTitle]").first();
		if (nameEle != null) {
			// ��Ϊ���� lemmaTitle ��String���ܲ�ֻ����Ŀ�������ÿո� spilt ֮��ȡ�����ַ���
			name = nameEle.text().split(" ")[0];
		}
		
		System.out.println("getNameSpecial +++>>> " + name);
		return name;
	}

	/*
	 * ���� Desc
	 */
	private static String getDesc(String html) {
		String desc = "";
		Document doc = Jsoup.parse(html);
//		Element ele = doc.getElementById("posterCon");
//		if (ele == null) {
//			ele = doc.getElementsByAttributeValue("class", "desc").first();
//		}
		
		Element descEle = doc.getElementsByAttributeValue("class", "card-summary-content").first();
		desc = descEle.text();
		
		System.out.println("getDesc +++>>> " + desc);
		return desc;
	}
	private static String getDescSpecial(String html) {
		String desc = "";
		Document doc = Jsoup.parse(html);
//		Element ele = doc.getElementById("posterCon");
//		if (ele == null) {
//			ele = doc.getElementsByAttributeValue("class", "desc").first();
//		}
		
		Element descEle = doc.getElementsByAttributeValue("class", "desc").first();
		if (descEle == null) {
			descEle = doc.getElementsByAttributeValue("class", "card-summary-content").first();
		}
		desc = descEle.text();
		
		System.out.println("getDescSpecial +++>>> " + desc);
		return desc;
	}
	
	
	
	/*
	 * ����Ŀ¼
	 */
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

	/*
	 * ����ÿ��Ŀ¼���������
	 */
	private static List<String> getContentDetail(String html) {
		// ���ÿ��Ŀ¼����������ݣ��б�ĳ�����Ŀ¼����Ŀ���
		List<String> contentDetail = new ArrayList<String>();

		Document doc = Jsoup.parse(html);
		// ���� DOM ��
		Element all = doc.getElementById("lemmaContent-0");
		// Ŀ¼
		Elements content = all.getElementsByAttributeValue("class","headline-1");
		List<Integer> index = new ArrayList<Integer>();

		// ���е� para ����
		Elements details = all.getElementsByAttributeValue("class", "para");
		// System.out.println("\n");

		// ��Ŀ¼�ڵ��� all �е���ż�¼
		for (Element e : content) {
			index.add(e.siblingIndex());
			// System.out.println(all.childNodeSize() + " --> " +
			// e.siblingIndex());
		}
		index.add(all.childNodeSize());
		// System.out.println("\n");

		int indexLength = index.size();
		// ���ÿ��Ŀ¼�������ϸ���ݵ���Ŀ
		List<Integer> sizeEachList = new ArrayList<Integer>();
		for (int j = 1; j < indexLength; ++j) {
			sizeEachList.add(index.get(j) - index.get(j - 1) - 2);
			System.out.println(index.get(j) - index.get(j - 1) - 2);
		}
		System.out.println("size --> " + sizeEachList.size() + "\n");

		// ��ͷ��� 0
		// sizeEachList.add(0, 0); 
		// ĩβ��� detail ������
		sizeEachList.add(sizeEachList.size(), details.size() - 1); 
		int l = sizeEachList.size();

		int jj = 0;
		for (int i = 0; i < l - 1; ++i) {
//			System.out.println("----------------------------> " + sizeEachList.get(i));

			// sb ��ŵ���Ŀ¼���µ�����
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < sizeEachList.get(i); ++j) {
				if (j+jj >= details.size()) {
					break;
				}
				sb.append(details.get(j + jj).text() + '\n');
				System.out.println("getContentDetail +++>>> " + "�� " + i + "�� ----> " + details.get(j + jj).text());
			}
			jj = jj + sizeEachList.get(i);
			// ����Ŀ¼���µ����ݴ��� List
			contentDetail.add(sb.toString());
//			System.out.println("contentDetail.get(" + i + ") --> " + contentDetail.get(i));
		}

		return contentDetail;
	}
}
