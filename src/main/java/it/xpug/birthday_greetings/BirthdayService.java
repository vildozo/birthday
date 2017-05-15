package it.xpug.birthday_greetings;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

	private EmployeeRepository repository;
	private MessageService messageService;
	
	
	public void repository_starter(XDate today, String fileName)throws IOException, ParseException, AddressException, MessagingException {
		repository= new EmployeeRepository(today,fileName);
	}
	
	public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
		EmployeeRepository employeeRespository = new EmployeeRepository(xDate, fileName);
		employeeRespository.sendEmailForBirthday(xDate, smtpHost, smtpPort);
	}
	
	public void loadEmployeeList(XDate today, String fileName)throws IOException, ParseException, AddressException, MessagingException{
		ArrayList<Employee> employees = new ArrayList<Employee>();
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = "";
		str = in.readLine(); // skip header
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			employees.add(new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]));
		}
	}
	
	
}
