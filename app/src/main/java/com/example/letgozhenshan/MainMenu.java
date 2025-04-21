package com.example.letgozhenshan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letgozhenshan.Utility.Note;
import com.example.letgozhenshan.Utility.Transportation;

public class MainMenu extends AppCompatActivity {
    Button mButton , mButton_transportation , mButton_note ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        mButton_transportation = findViewById(R.id.Button16);
        mButton = findViewById(R.id.Button18);
        mButton_note= findViewById(R.id.Button17);

        mButton_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Transportation.class);
                startActivity(intent);
            }
        });

        mButton_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Note.class);
                startActivity(intent);
            }
        });



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MainMap.class);
                startActivity(intent);
            }
        });
    }
}