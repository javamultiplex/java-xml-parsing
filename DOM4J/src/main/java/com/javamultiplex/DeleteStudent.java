package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class DeleteStudent {

	public static void main(String[] args) throws DocumentException,IOException {

		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			File file = new File("src/main/resources/student.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			System.out.println("Enter roll number : ");
			int rollNumber = Integer.parseInt(input.readLine());
			boolean result = deleteStudent(document, rollNumber);
			if (result) {
				FileOutputStream fos = new FileOutputStream(file);
				OutputFormat format = OutputFormat.createPrettyPrint();
				XMLWriter writer = new XMLWriter(fos, format);
				writer.write(document);
				System.out.println("Student details has been deleted successfully.");
			} else {
				System.out.println("Student not found.");
			}

		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static boolean deleteStudent(Document document, int rollNumber) {
		
		Element root=document.getRootElement();
		String expression="/class/student[@rollnumber="+rollNumber+"]";
		Node node=document.selectSingleNode(expression);
		boolean result=false;
		if(node!=null){
			Element element=(Element)node;
			root.remove(element);
			result=true;
		}
		return result;
	}

}
