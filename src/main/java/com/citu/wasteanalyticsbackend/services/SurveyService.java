package com.citu.wasteanalyticsbackend.services;

import com.citu.wasteanalyticsbackend.entities.Survey;
import com.citu.wasteanalyticsbackend.entities.WasteEntry;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    private final Firestore firestore;

    public SurveyService(FirebaseApp firebaseApp) {
        this.firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    public String addSurvey(Survey survey) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("survey").document();
        docRef.set(survey).get();
        return docRef.getId();
    }

/*    public List<Map<String, Object>> getAllEntry(String collectionName) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(collectionName).get();
        QuerySnapshot datas = query.get();
        return datas
                .getDocuments()
                .stream()
                .map(data -> {
                    Map<String, Object> result = data.getData();
                    result.put("id", data.getId());
                    return result;
                })
                .collect(Collectors.toList());
    }*/

    public Map<String, Object> getResponses()
            throws ExecutionException, InterruptedException {

        QuerySnapshot snapshot = firestore.collection("survey").get().get();

        Map<String, Map<String, Integer>> counts = new HashMap<>();
        List<Map<String, Object>> q5Responses = new ArrayList<>();

        for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {
            Map<String, Object> data = doc.getData();

            // 🔁 Loop fields
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value == null) continue;

                // ✅ Handle q5 separately (no counting)
                if (key.equals("q5")) {
                    Map<String, Object> q5Entry = new HashMap<>();
                    q5Entry.put("answer", value);

                    // assuming you store timestamp field like "submittedAt"
                    q5Entry.put("submittedAt", data.get("submittedAt"));

                    q5Responses.add(q5Entry);
                    continue;
                }

                // ✅ Handle q1–q4 (counting)
                String answer = String.valueOf(value);

                counts.putIfAbsent(key, new HashMap<>());
                Map<String, Integer> answerCounts = counts.get(key);

                answerCounts.put(answer, answerCounts.getOrDefault(answer, 0) + 1);
            }
        }

        // ✅ Final response
        Map<String, Object> result = new HashMap<>();
        result.put("counts", counts);
        result.put("q5Responses", q5Responses);
        result.put("totalResponses", snapshot.size());

        return result;
    }
}
