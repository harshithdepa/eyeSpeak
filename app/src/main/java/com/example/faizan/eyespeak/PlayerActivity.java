package com.example.faizan.eyespeak;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    FloatingActionButton cameraButton;
    ListView oldPicsListView;
    ProgressBar levelBar;
    ArrayList<OldPic> oldPicsArrayList;
    OldPicListAdapter oldPicListAdapter = null;
    public static OldPicturesDBManager oldPicturesDBManager;
    private static final int RESULT_CODE = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Log.d("PAOnCreate", "HERE");

        cameraButton = (FloatingActionButton) findViewById(R.id.camera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPicture = new Intent(PlayerActivity.this, PictureActivity.class);
                toPicture.putExtra("selectedOldPic", "NONE");
                PlayerActivity.this.startActivity(toPicture);
            }
        });

        updateListView();

        oldPicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OldPic selectedOldPic = oldPicsArrayList.get(i);
                Intent toPicture = new Intent(PlayerActivity.this, PictureActivity.class);
                toPicture.putExtra("selectedOldPic", selectedOldPic.getWord());
                PlayerActivity.this.startActivity(toPicture);
            }
        });
    }

    private void updateListView(){
        oldPicturesDBManager = new OldPicturesDBManager(this, "OldPicsDB.sqlite", null, 1);
        oldPicturesDBManager.queryData("CREATE TABLE IF NOT EXISTS OLDPICS (Id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR, image BLOB)");

        oldPicsListView = (ListView) findViewById(R.id.oldPicsList);
        levelBar = (ProgressBar) findViewById(R.id.levelBar);
        oldPicsArrayList = new ArrayList<>();
        oldPicListAdapter = new OldPicListAdapter(this, R.layout.old_pic_row, oldPicsArrayList);
        oldPicsListView.setAdapter(oldPicListAdapter);

        // get all data from sqlite
        Cursor cursor = oldPicturesDBManager.getData("SELECT * FROM OLDPICS");
        oldPicsArrayList.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String word = cursor.getString(1);
            Log.d("wordSqlite", word);
            byte[] image = cursor.getBlob(2);

            oldPicsArrayList.add(0, new OldPic(word, image, id));
        }
        oldPicListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HERE", "onResume");

        updateListView();
    }
}
