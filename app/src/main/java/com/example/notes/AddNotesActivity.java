package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNotesActivity extends Activity implements View.OnClickListener {
    //widgets
    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText noteEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Note");
        setContentView(R.layout.activity_add_notes);

        //instantiate
        subjectEditText = findViewById(R.id.subject_edittext);
        noteEditText = findViewById(R.id.note_edittext);
        addTodoBtn = findViewById(R.id.add_note);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note:
                final String subject = subjectEditText.getText().toString();
                final String note = noteEditText.getText().toString();
                dbManager.insert(subject, note);
                Intent main = new Intent(AddNotesActivity.this, NotesListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}