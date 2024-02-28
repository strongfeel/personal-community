package personal.alcoholic.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

  private final String LOGIN_SESSION_KEY = "userId";
  @GetMapping("/")
  public String index(Model model, HttpSession session,
      @RequestParam(required = false) String redirect) {
    model.addAttribute("isSignIn", session.getAttribute(LOGIN_SESSION_KEY) != null);
    model.addAttribute("redirect", redirect);
    return "index";
  }
}
