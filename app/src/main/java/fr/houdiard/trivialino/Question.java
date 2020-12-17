package fr.houdiard.trivialino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Question extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private TextView tv;
    private Button results;
    private Button home;
    private TextView numQues;
    private ImageView imacate;

    public static AReponse r1 = new AReponse("", false);
    public static AReponse r2 = new AReponse("", false);
    public static AReponse r3 = new AReponse("", false);
    public static AReponse r4 = new AReponse("", false);
    public static String question = "";
    public static ArrayList<String> caa = new ArrayList<String>();
    public ArrayList<Boolean> bool = new ArrayList<Boolean>();

    private ArrayList<String> questionS = new ArrayList<String>();
    private ArrayList<ArrayList<String>> reponses = new ArrayList<ArrayList<String>>();
    public static ArrayList<Integer> categs = new ArrayList<Integer>();





    private String diff;
    public static int nbQ;
    public static int numQ = 1;
    private ArrayList<ArrayList<String>> cate;
    private String QuestionCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        tv = (TextView) findViewById(R.id.question);
        results = (Button) findViewById(R.id.result);
        numQues = (TextView) findViewById(R.id.numeroquestion);
        imacate = (ImageView) findViewById(R.id.categimage);
        home = (Button) findViewById(R.id.homeb);
        categs = new ArrayList<Integer>();



        Bundle extras = getIntent().getExtras();
        nbQ = (int) extras.getInt("nbQ");
        diff = new String(extras.getString("difficulty"));
        cate = new ArrayList<ArrayList<String>>(extras.getParcelableArrayList("categorie"));






        getAQuestion();


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHome);
                finish();
            }
        });


        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent seeResults = new Intent(getApplicationContext(), Results.class);
                seeResults.putExtra("lesquestions", questionS);
                seeResults.putExtra("lesreponses", reponses);
                seeResults.putExtra("checked", bool);
                seeResults.putExtra("catego", categs);
                startActivity(seeResults);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numQ <= nbQ) {

                    questionS.add(question);
                    caa.add(r1.getReponse());
                    bool.add(r1.getVerif());
                    reponses.add(caa);
                    caa = new ArrayList<String>();
                    numQ++;
                    if (numQ != nbQ +1) {
                        getAQuestion();
                    }


                }

                if (numQ == nbQ+1) {
                    results.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.INVISIBLE);
                    numQ = 1;

                }


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numQ <= nbQ) {

                    questionS.add(question);
                    caa.add(r2.getReponse());
                    bool.add(r2.getVerif());
                    reponses.add(caa);
                    caa = new ArrayList<String>();
                    numQ++;
                    if (numQ != nbQ +1) {
                        getAQuestion();
                    }
                }

                if (numQ == nbQ+1) {
                    results.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.INVISIBLE);
                    numQ = 1;
                }


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numQ <= nbQ) {

                    questionS.add(question);
                    caa.add(r3.getReponse());
                    bool.add(r3.getVerif());
                    reponses.add(caa);
                    caa = new ArrayList<String>();
                    numQ++;
                    if (numQ != nbQ +1) {
                        getAQuestion();
                    }
                }

                if (numQ == nbQ+1) {
                    results.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.INVISIBLE);
                    numQ = 1;
                }


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numQ <= nbQ) {

                    questionS.add(question);
                    caa.add(r4.getReponse());
                    bool.add(r4.getVerif());
                    reponses.add(caa);
                    caa = new ArrayList<String>();
                    numQ++;
                    if (numQ != nbQ +1) {
                        getAQuestion();
                    }
                }

                if (numQ == nbQ +1) {
                    results.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.INVISIBLE);
                    numQ = 1;
                }


            }
        });
    }

    public void getAQuestion() {
        String url = getQuestionUrl();



        AsyncQuestionJSON async = new AsyncQuestionJSON(this);
        async.execute(url);


        //AQuestion theQuestion = new AQuestion(cA, bA1, bA2, bA3, q, cat, dif);


    }

    public int pickACateg() {
        int nbCate = cate.size();
        Random random1 = new Random();
        int cateselected = random1.nextInt(nbCate);
        QuestionCategory = cate.get(cateselected).get(0);
        return cateselected;

    }

    public String pickAnId() {
        int cateSelected = pickACateg();

        int nbIds = cate.get(cateSelected).size();
        int pickedId = genererInt(1,nbIds);

        String id = cate.get(cateSelected).get(pickedId);
        return id;
    }

    public String getQuestionUrl() {
        String ajout1 = "";
        String ajout2 = "";

        if (cate.get(0).get(0).equals("Any")) {
            if (diff.equals("Any difficulty")) {

            } else {
                ajout2 = "difficulty=" + diff + "&";

            }
        } else {
            ajout1 = "category=" + pickAnId() + "&";
            if (diff.equals("Any difficulty")) {

            } else {
                ajout2 = "difficulty=" + diff + "&";
            }
        }
        String url = "https://opentdb.com/api.php?amount=1&" + ajout1 + ajout2 + "type=multiple" ;
        return url;

    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }








}