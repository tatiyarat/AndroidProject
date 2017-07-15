package com.techin1.androidproject.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.LogoutDao;
import com.techin1.androidproject.dao.ResetPasswordDao;
import com.techin1.androidproject.manager.HTTPManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ResetPasswordFragment extends Fragment implements View.OnClickListener {

    EditText old_password, new_password;
    Button butuppassword;

    String oldpass, newpass;

    public SharedPreferences sharedPreferences;
    int idu;

    public ResetPasswordFragment() {
        super();
    }

    public static ResetPasswordFragment newInstance() {
        ResetPasswordFragment fragment = new ResetPasswordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reset_password, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        sharedPreferences = this.getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_APPEND);
        idu = sharedPreferences.getInt("iduser", 0);


        old_password = (EditText) rootView.findViewById(R.id.old_password);
        new_password = (EditText) rootView.findViewById(R.id.New_password);
        butuppassword = (Button) rootView.findViewById(R.id.butuppassword);
        butuppassword.setOnClickListener(this);

    }

    private void resetpassword(int idu, String oldpass, String newpass) {
        Call<ResetPasswordDao> call = HTTPManager.getInstances().getService().getresetpassword(idu, oldpass, newpass);
        call.enqueue(new Callback<ResetPasswordDao>() {
            @Override
            public void onResponse(Call<ResetPasswordDao> call, Response<ResetPasswordDao> response) {
                ResetPasswordDao dao = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "" + dao.getResetpassword()
                            , Toast.LENGTH_SHORT)
                            .show();

                    if (dao.getResetpassword().equals("เรียบร้อย")) {
                        old_password.setText("");
                        new_password.setText("");
                    }

                } else {
                    Toast.makeText(getActivity(), "fail!"
                            , Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordDao> call, Throwable t) {
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

    @Override
    public void onClick(View view) {
        if (view == butuppassword) {
            oldpass = old_password.getText().toString();
            newpass = new_password.getText().toString();

            resetpassword(idu, oldpass, newpass);
        }

    }
}
