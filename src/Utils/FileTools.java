package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import JavaBeans.DiseaseBean;

public class FileTools {
	/*
	 * ����ȡ���� DiseaseBean �б�д���ļ�
	 */
	public static void writeDiseaseBeanListToFile(List<DiseaseBean> list,
			String fileName) {

		/*
		 * ���л��洢 DiseaseBean ����
		 */
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (DiseaseBean db : list) {
				oos.writeObject(db);
				oos.flush();
			}
			oos.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		File f = new File("diseaseList.txt");
		FileWriter fw = null;

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			for (DiseaseBean d : list) {
				fw = new FileWriter(f);
				fw.write(d.toString());
				// ��ˢ�µĻ��޷�д��
				fw.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
