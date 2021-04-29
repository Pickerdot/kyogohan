package com.example.mysql_db.kyogohan;

import com.example.mysql_db.entuty.Dish;
import com.example.mysql_db.entuty.User;
import com.example.mysql_db.service.DishService;
import com.example.mysql_db.service.UserNotFoundException;
import com.example.mysql_db.service.UserService;
import com.example.mysql_db.user.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/kyogohan")
public class KyogohanController {

    private final DishService dishService;

    @Autowired
    public KyogohanController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "きょごはん＠ホーム");
        return "kyogohan/index.html";
    }

    @PostMapping
    public String indexGoBack(Model model) {
        model.addAttribute("title", "きょうごはん＠ホーム");
        return "kyogohan/index.html";
    }

    @PostMapping("/result")
    public String result(Model model){
        List<Dish> list = dishService.getAll();
        int listLength = list.size();
        Random random = new Random();
        int randomDishId = random.nextInt(listLength) + 1;
        System.out.println("listLength:" + listLength );
        System.out.println(randomDishId);
        Dish dish = dishService.getOne(randomDishId);
        String dishName = dish.getDishName();
        model.addAttribute("title", "きょうごはん＠結果");
        model.addAttribute("dishName", dishName);
        return "kyogohan/result.html";
    }


}
