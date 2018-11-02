package com.example.kalpeshsoni.systemdesign1;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Display extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> lisItems;
    ArrayAdapter adapter;

    ListView ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ll = findViewById(R.id.list);
        db = new DatabaseHelper(this);
        lisItems = new ArrayList<>();
        viewData();

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String text = ll.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +text));
                startActivity(intent);
            }
        });
    }


    private void viewData() {

        Cursor cursor = db.getAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                lisItems.add(cursor.getString(1));
                lisItems.add(cursor.getString(2));

            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lisItems);
            ll.setAdapter(adapter);

        }
    }
}