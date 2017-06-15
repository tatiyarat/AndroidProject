package com.techin1.androidproject.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.Home;
import com.techin1.androidproject.dao.upuser;
import com.techin1.androidproject.manager.HTTPManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpFragment extends Fragment implements View.OnClickListener {

    private int idu;
    TextView tvname, tviduser;
    TextView tvemail, tvnickname, tvnumber;
    public SharedPreferences sharedPreferences;
    Button butup;
    String Semail, Snic, Snumber, Spass;
    TextView tvpass;
    ImageView imup;
    ImageView imuser;

    public UpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_up, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser", 0);
        gethomeuser(idu);

        imuser = (ImageView) rootView.findViewById(R.id.imuser);
        tvname = (TextView) rootView.findViewById(R.id.tvname);
        tviduser = (TextView) rootView.findViewById(R.id.tviduser);
        tvemail = (TextView) rootView.findViewById(R.id.tvemail);
        tvnickname = (TextView) rootView.findViewById(R.id.tvnickname);
        tvnumber = (TextView) rootView.findViewById(R.id.tvnumber);
//        tvpass = (TextView) rootView.findViewById(R.id.tvpass);
        imup = (ImageView) rootView.findViewById(R.id.imup);
        imup.setOnClickListener(this);

        getActivity().setTitle(getString(R.string.Home));
//        string = getString(R.string.title_activity_menu_group);
        butup = (Button) rootView.findViewById(R.id.butup);
        butup.setOnClickListener(this);

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
                    tvnickname.setText(dao.getNickname());
                    tvnumber.setText(dao.getNumber());
                    tvemail.setText(dao.getMail());
//                    tvpass.setText(dao.getPass());
                }
                Glide.with(getContext())
                        .load(dao.getIm())
                        .into(imuser);
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
//                Toast.makeText(MenuGroupActivity.this,"NO...gethomeuser"
//                        , Toast.LENGTH_LONG)
//                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == imup) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

            alert.setTitle("แก้ไข");
            //alert.setMessage("ยืนยันการเข้าร่วม");
            alert.setIcon(R.mipmap.ic_launcher);

            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.create();

            alert.show();
        }
        if (v == butup) {

//            Spass = tvpass.getText().toString();
            Snic = tvnickname.getText().toString();
            Snumber = tvnumber.getText().toString();
            Semail = tvemail.getText().toString();
            upuser(Snic, Snumber, Semail, idu, Spass);
        }
    }

    private void upuser(String Snic, String Snumber, String Semail, int idu, String Spass) {

        Call<upuser> call = HTTPManager.getInstances().getService().upuser(Snic, Snumber, Semail, idu, Spass);
        call.enqueue(new Callback<upuser>() {
            @Override
            public void onResponse(Call<upuser> call, Response<upuser> response) {
                upuser dao = response.body();
                if (response.isSuccessful()) {
//                    Toast.makeText(UpFragment.this,"up Ok"
//                            , Toast.LENGTH_LONG)
//                            .show();
                    Toast.makeText(getActivity(), "up Ok", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<upuser> call, Throwable t) {
                Toast.makeText(getActivity(), "NO UP", Toast.LENGTH_LONG).show();
            }
        });
    }
}
