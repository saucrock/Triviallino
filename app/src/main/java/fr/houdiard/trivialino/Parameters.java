package fr.houdiard.trivialino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parameters extends AppCompatActivity {

    private Button home;
    private Button validate;
    private Button start;
    private Spinner spin;
    private RadioGroup diffselect;

    private int nbQuestion;
    private ArrayList<ArrayList<String>> categorieSelected;
    private String difficulty;

    private CheckBox cbAny;
    private CheckBox cbScience;
    private CheckBox cbHistoire;
    private CheckBox cbArt;
    private CheckBox cbTel;
    private CheckBox cbSport;
    private CheckBox cbCulG;
    private RadioButton mediumB;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        this.home = (Button) findViewById(R.id.homeButton);
        this.validate = (Button) findViewById(R.id.validateButton);
        this.start = (Button) findViewById(R.id.StartButton);
        start.setEnabled(false);
        this.spin = (Spinner) findViewById(R.id.spinner);
        this.diffselect = (RadioGroup) findViewById(R.id.radiogroupdifficulty);
        this.mediumB = (RadioButton) findViewById(R.id.mediumone);
        mediumB.setChecked(true);

        this.cbAny = (CheckBox) findViewById(R.id.cb_any);
        this.cbScience = (CheckBox) findViewById(R.id.cb_sciences);
        this.cbArt = (CheckBox) findViewById(R.id.cb_art);
        this.cbTel = (CheckBox) findViewById(R.id.cb_television);
        this.cbSport = (CheckBox) findViewById(R.id.cb_sport);
        this.cbCulG = (CheckBox) findViewById(R.id.cb_cultureG);
        this.cbHistoire = (CheckBox) findViewById(R.id.cb_histoire);


        getSpinner();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent demarerlequizz = new Intent (getApplicationContext(), Question.class );
                demarerlequizz.putExtra("nbQ", nbQuestion );
                demarerlequizz.putExtra("difficulty", difficulty);
                demarerlequizz.putExtra("categorie", categorieSelected);
                startActivity(demarerlequizz);
                finish();
            }
        });






        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verify()) {
                    int selectedId = diffselect.getCheckedRadioButtonId();
                    RadioButton selectedButton = (RadioButton) findViewById(selectedId);



                    difficulty = selectedButton.getText().toString();
                    nbQuestion = (int) spin.getSelectedItem();
                    categorieSelected = getIds();




                    for (int i=0; i < categorieSelected.size(); i++) {

                        for (int j=1; j<categorieSelected.get(i).size(); j++) {
                        }
                    }

                    validate.setEnabled(false);
                    start.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(),"Please Select at least one category", Toast.LENGTH_SHORT).show();
                }

            }
        });


        cbAny.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toutcoche(isChecked);
            }
        });









        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nbQuestion = (int) spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHome);
                finish();
            }
        });
    }

    private void getSpinner() {
        Integer[] tableauNombres = new Integer[46];
        for (int i = 0; i < 46; i++) {
            tableauNombres[i] = i + 3;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,tableauNombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

    }

    private void toutcoche(boolean isChecked) {
        this.cbCulG.setChecked(isChecked);
        this.cbScience.setChecked(isChecked);
        this.cbHistoire.setChecked(isChecked);
        this.cbArt.setChecked(isChecked);
        this.cbSport.setChecked(isChecked);
        this.cbTel.setChecked(isChecked);
    }

    private ArrayList<ArrayList<String>> getIds() {
        ArrayList<ArrayList<String>> cate = new ArrayList<ArrayList<String>>();
        if (cbAny.isChecked()) {
            ArrayList<String> a = new ArrayList<String>();
            a.add("Any");
            cate.add(a);
            return cate;

        } else {
            if (cbCulG.isChecked()) {
                ArrayList<String> a = new ArrayList<String>();
                a.add("Culture G");
                a.add("9");
                cate.add(a);
            }
            if (cbSport.isChecked()) {
                ArrayList<String> a = new ArrayList<String>();
                a.add("Sport et divertissement");
                a.add("12");
                a.add("15");
                a.add("16");
                a.add("21");
                a.add("29");
                cate.add(a);
            }
            if (cbHistoire.isChecked()) {
                ArrayList<String> a = new ArrayList<String>();
                a.add("Histoire et géographie");
                a.add("20");
                a.add("22");
                a.add("23");
                a.add("24");
                cate.add(a);
            }

            if (cbScience.isChecked()) {
                ArrayList<String> a = new ArrayList<String>();
                a.add("Sciences et technologies");
                a.add("17");
                a.add("18");
                a.add("19");
                a.add("27");
                a.add("28");
                a.add("30");
                cate.add(a);
            }

            if (cbArt.isChecked()) {
                ArrayList<String> a = new ArrayList<String>();
                a.add("Art et littérature");
                a.add("10");
                a.add("13");
                a.add("25");
                cate.add(a);
            }

            if (cbTel.isChecked()) {
                ArrayList<String> a = new ArrayList<String>();
                a.add("Télévision et Musique");
                a.add("11");
                a.add("14");
                a.add("26");
                a.add("31");
                a.add("32");
                cate.add(a);
            }

            return cate;
        }


    }

    private Boolean verify() {
        if (cbAny.isChecked() || cbArt.isChecked() || cbHistoire.isChecked() || cbScience.isChecked() || cbSport.isChecked() || cbCulG.isChecked() || cbTel.isChecked()) {
            return true;
        } else {
            return false;
        }
    }
}