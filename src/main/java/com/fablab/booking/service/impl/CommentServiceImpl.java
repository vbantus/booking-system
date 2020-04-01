package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.domain.Comment;
import com.fablab.booking.dto.CommentDto;
import com.fablab.booking.mapper.CommentMapper;
import com.fablab.booking.repository.CommentRepository;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    @Override
    public CommentDto findDtoById(Long id) {
        // TODO ad exception for comment not found
        Comment comment = commentRepository.findById(id).orElse(null);
        return CommentMapper.INSTANCE.commentToCommentDto(comment);
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = CommentMapper.INSTANCE.commentDtoToComment(commentDto);
        Article article = articleService.findById(commentDto.getArticleId());
        article.addComment(comment);
        return CommentMapper.INSTANCE.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElse(null);
        CommentMapper.INSTANCE.updateCommentFromCommentDto(commentDto, comment);
        return CommentMapper.INSTANCE.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
