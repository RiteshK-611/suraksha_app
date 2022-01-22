package com.mad.miniproject.complaintRegistration;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import com.mad.miniproject.databinding.ActivityComplaintBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.mad.miniproject.R;
import com.mad.miniproject.UserHelperClass;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterComplaint extends AppCompatActivity {

    ActivityComplaintBinding binding;

    /* EditText name, contact, email, loi, bdesc, complaint;
    Button register;
    FirebaseDatabase rootNode;
    DatabaseReference reference; */
    String name, contact, email, loi, bdesc, complaint;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /* setContentView(R.layout.activity_complaint);

        name = (EditText) findViewById(R.id.fullName);
        contact = (EditText)findViewById(R.id.contact);
        email = (EditText)findViewById(R.id.mail);
        loi = (EditText)findViewById(R.id.loi);
        bdesc = (EditText)findViewById(R.id.bdesc);
        complaint = (EditText)findViewById(R.id.complaint);

        register = (Button) findViewById(R.id.register); */

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get all the values
                /* String n = (String) name.getText().toString();
                String c = (String) contact.getText().toString();
                String e = (String) email.getText().toString();
                String l = (String) loi.getText().toString();
                String bd = (String) bdesc.getText().toString();
                String cmplnt = (String) complaint.getText().toString(); */



                UserHelperClass helperClass = new UserHelperClass(

                        binding.fullname.getText().toString(),
                        binding.contact.getText().toString(),
                        binding.mail.getText().toString(),
                        binding.loi.getText().toString(),
                        binding.bdesc.getText().toString(),
                        binding.complaint.getText().toString());

                rootNode = FirebaseDatabase.getInstance("https://mad-mp-default-rtdb.firebaseio.com/");
                reference = rootNode.getReference("test");

                reference.push().setValue(helperClass);

                //reference.setValue("helperClass");

            }
        });

    }
}
