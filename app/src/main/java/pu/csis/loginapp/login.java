package pu.csis.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    Activity context = this;
    FirebaseAuth mAuth; //連接firebase

    Button b3,b4,b2;
    EditText et3,et4;
    TextView tv;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginsystem);

        tv=(TextView)findViewById(R.id.tv8);

        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button4); //確定
        b4 = findViewById(R.id.button5); //註冊頁面

        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);

        mAuth=FirebaseAuth.getInstance();

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(et3.getText().toString(),et4.getText().toString()).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                            tv.setText("登入成功"+user.getEmail());
                            email=user.getEmail();
                            Intent intent = new Intent(login.this,UserActivity.class);
                            startActivity(intent);
                        }else{
                            tv.setText("登入失敗");

                        }
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        et3.setText(null);
                        et4.setText(null);
                    }
                });
            }
        });
    }
}
