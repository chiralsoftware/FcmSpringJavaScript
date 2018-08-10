package chiralsoftware.fcmspringjavascript;

import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@SpringBootApplication
@ComponentScan({"chiralsoftware.fcmspringjavascript"})
@EnableScheduling
public class Application extends SpringBootServletInitializer {
    private static final Logger LOG = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        LOG.info("Starting applicaiton");
        SpringApplication.run(Application.class, args);
    }

}
