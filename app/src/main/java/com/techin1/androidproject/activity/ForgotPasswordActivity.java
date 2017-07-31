package com.techin1.androidproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techin1.androidproject.LoginActivity;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.ForgotPasswordDao;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText edtEmail;
    Button butEmail;
    String emin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
    }

    private void init() {

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        butEmail = (Button) findViewById(R.id.butEmail);

        butEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emin = edtEmail.getText().toString();
                if (emin.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this,"กรุณาใส่อีเมล"
                            , Toast.LENGTH_LONG)
                            .show();
                }else{
                    forgotPassword(emin);
                }
            }
        });

    }

    private void  forgotPassword(String emin){

        final ProgressDialog dialog = ProgressDialog.show(ForgotPasswordActivity.this, "",
                "กรุณารอสักครู่...", true);
        dialog.show();

        Call<ForgotPasswordDao> call = HTTPManager.getInstances().getService().getEmail(emin);
        call.enqueue(new Callback<ForgotPasswordDao>() {
            @Override
            public void onResponse(Call<ForgotPasswordDao> call, Response<ForgotPasswordDao> response) {
                ForgotPasswordDao dao = response.body();

                if (response.isSuccessful()) {
                    if (dao.getSuccess() == 1){
                        Intent intent = new Intent(ForgotPasswordActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
//                        Toast.makeText(ForgotPasswordActivity.this,"ได้ทำการส่งหรัสไปยังอีเมล เรียบร้อยแล้ว"
//                                , Toast.LENGTH_LONG)
//                                .show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this,""+dao.getMessage()
                                , Toast.LENGTH_LONG)
                                .show();
                        dialog.dismiss();
                    }

                }else{
                    try {
                        Toast.makeText(ForgotPasswordActivity.this,
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                        dialog.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordDao> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, t.toString()
                        , Toast.LENGTH_LONG)
                        .show();
                dialog.dismiss();
            }
        });
    }


}
