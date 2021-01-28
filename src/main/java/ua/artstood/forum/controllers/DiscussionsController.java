package ua.artstood.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.artstood.forum.dao.CommentsDAO;
import ua.artstood.forum.dao.DiscussionsDAO;
import ua.artstood.forum.entities.Discussion;

import javax.validation.Valid;

@Controller
@RequestMapping("discussions")
public class DiscussionsController {

    private final DiscussionsDAO discussionsDAO;
    private final CommentsDAO commentsDAO;


    @Autowired
    public DiscussionsController(DiscussionsDAO discussionsDAO, CommentsDAO commentsDAO){
        this.discussionsDAO = discussionsDAO;
        this.commentsDAO = commentsDAO;
    }

    @GetMapping()
    public String showAllEntries(Model model){
        model.addAttribute("discussions", discussionsDAO.getAllEntries());
        return "discussions/discussions";
    }

    @GetMapping("{id}")
    public String showSpecificEntry(@PathVariable int id, Model model){
        model.addAttribute("discussion", discussionsDAO.getDiscussion(id));
        model.addAttribute("comments", commentsDAO.getDiscussionsComments(id));
        return "discussions/specific_discussion";
    }

    @GetMapping("new")
    public String newDiscussion(@ModelAttribute("discussion") Discussion discussion){
        return "discussions/new";
    }

    @PostMapping()
    public String addNewDiscussion(@ModelAttribute("discussion") @Valid Discussion discussion,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "discussions/new";
        }
        discussionsDAO.save(discussion);
        return "redirect:/discussions";
    }

    @GetMapping("{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id){
        model.addAttribute("discussion", discussionsDAO.getDiscussion(id));
        return "discussions/edit";
    }

    @PatchMapping("{id}")
    public String edit( @PathVariable("id") int id,
                        @ModelAttribute("discussion") @Valid Discussion discussion,
                        BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "discussions/edit";
        }
        discussionsDAO.update(id, discussion);
        return "redirect:/discussions";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        discussionsDAO.delete(id);
        return "redirect:/discussions";
    }


}
