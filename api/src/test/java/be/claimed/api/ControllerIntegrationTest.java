package be.claimed.api;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.SpringApplication.run;

@SpringBootTest(classes = ControllerIntegrationTest.ControllerIntegrationTestRunner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
public abstract class ControllerIntegrationTest {

    @LocalServerPort
    private int port;

    public int getPort() {
        return port;
    }

    @SpringBootApplication(scanBasePackages = "be.claimed")
    public static class ControllerIntegrationTestRunner {
        public static void main(String[] args) {
            run(ControllerIntegrationTest.ControllerIntegrationTestRunner.class, args);
        }
    }
}
