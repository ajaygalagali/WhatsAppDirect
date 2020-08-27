package com.astro.whatsappdirect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    ExtendedFloatingActionButton fab_send;
    TextInputEditText phoneNumber;
    TextInputEditText message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_send = findViewById(R.id.fab_send);
        phoneNumber = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.messageID);

    }

    public void SendClicked(View view) {

        String ph = phoneNumber.getText().toString();
        String msg = message.getText().toString();

        if(ph.isEmpty()){
            phoneNumber.setError("Phone Field cannot be empty");

        }else if(!ph.startsWith("+")){
            phoneNumber.setError("Enter phone number with country code");
        }else{
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            String msgEncoded = null;
            try {
                msgEncoded = URLEncoder.encode(msg,"UTF-8");
                String url = String.format("https://api.whatsapp.com/send?phone=%s&text=%s",ph,msgEncoded);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setData(Uri.parse(url));
                startActivity(sendIntent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong. Try Again.", Toast.LENGTH_SHORT).show();
            }


        }


    }
}