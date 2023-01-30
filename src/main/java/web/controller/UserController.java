package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("newUser")
    public User getPerson(){
        return new User();
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("people",userService.getAllUsers());
        return "index";
    }

    @PostMapping("/")
    public String creat(@ModelAttribute("newUser")@Valid User user,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("people",userService.getAllUsers());
            return "index";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") long id){
        userService.removeUserById(id);
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("user")@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public String edit (@ModelAttribute("id") long id,Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
}
