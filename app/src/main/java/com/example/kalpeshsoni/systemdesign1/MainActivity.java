package com.example.kalpeshsoni.systemdesign1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cName,cNum,pRi,cData;
    Button Save;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        cName = findViewById(R.id.conName);
        cNum = findViewById(R.id.conNum);
        pRi = findViewById(R.id.priority);
        cData = findViewById(R.id.comData);
        Save = findViewById(R.id.save);

        final String cname = cName.getText().toString();
        final String cnum = cNum.getText().toString();
        final String pri = pRi.getText().toString();
        final String cdata = cData.getText().toString();



        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = db.insertData(cname,cnum,pri,cdata);
                if(isInserted)
                {
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Display.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
