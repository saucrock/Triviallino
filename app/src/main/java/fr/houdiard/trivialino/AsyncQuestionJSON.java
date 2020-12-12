package fr.houdiard.trivialino;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;


public class AsyncQuestionJSON extends AsyncTask<String, Void, JSONObject> {

    private AReponse ra1 = new AReponse("", false);
    private AReponse ra2 = new AReponse("", false);
    private AReponse ra3 = new AReponse("", false);
    private AReponse ra4 = new AReponse("", false);

    private AppCompatActivity myActivity;


   public AsyncQuestionJSON(AppCompatActivity a) {
       myActivity = a;
   }



    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url = null;
        String s = null;
        JSONObject json = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                s = readStream(in);


            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            json = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;

    }


    @Override
    protected void onPostExecute(JSONObject json) {

        if (json != null) {
            Log.i("HH", "J'ai une Question !");
            try {
                JSONArray array = json.getJSONArray("results");
                JSONObject objet = array.getJSONObject(0);

                String categ = objet.getString("category");
                String qu = objet.getString("question");
                String ca = objet.getString("correct_answer");
                JSONArray tableauBA = objet.getJSONArray("incorrect_answers");
                String mr1 = (String) tableauBA.get(0);
                String mr2 = (String) tableauBA.get(1);
                String mr3 = (String) tableauBA.get(2);
                String di = objet.getString("difficulty");

                Question.caa.add(ca);
                Question.categs.add(getCate(categ));

                ra1.setReponse(ca);
                ra1.setVerif(true);
                ra2.setReponse(mr1);
                ra3.setReponse(mr2);
                ra4.setReponse(mr3);


                chargeQuestion(qu, getCate(categ));






                Log.i("HH", "Question inésrée !");
                Log.i("HH", mr1);
                Log.i("HH", mr2);
                Log.i("HH", mr3);
                Log.i("HH", ca);
                Log.i("HH", categ);
                Log.i("je suis", ""+getCate(categ));




            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.i("HH", "J'ai pas de question :/");
        }

    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    private void chargeQuestion(String a, int b) {
        TextView tvQ = (TextView) myActivity.findViewById(R.id.question);
        Button b1 = (Button) myActivity.findViewById(R.id.b1);
        Button b2 = (Button) myActivity.findViewById(R.id.b2);
        Button b3 = (Button) myActivity.findViewById(R.id.b3);
        Button b4 = (Button) myActivity.findViewById(R.id.b4);
        TextView nq = (TextView) myActivity.findViewById(R.id.numeroquestion);
        ImageView ci = (ImageView) myActivity.findViewById(R.id.categimage);





        ArrayList<AReponse> reponses= new ArrayList<AReponse>();
        reponses.add(ra1);
        reponses.add(ra2);
        reponses.add(ra3);
        reponses.add(ra4);

        Collections.shuffle(reponses);

        Question.question = a;

        Question.r1.setReponse(reponses.get(0).getReponse());
        Question.r1.setVerif(reponses.get(0).getVerif());

        Question.r2.setReponse(reponses.get(1).getReponse());
        Question.r2.setVerif(reponses.get(1).getVerif());

        Question.r3.setReponse(reponses.get(2).getReponse());
        Question.r3.setVerif(reponses.get(2).getVerif());

        Question.r4.setReponse(reponses.get(3).getReponse());
        Question.r4.setVerif(reponses.get(3).getVerif());



        nq.setText("Question " + (Question.numQ) + "/" + Question.nbQ);
        if ( b ==1) {
            ci.setImageResource(R.mipmap.a1);
            b1.setBackgroundColor(Color.rgb(71,171,18));
            b2.setBackgroundColor(Color.rgb(71,171,18));
            b3.setBackgroundColor(Color.rgb(71,171,18));
            b4.setBackgroundColor(Color.rgb(71,171,18));            }
        if ( b ==2) {
            ci.setImageResource(R.mipmap.a2);
            b1.setBackgroundColor(Color.rgb(239,239,37));
            b2.setBackgroundColor(Color.rgb(239,239,37));
            b3.setBackgroundColor(Color.rgb(239,239,37));
            b4.setBackgroundColor(Color.rgb(239,239,37));
        }
        if ( b ==3) {
            ci.setImageResource(R.mipmap.a3);
            b1.setBackgroundColor(Color.rgb(105,70,63));
            b2.setBackgroundColor(Color.rgb(105,70,63));
            b3.setBackgroundColor(Color.rgb(105,70,63));
            b4.setBackgroundColor(Color.rgb(105,70,63));
        }
        if ( b ==4) {
            ci.setImageResource(R.mipmap.a4);
            b1.setBackgroundColor(Color.rgb(39,72,187));
            b2.setBackgroundColor(Color.rgb(39,72,187));
            b3.setBackgroundColor(Color.rgb(39,72,187));
            b4.setBackgroundColor(Color.rgb(39,72,187));
        }
        if ( b ==5) {
            ci.setImageResource(R.mipmap.a5);
            b1.setBackgroundColor(Color.rgb(245,136,13));
            b2.setBackgroundColor(Color.rgb(245,136,13));
            b3.setBackgroundColor(Color.rgb(245,136,13));
            b4.setBackgroundColor(Color.rgb(245,136,13));
        }
        if ( b ==6) {
            ci.setImageResource(R.mipmap.a6);
            b1.setBackgroundColor(Color.rgb(245,34,13));
            b2.setBackgroundColor(Color.rgb(245,34,13));
            b3.setBackgroundColor(Color.rgb(245,34,13));
            b4.setBackgroundColor(Color.rgb(245,34,13));
        }

        tvQ.setText(a);
        b1.setText(Question.r1.getReponse());
        b2.setText(Question.r2.getReponse());
        b3.setText(Question.r3.getReponse());
        b4.setText(Question.r4.getReponse());




    }

    public boolean isGeneral (String s) {
       if (s.equals("General Knowledge")) {
           return true;
       } else {
           return false;
       }

    }

    public boolean isArt (String s) {
       if (s.equals("Entertainment: Books") || s.equals("Entertainment: Musicals & Theatres") || s.equals("Art")) {
           return true;
       } else {
           return false;
       }
    }

    public boolean isTel (String s) {
        if (s.equals("Entertainment: Film") || s.equals("Entertainment: Television") || s.equals("Celebrities") || s.equals("Entertainment: Japanese Anime & Manga") || s.equals("Entertainment: Cartoon & Animations")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHis (String s) {
        if (s.equals("Mythology") || s.equals("Geography") || s.equals("History") || s.equals("Politics")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSci (String s) {
        if (s.equals("Science & Nature") || s.equals("Science: Computers") || s.equals("Science: Mathematics") || s.equals("Animals") || s.equals("Vehicles") || s.equals("Science: Gadgets")){
            return true;
        } else {
            return false;
        }
    }

    public boolean isSpo (String s) {
        if (s.equals("Entertainment: Music") || s.equals("Entertainment: Video Games") || s.equals("Entertainment: Board Games") || s.equals("Sports") || s.equals("Entertainment: Comics")) {
            return true;
        } else {
            return false;
        }
    }

    public int getCate(String s) {
       if (isSci(s)) { return 1;}
       if (isHis(s)) { return 2;}
       if (isArt(s)) { return 3;}
       if (isTel(s)) { return 4;}
       if (isSpo(s)) { return 5;}
       else { return 6 ;}




    }






}
