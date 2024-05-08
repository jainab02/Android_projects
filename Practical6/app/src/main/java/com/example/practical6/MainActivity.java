package com.example.practical6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextEnrollmentNumber, editTextSemester, editTextDepartment;
    private MyDatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
//    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEnrollmentNumber = findViewById(R.id.editTextEnrollmentNumber);
        editTextSemester = findViewById(R.id.editTextSemester);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        dbHelper = new MyDatabaseHelper(this);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });

        Button buttonViewAll = findViewById(R.id.buttonViewAll);
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllUserData();
            }
        });

        // Initialize the ListView and adapter
        ListView listView = findViewById(R.id.listView);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null, // Cursor will be set when viewing all data
                new String[]{"_id", "name", "email", "enrollmentNumber", "semester", "department"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listView.setAdapter(adapter);
    }

    private void saveUserData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String enrollmentNumber = editTextEnrollmentNumber.getText().toString();
        String semester = editTextSemester.getText().toString();
        String department = editTextDepartment.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("enrollmentNumber", enrollmentNumber);
        values.put("semester", semester);
        values.put("department", department);

        long newRowId = db.insert("Users", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Data saved with ID " + newRowId, Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextEmail.setText("");
            editTextEnrollmentNumber.setText("");
            editTextSemester.setText("");
            editTextDepartment.setText("");
        } else {
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
    private void viewAllUserData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"_id", "name", "email", "enrollmentNumber", "semester", "department"};
        Cursor cursor = db.query("Users", projection, null, null, null, null, null);

        // Update the adapter's cursor with the new data
        adapter.changeCursor(cursor);
    }

//    private void viewAllUserData() {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String[] projection = {"_id", "name", "email", "enrollmentNumber", "semester", "department"};
//        Cursor cursor = db.query("Users", projection, null, null, null, null, null);
//
//        // Update the adapter's cursor with the new data
//        adapter.changeCursor(cursor);
//    }
}
