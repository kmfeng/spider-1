package JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiseaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String description;
	public List<String> content;
	public List<String> contentDetail;
	
	/*
	 * ¹¹Ôìº¯Êý
	 */
	public DiseaseBean() {
		name = "default name";
		description = "default description";
		content = new ArrayList<String>();
		contentDetail = new ArrayList<String>();
	}
	
	public String toString() {
		return "name: \n" + name + "\n" + "description: \n" + description + "\n" + "content: \n" + content + "\n" + "contentDetail: \n" + contentDetail + "\n";
	}
}
