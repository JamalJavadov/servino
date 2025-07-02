package com.example.businessproject.service;

import com.example.businessproject.exception.CommentNotFoundException;
import com.example.businessproject.exception.ProductNotFoundException;
import com.example.businessproject.exception.UserNotFoundException;
import com.example.businessproject.model.dto.comment.CommentRequestDto;
import com.example.businessproject.model.dto.comment.CommentResponseDto;
import com.example.businessproject.model.dto.comment.CommentUpdateDto;
import com.example.businessproject.model.entity.Comment;
import com.example.businessproject.model.entity.Product;
import com.example.businessproject.model.entity.User;
import com.example.businessproject.model.mapper.CommentMapper;
import com.example.businessproject.repository.CommentRepository;
import com.example.businessproject.repository.ProductRepository;
import com.example.businessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServices {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public CommentResponseDto createComment(CommentRequestDto commentRequestDto){
        Comment comment = commentMapper.toEntity(commentRequestDto);
        User user = userRepository.findByEmail(commentRequestDto.getUserGmail()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        Product product = productRepository.findProductById(commentRequestDto.getProductId()).orElseThrow(()->new ProductNotFoundException("Product Not Found"));
        comment.setService(product);
        comment.setUser(user);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public CommentResponseDto updateComment(CommentUpdateDto commentUpdateDto){
        Comment comment = commentRepository.findById(commentUpdateDto.getId()).orElseThrow(()->new CommentNotFoundException("Comment Not Found"));
        Comment updatedComment = commentMapper.toEntity(commentUpdateDto);
        updatedComment.setCreationAt(comment.getCreationAt());
        return commentMapper.toDto(commentRepository.save(updatedComment));
    }

}
