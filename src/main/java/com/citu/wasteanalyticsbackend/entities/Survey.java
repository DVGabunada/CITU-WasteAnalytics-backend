package com.citu.wasteanalyticsbackend.entities;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @DocumentId
    private String id;

    private String date;

    private String q1;

    private String q2;

    private String q3;

    private String q4;

    private String q5;





}
