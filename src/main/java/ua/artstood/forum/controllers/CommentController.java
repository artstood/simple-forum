package ua.artstood.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.artstood.forum.dao.ForumDAO;
import ua.artstood.forum.entities.Comment;

@Controller
@RequestMapping("discussion")
public class CommentController {

    private ForumDAO forumDAO;

    @Autowired
    public CommentController(ForumDAO forumDAO){
        this.forumDAO = forumDAO;
    }

    @GetMapping("{dis_id}/new")
    public String newCommentForm(@ModelAttribute("comment") Comment comment, @PathVariable("dis_id") int dis_id){
        System.out.println("comment get method new");return "redirect:comments/new";}

    @PostMapping("{dis_id}")
    public String createComment(@ModelAttribute("comment")Comment comment,
                                BindingResult bindingResult,
                                @PathVariable("dis_id") int dis_id){
        if (bindingResult.hasErrors()){
            return "redirect:/comments/new";
        }
        forumDAO.save(dis_id, comment);
        return "redirect:/discussion/specific_discussion";
    }





}
