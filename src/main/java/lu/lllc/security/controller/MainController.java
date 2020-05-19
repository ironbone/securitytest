package lu.lllc.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		if (loggedInUser.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("logged", false);
		}else {
			model.addAttribute("logged", true);
			model.addAttribute("username", loggedInUser.getName());
		}
		return "index";

	}

	@GetMapping("/restricted")
	public String restricted() {
		return "restricted";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/accessdenied")
	public String accessdenied() {
		return "accessdenied";
	}
}
