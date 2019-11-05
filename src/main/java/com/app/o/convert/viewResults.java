package com.app.o.convert;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.List;

import javax.xml.transform.Result;

public class viewResults extends AppCompatActivity {
    DAO dbHelper;
        private int id;
        private String firstName;
        private String lastName;

        /**
         * Default constructor
         */
        public viewResults(){
            this.id=1;
            this.firstName="XX";
            this.lastName="YY";
        }

        /**
         * Parameterized constructor
         * @param id
         * @param firstName
         * @param lastName
         */
        public viewResults(final int id, final String firstName, final String lastName){
            this.id=id;
            this.firstName=firstName;
            this.lastName=lastName;
        }



    /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * @param firstName the firstName to set
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * @param lastName the lastName to set
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StringBuffer details = new StringBuffer();

        setContentView(R.layout.activity_view_results);
        dbHelper = new DAO(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<viewResults> ResultList = dbHelper.getAllResults();
        TextView textView2 = (TextView) findViewById(R.id.textView3);

        for (viewResults textView3 : ResultList) {
            details.append("Id: "+textView3.getId()).append("\n");
        }
        textView2.setText(details.toString());

FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
