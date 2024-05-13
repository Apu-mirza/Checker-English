package com.example.checkerenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter2 extends BaseAdapter {

    String[] subTopics;
    int[] flags;
    Context context;
    LayoutInflater layoutInflater;

    CustomAdapter2(Context context, String[] subTopics, int[] flags){
        this.context = context;
        this.subTopics = subTopics;
        this.flags = flags;
    }

    @Override
    public int getCount() {
        return subTopics.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.sample_view1, viewGroup, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewId);
        TextView textView = (TextView) view.findViewById(R.id.headingId);

        textView.setText(subTopics[i]);
        imageView.setImageResource(flags[i]);


        return view;
    }


}


