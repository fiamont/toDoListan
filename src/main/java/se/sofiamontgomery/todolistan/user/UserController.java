package se.sofiamontgomery.todolistan.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.sofiamontgomery.todolistan.config.AppPasswordConfig;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final AppPasswordConfig appPasswordConfig;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, AppPasswordConfig appPasswordConfig, UserService userService) {
        this.userRepository = userRepository;
        this.appPasswordConfig = appPasswordConfig;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String displayRegisterUser(UserModel userModel) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result) {
        return userService.createUser(userModel, result);
    }

    @GetMapping("/admin")
    public ModelAndView displayAllUsers(UserModel userModel) {
        return userService.showAllUsers(userModel);
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/admin";
    }
}
