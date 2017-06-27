package com.techin1.androidproject.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.techin1.androidproject.GroupMenuName;
import com.techin1.androidproject.LoginActivity;
import com.techin1.androidproject.R;
import com.techin1.androidproject.activity.MenuGroupActivity;
import com.techin1.androidproject.activity.StatusActivity;
import com.techin1.androidproject.adapter.GroupListAdapter;
import com.techin1.androidproject.dao.Groups;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoupMenuFragment extends Fragment {

    private int idu;
    int idg;
    int LIMIT = 3;
    int OFFSET = 0;
    ListView GlistView;
    GroupListAdapter groupListAdapter;
    public SharedPreferences sharedPreferences;
    GroupMenuName groupMenuName;

    ArrayList<GroupMenuName> gm = new ArrayList<>();

    public GoupMenuFragment() {
        // Required empty public constructor
    }

    public static GoupMenuFragment newInstance() {
        GoupMenuFragment fragment = new GoupMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.title_activity_menu_group));

        View rootView = inflater.inflate(R.layout.fragment_goup_menu, container, false);
        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        GlistView = (ListView) rootView.findViewById(R.id.GlistView);


        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser", 0);


        Call<Groups> call = HTTPManager.getInstances().getService().getmenugroup(idu,LIMIT,OFFSET);
        call.enqueue(new Callback<Groups>() {
            @Override
            public void onResponse(Call<Groups> call, Response<Groups> response) {
                if (response.isSuccessful()) {
                    getGoups(response);
                    Log.d("log_groupmenu",response.toString());
                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_LONG)
                                .show();
                        Log.d("log_groupmenu",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("log_groupmenu",e.toString());
                    }
                }

                groupListAdapter = new GroupListAdapter(gm,getContext());
                GlistView.setAdapter(groupListAdapter);
                GlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(),
                                StatusActivity.class);
                        idg = gm.get(position).getIDG();
//                        intent.putExtra("IDG",gm.get(position).getIDG());

                        SharedPreferences sp = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("idgroup", idg);
                        editor.commit();

                        startActivity(intent);

                        Toast.makeText(Contextor.getInstance().getContext(),""+gm.get(position).getNameGroup() ,Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Groups> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void getGoups(Response<Groups> response) {
        Groups dao = response.body();
        for (int i = 0; i < dao.getSumG(); i++) {
            groupMenuName = new GroupMenuName(dao.getGname().get(i).toString(),dao.getIMGroup().get(i).toString(),dao.getIDG().get(i).intValue());
            gm.add(groupMenuName);
//          Toast.makeText(Contextor.getInstance().getContext(), dao.getIMGroup().get(i).toString(),Toast.LENGTH_LONG).show();
//          ystem.out.print("XXX"+dao.getGname().get(i).toString());
//          Log.d("XXX",dao.getGname().get(i).toString());//
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
