package com.talharic.resttemplate.service;

import com.talharic.resttemplate.dto.PostDto;
import com.talharic.resttemplate.dto.converter.PostDtoConverter;
import com.talharic.resttemplate.exception.PostNotFoundException;
import com.talharic.resttemplate.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestTemplateService {

    @Value("${apiBaseUrl}")
    private String apiBaseUrl;

    private final RestTemplate restTemplate;
    private final PostDtoConverter postDtoConverter;


    public RestTemplateService(RestTemplate restTemplate, PostDtoConverter postDtoConverter) {
        this.restTemplate = restTemplate;
        this.postDtoConverter = postDtoConverter;
    }

    public List<PostDto> getAllPosts() {
        return getPostList()
                .stream()
                .map(postDtoConverter::convert).collect(Collectors.toList());
    }

    public List<PostDto> getPostBySearch(String search) {
        return getPostList()
                .stream()
                .map(postDtoConverter::convert)
                .filter(x -> x.getBody().toString().contains(search))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new PostNotFoundException("post not found with searched words :("));
    }


    public PostDto getPostById(Long id) {
        return postDtoConverter.convert(Objects.requireNonNull(restTemplate.getForObject(
                apiBaseUrl + "/posts/" + id,
                Post.class)));

    }


    protected List<Post> getPostList() {
        return restTemplate.exchange(
                apiBaseUrl + "/posts",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
                }).getBody();
    }

}
