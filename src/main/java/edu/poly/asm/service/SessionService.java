package edu.poly.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	
	public SessionService(HttpSession session) {
		this.session = session;
	}
	
	public void set(String name, Object value) {
		session = request.getSession();
		session.setAttribute(name, value);
	}
	
	public void remove(String name) {
		session = request.getSession();
		session.removeAttribute(name);
		session.invalidate();
	}
	
	public <T> T get(String name, T defaultValue) {
		session = request.getSession();
        T value = (T) session.getAttribute(name);
        return value != null ? value : defaultValue;
    }
}
