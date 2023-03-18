package se.sofiamontgomery.todolistan.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import se.sofiamontgomery.todolistan.auth.AppRoles;
import se.sofiamontgomery.todolistan.config.AppPasswordConfig;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final AppPasswordConfig appPasswordConfig;

    @Autowired
    public UserController(UserRepository userRepository, AppPasswordConfig appPasswordConfig) {
        this.userRepository = userRepository;
        this.appPasswordConfig = appPasswordConfig;
    }

    @GetMapping("/register")
    public String displayRegisterUser(UserModel userModel) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result) {
        if(result.hasErrors()){
            return "register";
        }
        String role = String.valueOf(userModel.getAuthorities().iterator().next());

        switch (role) {
            case "Admin" -> userModel.setAuthorities(AppRoles.ADMIN.getGrantedAuthorities());
            case "User" -> userModel.setAuthorities(AppRoles.USER.getGrantedAuthorities());
        }

        userModel.setPassword(appPasswordConfig.bcryptPasswordEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);

        userRepository.save(userModel);
        return "index";
    }

    @GetMapping("/admin")
    public ModelAndView displayAllUsers(UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", userRepository.findAll());
        return modelAndView;
    }

    //TODO: OM JAG FÅR TID:
    // gör så man kan ta bort en användare på admin
}
