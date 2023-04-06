//package com.example.base.config;
//
//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.QueryDocumentSnapshot;
//import com.google.cloud.firestore.QuerySnapshot;
//import com.google.firebase.cloud.FirestoreClient;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import lombok.extern.slf4j.Slf4j;
//import com.example.base.entity.Member;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@Slf4j
//public class UserDao {
//
//    public static final String COLLECTION_NAME = "users";
//
//    public List<Member> getUsers() throws ExecutionException, InterruptedException {
//        List<Member> list = new ArrayList<>();
//        Firestore db = FirestoreClient.getFirestore();
//        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//        for (QueryDocumentSnapshot document : documents) {
//            list.add(document.toObject(Member.class));
//        }
//        return list;
//    }
//
//}