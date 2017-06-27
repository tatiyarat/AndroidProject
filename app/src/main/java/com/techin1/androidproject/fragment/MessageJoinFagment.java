package com.techin1.androidproject.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.MessageDao;
import com.techin1.androidproject.dao.inuserjoinDao;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MessageJoinFagment extends Fragment {

    TextView joinpost,tvDataJoin;
    TextView tvmessage;

    public SharedPreferences sharedPreferences;
    private int ids;
    private int idu;

    public MessageJoinFagment() {
        super();
    }

    public static MessageJoinFagment newInstance() {
        MessageJoinFagment fragment = new MessageJoinFagment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_join, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser", 0);
        ids = sharedPreferences.getInt("idmessage", 0);

        tvmessage = (TextView) rootView.findViewById(R.id.tvmessage);
        tvDataJoin = (TextView) rootView.findViewById(R.id.tvDataJoin);
        joinpost = (TextView) rootView.findViewById(R.id.joinpost);

        tvmessage.setText(""+ids);

        joinpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle("ยืนยันการเข้าร่วม");
                alert.setMessage("ยืนยันการเข้าร่วม");
                alert.setIcon(R.mipmap.ic_launcher);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getJoin(idu, ids);
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
        });

        Call<MessageDao> call = HTTPManager.getInstances().getService().getidmessage(ids);
        call.enqueue(new Callback<MessageDao>() {
            @Override
            public void onResponse(Call<MessageDao> call, Response<MessageDao> response) {
                if (response.isSuccessful()) {
                    MessageDao dao = response.body();

                    tvDataJoin.setText(dao.getTime());
                    tvmessage.setText(dao.getStatus());

                } else {
                    try {
                        Toast.makeText(getActivity(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageDao> call, Throwable t) {

                Toast.makeText(getActivity(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
                Log.e("xxx",t.toString());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private void getJoin(final int idu, final int ids) {

        Call<inuserjoinDao> call = HTTPManager.getInstances().getService().getjoin(idu, ids);
        call.enqueue(new Callback<inuserjoinDao>() {
            @Override
            public void onResponse(Call<inuserjoinDao> call, Response<inuserjoinDao> response) {
                inuserjoinDao dao = response.body();
                if (response.isSuccessful()) {
                    if (dao.getSuccess() == 1) {
                        Toast.makeText(getContext(), dao.getJoin(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), dao.getJoin(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<inuserjoinDao> call, Throwable t) {

                Toast.makeText(getContext(), "NO...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
