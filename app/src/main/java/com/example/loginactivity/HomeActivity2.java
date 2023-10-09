package com.example.loginactivity;


import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity2 extends AppCompatActivity {
TextView inputshloka,inputtranslation;
TextView text1,text2,explore;
ImageView img,img2,shareButton,watsapbtn;
private TextView textView1;
private TextView textView2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_home2);
        inputshloka = findViewById(R.id.Textshloka);
        inputtranslation = findViewById(R.id.Texttranslation);
        text1 = findViewById(R.id.Textshloka);
        text2 = findViewById(R.id.Texttranslation);
        img = findViewById(R.id.copybtn1);
        img2 = findViewById(R.id.copybtn2);
        textView1 = findViewById(R.id.Textshloka);
        textView2 = findViewById(R.id.Texttranslation);
        explore=findViewById(R.id.explore);

        shareButton = findViewById(R.id.sharebtn);
        watsapbtn=findViewById(R.id.watsapbtn);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("demo");
        DatabaseReference translation = database.getReference("trans");

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity2.this, MenuActivity.class));
            }
        });

        watsapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareOnWhatsApp();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Textview", text1.getText().toString());
                clipboardManager.setPrimaryClip(clip);
                Toast.makeText(HomeActivity2.this, "Shloka Copied", Toast.LENGTH_SHORT).show();

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip1 = ClipData.newPlainText("Textview", text2.getText().toString());
                clipboardManager1.setPrimaryClip(clip1);
                Toast.makeText(HomeActivity2.this, "Translation Copied", Toast.LENGTH_SHORT).show();

            }
        });

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                inputshloka.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        translation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String hello = snapshot.getValue(String.class);
                inputtranslation.setText(hello);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void shareOnWhatsApp() {
        String text3 = textView1.getText().toString();
        String text4= textView2.getText().toString();

        String message = text3 + "\n\n" + text4;

        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            // WhatsApp not installed
            // You can handle this case or display a message to the user
        }
    }

    private void shareContent() {
        String text1 = textView1.getText().toString();
        String text2 = textView2.getText().toString();

        String shareText = text1 + "\n\n" + text2;

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Content");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}