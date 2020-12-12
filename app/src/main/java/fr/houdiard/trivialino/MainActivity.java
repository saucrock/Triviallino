package fr.houdiard.trivialino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button scores;
    private Button play;
    private Button rules;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.scores = (Button) findViewById(R.id.scoreButton);
        this.play = (Button) findViewById(R.id.playButton);
        this.rules = (Button) findViewById(R.id.rulesButton);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPlay = new Intent(getApplicationContext(),Parameters.class);
                startActivity(goPlay);
                finish();
            }
        });

        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Statistics.class);
                startActivity(a);
                finish();
            }
        });


    }
}