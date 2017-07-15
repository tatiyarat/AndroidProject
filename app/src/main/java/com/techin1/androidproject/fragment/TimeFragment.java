package com.techin1.androidproject.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.techin1.androidproject.R;
import com.techin1.androidproject.Service.MyService;
import com.techin1.androidproject.dao.GetDateDao;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TimeFragment extends Fragment {

    private PendingIntent pIntent;
    private AlarmManager alarm;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public TimeFragment() {
        super();
    }

    public static TimeFragment newInstance() {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);
        initInstances(rootView);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initInstances(View rootView) {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Init 'View' instance(s) with rootView.findViewById here
        Call<GetDateDao> call = HTTPManager.getInstances().getService().getdate(1);
        call.enqueue(new Callback<GetDateDao>() {
            @Override
            public void onResponse(Call<GetDateDao> call, Response<GetDateDao> response) {
                GetDateDao dao = response.body();
                if (response.isSuccessful()) {
//                    Toast.makeText(getActivity(),
//                            dao.getData().get(1).getId_s(),
//                            Toast.LENGTH_SHORT)
//                            .show();


                    for (int i = 0; i < dao.getData().size(); i++) {
                        year = dao.getData().get(i).getYear();
                        month = dao.getData().get(i).getMonth();
                        day = dao.getData().get(i).getDay();

                        hour = dao.getData().get(i).getHh();
                        minute = dao.getData().get(i).getMm();

                        setTimeDate(year, month, day, hour, minute);
                    }
//                    Log.e("date1", "" + dao.getData().size());
//                    Log.e("date1", "" + dao.getData().get(1).getYear());
//                    Log.e("date2",""+dao.getData().get(1).getDate().getYear());
//                    Log.e("date2",""+dao.getData().get(1).getDate().getMonth());
//                    Log.e("date2",""+dao.getData().get(1).getDate().getDate());
//                    Log.e("date2",""+dao.getData().get(1).getDate().getHours());
//                    Log.e("date2",""+dao.getData().get(1).getDate().getMinutes());


                } else {
                    try {
                        Toast.makeText(getActivity(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                        Log.e("error1", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDateDao> call, Throwable t) {
                Toast.makeText(getActivity(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
                Log.e("error2", t.toString());
            }
        });


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("date", "xxx" + sdf.format(cal.getTime()));

//                for (int i = 0; i < day.length; i++) {
//
//                    cal.set(year, month[i] - 1, day[i], hour[i], minute[i]);
//                    Log.e("date", "" + sdf.format(cal.getTime()));
//
//                    Intent intent = new Intent(getActivity(), MyService.class);
//                    pIntent = PendingIntent.getService(getActivity(), i, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//                    alarm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//                    alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pIntent);
//
//                }

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTimeDate(int year, int month, int day, int hour, int minute) {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cal.set(year, month - 1, day, hour, minute);
        Log.e("date", "" + sdf.format(cal.getTime()));

        Intent intent = new Intent(getActivity(), MyService.class);
        pIntent = PendingIntent.getService(getActivity(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pIntent);
    }
}
