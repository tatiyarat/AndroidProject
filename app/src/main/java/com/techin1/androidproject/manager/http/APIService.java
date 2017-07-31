package com.techin1.androidproject.manager.http;


import com.techin1.androidproject.dao.AddGroupJoinDao;
import com.techin1.androidproject.dao.ForgotPasswordDao;
import com.techin1.androidproject.dao.GetDateDao;
import com.techin1.androidproject.dao.GroupJoinDataDao;
import com.techin1.androidproject.dao.Groups;
import com.techin1.androidproject.dao.Home;
import com.techin1.androidproject.dao.Login;
import com.techin1.androidproject.dao.LogoutDao;
import com.techin1.androidproject.dao.MessageDao;
import com.techin1.androidproject.dao.PhotoItemCollectinDao;
import com.techin1.androidproject.dao.ResetPasswordDao;
import com.techin1.androidproject.dao.inuserjoinDao;
import com.techin1.androidproject.dao.upuser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zel2__000 on 5/26/2016.
 */
public interface APIService {

/*    @GET("Pro_1_PHP/login_data.php")
    Call<UserItemDao> getUser(@QueryMap Map<String,String> data);*/

    @FormUrlEncoded
    @POST("chklogin.php")
    Call<Login> getUser(@Field("IDuser") String user,
                        @Field("Passuser") String pass,
                        @Field("Token") String token);

    @FormUrlEncoded
    @POST("home.php")
    Call<Home> getNameUser(@Field("idu") int idu);

    @FormUrlEncoded
    @POST("upuser.php")
    Call<upuser> upuser (@Field("Snic") String Snic,
                         @Field("Snumber") String Snumber,
                         @Field("Semail") String Semail,
                         @Field("idu") int idu);

    @FormUrlEncoded
    @POST("menugroup.php")
    Call<Groups> getmenugroup(@Field("idu") int idu);

    @FormUrlEncoded
    @POST("status.php")
    Call<PhotoItemCollectinDao> getstatus(@Field("IDG") int idg,
                                          @Field("IDU") int idu);

    @FormUrlEncoded
    @POST("inmessage.php")
    Call<MessageDao> getidmessage(@Field("IDS") int ids);

    @FormUrlEncoded
    @POST("inuserjoin.php")
    Call<inuserjoinDao> getjoin(@Field("IDU") int idu,
                                @Field("IDS") int ids);

    @FormUrlEncoded
    @POST("groupjoin.php")
    Call<GroupJoinDataDao> getgrojoin(@Field("idu") int idu);

    @FormUrlEncoded
    @POST("logout.php")
    Call<LogoutDao> gettoken(@Field("token") String token);

    @FormUrlEncoded
    @POST("resetpassword.php")
    Call<ResetPasswordDao> getresetpassword(@Field("uid") int uid,
                                            @Field("old_password") String old_password,
                                            @Field("New_password") String New_password);

    @FormUrlEncoded
    @POST("groupjoinadd.php")
    Call<AddGroupJoinDao> getaddgroupjoin(@Field("uid") int uid,
                                          @Field("gid") int gid);

    @FormUrlEncoded
    @POST("Get_Date_Calendar.php")
    Call<GetDateDao> getdate(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("../Forgot_Password/ForgotPassword.php")
    Call<ForgotPasswordDao> getEmail(@Field("email") String email);
}
