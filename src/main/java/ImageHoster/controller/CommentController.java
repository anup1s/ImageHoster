package ImageHoster.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;

@Controller
public class CommentController {
	
    @Autowired
    private CommentService commentService;
    
	@RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
	public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle,
			@RequestParam("comment") String comment, Comment newComment, HttpSession session) throws IOException {
		User user = (User) session.getAttribute("loggeduser");
		newComment.setUser(user);
		
		Image image = new Image(imageId, imageTitle, null, null);
		newComment.setImage(image);

		newComment.setText(comment);
		newComment.setCreatedDate(Calendar.getInstance().getTime());
		
		commentService.addComment(newComment);
		
		return "redirect:/images/" + imageId + "/" + imageTitle;
	}

}
