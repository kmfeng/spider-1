package mySpider;

import java.util.ArrayList;
import java.util.List;

import JavaBeans.DiseaseBean;
import Utils.FileTools;

import common.Common;

public class firstSpider {

	public static void main(String[] args) {
		
		List<DiseaseBean> fukeDiseaseBeanList = new ArrayList<DiseaseBean>();
		List<DiseaseBean> pifuDiseaseBeanList = new ArrayList<DiseaseBean>();
		List<DiseaseBean> szDiseaseBeanList = new ArrayList<DiseaseBean>();
		List<DiseaseBean> sexDiseaseBeanList = new ArrayList<DiseaseBean>();
		
		// ������
		for (String url : Common.getFukeList()) {
			// ��ȡ URL ��ԭʼ��ҳ����
			String rawSource = DownLoader.getUrlContent(url);
			// ��ԭʼ��ҳ��ȡ DiseaseDetail �������ݷ����б�
			fukeDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		// ��Ƥ����
		for (String url : Common.getPifuList()) {
			String rawSource = DownLoader.getUrlContent(url);
			pifuDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		// ����ֳ��
		for (String url : Common.getSZList()) {
			String rawSource = DownLoader.getUrlContent(url);
			szDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		// ���Բ�
		for (String url : Common.getSexList()) {
			String rawSource = DownLoader.getUrlContent(url);
			sexDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		/*
		 * �����л��ķ�ʽ�洢
		 */
		FileTools.writeDiseaseBeanListToFile(fukeDiseaseBeanList, "fuke.txt");
		FileTools.writeDiseaseBeanListToFile(pifuDiseaseBeanList, "pifu.txt");
		FileTools.writeDiseaseBeanListToFile(szDiseaseBeanList, "sz.txt");
		FileTools.writeDiseaseBeanListToFile(sexDiseaseBeanList, "sex.txt");
		
		for (DiseaseBean d : fukeDiseaseBeanList) {
			System.out.println(d);
		}	
	}
}
