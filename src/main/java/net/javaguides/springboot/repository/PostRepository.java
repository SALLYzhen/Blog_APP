package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Comment;
import net.javaguides.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);

    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    @Query(value = "select * from posts p where p.created_by =:userId", nativeQuery = true)
    List<Post> findPostsByUser(Long userId);
}
// 1. JpaRepository interface each internally extends CrudRepository interface.
// Hence, JpaRepository will leverage all the CRUD methods from CrudRepository interface.
// 2. JpaRepository is an interface, and it has the implementation class named Simple JpaRepository Class.
// Simple JpaRepository is a default implementation class of repository, and it provides implementation
// of all the JpaRepository interface methods.
// 3.  All the public methods which are defined in a simple JpaRepository class are by default transactional.
// So we don't have to use again @Transactional annotation to make these methods transactional.
// *** All the methods in a JpaRepository interface are by default transactional.
// 4. Simple JpaRepository Class is already annotated with @Repository annotation.
// Hence, we don't have to use @Repository annotation in our repository interface.