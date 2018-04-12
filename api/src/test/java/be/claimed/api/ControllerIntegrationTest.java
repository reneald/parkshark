package be.claimed.api;


import be.claimed.domain.AbstractRepository;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.entities.AbstractEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.TestTransaction;

import javax.inject.Inject;

import static org.springframework.boot.SpringApplication.run;

@SpringBootTest(classes = ControllerIntegrationTest.ControllerIntegrationTestRunner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
public abstract class ControllerIntegrationTest<T extends AbstractEntity, U extends AbstractRepository<T>> {

    @LocalServerPort
    private int port;

    @Inject
    private U abstractRepository;

    public int getPort() {
        return port;
    }

    public abstract void setUpDatabase();

    public abstract void breakDatabase();

    public void clearDataBase(Class<T> abstractClass) {
        abstractRepository.getEntityManager().createQuery(String.format("delete from %s", abstractClass.getSimpleName())).executeUpdate();
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();
    }

    @SpringBootApplication(scanBasePackages = "be.claimed")
    public static class ControllerIntegrationTestRunner {
        public static void main(String[] args) {
            run(ControllerIntegrationTest.ControllerIntegrationTestRunner.class, args);
        }
    }
}
