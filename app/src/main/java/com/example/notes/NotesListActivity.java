package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class NotesListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.NOTE};

    final int[] to = new int[]{R.id.id, R.id.subject, R.id.note};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_note, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        //on click for list items:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewid) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView subjectTextView = view.findViewById(R.id.subject);
                TextView noteTextView = view.findViewById(R.id.note);

                String id = idTextView.getText().toString();
                String subject = subjectTextView.getText().toString();
                String note = noteTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyNoteActivity.class);
                modify_intent.putExtra("subject", subject);
                modify_intent.putExtra("note", note);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_note) {
            Intent add_mem = new Intent(this, AddNotesActivity.class);
            startActivity(add_mem);
        }

        return super.onOptionsItemSelected(item);
    }
}