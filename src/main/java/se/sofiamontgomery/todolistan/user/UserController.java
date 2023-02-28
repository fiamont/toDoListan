package se.sofiamontgomery.todolistan.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import se.sofiamontgomery.todolistan.auth.AppRoles;
import se.sofiamontgomery.todolistan.config.AppPasswordConfig;

import java.util.List;
import java.util.Optional;

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

    //PRIO:
    //gör så den visar allt i todolistan
    //gör så man kan lägga till i sin lista
    //gör så man kan ta bort från sin lista

    //OM JAG FÅR TID:
    //gör så den visar *specifik användare* på userhome och admin
    //gör så man kan ta bort en användare på admin

    @GetMapping("/admin")
    public ModelAndView displayAdminPage(UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("user", userRepository.findAll());
        return modelAndView;
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
}
