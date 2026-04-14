package com.citu.wasteanalyticsbackend.entities;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @DocumentId
    private String id;

    private String username;

    private String password;

    private boolean admin;

}