package chiralsoftware.fcmspringjavascript;

import static chiralsoftware.fcmspringjavascript.controllers.FcmRegister.tokens;
import static com.google.auth.oauth2.GoogleCredentials.fromStream;
import static com.google.firebase.FirebaseApp.initializeApp;
import static com.google.firebase.FirebaseOptions.Builder;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Random;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 */
@Configuration
public class FcmConfig {

    private static final Logger LOG = getLogger(FcmConfig.class.getName());

    private static final String path = "path/to/XXXXX-firebase-adminsdk.json";

    private final Random random = new Random();

    @PostConstruct
    public void configure() throws Exception {
        LOG.info("I'm configuring FCM now");

        initializeApp(new Builder()
                .setCredentials(fromStream(new FileInputStream(path)))
                .setDatabaseUrl("https://XXXXXXXX.firebaseio.com")
                .build());
        LOG.info("I did the initliazation");
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void sendMessage() throws FirebaseMessagingException {
        final FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        tokens.forEach(s -> {
            if(s.length() != 152) {
                LOG.info("Token value is not 152 characters so this won't work");
                return;
            }
            final Message message = Message.builder().
                    putData("theAnswer", "the answer is: " + random.nextInt(100000)).
                    putData("time", new Date().toString()).
                    setToken(s).
                    build();
            final String response;
            try {
                response = firebaseMessaging.send(message);
                LOG.info("I got this response: " + response);
            } catch (FirebaseMessagingException ex) {
                LOG.log(SEVERE, "oh no!", ex);
            }
        });

    }

}
