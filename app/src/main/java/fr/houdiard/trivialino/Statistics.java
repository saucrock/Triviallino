package fr.houdiard.trivialino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static fr.houdiard.trivialino.FeedReaderDbHelper.DATABASE_NAME;
import static java.lang.Float.NaN;

public class Statistics extends AppCompatActivity {

    private Button home;
    private ListView stat;
    private Button del;
    private TextView nbP;
    private TextView nbQ;
    private TextView avS;
    private TextView cA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        home = (Button) findViewById(R.id.stathome);
        stat = (ListView) findViewById(R.id.statlist);
        del = (Button) findViewById(R.id.delete);
        nbP = (TextView) findViewById(R.id.playedparties);
        nbQ = (TextView) findViewById(R.id.questionanswered);
        cA = (TextView) findViewById(R.id.correctanswered);

        avS = (TextView) findViewById(R.id.averagescore);



        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());

        nbP.setText("Nombre de parties jouées : " + dbHelper.nbPP());
        nbQ.setText("Nombre de questions posées : " + dbHelper.nbQA());
        cA.setText("Nombre de Bonnes Réponses : " + dbHelper.gA());
        avS.setText("Score Moyen : " + dbHelper.scoreMoy() + " %");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(stat.getContext(),R.layout.montext);
        ArrayList<String> cateName = new ArrayList<String>();

        cateName.add("Sciences et Technologies");
        cateName.add("Histoire et Géographie");
        cateName.add("Art et Littérature");
        cateName.add("Télévision et Cinéma");
        cateName.add("Sports et Divertissements");
        cateName.add("Culture Générale");

        for (int i = 1; i < 7; i++) {
            double a = dbHelper.nbQC(i);
            double b = dbHelper.nbQCC(i);
            double r = (b/a) * 100;


            int res = Math.round( (float) r);
            if (res != NaN) {
                adapter.add(cateName.get(i - 1) + " : " + res + " %");
            } else {
                adapter.add("Pas encore de questions posées dans cette catégorie");
            }

        }
        stat.setAdapter(adapter);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().deleteDatabase(DATABASE_NAME);
                Toast.makeText(getApplicationContext(),"Data Deleted", Toast.LENGTH_SHORT).show();
                del.setEnabled(false);

            }
        });


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