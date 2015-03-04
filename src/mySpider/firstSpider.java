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
		
		// 爬妇科
		for (String url : Common.getFukeList()) {
			// 获取 URL 的原始网页内容
			String rawSource = DownLoader.getUrlContent(url);
			// 从原始网页获取 DiseaseDetail 对象内容放入列表
			fukeDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		// 爬皮肤科
		for (String url : Common.getPifuList()) {
			String rawSource = DownLoader.getUrlContent(url);
			pifuDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		// 爬生殖科
		for (String url : Common.getSZList()) {
			String rawSource = DownLoader.getUrlContent(url);
			szDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		// 爬性病
		for (String url : Common.getSexList()) {
			String rawSource = DownLoader.getUrlContent(url);
			sexDiseaseBeanList.add(SourceManagerByJsoup.getDiseaseFromRaw(rawSource));
		}
		
		/*
		 * 以序列化的方式存储
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
