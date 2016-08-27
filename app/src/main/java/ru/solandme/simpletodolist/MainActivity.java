package ru.solandme.simpletodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ArrayList<String> myList = new ArrayList<>();
    ArrayAdapter listAdapter;
    EditText editText;
    ListView todoList;
    ShareActionProvider mShareActionProvider;

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
        todoList.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {
        myList.add(editText.getText().toString());
        listAdapter.notifyDataSetChanged();
        setShareIntent();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        myList.remove(i);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu.
        // Adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Access the Share Item defined in menu XML
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        // Access the object responsible for
        // putting together the sharing submenu
        if (shareItem != null) {
            mShareActionProvider = (ShareActionProvider)shareItem.getActionProvider();
        }

        // Create an Intent to share your content
        setShareIntent();

        return true;
    }

    private void setShareIntent() {

        if (mShareActionProvider != null) {
            String allTextList = "ToDo List, just do it:";
            for (String s : myList) {
                allTextList = allTextList + "\n" + s;
            }

            // create an Intent with the contents of the TextView
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ToDo List, just do it:");
            shareIntent.putExtra(Intent.EXTRA_TEXT, allTextList);

            // Make sure the provider knows
            // it should work with that Intent
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
