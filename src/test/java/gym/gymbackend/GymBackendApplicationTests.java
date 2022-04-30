package gym.gymbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GymBackendApplicationTests {

    @Test
    @DisplayName("Testing if context is correctly set")
    void contextLoads(ApplicationContext context) {
        assertNotNull(context);
    }

}
