package com.example.businessproject.controller;

import com.example.businessproject.model.dto.comment.CommentRequestDto;
import com.example.businessproject.model.dto.comment.CommentResponseDto;
import com.example.businessproject.model.dto.comment.CommentUpdateDto;
import com.example.businessproject.service.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentServices commentServices;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentServices.createComment(commentRequestDto));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentUpdateDto commentUpdateDto){
        return ResponseEntity.ok(commentServices.updateComment(commentUpdateDto));
    }

    @GetMapping("/product-comments/{productid}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable long productid){
        return ResponseEntity.ok(commentServices.getComments(productid));
    }

}
