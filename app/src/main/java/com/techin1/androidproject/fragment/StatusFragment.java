package com.techin1.androidproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.techin1.androidproject.R;
import com.techin1.androidproject.activity.InMessageActivity;
import com.techin1.androidproject.activity.InMessage_JoinActivity;
import com.techin1.androidproject.activity.MenuGroupActivity;
import com.techin1.androidproject.adapter.StatusListAdapter;
import com.techin1.androidproject.dao.PhotoItemCollectinDao;
import com.techin1.androidproject.manager.HTTPManager;
import com.techin1.androidproject.view.JoinMessageListltem;
import com.techin1.androidproject.view.JoinMessageNonImListltem;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class StatusFragment extends Fragment {

    ListView listStatus;
    StatusListAdapter listAdapter;
    public SharedPreferences sharedPreferences;
    int idg;
    SwipeRefreshLayout swipeRefreshLayout;
    Button btnNew;
    int idmessage;

    public StatusFragment() {
        super();
    }

    public static StatusFragment newInstance() {
        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_status, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idg = sharedPreferences.getInt("idgroup", 0);

        initInstances(rootView, idg);

        return rootView;
    }

    private void initInstances(View rootView, final int idg) {
        // Init 'View' instance(s) with rootView.findViewById here
        listStatus = (ListView) rootView.findViewById(R.id.listStatus);
        listAdapter = new StatusListAdapter();
        listStatus.setAdapter(listAdapter);

        btnNew = (Button) rootView.findViewById(R.id.btnNew);

//        listStatus.setOnItemClickListener(listViewItemClicKListener);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData(idg);
            }
        });
        listStatus.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);

            }
        });

        reloadData(idg);
    }

    private void reloadData(int idg) {
        Call<PhotoItemCollectinDao> call = HTTPManager.getInstances().getService().getstatus(idg);
        call.enqueue(new Callback<PhotoItemCollectinDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectinDao> call,
                                   Response<PhotoItemCollectinDao> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    final PhotoItemCollectinDao dao = response.body();
//                    PhotoListManager.getInstances().setDao(dao);

                    listAdapter.setDao(dao);
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText(Contextor.getInstance().getContext(),
                            dao.getData().get(0).getGname(),
                            Toast.LENGTH_SHORT)
                            .show();

                    listStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                            JoinMessageListltem item;
//                            if (view != null)
//                                item = (JoinMessageListltem) view;
//                            else
//                                item = new JoinMessageListltem(parent.getContext());
//                            item.getIDS(dao.getStatus());

                            if (dao.getData().get(position).getJoin().equals("1") == true){
                                Intent intent = new Intent(getActivity(),
                                        InMessage_JoinActivity.class);
                                idmessage = dao.getData().get(position).getSid();
                                SharedPreferences sp = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putInt("idmessage", idmessage);
                                editor.commit();
                                startActivity(intent);

                            }
                            else {
                                Intent intent = new Intent(getActivity(),
                                        InMessageActivity.class);
                                idmessage = dao.getData().get(position).getSid();
                                SharedPreferences sp = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putInt("idmessage", idmessage);
                                editor.commit();
                                startActivity(intent);
                            }

                        }
                    });

                } else {
                    // Handle
                    PhotoItemCollectinDao dao = response.body();
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

//                listStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        Intent intent = new Intent(getActivity(),
//                                MessageActivity.class);
//                        startActivity(intent);
//                    }
//                });

            }

            @Override
            public void onFailure(Call<PhotoItemCollectinDao> call
                    , Throwable t) {
                // Handle
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Contextor.getInstance().getContext(),
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
//    final AdapterView.OnItemClickListener listViewItemClicKListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = new Intent(getContext(),
//                    InMessageActivity.class);
//            startActivity(intent);
////            Toast.makeText(getContext(), "Position" + position,
////                    Toast.LENGTH_SHORT)
////                    .show();
//        }
//    };

    public  void showButtonNewPhoros(){
        btnNew.setVisibility(View.VISIBLE);
    }

    public void hideButtonNewPhotos(){
        btnNew.setVisibility(View.GONE);
    }
}
