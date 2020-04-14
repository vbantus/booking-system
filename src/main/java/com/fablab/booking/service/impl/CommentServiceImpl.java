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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

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

    @Override
    public RsCommentDto findDtoById(Long id) {
        // TODO ad exception for comment not found
        Comment comment = commentRepository.findById(id).orElse(null);
        return CommentMapper.INSTANCE.commentToRsCommentDto(comment);
    }

    @Override
    public List<RsCommentDto> findAllDtoByArticleId(Long id) {
        return commentRepository.findAllByArticleId(id).stream()
                .map(CommentMapper.INSTANCE::commentToRsCommentDto)
                .collect(Collectors.toList());
    }
}
