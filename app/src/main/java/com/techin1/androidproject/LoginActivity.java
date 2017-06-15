package com.techin1.androidproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techin1.androidproject.activity.MenuGroupActivity;
import com.techin1.androidproject.dao.Login;
import com.techin1.androidproject.manager.HTTPManager;
import com.techin1.androidproject.manager.PhotoListManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etuser,etpass;
    Button butlogin;
    String iduser,passuser;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MenuGroupActivity.class));
            finish();
        }

        init();
    }

    private void init() {

        etuser = (EditText) findViewById(R.id.etuser);
        etpass = (EditText) findViewById(R.id.etpass);

        butlogin = (Button) findViewById(R.id.butlogin);
        butlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == butlogin){
            iduser = etuser.getText().toString();
            passuser = etpass.getText().toString();
            getResponseLogin(iduser,passuser);
//            Intent intent = new Intent(LoginActivity.this,
//                    HomeActivity.class);
//            startActivity(intent);

        }
    }
    private void getResponseLogin(String user, String pass) {

        Call<Login> call = HTTPManager.getInstances().getService().getUser(user, pass);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login dao = response.body();
                if (response.isSuccessful()) {
                    if (dao.getSuccess()==1) {

                        session.setLoggedin(true);
//                        session.IDuser(dao.getId());

                        Toast.makeText(LoginActivity.this,""+dao.getName()
                                , Toast.LENGTH_LONG)
                                .show();

                    Intent intent = new Intent(LoginActivity.this,
                            MenuGroupActivity.class);
                        int id = dao.getId();

                        SharedPreferences sp = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("iduser", id);
                        editor.commit();

//                        intent.putExtra("id",dao.getId());
//                        intent.putExtra("name",dao.getName());
//                        intent.putExtra("iduser",dao.getIduser());
//                        intent.putExtra("pass",dao.getPass());
//                        intent.putExtra("nickname",dao.getNickname());
//                        intent.putExtra("number",dao.getNumber());
//                        intent.putExtra("mail",dao.getMail());
//                        intent.putExtra("im",dao.getIm());

                    startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this,"Log in fail!"
                                , Toast.LENGTH_LONG)
                                .show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"NO..."
                        , Toast.LENGTH_LONG)
                        .show();

            }
        });

    }
}
