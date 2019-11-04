package edu.udacity.java.nano.chatroomstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;



@SpringBootApplication
@RestController
public class ChatroomStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatroomStarterApplication.class, args);
	}

	/**
	 * Login Page
	 */
	@GetMapping("/")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	/**
	 * Chatroom Page
	 */
	@GetMapping("/index/{username}")
	public ModelAndView index(@PathVariable("username") String username, HttpServletRequest request) throws UnknownHostException {
		ModelAndView modelAndView = new ModelAndView("chat");
		modelAndView.addObject(username);
		return modelAndView;
	}


}
