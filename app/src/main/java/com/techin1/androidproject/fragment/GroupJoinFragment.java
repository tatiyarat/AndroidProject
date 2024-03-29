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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import com.techin1.androidproject.R;
import com.techin1.androidproject.adapter.GroupJoinListAdapter;
import com.techin1.androidproject.dao.AddGroupJoinDao;
import com.techin1.androidproject.dao.GroupJoinDataDao;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class GroupJoinFragment extends Fragment {

    ListView listgroupjoin;
    GridView gridgroupjoin;
    public SharedPreferences sharedPreferences;
    int idu;

    GroupJoinListAdapter groupJoinListAdapter;


    public GroupJoinFragment() {
        super();
    }

    public static GroupJoinFragment newInstance() {
        GroupJoinFragment fragment = new GroupJoinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.title_activity_menu_group));

        View rootView = inflater.inflate(R.layout.fragment_group_join, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here


//        listgroupjoin = (ListView) rootView.findViewById(R.id.listgroupjoin);
//        groupJoinListAdapter = new GroupJoinListAdapter();
//        listgroupjoin.setAdapter(groupJoinListAdapter);

        gridgroupjoin = (GridView) rootView.findViewById(R.id.gridgroupjoin);
        groupJoinListAdapter = new GroupJoinListAdapter();
        gridgroupjoin.setAdapter(groupJoinListAdapter);

        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser", 0);

        getGroupJoin(idu);

    }

    private void getGroupJoin(final int idu) {
        Call<GroupJoinDataDao> call = HTTPManager.getInstances().getService().getgrojoin(idu);
        call.enqueue(new Callback<GroupJoinDataDao>() {
            @Override
            public void onResponse(Call<GroupJoinDataDao> call, Response<GroupJoinDataDao> response) {
                if (response.isSuccessful()) {
                    final GroupJoinDataDao dao = response.body();

                    groupJoinListAdapter.setDao(dao);
                    groupJoinListAdapter.notifyDataSetChanged();

                    gridgroupjoin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            AddUserJoin(idu, dao.getData().get(position).getId(), dao.getData().get(position).getGname());
                        }
                    });

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
            public void onFailure(Call<GroupJoinDataDao> call, Throwable t) {
                Toast.makeText(getActivity(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
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

    private void AddUserJoin(final int uid, final int gid, String name) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        alert.setTitle("กลุ่ม : " + name);
        alert.setMessage("ยืนยันการเข้าร่วมกลุ่ม");
        alert.setIcon(R.drawable.confirm);

        alert.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                groupjain(uid,gid);
            }

        });

        alert.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.create();
        alert.show();
    }

    private void groupjain(final int uid, final int gid) {

        Call<AddGroupJoinDao> call = HTTPManager.getInstances().getService().getaddgroupjoin(uid, gid);
        call.enqueue(new Callback<AddGroupJoinDao>() {
            @Override
            public void onResponse(Call<AddGroupJoinDao> call, Response<AddGroupJoinDao> response) {
                AddGroupJoinDao dao = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "" + dao.getShow()
                            , Toast.LENGTH_SHORT)
                            .show();

                } else {
                    Toast.makeText(getActivity(), "fail!"
                            , Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<AddGroupJoinDao> call, Throwable t) {
                Toast.makeText(getActivity(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

}
