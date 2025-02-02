package ru.company.shareit.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.company.shareit.item.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query("SELECT c FROM Comment c JOIN FETCH c.item WHERE c.item.id = :ownerId")
    List<Comment> findAllCommentsByItem_Id(Long itemId);
}
