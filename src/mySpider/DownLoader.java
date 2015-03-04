package mySpider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DownLoader {
	/*
	 * ���ݴ���� URL������ URL ������
	 */
	public static String getUrlContent(String urlStr) {
		// ��Ҫ���ʵ�����
		String url = urlStr;
		// �洢��ҳ����
		String result = "";
		// �����ַ�������
		BufferedReader in = null;

		try {
			// String --> URL
			URL realUrl = new URL(url);
			// ��������
			URLConnection connection = realUrl.openConnection();
			// ʵ������
			connection.connect();
			// ��ʼ�� BufferedReader ��ȡ URL ����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8")); // �˴�ע�� UTF-8 ��������
			// ��ʱ��ȡץȡ��ÿһ�е�����
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally �йر�������
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
