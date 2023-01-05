package csed.swe.studentunity.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableConfigurationProperties(FirebaseProperties.class)
public class NotificationConfig {


    private final FirebaseProperties firebaseProperties;

    public NotificationConfig(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials googleCredentials() throws IOException {
        if (firebaseProperties.getServiceAccount() != null) {
            try (InputStream is = firebaseProperties.getServiceAccount().getInputStream()) {
                return GoogleCredentials.fromStream(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            // Use standard credentials chain. Useful when running inside GKE
            return GoogleCredentials.getApplicationDefault();
        }
    }

}
