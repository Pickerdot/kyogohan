package com.example.mysql_db.user;

import com.example.mysql_db.entuty.User;
import com.example.mysql_db.service.UserNotFoundException;
import com.example.mysql_db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        List<User> list = userService.getAll();
        model.addAttribute("title", "User list");
        model.addAttribute("userList", list);

        return "user/index.html";
    }

    @GetMapping("/form")
    public String form(UserForm userForm, Model model, @ModelAttribute("complete") String complete){
        model.addAttribute("title", "User Form");
        return "user/form";
    }

    @PostMapping("/form")
    public String formGoBack(UserForm userForm, Model model) {
        model.addAttribute("title", "User Form");
        return "user/form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated UserForm userForm, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("title", "User Form");
            return "user/form";
        }
        model.addAttribute("title", "Confirm Page");
        System.out.println(userForm.getUserName());
        System.out.println(userForm.getUserMail());
        return "user/confirm";
    }

    @PostMapping("/complete")
    public String complete(@Validated UserForm userForm,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("title", "UserForm") ;
            return "user/form";}

        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setUserMail(userForm.getUserMail());


        userService.save(user);

        redirectAttributes.addFlashAttribute("complete", "Registered!");
        return "redirect:/user/form";

    }






    @GetMapping("/form2")
    public String form2(UserForm userForm, Model model, @ModelAttribute("complete") String complete){
        model.addAttribute("title", "User Form");
        return "user/form2";
    }

    @PostMapping("/form2")
    public String form2GoBack(UserForm userForm, Model model) {
        model.addAttribute("title", "Sign in Form");
        return "user/form2";
    }

    @PostMapping("/confirm2")
    public String confirm2(@Validated UserForm userForm, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            model.addAttribute("title", "Sign in Form");
            return "user/form2";
        }

        List<User> list = userService.getAll();
        for(int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.print(userForm.getUserName());
            System.out.println(user.getUserName());
            if ((userForm.getUserName().equals(user.getUserName())) && (userForm.getUserMail().equals(user.getUserMail()))) {
                System.out.println("done");
                redirectAttributes.addFlashAttribute("complete", "welcome!");
                return "user/confirm2";

            }

        }


        model.addAttribute("title", "Confirm Page");
        System.out.println(userForm.getUserName());
        System.out.println(userForm.getUserMail());

        model.addAttribute("title", "User Form");
        model.addAttribute("complete", "password was  not correct");
        return "user/form2";
    }

    @PostMapping("/complete2")
    public String complete2(@Validated UserForm userForm,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("title", "UserForm") ;
            return "user/form2";}

        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setUserMail(userForm.getUserMail());


        userService.save(user);

        redirectAttributes.addFlashAttribute("complete", "Registered!");
        return "redirect:/user/form2";

    }



    @ExceptionHandler(UserNotFoundException.class)
    public String handleException(UserNotFoundException e, Model model){
        model.addAttribute("message", e);
        return "error/CustomPage";
    }




}
