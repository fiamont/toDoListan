package se.sofiamontgomery.todolistan.post;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import se.sofiamontgomery.todolistan.user.UserModel;

@Controller
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/userhome")
    public ModelAndView getListItems(PostModel postModel, UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("post", postRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/userhome")
    public String addListItem(@Valid PostModel postModel, BindingResult result, UserModel userModel) {
        if(result.hasErrors()){
            return "userhome";
        }
        postModel.setDone(false);
        postModel.setUserModel(userModel);
        postRepository.save(postModel);
        return "userhome";
    }
}
