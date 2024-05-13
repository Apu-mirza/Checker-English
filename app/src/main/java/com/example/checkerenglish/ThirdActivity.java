package com.example.checkerenglish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private Button button;
    private GridView gridView;
    private String[] subTopics, alphabet, animal, calendar, color, conversation, direction, driving, emergency,
            expressions, feelings, food, friendship, fruits, games, greetings, grocery, health, home, hotel, humanbody,
            kitchen, love, number, office, relation, regular, shipping, spice, study, time, travel, vegetable, weather;
    private int[] flags = {R.drawable.abc, R.drawable.animal, R.drawable.calender, R.drawable.colour,
            R.drawable.talking, R.drawable.direction, R.drawable.driving, R.drawable.emergency, R.drawable.expression,
            R.drawable.feeling_2,R.drawable.food, R.drawable.friendship,R.drawable.fruits,R.drawable.playing,
            R.drawable.handshake,R.drawable.grocery, R.drawable.healthcare,R.drawable.home,R.drawable.hotel, R.drawable.body,
            R.drawable.kitchen, R.drawable.love,R.drawable.numbers,R.drawable.office,R.drawable.health,R.drawable.relation,
            R.drawable.shopping,R.drawable.spice, R.drawable.reading,R.drawable.time,R.drawable.travel,
            R.drawable.vegetable,R.drawable.weather
    };
    private String[] countryDetails, countryNames;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        subTopics = getResources().getStringArray(R.array.sub_topics);
        countryDetails = getResources().getStringArray(R.array.country_details);
        countryNames = getResources().getStringArray(R.array.country_names);
        alphabet = getResources().getStringArray(R.array.alphabet);
        animal = getResources().getStringArray(R.array.animal);
        calendar = getResources().getStringArray(R.array.calender);
        color = getResources().getStringArray(R.array.colors);


        gridView = (GridView) findViewById(R.id.gridViewId);

        CustomAdapter2 adapter = new CustomAdapter2(this,subTopics,flags);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),subTopics[i]+" is clicked",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                if(i==0){
                    intent.putExtra("data_header", alphabet);
                    intent.putExtra("data_child", alphabet);

                    startActivity(intent);
                }
                else if(i==1){
                    intent.putExtra("data_header", animal);
                    intent.putExtra("data_child", animal);

                    startActivity(intent);
                }
                else if(i==2){
                    intent.putExtra("data_header", calendar);
                    intent.putExtra("data_child", calendar);

                    startActivity(intent);
                }
                else if(i==3){
                    intent.putExtra("data_header", color);
                    intent.putExtra("data_child", color);

                    startActivity(intent);
                }
                else {
                    intent.putExtra("data_header", countryNames);
                    intent.putExtra("data_child", countryNames);
                    startActivity(intent);
                }

            }
        });


    }

}