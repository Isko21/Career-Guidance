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

public class MainActivity5 extends AppCompatActivity {
    TextView question1;
    TextView question2;
    TextView question3;
    TextView question4;
    TextView question5;
    Intent intent;
    Button next;
    Button back;
    TextView nav;
    PesonalData pesonalData;
    MainActivity mainActivity;
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
    int page = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mainActivity =  new MainActivity();
        setContentView(R.layout.activity_main5);
        intent = getIntent();
        pesonalData = new PesonalData();

        ArrayList<Integer> values = intent.getIntegerArrayListExtra("values");
        ArrayList<String> questions = intent.getStringArrayListExtra("questions");
        question1 = (TextView) findViewById(R.id.q1);
        question1.setText(questions.get(20).trim());
        question2 = (TextView) findViewById(R.id.q2);
        question2.setText(questions.get(21).trim());
        question3 = (TextView) findViewById(R.id.q3);
        question3.setText(questions.get(22).trim());
        question4 = (TextView) findViewById(R.id.q4);
        question4.setText(questions.get(23).trim());
        question5 = (TextView) findViewById(R.id.q5);
        question5.setText(questions.get(24).trim());
        next = (Button) findViewById(R.id.next);
        nav = (TextView) findViewById(R.id.nav);
        e = (RadioGroup)findViewById(R.id.gr1);
        e.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = e.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_e = getId(radioButton);
                next.setEnabled(mainActivity.isPressed(e,a,c,n,o));
            }
        });
        a = (RadioGroup) findViewById(R.id.gr2);
        a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = a.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_a = getId(radioButton);
                next.setEnabled(mainActivity.isPressed(e,a,c,n,o));
            }
        });
        c = (RadioGroup)findViewById(R.id.gr3);
        c.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = c.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_c = getId(radioButton);
                next.setEnabled(mainActivity.isPressed(e,a,c,n,o));
            }
        });
        n = (RadioGroup)findViewById(R.id.gr4);
        n.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = n.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_n = getId(radioButton);
                next.setEnabled(mainActivity.isPressed(e,a,c,n,o));
            }
        });
        o = (RadioGroup)findViewById(R.id.gr5);
        o.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = o.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_o = getId(radioButton);
                next.setEnabled(mainActivity.isPressed(e,a,c,n,o));         }
        });
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity5.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        nav.setText(page + "/6");
        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
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
}