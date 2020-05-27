package com.example.letsplay;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


public class CurrentList extends AppCompatActivity {
    Toolbar mListToolbar;
    ArrayList<File> currentSongs;
    ListView mListView;
    String songs[];
    ArrayAdapter<String> mArrayAdapter;
    TextInputEditText search_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);
        mListToolbar = findViewById(R.id.mListToolbar);
        setSupportActionBar(mListToolbar);
        getSupportActionBar().setTitle("Now Playing");
        mListView = findViewById(R.id.currentList);
        search_bar = findViewById(R.id.search_field);
        Intent songData = getIntent();
        currentSongs = (ArrayList) songData.getParcelableArrayListExtra("songsList");
        songs = new String[currentSongs.size()];

        //if (search_bar.isActivated() && search_bar.getText().toString().length() < 4) {

            //Code to search using regular expression


       // } else {
            //Code to list all songs
            for (int i = 0; i < currentSongs.size(); i++) {
                songs[i] = currentSongs.get(i).getName().replace(".mp3", "").replace(".m4a", "").replace(".wav", "").replace(".m4b", "");
                Log.i("Song Name: ", currentSongs.get(i).getName());
            }
        //}

        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // start music player when song name is clicked

                String songName = mListView.getItemAtPosition(position).toString();
                Intent play = new Intent(CurrentList.this, Player.class);
                play.putExtra("songs", currentSongs).putExtra("songName", songName).putExtra("position", position);
                startActivity(play);
            }
        });
    }
}