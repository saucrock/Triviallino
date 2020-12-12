package fr.houdiard.trivialino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    private Button home;
    private ListView stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        home = (Button) findViewById(R.id.stathome);
        stat = (ListView) findViewById(R.id.statlist);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(stat.getContext(),R.layout.montext);
        ArrayList<ArrayList<Integer>> data = dbHelper.loadHandler();

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < 14; j++) {
                adapter.add(data.get(i).get(j));
            }
        }
        stat.setAdapter(adapter);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
                finish();
            }
        });

    }
}