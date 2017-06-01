package com.techin1.androidproject.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.Home;
import com.techin1.androidproject.manager.HTTPManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{


    private int idu;
    TextView tvname,tviduser;
    TextView tvemail,tvnickname,tvnumber;
    public SharedPreferences sharedPreferences;
    String string;
    Button butup;
    ImageView imuser;
    public static final String TAG = "MyFirebaseInsIDService";

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser",0);
        gethomeuser(idu);

        tvname = (TextView) rootView.findViewById(R.id.tvname);
        tviduser = (TextView) rootView.findViewById(R.id.tviduser);
        tvemail = (TextView) rootView.findViewById(R.id.tvemail);
        tvnickname = (TextView) rootView.findViewById(R.id.tvnickname);
        tvnumber = (TextView) rootView.findViewById(R.id.tvnumber);

        imuser = (ImageView) rootView.findViewById(R.id.imuser);

        getActivity().setTitle(getString(R.string.Home));
//        string = getString(R.string.title_activity_menu_group);

        butup = (Button) rootView.findViewById(R.id.butup);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();

        String token = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(getActivity(), token, Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Token: " + token);

        return rootView;

    }

    private void gethomeuser(int idu) {

        Call<Home> call = HTTPManager.getInstances().getService().getNameUser(idu);
        call.enqueue(new Callback<Home>() {

            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Home dao = response.body();
                if (response.isSuccessful()) {
                    tvname.setText(dao.getName());
                    tviduser.setText(dao.getIduser());

                    if (dao.getNickname() == "" || dao.getNickname() == null){tvnickname.setText("ชื่อเล่น..");}
                    if (dao.getNumber() == "" || dao.getNumber() == null){tvnumber.setText("เบอร์โทรศัพท์..");}
                    if (dao.getMail() == "" || dao.getMail() == null){tvemail.setText("Email..");}
                    else{
                        tvnickname.setText(dao.getNickname());
                        tvnumber.setText(dao.getNumber());
                        tvemail.setText(dao.getMail());
                    }
                    Glide.with(getContext())
                            .load(dao.getIm())
                            .into(imuser);
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Toast.makeText(getActivity(),"NO...gethomeuser"
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
