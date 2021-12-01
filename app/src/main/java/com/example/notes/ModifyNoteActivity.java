package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    //widgets
    private EditText subjectText;
    private Button updateBtn, deleteBtn;
    private EditText noteText;

    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Note");
        setContentView(R.layout.activity_modify_note);

        dbManager = new DBManager(this);
        dbManager.open();

        subjectText = findViewById(R.id.subject_edittext);
        noteText = findViewById(R.id.note_edittext);

        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String subject = intent.getStringExtra("subject");
        String note = intent.getStringExtra("note");

        _id = Long.parseLong(id);

        subjectText.setText(subject);
        noteText.setText(note);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String subject = subjectText.getText().toString();
                String note = noteText.getText().toString();
                dbManager.update(_id, subject, note);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), NotesListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}