package com.citu.wasteanalyticsbackend.services;

import com.citu.wasteanalyticsbackend.entities.WasteEntry;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class EntryService {

    private final Firestore firestore;

    public EntryService(FirebaseApp firebaseApp) {
        this.firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    public String addEntry(String collectionName, WasteEntry entry) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document();
        docRef.set(entry).get();
        return docRef.getId();
    }

    public List<Map<String, Object>> getAllEntry(String collectionName) throws ExecutionException, InterruptedException {
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
    }

    public List<Map<String, Object>> getEntryById(String collectionName, String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot data = firestore.collection(collectionName)
                .document(id)
                .get()
                .get();

        if (!data.exists()) return Collections.emptyList();

        Map<String, Object> result = data.getData();
        result.put("id", data.getId());
        return List.of(result);
    }

    public boolean deleteEntry(String collectionName, String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot rawData = firestore.collection(collectionName)
                .document(id)
                .get()
                .get();

        if (!rawData.exists()) {
            return false;
        }
        firestore.collection(collectionName).document(rawData.getId()).delete();
        return true;
    }

    public boolean updateEntry(String collectionName, String id, Map<String, Object> updates) throws ExecutionException, InterruptedException {
        DocumentSnapshot rawData = firestore.collection(collectionName)
                .document(id)
                .get()
                .get();

        if (!rawData.exists()) {
            return false;
        }

        firestore.collection(collectionName).document(rawData.getId()).update(updates);
        return true;
    }



}
