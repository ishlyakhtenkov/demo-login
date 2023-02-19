package jira.javarush.com.registration;

import jakarta.validation.Valid;
import jira.javarush.com.stub.Role;
import jira.javarush.com.stub.User;
import jira.javarush.com.stub.UserServiceStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserServiceStub service;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("registerTo", new RegisterTo());
        return "register";
    }

    @PostMapping
    public String saveRegister(@Valid RegisterTo registerTo, BindingResult result, SessionStatus status, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        service.create(new User(null, registerTo.getName(), registerTo.getEmail(), "{noop}" + registerTo.getPassword(), true, Set.of(Role.USER)));
        status.setComplete();
        return "redirect:/login";
    }
}
