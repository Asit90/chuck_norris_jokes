package cakart.cakart.in.chucknorris_jokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    String[] categories=new String[]{
            "explicit",
            "dev",
            "movie",
            "food",
            "celebrity",
            "science",
            "sport",
            "political",
            "religion",
            "animal",
            "history",
            "music",
            "travel",
            "career",
            "money",
            "fashion"
    };
    int[] flags=new int[]{
            R.drawable.explicit,
            R.drawable.dev,
            R.drawable.movie,
            R.drawable.food,
            R.drawable.celebrity,
            R.drawable.science,
            R.drawable.sport,
            R.drawable.politician,
            R.drawable.religion,
            R.drawable.animal,
            R.drawable.history,
            R.drawable.music,
            R.drawable.travel,
            R.drawable.career,
            R.drawable.money,
            R.drawable.fashion
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<flags.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", categories[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
        // Keys used in Hashmap
        String[] from = { "flag","txt"};
        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.cell, from, to);
        // Getting a reference to gridview of MainActivity
        GridView gridView = (GridView) findViewById(R.id.gridview);
        // Setting an adapter containing images to the gridview
        gridView.setAdapter(adapter);
        // gridview calling and go to another activity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(MainActivity.this,Category.class);
                i.putExtra("category",categories[position]);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}



