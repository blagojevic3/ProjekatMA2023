package com.example.projekatma2023;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class AsocijacijeFragment extends Fragment {

    private FirebaseFirestore db;
    private CollectionReference asocijacijeCollection;

    private Button potvrdiBtn;
    private int points = 0;


    public AsocijacijeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firestore and collection/document references
        db = FirebaseFirestore.getInstance();
        asocijacijeCollection = db.collection("asocijacije"); // Replace with your collection name
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_asocijacije, container, false);

        // Get reference to your buttons using findViewById or data binding

        // Button A1 example
        Button buttonA1 = view.findViewById(R.id.a1);
        Button buttonA2 = view.findViewById(R.id.a2);
        Button buttonA3 = view.findViewById(R.id.a3);
        Button buttonA4 = view.findViewById(R.id.a4);
        Button buttonB1 = view.findViewById(R.id.b1);
        Button buttonB2 = view.findViewById(R.id.b2);
        Button buttonB3 = view.findViewById(R.id.b3);
        Button buttonB4 = view.findViewById(R.id.b4);
        Button buttonC1 = view.findViewById(R.id.c1);
        Button buttonC2 = view.findViewById(R.id.c2);
        Button buttonC3 = view.findViewById(R.id.c3);
        Button buttonC4 = view.findViewById(R.id.c4);
        Button buttonD1 = view.findViewById(R.id.d1);
        Button buttonD2 = view.findViewById(R.id.d2);
        Button buttonD3 = view.findViewById(R.id.d3);
        Button buttonD4 = view.findViewById(R.id.d4);

        EditText konacnoA = view.findViewById(R.id.konacnoA);
        EditText konacnoB = view.findViewById(R.id.konacnoB);
        EditText konacnoC= view.findViewById(R.id.konacnoC);
        EditText konacnoD= view.findViewById(R.id.konacnoD);
        EditText konacnoSve= view.findViewById(R.id.konacnoSve);
        potvrdiBtn = view.findViewById(R.id.potvrdiBtn);

        potvrdiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String konacnoAText = konacnoA.getText().toString().toLowerCase();
                String konacnoBText = konacnoB.getText().toString().toLowerCase();
                String konacnoCText = konacnoC.getText().toString().toLowerCase();
                String konacnoDText = konacnoD.getText().toString().toLowerCase();
                String konacnoSveText = konacnoSve.getText().toString().toLowerCase();

                asocijacijeCollection.document("igra1").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String konacnoAValue = documentSnapshot.getString("konacnoA");
                            String konacnoBValue = documentSnapshot.getString("konacnoB");
                            String konacnoCValue = documentSnapshot.getString("konacnoC");
                            String konacnoDValue = documentSnapshot.getString("konacnoD");
                            String konacnoSveValue = documentSnapshot.getString("konacnoSve");

                            if (konacnoAText.equals(konacnoAValue.toLowerCase())) {
                                konacnoA.setEnabled(false);
                                points+=2;
                            } else {
                                konacnoA.setText("");
                            }

                            if (konacnoBText.equals(konacnoBValue.toLowerCase())) {
                                konacnoB.setEnabled(false);
                                points+=2;
                            } else {
                                konacnoB.setText("");
                            }

                            if (konacnoCText.equals(konacnoCValue.toLowerCase())) {
                                konacnoC.setEnabled(false);
                                points+=2;
                            } else {
                                konacnoC.setText("");
                            }

                            if (konacnoDText.equals(konacnoDValue.toLowerCase())) {
                                konacnoD.setEnabled(false);
                                points+=2;
                            } else {
                                konacnoD.setText("");
                            }
                            if (konacnoSveText.equals(konacnoSveValue.toLowerCase())) {
                                konacnoSve.setEnabled(false);
                                points+=7;
                            } else {
                                konacnoSve.setText("");
                            }
                            if (buttonA1.isClickable() && !konacnoA.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("a1");
                                buttonA1.setText(attributeValue);
                                buttonA1.setClickable(false);

                            }
                            if (buttonA2.isClickable()&& !konacnoA.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("a2");
                                buttonA2.setText(attributeValue);
                                buttonA2.setClickable(false);
                            }
                            if (buttonA3.isClickable()&& !konacnoA.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("a3");
                                buttonA3.setText(attributeValue);
                                buttonA3.setClickable(false);
                            }
                            if (buttonA4.isClickable()&& !konacnoA.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("a4");
                                buttonA4.setText(attributeValue);
                                buttonA4.setClickable(false);
                            }
                            if (buttonB1.isClickable() && !konacnoB.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("b1");
                                buttonB1.setText(attributeValue);
                                buttonB1.setClickable(false);
                            }
                            if (buttonB2.isClickable() && !konacnoB.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("b2");
                                buttonB2.setText(attributeValue);
                                buttonB2.setClickable(false);
                            }
                            if (buttonB3.isClickable() && !konacnoB.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("b3");
                                buttonB3.setText(attributeValue);
                                buttonB3.setClickable(false);
                            }
                            if (buttonB4.isClickable() && !konacnoB.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("b4");
                                buttonB4.setText(attributeValue);
                                buttonB4.setClickable(false);
                            }
                            if (buttonC1.isClickable() && !konacnoC.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("c1");
                                buttonC1.setText(attributeValue);
                                buttonC1.setClickable(false);
                            }
                            if (buttonC2.isClickable() && !konacnoC.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("c2");
                                buttonC2.setText(attributeValue);
                                buttonC2.setClickable(false);
                            }
                            if (buttonC3.isClickable() && !konacnoC.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("c3");
                                buttonC3.setText(attributeValue);
                                buttonC3.setClickable(false);
                            }
                            if (buttonC4.isClickable() && !konacnoC.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("c4");
                                buttonC4.setText(attributeValue);
                                buttonC4.setClickable(false);
                            }
                            if (buttonD1.isClickable() && !konacnoD.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("d1");
                                buttonD1.setText(attributeValue);
                                buttonD1.setClickable(false);
                            }
                            if (buttonD2.isClickable() && !konacnoD.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("d2");
                                buttonD2.setText(attributeValue);
                                buttonD2.setClickable(false);
                            }
                            if (buttonD3.isClickable() && !konacnoD.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("d3");
                                buttonD3.setText(attributeValue);
                                buttonD3.setClickable(false);
                            }
                            if (buttonD4.isClickable() && !konacnoD.isEnabled()) {
                                points += 1;
                                String attributeValue = documentSnapshot.getString("d4");
                                buttonD4.setText(attributeValue);
                                buttonD4.setClickable(false);
                            }
                        }
                    }
                });
            }
        });

        konacnoA.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String inputValue = konacnoA.getText().toString().trim();

                    asocijacijeCollection.document("igra1").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String fieldValue = documentSnapshot.getString("konacnoA");

                                if (TextUtils.equals(inputValue, fieldValue)) {
                                    // Disable the EditText
                                    konacnoA.setEnabled(false);
                                } else {
                                    // Clear the text in the EditText
                                    konacnoA.setText("");
                                }
                            }
                        }
                    });
                    return true;
                }
                return false;
            }
        });



        buttonA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Query the Firestore document to get the desired attribute value
                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    // Retrieve the attribute value from the document
                                    String attributeValue = documentSnapshot.getString("a1");

                                    // Update the button text
                                    buttonA1.setText(attributeValue);

                                    // Disable the button
                                    buttonA1.setClickable(false);
                                }
                            }
                        });
            }
        });

        buttonA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("a2");


                                    buttonA2.setText(attributeValue);

                                    buttonA2.setClickable(false);
                                }
                            }
                        });
            }
        });

        buttonA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("a3");


                                    buttonA3.setText(attributeValue);

                                    buttonA3.setClickable(false);
                                }
                            }
                        });
            }
        });

        buttonA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("a4");

                                    buttonA4.setText(attributeValue);

                                    buttonA4.setClickable(false);
                                }
                            }
                        });
            }
        });

        buttonB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("b1");

                                    buttonB1.setText(attributeValue);

                                    buttonB1.setClickable(false);
                                }
                            }
                        });
            }
        });

        buttonB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("b2");

                                    buttonB2.setText(attributeValue);

                                    buttonB2.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("b3");

                                    buttonB3.setText(attributeValue);

                                    buttonB3.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("b4");

                                    buttonB4.setText(attributeValue);

                                    buttonB4.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("c1");

                                    buttonC1.setText(attributeValue);

                                    buttonC1.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("c2");

                                    buttonC2.setText(attributeValue);

                                    buttonC2.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("c3");

                                    buttonC3.setText(attributeValue);

                                    buttonC3.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("c4");

                                    buttonC4.setText(attributeValue);

                                    buttonC4.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("d1");

                                    buttonD1.setText(attributeValue);

                                    buttonD1.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("d2");

                                    buttonD2.setText(attributeValue);

                                    buttonD2.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("d3");

                                    buttonD3.setText(attributeValue);

                                    buttonD3.setClickable(false);
                                }
                            }
                        });
            }
        });
        buttonD4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asocijacijeCollection.document("igra1").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    String attributeValue = documentSnapshot.getString("d4");

                                    buttonD4.setText(attributeValue);

                                    buttonD4.setClickable(false);
                                }
                            }
                        });
            }
        });


        return view;
    }









}