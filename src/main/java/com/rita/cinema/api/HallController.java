package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HallController {
    @Autowired
    private HallService hallService;

    @GetMapping("halls")
    public String all(@AuthenticationPrincipal User user, Model model) {
        checkUser(user, model);
        List<Hall> halls = hallService.findAll();
        model.addAttribute("halls", halls);
        return "halls";
    }

    @GetMapping("add/hall")
    public String addPage(Model model, @AuthenticationPrincipal User user) {
        checkUser(user, model);
        return "add-hall";
    }

    @PostMapping("add/hall")
    public String add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "linesNum") int linesNum,
                    @RequestParam(value = "seatsNum") int seatsNum,
                    @RequestParam(value = "hallType") HallType hallType) {
        Hall hall = new Hall(name, linesNum, seatsNum, hallType);
        hallService.add(hall);
        return "redirect:/halls";
    }

    @GetMapping("delete/hall/{hall}")
    public String delete(@PathVariable Hall hall){
        if(hall.getSeances().isEmpty())
            hallService.deleteById(hall.getId());
        return "redirect:/halls";
    }

    private void checkUser(@AuthenticationPrincipal User user, Model model) {
        boolean isAuthenticated = false;
        boolean isAdmin = false;
        if(user != null) {
            isAuthenticated = true;
            if (user.isAdmin())
                isAdmin = true;
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
    }
}
