package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.domain.Comment;
import com.fablab.booking.dto.RqCreateCommentDto;
import com.fablab.booking.dto.RqUpdateCommentDto;
import com.fablab.booking.dto.RsCommentDto;
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
    public RsCommentDto findDtoById(Long id) {
        // TODO ad exception for comment not found
        Comment comment = commentRepository.findById(id).orElse(null);
        return CommentMapper.INSTANCE.commentToRsCommentDto(comment);
    }

    @Override
    public RsCommentDto save(RqCreateCommentDto rqCreateCommentDto) {
        Comment comment = CommentMapper.INSTANCE.rqCreateCommentDtoToComment(rqCreateCommentDto);
        Article article = articleService.findById(rqCreateCommentDto.getArticleId());
        article.addComment(comment);
        return CommentMapper.INSTANCE.commentToRsCommentDto(commentRepository.save(comment));
    }

    @Override
    public RsCommentDto update(RqUpdateCommentDto rqUpdateCommentDto, Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        CommentMapper.INSTANCE.updateCommentFromRqUpdateCommentDto(rqUpdateCommentDto, comment);
        return CommentMapper.INSTANCE.commentToRsCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
