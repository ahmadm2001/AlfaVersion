package com.example.alfaversion;

import static com.example.alfaversion.FBref.refAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class StudentLogIN extends AppCompatActivity {
    TextView tVtitle;
    EditText eTemail, eTpass;
    Button btn;
    Spinner spinner;
    LinearLayout LL;
    String email, password;
    Student student;
    Boolean stayConnect, registered, firstrun, inorder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_in);
        tVtitle = (TextView) findViewById(R.id.tVtitle);
        eTemail = (EditText) findViewById(R.id.eTemail);
        eTpass = (EditText) findViewById(R.id.eTpass);
        btn = (Button) findViewById(R.id.btn);

    }
    public void logorreg(View view) {
        /**
         * Checks if the user is registered and logging in.
         * <p>
         *
         * @param	view Button	on click operate the action.
         */
            email=eTemail.getText().toString();
            password=eTpass.getText().toString();

            final ProgressDialog pd=ProgressDialog.show(this,"Login","Connecting...",true);
            refAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if (task.isSuccessful()) {
                                SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
                                SharedPreferences.Editor editor=settings.edit();
                                Log.d("MainActivity", "signinUserWithEmail:success");
                                Toast.makeText(StudentLogIN.this, "Login Success", Toast.LENGTH_LONG).show();
                                Intent si = new Intent(StudentLogIN.this,StudentMain_Activity.class);
                                startActivity(si);
                            } else {
                                Log.d("MainActivity", "signinUserWithEmail:fail");
                                Toast.makeText(StudentLogIN.this, "e-mail or password are wrong!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
}
