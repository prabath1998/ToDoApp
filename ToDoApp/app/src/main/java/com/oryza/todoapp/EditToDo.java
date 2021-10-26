package com.oryza.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class EditToDo extends AppCompatActivity {

    private EditText title,des;
    private Button edit;
    private DbHandler dbHandler;
    private Context context;
    private Long updatedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        context = this;
        dbHandler = new DbHandler(context);

        title = findViewById(R.id.editToDoTextTitle);
        des = findViewById(R.id.editToDoTextDescription);
        edit = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        ToDo todo =  dbHandler.getSingleToDo(Integer.parseInt(id));

        title.setText(todo.getTitle());
        des.setText(todo.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText =  title.getText().toString();
                String descText = des.getText().toString();
                updatedDate = System.currentTimeMillis();

                ToDo toDo = new ToDo(Integer.parseInt(id),titleText,descText,updatedDate,0);
                int state = dbHandler.updateSingleToDo(toDo);
                System.out.println(state);
                Toasty.info(context,"To do updated..!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,MainActivity.class));
            }
        });




    }
}