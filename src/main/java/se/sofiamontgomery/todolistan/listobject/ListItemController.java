package se.sofiamontgomery.todolistan.listobject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListItemController {

    private final ListItemRepository listItemRepository;

    @Autowired
    public ListItemController(ListItemRepository listItemRepository) {
        this.listItemRepository = listItemRepository;
    }

    /*TODO:
       gör så den visar allt i todolistan
       gör så man kan lägga till i sin lista
       gör så man kan ta bort från sin lista
     */

    @GetMapping("/userhome")
    public ModelAndView getListItems(ListItemModel listItemModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("item", listItemRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/userhome")
    public String addListItem(@Valid ListItemModel listItemModel, BindingResult result) {
        if(result.hasErrors()){
            return "userhome";
        }
        listItemModel.setDone(false);
        listItemRepository.save(listItemModel);
        return "userhome";
    }


    /*@PostMapping("/register")
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
    }*/
}
