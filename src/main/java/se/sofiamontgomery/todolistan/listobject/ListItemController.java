package se.sofiamontgomery.todolistan.listobject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.sofiamontgomery.todolistan.user.UserModel;
import se.sofiamontgomery.todolistan.user.UserRepository;

@Controller
public class ListItemController {

    private final ListItemRepository listItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ListItemController(ListItemRepository listItemRepository, UserRepository userRepository) {
        this.listItemRepository = listItemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/userhome")
    public String getListItem(Model model) {
        model.addAttribute("listitem", new ListItemModel());
        model.addAttribute("user", new UserModel());
        return "userhome";
    }


    @PostMapping("/userhome")
    public String addListItem(@Valid @ModelAttribute("listitem") ListItemModel listItemModel) {
        listItemRepository.save(new ListItemModel(listItemModel.getListItemName(), false));
        return "userhome";
    }

}
