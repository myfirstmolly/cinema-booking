package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("hall/{id}")
    public Hall byId(@PathVariable Long id, @AuthenticationPrincipal User user, Map<String, Object> model) {
        return hallService.findById(id);
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

    @DeleteMapping("delete/hall/{id}")
    public void delete(@PathVariable Long id){
        hallService.deleteById(id);
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
