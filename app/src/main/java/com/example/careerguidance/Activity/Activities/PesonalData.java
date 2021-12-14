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
import android.widget.Toast;

import com.example.careerguidance.DataBase.DBHandler;
import com.example.careerguidance.R;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PesonalData extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText email;
    RadioGroup gender;
    Intent intent;
    Button button;
    ArrayList<Integer> values;
    Map<String, Integer> map;
    MainActivity5 mainActivity5;
    String value_n;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_pesonal_data);
        name = findViewById(R.id.nametextFieldInput);
        email = findViewById(R.id.emailtextFieldInput);
        gender = findViewById(R.id.radioGroup);
        db = new DBHandler(PesonalData.this);
        mainActivity5 = new MainActivity5();
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = gender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                value_n = radioButton.getText().toString();
            }
        });
        map = new HashMap<String,Integer>();
        intent = getIntent();
        values = intent.getIntegerArrayListExtra("values");
        button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesonalData.this, Results.class);
                map.put("Extraversion", values.get(0));
                map.put("Agreeableness", values.get(1));
                map.put("Conscientiousness", values.get(2));
                map.put("Neuroticism", values.get(3));
                map.put("Openess", values.get(4));
                Map.Entry<String, Integer> data = getMaxEntryInMapBasedOnValue(map);
                String name_add = name.getText().toString();
                String email_add = email.getText().toString();
                String gender_add = value_n;
                String personality;
                if(data.getKey() == "Extraversion"){
                    personality = "Extroversion";
                }
                else if(data.getKey() == "Openess"){
                    personality = "Openness";
                }
                else{
                    personality = data.getKey();
                }
                if(name_add.isEmpty() || email_add.isEmpty() || gender.getCheckedRadioButtonId() == -1){
                    Toast.makeText(PesonalData.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                db.addNewUser(name_add, email_add, gender_add, personality, dtf.format(now));
                intent.putExtra("personality", data.getKey());
                startActivity(intent);
            }
        });


    }
    public static <K, V extends Comparable<V> > Map.Entry<K, V> getMaxEntryInMapBasedOnValue(Map<K, V> map)
    {

        // To store the result
        Map.Entry<K, V> entryWithMaxValue = null;

        for (Map.Entry<K, V> currentEntry :
                map.entrySet()) {

            if (
                    entryWithMaxValue == null

                            || currentEntry.getValue().compareTo(
                            entryWithMaxValue.getValue())
                            > 0) {
                entryWithMaxValue = currentEntry;
            }
        }
        return entryWithMaxValue;
    }
}