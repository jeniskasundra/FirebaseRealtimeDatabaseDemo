package com.jenisk.firebase_realtime_db;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jenisk.firebase_realtime_db.model.UserDetail;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUserActivity extends AppCompatActivity {

    EditText edtName, edtNumber, edtEmail, edtLatitude, edtLongitude;
    Button btnAddUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        init();
        bindView();
        addListner();
    }

    private void init() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceUser = firebaseDatabase.getReference("user");
    }

    private void bindView() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtLatitude = (EditText) findViewById(R.id.edtLatitude);
        edtLongitude = (EditText) findViewById(R.id.edtLongitude);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);
    }

    private void addListner() {

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString(),
                        number = edtNumber.getText().toString(),
                        email = edtEmail.getText().toString(),
                        latitude = edtLatitude.getText().toString(),
                        longitude = edtLongitude.getText().toString();

                if (name.equals("")) {
                    edtName.setError("Enter Name");
                } else if (!isValidEmail(email)) {
                    edtEmail.setError("Invalid Email");
                } else if (!isValidMobile(number)) {
                    edtNumber.setError("Invalid Mobile Number");
                } else if (!isValidLatitude(latitude)) {
                    edtLatitude.setError("Invalid Latitude");
                } else if (!isValidLongitude(longitude)) {
                    edtLongitude.setError("Invalid Longitude");
                } else {

                    String id = databaseReferenceUser.push().getKey();

                    UserDetail userDetail = new UserDetail(id, name, number, email, latitude, longitude);

                    databaseReferenceUser.child(id).setValue(userDetail);

                    Toast.makeText(AddUserActivity.this, "User Add Successfully", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });

    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isValidLatitude(String latitude) {
        String PATTERN = "^(\\+|-)?(?:90(?:(?:\\.0{1,7})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,7})?))$";

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(latitude);
        return matcher.matches();
    }

    private boolean isValidLongitude(String longitude) {
        String PATTERN = "^(\\+|-)?(?:180(?:(?:\\.0{1,7})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,7})?))$";

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(longitude);
        return matcher.matches();
    }

}
