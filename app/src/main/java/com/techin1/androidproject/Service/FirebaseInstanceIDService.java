package com.techin1.androidproject.Service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by TECHIN1 on 23/11/2559.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TAG = "MyFirebaseInsIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(token);
    }

    private void registerToken(String token) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();
        Log.d(TAG,"Token: " + token);

        Request request = new Request.Builder()
                .url("http://csmsucenter.com/cs_msu_club/PHPAndroid/register.php")
//                .url("http://192.168.2.111/PHPAndroid/register.php")
                .post(body)
                .build();
        try {
            client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
