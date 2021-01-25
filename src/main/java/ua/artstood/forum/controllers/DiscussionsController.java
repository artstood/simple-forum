package ua.artstood.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.artstood.forum.dao.DiscussionsDAO;
import ua.artstood.forum.entities.Discussion;

import java.util.List;

@Controller
@RequestMapping("discussions")
public class DiscussionsController {

    private final DiscussionsDAO dao;

    @Autowired
    public DiscussionsController(DiscussionsDAO dao){
        this.dao = dao;
    }

    @GetMapping()
    public String showAllEntries(Model model){
        model.addAttribute("discussions",dao.getAllEntries());
        return "discussions/discussions";
    }


}
