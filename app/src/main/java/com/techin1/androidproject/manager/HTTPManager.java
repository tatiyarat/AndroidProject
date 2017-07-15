package com.techin1.androidproject.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techin1.androidproject.manager.http.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zel2__000 on 5/25/2016.
 */
public class HTTPManager {

    private static HTTPManager instances;


    public static HTTPManager getInstances() {
        if (instances == null)
            instances = new HTTPManager();
        return instances;

    }

    private Context mContext;
    private APIService service;

    private HTTPManager() {
        mContext = Contextor.getInstances().getContext();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.HTTP_SERVICE_URL)
//                .baseUrl("http://csmsucenter.com/cs_msu_club/PHPAndroid/")
                .baseUrl("http://202.28.34.201/CS_Msuclub/csmsuclub/PHPAndroid/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(APIService.class);
        
    }

    public APIService getService(){
        return service;
    }
}
