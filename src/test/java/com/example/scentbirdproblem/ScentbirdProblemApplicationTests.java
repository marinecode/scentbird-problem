package com.example.scentbirdproblem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@SpringBootTest
@TestPropertySources({
        @TestPropertySource("classpath:application-test.properties")
})
class ScentbirdProblemApplicationTests {

    @Test
    void contextLoads() {
    }

}
