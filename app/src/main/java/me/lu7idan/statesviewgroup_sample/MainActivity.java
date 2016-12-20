package me.lu7idan.statesviewgroup_sample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.lu7idan.statesviewgroup.StatesViewGroup;

public class MainActivity extends AppCompatActivity {

    StatesViewGroup signIn;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = (StatesViewGroup) findViewById(R.id.states_view_group);

    }

    public void signIn(View view) {
        signIn.showState(R.id.signin_progress);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                signIn.showState(R.id.signin_btn);
            }
        }, 5000);
    }
}
