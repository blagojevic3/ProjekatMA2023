package com.example.projekatma2023;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projekatma2023.baza.DBUtil;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.Timer;
import java.util.TimerTask;


public class KorakPoKorak extends Fragment {
    private FirebaseFirestore db;
    private CollectionReference korakpokorakCollection;

    private TextView sentence1, sentence2, sentence3,
            sentence4, sentence5, sentence6,
            sentence7, gameTerm;
    private EditText answerInput;
    private Button confirmBtn;
    private CountDownTimer timer;
    private int currentSentenceIndex = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();
        korakpokorakCollection = db.collection("korakpokorak");

        // Create a 10-second timer
        timer = new CountDownTimer(10000, 10000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTextViews();
            }

            @Override
            public void onFinish() {
                // Timer finished, do any necessary cleanup or actions
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_korak_po_korak, container, false);

        // Initialize TextViews, EditText, and Button
        sentence1 = view.findViewById(R.id.sentence1);
        sentence2 = view.findViewById(R.id.sentence2);
        sentence3 = view.findViewById(R.id.sentence3);
        sentence4 = view.findViewById(R.id.sentence4);
        sentence5 = view.findViewById(R.id.sentence5);
        sentence6 = view.findViewById(R.id.sentence6);
        sentence7 = view.findViewById(R.id.sentence7);
        gameTerm = view.findViewById(R.id.gameTerm);
        answerInput = view.findViewById(R.id.answerInput);
        confirmBtn = view.findViewById(R.id.confirmBtn);

        // Set OnClickListener for the confirm button
        confirmBtn.setOnClickListener(v -> {
            confirmAnswer();
        });

        return view;
    }

    private void updateTextViews() {
        korakpokorakCollection.document("igra1").get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String sentence1Text = documentSnapshot.getString("sentence1");
                        String sentence2Text = documentSnapshot.getString("sentence2");
                        String sentence3Text = documentSnapshot.getString("sentence3");
                        String sentence4Text = documentSnapshot.getString("sentence4");
                        String sentence5Text = documentSnapshot.getString("sentence5");
                        String sentence6Text = documentSnapshot.getString("sentence6");
                        String sentence7Text = documentSnapshot.getString("sentence7");

                        // Update the TextViews with the fetched data based on currentSentenceIndex
                        switch (currentSentenceIndex) {
                            case 1:
                                sentence1.setText(sentence1Text);
                                break;
                            case 2:
                                sentence2.setText(sentence2Text);
                                break;
                            case 3:
                                sentence3.setText(sentence3Text);
                                break;
                            case 4:
                                sentence4.setText(sentence4Text);
                                break;
                            case 5:
                                sentence5.setText(sentence5Text);
                                break;
                            case 6:
                                sentence6.setText(sentence6Text);
                                break;
                            case 7:
                                sentence7.setText(sentence7Text);
                                timer.cancel(); // Stop the timer after all sentences have been replaced
                                break;
                        }

                        // Increment the currentSentenceIndex for the next iteration
                        currentSentenceIndex++;

                        // If all sentences have been replaced, stop the timer
                        if (currentSentenceIndex > 7) {
                            timer.cancel();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                });
    }

    private void scheduleSentenceUpdates() {
        // Create a timer task to update the sentences
        TimerTask updateTask = new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> updateTextViews());
                }
            }
        };

        // Schedule the task to run every ten seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(updateTask, 0, 10000);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Start scheduling sentence updates when the fragment resumes
        scheduleSentenceUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Cancel the timer when the fragment is paused
        timer.cancel();
    }

    private void confirmAnswer() {
        String answer = answerInput.getText().toString().trim();

        korakpokorakCollection.document("igra1").get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String gameTermText = documentSnapshot.getString("gameTerm");

                        if (answer.equals(gameTermText)) {
                            gameTerm.setText(gameTermText);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                });
    }
}