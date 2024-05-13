package com.example.checkerenglish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private String[] abbreviations, acronyms, idioms, idioms_example, topics, phrases, phrase_example, proverb, proverb_example;
    private Button button1, button2;
    private GridView gridView;
    private int[] flags = {R.drawable.microphone, R.drawable.translate_1, R.drawable.favorites,
            R.drawable.talking, R.drawable.phrases, R.drawable.idioms, R.drawable.speech,
            R.drawable.share, R.drawable.star, R.drawable.share
    };


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

//        button1 = findViewById(R.id.button1Id);
//        button2 = findViewById(R.id.button2Id);

        gridView = (GridView) findViewById(R.id.gridViewId);

        topics = getResources().getStringArray(R.array.topics);

        phrases = getResources().getStringArray(R.array.phrases);
        phrase_example = getResources().getStringArray(R.array.phrases_example);
        idioms = getResources().getStringArray(R.array.idioms);
        idioms_example = getResources().getStringArray(R.array.idioms_example);
        proverb = getResources().getStringArray(R.array.proverb);
        proverb_example = getResources().getStringArray(R.array.proverb_example);



        MyAdapter myAdapter = new MyAdapter(this,topics, flags);
        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 3){
                    Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
                    startActivity(intent);
                }
                if (i == 4){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    intent.putExtra("data_header", phrases);
                    intent.putExtra("data_child", phrase_example);
                    startActivity(intent);
                }
                else if(i == 5){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    intent.putExtra("data_header", idioms);
                    intent.putExtra("data_child", idioms_example);
                    startActivity(intent);
                }

                else if(i == 6){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    intent.putExtra("data_header", proverb);
                    intent.putExtra("data_child", proverb_example);
                    startActivity(intent);
                }
            }
        });

    }


}