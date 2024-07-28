package edu.poly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.model.MailInfo;
import edu.poly.asm.model.Users;
import edu.poly.asm.repository.UserReponsitory;
import edu.poly.asm.service.CartService;
import edu.poly.asm.service.MailerService;
import edu.poly.asm.service.SessionService;
import edu.poly.asm.service.UserService;
import jakarta.mail.MessagingException;

@Controller
@RequestMapping("checkout")
public class CheckoutController {
	@Autowired
	UserReponsitory userReponsitory;
	@Autowired
	CartService cartservice;
	@Autowired
	CartService cartimpl;
	@Autowired
	SessionService session;
	@Autowired
	MailerService mailer;
	private String fullname;

	@RequestMapping("view")
	public String checkout(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = null;

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			user = userReponsitory.findByUsername(username).get(); // Lấy đối tượng Users từ cơ sở dữ liệu
		}
		session.set("userdetail", user);

		// user = session.get("userdetail", null);
		if (user != null) {
			model.addAttribute("cartitem", cartservice.getItems());
			model.addAttribute("amount", cartservice.getAmout());
			return "auth/checkout";
		} else {
			return "redirect:/login/index";
		}
	}

	@RequestMapping("checksuccess")
	public String addorder(@RequestParam("email") String to) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = null;

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			user = userReponsitory.findByUsername(username).get();
			fullname = user.getFullname();
		}

		// user = session.get("user", null);
		if (user != null) {
			cartservice.checkout(user);
			sendMail(to);
			session.set("countcart", cartservice.getCount());
			return "auth/success";
		} else {
			return "redirect:/login/index";
		}
	}

	private void sendMail(String to) {
		String subject = "Hóa đơn mua hàng";
		String body = "<html>\r\n"
				+ "  <head></head>\r\n"
				+ "  <body>\r\n"
				+ "    <div>\r\n"
				+ "       <dd><h1>Hi [" + fullname + "],</h1> </dd>\r\n"
				+ "       <dd>Thank you so much for your order. We really appreciate your support! </dd>\r\n"
				+ "       <dd>We are getting ready to deliver [Product(s) Ordered] and will send you an update as soon as [it/they] ship. </dd>\r\n"
				+ "       <dd>In the meantime, if you need anything, don’t hesitate to reach out to us!</dd>\r\n"
				+ "       <dd>Have a great day 😄</dd>\r\n"
				+ "    </div>\r\n"
				+ "  </body>\r\n"
				+ "</html>";

		MailInfo mailinfo = new MailInfo();
		mailinfo.setFrom("minhdcpd07427@fpt.edu.vn");
		mailinfo.setTo(to);
		mailinfo.setSubject(subject);
		mailinfo.setBody(body);

		try {
			mailer.send(mailinfo);
		} catch (MessagingException e) {
			// Xử lý khi gửi email thất bại, ví dụ log lỗi hoặc thông báo cho người dùng
			e.printStackTrace(); // Nên log lỗi hoặc thông báo lỗi ra console hoặc file log
		}
	}
}
