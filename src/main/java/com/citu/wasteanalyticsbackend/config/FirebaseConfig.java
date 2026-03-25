package com.citu.wasteanalyticsbackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream("wasteanalytics-7f34e-firebase-adminsdk-fbsvc-0bba23c880.json");

        if (serviceAccount == null) {
            throw new FileNotFoundException("firebase-service-account.json not found in resources folder");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }
}
