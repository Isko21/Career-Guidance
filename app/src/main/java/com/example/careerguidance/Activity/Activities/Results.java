package com.example.careerguidance.Activity.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.careerguidance.DataBase.DataBaseHelper;
import com.example.careerguidance.Others.ChildInfo;
import com.example.careerguidance.Others.CustomAdapter;
import com.example.careerguidance.Others.GroupInfo;
import com.example.careerguidance.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;


public class Results extends AppCompatActivity {
    DataBaseHelper db;
    Intent intent;
    Button tryAgain;
    Button exit;
    TextView result;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    ArrayList<String> job = new ArrayList<>();
    ArrayList<String> university = new ArrayList<>();
    ArrayList<String> degree = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        intent = getIntent();
        result = findViewById(R.id.result);
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        tryAgain = findViewById(R.id.again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Results.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        String key = intent.getStringExtra("personality");
        String res = key.toString();
        if (res.equals("Extraversion")){
            res = "Extroversion";
        }else if(res.equals("Openess")){
            res = "Openness";
        }
        result.setText("Your personality is: " + res.toUpperCase(Locale.ROOT));
        db = new DataBaseHelper(this, "careers.db", 2);
        try {
            db.checkDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }try {
            db.openDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.loadHandler(key, job, university, degree);
        loadData();

        simpleExpandableListView = findViewById(R.id.simpleExpandableListView);
        listAdapter = new CustomAdapter(Results.this, deptList);
        simpleExpandableListView.setAdapter(listAdapter);

        //expandAll();
        collapseAll();
    }
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    private void loadData(){

        addProduct("Jobs Matching to your Personality",job.get(0));
        addProduct("Jobs Matching to your Personality",job.get(1));
        addProduct("Jobs Matching to your Personality",job.get(2));
        addProduct("Jobs Matching to your Personality",job.get(3));
        addProduct("Jobs Matching to your Personality",job.get(4));
        addProduct("Jobs Matching to your Personality",job.get(5));

        addProduct("Universities Matching to your Personality",university.get(0));
        addProduct("Universities Matching to your Personality",university.get(1));
        addProduct("Universities Matching to your Personality",university.get(2));
        addProduct("Universities Matching to your Personality",university.get(3));
        addProduct("Universities Matching to your Personality",university.get(4));
        addProduct("Universities Matching to your Personality",university.get(5));

        addProduct("Degrees Matching to your Personality",degree.get(0));
        addProduct("Degrees Matching to your Personality",degree.get(1));
        addProduct("Degrees Matching to your Personality",degree.get(2));
        addProduct("Degrees Matching to your Personality",degree.get(3));
        addProduct("Degrees Matching to your Personality",degree.get(4));
        addProduct("Degrees Matching to your Personality",degree.get(5));

    }
    private int addProduct(String department, String product){

        int groupPosition = 0;
        GroupInfo headerInfo = subjects.get(department);
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        int listSize = productList.size();
        listSize++;

        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }
}