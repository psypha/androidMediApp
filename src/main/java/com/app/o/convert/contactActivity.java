package com.app.o.convert;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class
        contactActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        Uri allcontacts = ContactsContract.Contacts.CONTENT_URI;
        Cursor c = getContentResolver().query(allcontacts, null, null, null,null);
        startManagingCursor(c);
        String[] columns = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
        int[] views = new int[]{R.id.contactid, R.id.contactid};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_contact, c, columns, views);
        this.setListAdapter(adapter);
       // db = new MyDBHelper(getBaseContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
