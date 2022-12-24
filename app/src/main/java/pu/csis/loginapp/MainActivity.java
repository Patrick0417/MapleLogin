package pu.csis.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Activity context = this;
    FirebaseAuth mAuth; //連接firebase
    Button b1,b2;
    TextView tv4;
    EditText et1,et2;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button4);
        b2 = findViewById(R.id.button2);

        et1=(EditText)findViewById(R.id.editText3);
        et2=(EditText)findViewById(R.id.editText4);

        tv4=(TextView)findViewById(R.id.textView4);

        mAuth=FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(et1.getText().toString(),et2.getText().toString()).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            tv4.setText(user.getEmail()+"註冊成功");
                            Intent intent = new Intent(MainActivity.this,UserActivity.class);
                            startActivity(intent);

                        }else{
                            tv4.setText("註冊失敗"+task.getException());
                        }

                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        et1.setText(null);
                        et2.setText(null);
                    }
                });
            }
        });




    }
}
