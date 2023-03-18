package se.sofiamontgomery.todolistan.listobject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import se.sofiamontgomery.todolistan.user.UserModel;

@Controller
public class ListItemController {

    private final ListItemRepository listItemRepository;

    @Autowired
    public ListItemController(ListItemRepository listItemRepository) {
        this.listItemRepository = listItemRepository;
    }

    /*TODO:
       gör så den visar allt tillhörande endast den inloggade i todolistan
       gör så man kan lägga till i sin lista
       gör så man kan ta bort från sin lista
       gör så man kan ändra sin lista
       om jag har tid:
       gör så när det är färdigt så stryks det över istället för tas bort helt och hållet
     */

    @GetMapping("/userhome")
    public ModelAndView getListItems(ListItemModel listItemModel, UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("item", listItemRepository.findAll());
        return modelAndView;
    }


    @PostMapping("/userhome")
    public String addListItem(@Valid ListItemModel listItemModel, BindingResult result, UserModel userModel) {
        if(result.hasErrors()){
            return "userhome";
        }
        listItemModel.setDone(false);
        listItemModel.setUserModel(userModel);
        listItemRepository.save(listItemModel);
        return "userhome";
    }
}
