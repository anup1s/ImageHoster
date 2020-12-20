package ImageHoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	 //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void addComment(Comment comment) {
    	commentRepository.addComment(comment);
    }

	public List<Comment> fetchAllCommentsForImage(Image image) {
		return commentRepository.getAllComments(image);
	}

}
