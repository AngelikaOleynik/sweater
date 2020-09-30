package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sweater.domain.Role;
import sweater.domain.User;
import sweater.repository.UserRepository;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
      User userFromDb = userRepository.findByUsername(user.getUsername());
      if( userFromDb != null){
          model.put("message", "User exists!");
          return "registration";
      }
      user.setActive(true);
      user.setRoles(Collections.singleton(Role.USER));  //сет с единственным значением
        userRepository.save(user);
        return "redirect:/login"; // при успешной регистрации
    }

}
