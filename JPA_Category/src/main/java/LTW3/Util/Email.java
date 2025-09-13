package LTW3.Util;

import java.util.Properties;
import java.util.Random;

import LTW3.Entity.User;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Email {

	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public boolean sendEmail(User users) {
		boolean test = false;
		String toEmail = users.getEmail();
		String fromEmail = "baoproa6@gmail.com";
		String password = "0375478571b-n";
		try {
			Properties pr = configEmail(new Properties());

			Session session = Session.getInstance(pr, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setFrom(new InternetAddress(fromEmail, "LTW3 App"));
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mess.setSubject("Confirm Code");
			mess.setText("Your code is: " + users.getCode());

			Transport.send(mess);
			test = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return test;
	}

	public Properties configEmail(Properties pr) {
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		return pr;
	}
}
