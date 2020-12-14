package fr.houdiard.trivialino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static fr.houdiard.trivialino.FeedReaderDbHelper.DATABASE_NAME;

public class Results extends AppCompatActivity {

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> reponses;
    private ArrayList<Boolean> verified;
    private ArrayList<Integer> categor;
    public int score;
    private TextView tvsc;
    private Button homiB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tvsc = (TextView) findViewById(R.id.tvscore);
        homiB = (Button) findViewById(R.id.homebb);

        Bundle extras = getIntent().getExtras();
        reponses = new ArrayList<ArrayList<String>>(extras.getParcelableArrayList("lesreponses"));
        questions = new ArrayList<String>(extras.getParcelableArrayList("lesquestions"));
        verified = new ArrayList<Boolean>(extras.getParcelableArrayList("checked"));
        categor = new ArrayList<Integer>(extras.getParcelableArrayList("catego"));
        score = compte();
        tvsc.setText("Ton score est de " + score + "/" + verified.size());
        List<ResultReponse> detail = getListData();
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new CustomListAdapter(this, detail));





        for (int i = 0; i<questions.size(); i++) {
            Log.i("Question " + (i+1) + ": ", questions.get(i));
            Log.i("Answered : ", reponses.get(i).get(1));
            Log.i("Correct Answer : ", reponses.get(i).get(0));
            Log.i("Is Correct ?  ", verified.get(i).toString());
            Log.i("Categorie:  ", categor.get(i).toString());
        }


        homiB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillDb();
                Toast.makeText(getApplicationContext(),"Party saved", Toast.LENGTH_SHORT).show();

                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHome);
                finish();
            }
        });
    }


    public int compte() {
        int s = 0;
        for (int i = 0; i < verified.size(); i++) {
            if (verified.get(i) == true) {
                s++;
            }
        }
        return s;
    }

    private List<ResultReponse> getListData() {
        List<ResultReponse> list = new ArrayList<ResultReponse>();

        for (int i =0; i<questions.size(); i++) {
            ResultReponse repI = new ResultReponse(questions.get(i), verified.get(i), reponses.get(i).get(0), reponses.get(i).get(1),"a"+categor.get(i).toString());

            list.add(repI);
        }

        return list;


    }

    private Boolean fillDb() {

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());

        double s = compte();
        double e = verified.size();
        double a = (s/e)*100;


        int sc = Math.round((float) a);

        Log.i("Score", a+"points");
        Log.i("Score", sc+"points");
        ArrayList<Integer> nbqcTab = new ArrayList<Integer>();
        ArrayList<Integer> nbqccTab = new ArrayList<Integer>();
        for (int i = 1; i <= 6; i++) {
            int nbqc = 0;
            int nbqcc = 0;
            for (int j = 0; j < categor.size(); j++) {
                if (categor.get(j) == i) {
                    nbqc++;
                    if (verified.get(j)) {
                        nbqcc++;
                    }
                }
            }
            nbqcTab.add(nbqc);
            nbqccTab.add(nbqcc);
        }

        dbHelper.addParty(sc, nbqcTab.get(0),nbqccTab.get(0) , nbqcTab.get(1), nbqccTab.get(1), nbqcTab.get(2), nbqccTab.get(2), nbqcTab.get(3), nbqccTab.get(3), nbqcTab.get(4), nbqccTab.get(4), nbqcTab.get(5), nbqccTab.get(5));
        return true;


    }


}