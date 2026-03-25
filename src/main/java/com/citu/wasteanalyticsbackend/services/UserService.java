package com.citu.wasteanalyticsbackend.services;

import com.citu.wasteanalyticsbackend.entities.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Firestore firestore;

    public UserService(FirebaseApp firebaseApp) {
        this.firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    // ====================== CRUD For Users ====================== //

    public String addUser(String collectionName, User data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document();
        docRef.set(data).get();
        return docRef.getId();
    }

    public List<Map<String, Object>> getAllUsers(String collectionName) throws ExecutionException, InterruptedException {
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

    public List<Map<String, Object>> getByUsername(String collectionName, String username) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(collectionName)
                .whereEqualTo("username", username)
                .get();
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

    public boolean updateUser(String collectionName, String username, Map<String, Object> updates) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(collectionName)
                .whereEqualTo("username", username)
                .get();
        QuerySnapshot querySnapshot = query.get();
        if (querySnapshot.isEmpty()) {
            return false;
        }
        for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
            firestore.collection(collectionName).document(doc.getId()).update(updates);
        }
        return true;
    }

    public boolean deleteUser(String collectionName, String username) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(collectionName)
                .whereEqualTo("username", username)
                .get();
        QuerySnapshot querySnapshot = query.get();
        if (querySnapshot.isEmpty()) {
            return false;
        }

        for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
            firestore.collection(collectionName).document(doc.getId()).delete();
        }
        return true;
    }


}