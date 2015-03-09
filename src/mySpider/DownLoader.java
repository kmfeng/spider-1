package mySpider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DownLoader {
	/*
	 * 根据传入的 URL，下载 URL 的内容
	 */
	public static String getUrlContent(String urlStr) {
		// 将要访问的连接
		String url = urlStr;
		// 存储网页内容
		String result = "";
		// 缓冲字符输入流
		BufferedReader in = null;

		try {
			// String --> URL
			URL realUrl = new URL(url);
			// 建立连接
			URLConnection connection = realUrl.openConnection();
			// 实际连接
			connection.connect();
			// 初始化 BufferedReader 读取 URL 响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8")); // UTF-8 编码
			// 临时存取抓取的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally 中关闭输入流
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
