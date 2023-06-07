package com.example.projekatma2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.projekatma2023.baza.DBUtil;

import org.w3c.dom.Text;

public class SinglePlayerActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        DBUtil.selectAsocijacije();
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        if (savedInstanceState == null) {
            currentFragment = new AsocijacijeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, currentFragment).commit();
        }

        timerTextView = findViewById(R.id.timer);
        startCountdownTimer();
    }

    @Override
    public void onBackStackChanged() {
        currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        startCountdownTimer();
    }

    private void startCountdownTimer() {
        long countdownDuration = getDurationForFragment(currentFragment);

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(countdownDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timerTextView.setText(String.valueOf(seconds));
            }

            @Override
            public void onFinish() {
                if (currentFragment instanceof MojBrojFragment) {
                    Intent intent = new Intent(SinglePlayerActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    switchToNextFragment();
                }
            }
        };

        countDownTimer.start();
    }

    private long getDurationForFragment(Fragment fragment) {
        if (fragment instanceof AsocijacijeFragment) {
            return 120000; // 120 seconds for AsocijacijeFragment
        } else if (fragment instanceof SkockoFragment) {
            return 30000; // 30 seconds for SkockoFragment
        } else if (fragment instanceof KorakPoKorak) {
            return 70000; // 70 seconds for KorakPoKorak
        } else if (fragment instanceof MojBrojFragment) {
            return 60000;
        } else {
            return 60000; // Default case: 60 seconds for other fragments
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void switchToNextFragment() {
        Fragment nextFragment;

        if (currentFragment instanceof AsocijacijeFragment) {
            nextFragment = new SkockoFragment();
        } else if (currentFragment instanceof SkockoFragment) {
            nextFragment = new KorakPoKorak();
        } else if (currentFragment instanceof KorakPoKorak) {
            nextFragment = new MojBrojFragment();
        } else {
            nextFragment = new AsocijacijeFragment();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, nextFragment);
        fragmentTransaction.commit();
        currentFragment = nextFragment;
        startCountdownTimer();
    }


}