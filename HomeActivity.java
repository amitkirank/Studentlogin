package com.example.loginactivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class HomeActivity extends AppCompatActivity {
    //private VideoView nVideoView;
    EditText name, usn, password;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name = findViewById(R.id.s_name);
        usn = findViewById(R.id.usn);
        password = findViewById(R.id.stud_pass);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
         DB = new DBHelper(this);
         insert.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view)
             {
                 String nameTXT = name.getText().toString();
                 String usnTXT = usn.getText().toString();
                 String passTXT = password.getText().toString();
                 
                 boolean checkinsertdata = DB.insertuserdata(nameTXT,usnTXT,passTXT);
                 if(checkinsertdata == true)
                     Toast.makeText(HomeActivity.this,"New Entery Inserted", Toast.LENGTH_LONG).show();
                 else
                     Toast.makeText(HomeActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();

             }
         });

         update.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                 String nameTXT = name.getText().toString();
                 String usnTXT = usn.getText().toString();
                 String passTXT = password.getText().toString();

                 Boolean checkupdatedata = DB.updateuserdata(nameTXT, usnTXT, passTXT);
                 if(checkupdatedata==true)
                     Toast.makeText(HomeActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                 else
                     Toast.makeText(HomeActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
             }
         });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(HomeActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(HomeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("USN :"+res.getString(1)+"\n");
                    buffer.append("Password :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });






      /*  nVideoView = (VideoView)findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videoplay);
        nVideoView.setVideoURI(uri);
        nVideoView.start();
        nVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });*/


    }

}