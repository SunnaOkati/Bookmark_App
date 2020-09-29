package com.example.listofurls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText url=(EditText) findViewById(R.id.editURL);
        Button Add=(Button) findViewById(R.id.button_listurl);

        final ListView URLLi=(ListView) findViewById(R.id.listView_url);
        final ArrayList<String> URLAr=new ArrayList<String>();
        URLAr.add("https://www.google.com");
        URLAr.add("https://www.anu.edu.au");
        URLAr.add("https://www.stackoverflow.com");

        final ArrayAdapter<String> URLAd=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, URLAr);
        URLLi.setAdapter(URLAd);

        Add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String urlString =url.getText().toString();
                if(urlString.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter valid address",Toast.LENGTH_SHORT).show();
                }else {
                    if (URLUtil.isValidUrl(urlString)) {
                        if(!URLAr.contains(urlString)){
                            URLAr.add(urlString);
                            URLAd.notifyDataSetChanged();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Address exists  ",Toast.LENGTH_SHORT).show();
                        url.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        URLLi.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg){

                //Manually including the activity class in manifesto
                //source: https://stackoverflow.com/questions/18756356/how-to-fix-unable-to-find-explicit-activity-class
                Intent intent = new Intent(getApplicationContext(), Web.class);
                intent.putExtra("URL", URLAr.get(position).toString());
                startActivity(intent);
            }
        });
    }
}