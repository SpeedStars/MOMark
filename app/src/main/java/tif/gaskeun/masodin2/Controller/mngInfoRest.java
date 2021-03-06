package tif.gaskeun.masodin2.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import tif.gaskeun.masodin2.Model.Informasi;
import tif.gaskeun.masodin2.Model.Menu;
import tif.gaskeun.masodin2.R;

import static android.text.TextUtils.isEmpty;

public class mngInfoRest extends AppCompatActivity {

    private Button btSubmit;
    private EditText etNamaSf,etNamaResto,etAlamatResto,etNotelp,etEmailResto;
    FirebaseAuth mAuth;
    DatabaseReference dbref;
    String key;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_restinfo);

        etNamaSf = findViewById(R.id.et_namastaff);
        etNamaResto = findViewById(R.id.et_namarestoran);
        etAlamatResto= findViewById(R.id.et_alamatrestoran);
        etNotelp= findViewById(R.id.et_telprestoran);
        etEmailResto= findViewById(R.id.et_emailrestoran);
        btSubmit = findViewById(R.id.bt_submit2);

        mAuth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference();

        key = mAuth.getUid();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !isEmpty(etNamaSf.getText().toString()) && !isEmpty(etNamaResto.getText().toString()) && !isEmpty(etAlamatResto.getText().toString()) && !isEmpty(etNotelp.getText().toString()) && !isEmpty(etEmailResto.getText().toString()))
                    Submit(new Informasi((etNamaSf.getText().toString()), (etNamaResto.getText().toString()), (etAlamatResto.getText().toString()), (etNotelp.getText().toString()), (etEmailResto.getText().toString())));
                else Snackbar.make(findViewById(R.id.bt_submit2),"Data Tidak Boleh Kosong", Snackbar.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(),mngDashboard.class).putExtra("Key",key));
                finish();
            }
        });


    }

    private void Submit(Informasi informasi) {
        dbref.child(key).setValue(informasi);
        }
    }