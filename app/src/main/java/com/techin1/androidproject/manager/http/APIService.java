package com.techin1.androidproject.manager.http;


import com.techin1.androidproject.dao.GroupJoinDataDao;
import com.techin1.androidproject.dao.Groups;
import com.techin1.androidproject.dao.GroupsJoinDao;
import com.techin1.androidproject.dao.Home;
import com.techin1.androidproject.dao.Login;
import com.techin1.androidproject.dao.LogoutDao;
import com.techin1.androidproject.dao.MessageDao;
import com.techin1.androidproject.dao.PhotoItemCollectinDao;
import com.techin1.androidproject.dao.StatusDao;
import com.techin1.androidproject.dao.inuserjoinDao;
import com.techin1.androidproject.dao.upuser;

import java.security.acl.Group;

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
                         @Field("idu") int idu,
                         @Field("pass") String pass);

    @FormUrlEncoded
    @POST("menugroup.php")
    Call<Groups> getmenugroup(@Field("idu") int idu,
                              @Field("LIMIT") int LIMIT,
                              @Field("OFFSET") int OFFSET);

    @FormUrlEncoded
    @POST("status.php")
    Call<PhotoItemCollectinDao> getstatus(@Field("IDG") int idg);

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

}
