package com.example.checkerenglish;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    int condition=0;
    List<String> listDataHeader;
    TextToSpeech textToSpeech;
    HashMap<String,List<String>> listDataChild;
    private CustomAdapter customAdapter;
    private int lastExpandablePosition = -1;
    SearchView mSearchView;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        prepareListData();

        expandableListView = (ExpandableListView) findViewById(R.id.expandTextId);

        textToSpeech = new TextToSpeech(SecondActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(SecondActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SecondActivity.this, "Text to speech failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        customAdapter = new CustomAdapter(this,listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdapter);


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String groupName = listDataHeader.get(i);
                String[] groupLines = groupName.split("\\r?\\t?\\n"); // Split the group text into lines
                String firstLine = groupLines[0]; // Extract the first line of the group
                Toast.makeText(getApplicationContext(),firstLine,Toast.LENGTH_SHORT).show();
                if (condition == 0){
                    textToSpeech.speak(firstLine,TextToSpeech.QUEUE_FLUSH, null, null); // Pass only the first line to textToSpeech
                    condition++;
                }
                else{
                    textToSpeech.setSpeechRate(0.05f);
                    condition = 0;
                }
                return false;

            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String childString = listDataChild.get(listDataHeader.get(i)).get(i1);
                Toast.makeText(getApplicationContext(),childString,Toast.LENGTH_SHORT).show();
                textToSpeech.speak(""+childString,TextToSpeech.QUEUE_FLUSH, null, null);
                if (condition == 0){
                    textToSpeech.setSpeechRate(1.0f);
                    condition++;
                }
                else{
                    textToSpeech.setSpeechRate(0.05f);
                    condition = 0;
                }
                return false;
            }


        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandablePosition != -1 && lastExpandablePosition != i){
                    expandableListView.collapseGroup(lastExpandablePosition);

                }
                lastExpandablePosition = i;
            }
        });


    }
    public void prepareListData(){


        String[] headerString = getIntent().getStringArrayExtra("data_header");
        String[] childString = getIntent().getStringArrayExtra("data_child");

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        for(int i=0; i<headerString.length; i++){

            listDataHeader.add(headerString[i]);

            List<String> child = new ArrayList<>();
            child.add(childString[i]);

            listDataChild.put(listDataHeader.get(i),child);

        }

    }


}

