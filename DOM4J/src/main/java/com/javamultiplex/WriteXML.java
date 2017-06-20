package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class WriteXML {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader input=null;
		try
		{
			File file = new File("src/main/resources/student.xml");
			FileOutputStream fos=new FileOutputStream(file);
			input=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("How many students you want to add?");
			int count=Integer.parseInt(input.readLine());
			int rollNumber=0,marks=0;
			String firstName=null,lastName=null,nickName=null;
			Student student=null;
			Document document=DocumentHelper.createDocument();
			Element root=document.addElement("class");
			for(int i=1;i<=count;i++){
				student=new Student();
				System.out.println("*** Student : "+i+" ***");
				System.out.println("Enter roll number : ");
				rollNumber=Integer.parseInt(input.readLine());
				student.setRollNumber(rollNumber);
				System.out.println("Enter first name : ");
				firstName=input.readLine();
				student.setFirstName(firstName);
				System.out.println("Enter last name : ");
				lastName=input.readLine();
				student.setLastName(lastName);
				System.out.println("Enter nick name : ");
				nickName=input.readLine();
				student.setNickName(nickName);
				System.out.println("Enter marks : ");
				marks=Integer.parseInt(input.readLine());
				student.setMarks(marks);
				writeXML(root,student);
			}
			
			OutputFormat format=OutputFormat.createPrettyPrint();
			XMLWriter writer=new XMLWriter(fos,format);
			writer.write(document);
			System.out.println("Student details has been added successfully.");
		}finally{
			if(input!=null){
				input.close();
			}
		}
		
	}

	private static void writeXML(Element root, Student st) {
		
		Element student=root.addElement("student").addAttribute("rollnumber",String.valueOf(st.getRollNumber()));
		student.addElement("firstname").addText(st.getFirstName());
		student.addElement("lastname").addText(st.getLastName());
		student.addElement("nickname").addText(st.getNickName());
		student.addElement("marks").addText(String.valueOf(st.getMarks()));
	}
	
}
