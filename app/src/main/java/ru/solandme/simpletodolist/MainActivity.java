package ru.solandme.simpletodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> myList = new ArrayList<>();
    ArrayAdapter listAdapter;
    EditText editText;
    ListView todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_text);
        Button button = (Button) findViewById(R.id.btn_add);
        todoList = (ListView) findViewById(R.id.to_do_list_view);

        listAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                myList);

        todoList.setAdapter(listAdapter);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        myList.add(editText.getText().toString());
        listAdapter.notifyDataSetChanged();
    }
}
