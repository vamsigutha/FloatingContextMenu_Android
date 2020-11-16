package com.example.floatingcontextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    String[] android_versions ;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        registerForContextMenu(listView);

        android_versions = getResources().getStringArray(R.array.android_versions);

        for(String item:android_versions){
            arrayList.add(item);
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_layout, R.id.row_item, arrayList);
        listView.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //long press on the items to show floating context menu

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contextual_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.delete:
                Toast.makeText(getApplicationContext(),"Deleted "+ arrayList.get(info.position), Toast.LENGTH_SHORT).show();
                arrayList.remove(info.position);
                adapter.notifyDataSetChanged();

                return true;
            case R.id.share:
                Toast.makeText(getApplicationContext(),"Selected share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(getApplicationContext(),"Selected help", Toast.LENGTH_SHORT).show();
                return true;
            default:return super.onContextItemSelected(item);

        }



    }
}