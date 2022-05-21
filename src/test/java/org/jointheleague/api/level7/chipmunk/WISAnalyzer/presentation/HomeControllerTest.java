package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    HomeController homeController;

    @BeforeEach
    void setUp() {
        homeController = new HomeController();
    }

    @Test
    void home() {
    }

    @Test
    void whenHome_thenReturnRedirect() {
        //given
        String expected = "redirect:swagger-ui.html";

        //when
        String actual = homeController.home();

        //then
        assertEquals(expected, actual);
    }
}