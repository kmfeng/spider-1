package mySpider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JavaBeans.DiseaseBean;

public class SourceManager {
	/*
	 * ����ҳԴ��������
	 */
	public static DiseaseBean getDiseaseFromRaw(String source) {
		// �����ȡ������
		DiseaseBean diseaseBean = new DiseaseBean();
		
		// ���Ŀ������ʽ�б�
		List<String> targetList = new ArrayList<String>();
		targetList.add("<title>(.*?)_.*?</title>");	// name
		targetList.add("<meta name=\"Description\" content=\"(.*?)...\"");	// description
		targetList.add("catalog=\"true\" name=\"STAT_ONCLICK_UNSUBMIT_CATALOG\" onclick=\"return false;\">(.*?)<");	// Ŀ¼
		
		// ��ϸ��Ϣ --> �޷���ȡȫ�����������ʽ��Ҫ����д//////////////////////////
//		targetList.add("(<span class=\"headline-content\">.*?</span></h2>)(<div class=\"para\">(.*?)</div>)");	
//		targetList.add("<span class=\"headline-content\">(.*?)</span></h2>");
		// ��ϸ��Ϣ̫���ӣ���Ҫ�ö������ֱ����
		List<String> CDREList = new ArrayList<String>();
		// ��һ����ӣ���ȡ��һ��Ŀ¼
		CDREList.add("<span class=\"headline-content\">(.*?)</span></h2>");
		// �ڶ�����ӣ���ȡ�ڶ���Ŀ¼
		CDREList.add("<span class=\"headline-content\">(.*?)</span></h3>");
		// ��������ӣ���ȡ��Ŀ¼
		CDREList.add("<span class=\"headline-content\">(.*?)</span></h[0-9]>");
		
		// ��ȡ name
		diseaseBean.name = findByRE(source, targetList.get(0));
		// ��ȡ description
		diseaseBean.description = findByRE(source, targetList.get(1));
		// ��ȡĿ¼
		diseaseBean.content = findContentByRE(source, targetList.get(2));
		// ��ȡ��ϸ��Ϣ����Ŀ¼һһ��Ӧ
		diseaseBean.contentDetail = findContentDetailByRE(source, CDREList);
		
		// ����˴�ץȡ��ҳ����Ϣ
//		System.out.println(diseaseBean);
		return diseaseBean;
	} 
	/*
	 * �����б����� contentDetail �Ĳ�ѯ
	 */
	public static List<String> findContentDetailByRE(String source, List<String> target) {
		List<String> resultList = new ArrayList<String>();
		// һ��Ŀ¼
		Pattern pattern1 = Pattern.compile(target.get(0));
		// ����Ŀ¼
		Pattern pattern2 = Pattern.compile(target.get(1)); 
		// ��Ŀ¼
		Pattern patternTotal = Pattern.compile(target.get(2));
		
		Matcher matcher = patternTotal.matcher(source);
		while (matcher.find()) {
			resultList.add(matcher.group(1) + "---");
			//System.out.println(matcher.groupCount());
		}
		return resultList;
	}
	
	/*
	 * �����б����� content(Ŀ¼) �Ĳ�ѯ
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
	 * ���� String������ name & description �Ĳ�ѯ
	 */
	public static String findByRE(String source, String target) {
		
		StringBuilder sb = new StringBuilder();
		
		// ������ʽ�ı����ʾ
		Pattern pattern = Pattern.compile(target);
		// �����������ַ���
		Matcher matcher = pattern.matcher(source);

		while (matcher.find()) {
			if (! matcher.group(1).equals("")) {	// ȥ������
				sb.append(matcher.group(1));
				//System.out.println("---> " + matcher.group(1));
			}
		}
		return sb.toString();
	}
}
