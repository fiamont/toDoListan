package se.sofiamontgomery.todolistan.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import se.sofiamontgomery.todolistan.auth.AppRoles;
import se.sofiamontgomery.todolistan.config.AppPasswordConfig;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AppPasswordConfig appPasswordConfig;

    @Autowired
    public UserService(UserRepository userRepository, AppPasswordConfig appPasswordConfig) {
        this.userRepository = userRepository;
        this.appPasswordConfig = appPasswordConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public String createUser(@Valid UserModel userModel, BindingResult result) {
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

    public ModelAndView showAllUsers(UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", userRepository.orderByUsername());
        return modelAndView;
    }
}