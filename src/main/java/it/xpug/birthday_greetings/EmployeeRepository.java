package it.xpug.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class EmployeeRepository {
	//private String FileName;
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	EmployeeRepository(XDate today, String fileName)throws IOException, ParseException, AddressException, MessagingException{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str = "";
			str = in.readLine(); // skip header
			while ((str = in.readLine()) != null) {
				String[] employeeData = str.split(", ");
				Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
				employees.add(employee);
				
			}
	}
	
	public void sendEmailForBirthday(XDate xDate, String smtpHost, int smtpPort)throws IOException, ParseException, AddressException, MessagingException{
		MessageService messageService = new MessageService(smtpHost, smtpPort);
		for (Employee employee : employees) {
			if (employee.isBirthday(xDate)) {
				String recipient = employee.getEmail();
				String body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.getFirstName());
				String subject = "Happy Birthday!";
				messageService.composeMessage("sender@here.com", subject, body, recipient);
				messageService.sendMessage();
			}
			
		}
		
	}
	
	public ArrayList<Employee> setEmployee(){
		return this.employees;
	}
	
	

}
