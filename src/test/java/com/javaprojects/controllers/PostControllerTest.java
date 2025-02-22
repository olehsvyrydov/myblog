package com.javaprojects.controllers;

import com.javaprojects.WebConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = WebConfiguration.class)
class PostControllerTest {
    public static final MockMultipartFile IMAGE_FILE = new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "bla-bla".getBytes());
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllPosts_goingToBasePath_shouldReturnAllPosts() throws Exception {
        mockMvc.perform(get("/")
                        .param("offset", String.valueOf(0))
                        .param("limit", String.valueOf(10))
                        .param("tagFilter", "all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("feed"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attributeExists("offset"))
                .andExpect(model().attributeExists("limit"))
                .andExpect(model().attributeExists("allTags"))
                .andExpect(model().attributeExists("totalPosts"));
    }

    @Test
    void getAllPosts_shouldReturnAllPosts() throws Exception {
        mockMvc.perform(get("/posts")
                        .param("offset", String.valueOf(10))
                        .param("limit", String.valueOf(50))
                        .param("tagFilter", "all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("feed"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attributeExists("offset"))
                .andExpect(model().attributeExists("limit"))
                .andExpect(model().attributeExists("allTags"))
                .andExpect(model().attributeExists("totalPosts"));
    }

    @Test
    void getPostById_shouldBuildPostPage() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attributeExists("likesCount"));
    }

    @Test
    void createPost_postShouldBeCreated() throws Exception {
        mockMvc.perform(multipart("/posts")
                .file(IMAGE_FILE)
                .param("title", "title")
                .param("description", "description")
                .param("content", "some content")
                .param("tags", "tag1, tag2, tag3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/posts"));
    }

    @Test
    @Disabled
    void updatePost() throws Exception {
        mockMvc.perform(multipart("/posts")
                        .file(IMAGE_FILE)
                        .param("_method", "put")
                        .param("id", "1")
                        .param("title", "title")
                        .param("description", "description")
                        .param("content", "some content")
                        .param("imageUrl", "imageUrl")
                        .param("tags", "tag1, tag2, tag3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/posts"));
    }

    @Test
    void deletePost() throws Exception {
        mockMvc.perform(post("/posts/1").param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/posts"));
    }
}
