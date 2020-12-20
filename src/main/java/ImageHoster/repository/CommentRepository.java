package ImageHoster.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the User object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public void addComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            //persist() method changes the state of the model object from transient state to persistence state
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }


    //The method receives the entered imageId
    //Creates an instance of EntityManager
    //Executes JPQL query to fetch the comments from Comment class where imageId is equal to received imageId
    //Returns the fetched list of comment
    //Returns null in case of NoResultException
    public List<Comment> getAllComments(Image image) {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c FROM Comment c WHERE c.image = :image", Comment.class);
            typedQuery.setParameter("image", image);

            return typedQuery.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
}