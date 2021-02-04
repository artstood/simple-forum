package ua.artstood.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.artstood.forum.dao.ForumDAO;
import ua.artstood.forum.entities.Comment;
import ua.artstood.forum.entities.Discussion;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("discussions")
public class DiscussionsController {

    private final ForumDAO forumDAO;

    @Autowired
    public DiscussionsController(ForumDAO forumDAO) {
        this.forumDAO = forumDAO;
    }

    @GetMapping()
    public String showAllEntries(Model model) {
        model.addAttribute("discussions", forumDAO.getAllDiscussions());
        return "discussions/discussions";
    }

    @GetMapping("{id}")
    public String showSpecificEntry(@PathVariable int id, Model model) {
        model.addAttribute("discussion", forumDAO.getDiscussionById(id));
        model.addAttribute("comments", forumDAO.getAllCommentsByDiscussionId(id));
        return "discussions/specific_discussion";
    }

    @GetMapping("new")
    public String newDiscussion(@ModelAttribute("discussion") Discussion discussion) {
        return "discussions/new";
    }

    @PostMapping()
    public String addNewDiscussion(@ModelAttribute("discussion") @Valid Discussion discussion,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "discussions/new";
        }
        discussion.setDate(new Date());
        forumDAO.save(discussion);
        return "redirect:/discussions";
    }

    @GetMapping("{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("discussion", forumDAO.getDiscussionById(id));
        return "discussions/edit";
    }

    @PatchMapping("{id}")
    public String edit(@PathVariable("id") int id,
                       @ModelAttribute("discussion") @Valid Discussion discussion,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "discussions/edit";
        }
        forumDAO.update(id, discussion);
        return "redirect:/discussions";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        forumDAO.delete(id);
        return "redirect:/discussions";
    }
    @GetMapping("{dis_id}/new")
    public String newCommentForm(@ModelAttribute("comment") Comment comment, @PathVariable("dis_id") int dis_id){
        ;return "comments/new";
    }

    @PostMapping("{dis_id}")
    public String createComment(@ModelAttribute("comment")Comment comment,
                                BindingResult bindingResult,
                                @PathVariable("dis_id") int dis_id) {
        if (bindingResult.hasErrors()) {
            return "redirect:/comments/new";
        }
        forumDAO.save(dis_id, comment);
        return "redirect:/discussions/{dis_id}";
    }
}
