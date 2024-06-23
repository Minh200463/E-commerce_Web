package edu.poly.asm.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.asm.model.MailInfo;
import edu.poly.asm.service.MailerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerServiceImpl implements MailerService {
	@Autowired
	JavaMailSender sender;
	List<MailInfo> list = new ArrayList<>();

	@Scheduled(fixedDelay = 5000)
	public void run() {
		while (!list.isEmpty()) {
			MailInfo mail = list.remove(0);
			try {
				this.send(mail, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void send(MailInfo mail) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
//		helper.setReplyTo(mail.getFrom());

		// Gửi message đến SMTP server
		sender.send(message);
	}

	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		// TODO Auto-generated method stub
		this.send(new MailInfo(to, subject, body));
	}

	@Override
	public void queue(MailInfo mail) {
		// TODO Auto-generated method stub
		list.add(mail);

	}

	@Override
	public void queue(String to, String subject, String body) {
		// TODO Auto-generated method stub
		queue(new MailInfo(to, subject, body));
	}

	@Override
	public void send(MailInfo mail, MultipartFile[] attachments) throws MessagingException {
		// TODO Auto-generated method stub
				MimeMessage message = sender.createMimeMessage();
				// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(mail.getFrom());
				helper.setTo(mail.getTo());
				helper.setSubject(mail.getSubject());
				helper.setText(mail.getBody(), true);
				helper.setReplyTo(mail.getFrom());
				String[] cc = mail.getCc();
				if (cc != null && cc.length > 0) {
					helper.setCc(cc);
				}
				String[] bcc = mail.getBcc();
				if (bcc != null && bcc.length > 0) {
					helper.setBcc(bcc);
				}
				if (attachments != null && attachments.length > 0) {
		            for (MultipartFile file : attachments) {
		                if (!file.isEmpty()) {
		                    helper.addAttachment(file.getOriginalFilename(), file);
		                }
		            }
		        }
				sender.send(message);
	}

}
