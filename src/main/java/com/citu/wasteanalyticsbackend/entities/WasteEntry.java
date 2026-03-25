package com.citu.wasteanalyticsbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteEntry {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String date;

    private String office;

    private String wasteCategory;

    private float weight;

    private String collector;

    private String note;

}
