package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.TestDto;
import com.fablab.booking.repository.RoomBookingRepository;
import com.fablab.booking.repository.UserRepository;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.CommentService;
import com.fablab.booking.service.impl.MinioServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
//@CrossOrigin
@RequestMapping("/booking/test")
public class IndexController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final RoomBookingRepository roomBookingRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MinioServiceImpl minioService;
    private final MinioClient minioClient;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<String> hi() {
        roomBookingRepository.findAllActiveBookings();
        return ResponseEntity.status(HttpStatus.OK).body("hi there");
    }

    @PostMapping(value = "/article", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RqCreateArticleDto> testArticle(RqCreateArticleDto rqCreateArticleDto){
        return ResponseEntity.ok(rqCreateArticleDto);
    }


    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        String name = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf("\\") + 1);
        String path = minioService.uploadImage(name, files.getBytes(), "article");
        Map<String, String> result = new HashMap<>();
        result.put("key", files.getOriginalFilename());
        result.put("name", path);
        return result;
    }

    @ApiImplicitParam(name = "test", dataType = "string", paramType = "query",
            value = "article string json representation",
            defaultValue = "{\"name\" : \"Saniok\"}")
    @PostMapping(path = "/test-object", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> test(
            @RequestParam(value = "file", required = false) MultipartFile file,
            //@RequestParam(value = "file2", required = false) MultipartFile file2,
            @RequestParam(value = "test", required = false) TestDto test) throws JsonProcessingException {

        // TestDto testDto = objectMapper.readValue(test, TestDto.class);
        System.out.println(test);
        return ResponseEntity.ok("ok");
    }


}
