package JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiseaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private List<String> content;
	private List<String> contentDetail;
	
	/*
	 * constructor
	 */
	public DiseaseBean() {
		name = "default name";
		description = "default description";
		content = new ArrayList<String>();
		contentDetail = new ArrayList<String>();
	}
	
	// setter
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String des) {
		this.description = des;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public void setContentDetail(List<String> contentDetail) {
		this.contentDetail = contentDetail;
	}
	
	// getter
	public String getName() {
		return name;
	}
	public String gettDescription() {
		return description;
	}
	public List<String> getContent() {
		return content;
	}
	public List<String> getContentDetail() {
		return contentDetail;
	}
	
	public String toString() {
		return "name: \n" + name + "\n" + "description: \n" + description + "\n" + "content: \n" + content + "\n" + "contentDetail: \n" + contentDetail + "\n";
	}
}
