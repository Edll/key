package de.edlly.key.services;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes =
                {FetchPostsData.class, TestServiceConfiguration.class},
properties = {"de.edlly.key.wordpress.api.endpoint=https://localhost/wp-json/wp/v2/posts"})
class FetchPostsDataTest {

    @Autowired
    private FetchPostsData fetchPostsData;

    @Test
    void getPostsData() {
        fetchPostsData.getPostsData();
    }
}