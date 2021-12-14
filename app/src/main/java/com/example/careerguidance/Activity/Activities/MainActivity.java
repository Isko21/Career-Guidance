package com.example.careerguidance.Activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.careerguidance.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> questions;
    Button next;
    Button back;
    TextView question1;
    TextView question2;
    TextView question3;
    TextView question4;
    TextView question5;
    TextView nav;
    PesonalData pesonalData;
    RadioGroup e;
    RadioGroup a;
    RadioGroup c;
    RadioGroup n;
    RadioGroup o;
    int value_e;
    int value_a;
    int value_c;
    int value_n;
    int value_o;
    int page = 1;
    ArrayList<Integer> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pesonalData = new PesonalData();
        questions = readDataFromTxt("Question.txt");
        question1 = (TextView) findViewById(R.id.q1);
        question1.setText(questions.get(0).trim());
        question2 = (TextView) findViewById(R.id.q2);
        question2.setText(questions.get(1).trim());
        question3 = (TextView) findViewById(R.id.q3);
        question3.setText(questions.get(2).trim());
        question4 = (TextView) findViewById(R.id.q4);
        question4.setText(questions.get(3).trim());
        question5 = (TextView) findViewById(R.id.q5);
        values = new ArrayList<>();
        question5.setText(questions.get(4).trim());
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        e = (RadioGroup)findViewById(R.id.gr1);
        a = (RadioGroup) findViewById(R.id.gr2);
        c = (RadioGroup)findViewById(R.id.gr3);
        n = (RadioGroup)findViewById(R.id.gr4);
        o = (RadioGroup)findViewById(R.id.gr5);
        e.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = e.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_e = getId(radioButton);
                next.setEnabled(isPressed(e, a, c, n, o));
            }
        });
        a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = a.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_a = getId(radioButton);
                next.setEnabled(isPressed(e, a, c, n, o));
            }
        });
        c.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = c.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_c = getId(radioButton);
                next.setEnabled(isPressed(e, a, c, n, o));
            }
        });
        n.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = n.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_n = getId(radioButton);
                next.setEnabled(isPressed(e, a, c, n, o));
            }
        });
        o.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = o.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_o = getId(radioButton);
                next.setEnabled(isPressed(e, a, c, n, o));
            }
        });
        nav = (TextView)findViewById(R.id.nav);
        nav.setText(page + "/6");
        next = (Button) findViewById(R.id.next);
        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.add(value_e);
                values.add(value_a);
                values.add(value_c);
                values.add(value_n);
                values.add(value_o);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putStringArrayListExtra("questions", questions);
                intent.putIntegerArrayListExtra("values", values);
                startActivity(intent);
            }
        });
    }
    public int getId(RadioButton r){
        String id_string = getResources().getResourceEntryName(r.getId());
        return Integer.parseInt(id_string.substring(1));
    }
    public ArrayList<String> readDataFromTxt(String fileName){
        ArrayList<String> questions = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader((getAssets().open(fileName)), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null){
                questions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return questions;
    }
    public boolean isPressed(RadioGroup e, RadioGroup a, RadioGroup c, RadioGroup n, RadioGroup o){
        return e.getCheckedRadioButtonId() != -1 && o.getCheckedRadioButtonId() != -1 && a.getCheckedRadioButtonId() != -1 && n.getCheckedRadioButtonId() != -1 && c.getCheckedRadioButtonId() != -1;
    }
}