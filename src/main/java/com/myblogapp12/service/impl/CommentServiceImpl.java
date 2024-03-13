package com.myblogapp12.service.impl;
import com.myblogapp12.entity.Comment;
import com.myblogapp12.entity.Post;
import com.myblogapp12.exception.ResourceNotFoundException;
import com.myblogapp12.payload.CommentDto;
import com.myblogapp12.repository.CommentRepository;
import com.myblogapp12.repository.PostRepository;
import com.myblogapp12.service.CommentService;
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not found with id:" + postId)
        );
        Comment comment = new Comment ();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = new CommentDto();
         dto.setId(savedComment.getId());
         dto.setEmail(savedComment.getEmail());
        dto.setText(savedComment.getText());
        return dto ;
    }
}
