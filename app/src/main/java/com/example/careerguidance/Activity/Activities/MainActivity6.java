package com.example.careerguidance.Activity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.careerguidance.R;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    TextView question1;
    TextView question2;
    TextView question3;
    TextView question4;
    TextView question5;
    Intent intent;
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
    Button next;
    Button back;
    TextView nav;
    int page = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main6);
        intent = getIntent();
        ArrayList<String> questions = intent.getStringArrayListExtra("questions");
        question1 = (TextView) findViewById(R.id.q1);
        ArrayList<Integer> values = intent.getIntegerArrayListExtra("values");
        question1.setText(questions.get(25).trim());
        pesonalData = new PesonalData();
        question2 = (TextView) findViewById(R.id.q2);
        question2.setText(questions.get(26).trim());
        question3 = (TextView) findViewById(R.id.q3);
        question3.setText(questions.get(27).trim());
        question4 = (TextView) findViewById(R.id.q4);
        question4.setText(questions.get(28).trim());
        question5 = (TextView) findViewById(R.id.q5);
        question5.setText(questions.get(29).trim());
        next = (Button) findViewById(R.id.next);
        nav = (TextView) findViewById(R.id.nav);
        nav.setText(page + "/6");
        e = (RadioGroup)findViewById(R.id.gr1);
        e.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = e.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_e = getId(radioButton);
                if(e.getCheckedRadioButtonId() != -1 && o.getCheckedRadioButtonId() != -1 && a.getCheckedRadioButtonId() != -1 && n.getCheckedRadioButtonId() != -1 && c.getCheckedRadioButtonId() != -1){
                    next.setEnabled(true);
                }
            }
        });
        a = (RadioGroup) findViewById(R.id.gr2);
        a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = a.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_a = getId(radioButton);
                if(e.getCheckedRadioButtonId() != -1 && o.getCheckedRadioButtonId() != -1 && a.getCheckedRadioButtonId() != -1 && n.getCheckedRadioButtonId() != -1 && c.getCheckedRadioButtonId() != -1){
                    next.setEnabled(true);
                }
            }
        });
        c = (RadioGroup)findViewById(R.id.gr3);
        c.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = c.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_c = getId(radioButton);
                if(e.getCheckedRadioButtonId() != -1 && o.getCheckedRadioButtonId() != -1 && a.getCheckedRadioButtonId() != -1 && n.getCheckedRadioButtonId() != -1 && c.getCheckedRadioButtonId() != -1){
                    next.setEnabled(true);
                }
            }
        });
        n = (RadioGroup)findViewById(R.id.gr4);
        n.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = n.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_n = getId(radioButton);
                if(e.getCheckedRadioButtonId() != -1 && o.getCheckedRadioButtonId() != -1 && a.getCheckedRadioButtonId() != -1 && n.getCheckedRadioButtonId() != -1 && c.getCheckedRadioButtonId() != -1){
                    next.setEnabled(true);
                }
            }
        });
        o = (RadioGroup)findViewById(R.id.gr5);
        o.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = o.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_o = getId(radioButton);
                if(e.getCheckedRadioButtonId() != -1 && o.getCheckedRadioButtonId() != -1 && a.getCheckedRadioButtonId() != -1 && n.getCheckedRadioButtonId() != -1 && c.getCheckedRadioButtonId() != -1){
                    next.setEnabled(true);
                }
            }
        });
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity6.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity6.this, PesonalData.class);
                int e = values.get(0) + value_e;
                values.set(0, e);
                int a = values.get(1) + value_a;
                values.set(1, a);
                int c = values.get(2) + value_c;
                values.set(2, c);
                int n = values.get(3) + value_n;
                values.set(3, n);
                int o = values.get(4) + value_o;
                values.set(4, o);
                intent.putIntegerArrayListExtra("values", values);
                startActivity(intent);
            }
        });
    }
    public int getId(RadioButton r){
        String id_string = getResources().getResourceEntryName(r.getId());
        return Integer.parseInt(id_string.substring(1));
    }
}