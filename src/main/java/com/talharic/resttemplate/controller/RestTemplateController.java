package com.talharic.resttemplate.controller;

import com.talharic.resttemplate.dto.PostDto;
import com.talharic.resttemplate.service.RestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restTemplate")
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(restTemplateService.getAllPosts());
    }


    @GetMapping("/{search}")
    public ResponseEntity<List<PostDto>> getPostBySearch(@PathVariable String search) {
        return ResponseEntity.ok(restTemplateService.getPostBySearch(search));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(restTemplateService.getPostById(id));
    }


/*
    @PostMapping
    public ResponseEntity<List<PostDto>> createPost(@Valid @RequestBody PostDto post) {
        return ResponseEntity.ok(restTemplateService.create(post));
    }
*/

}
