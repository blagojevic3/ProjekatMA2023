package com.example.projekatma2023.baza;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.projekatma2023.model.Asocijacije;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class DBUtil {

    public static void initDB(){




        FirebaseFirestore db =FirebaseFirestore.getInstance();



    }

    public static void selectAsocijacije(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("asocijacije")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("REZ_DB", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("REZ_DB", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}
