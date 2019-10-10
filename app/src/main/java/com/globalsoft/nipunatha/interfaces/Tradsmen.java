package com.globalsoft.nipunatha.interfaces;

import android.net.Uri;

import com.globalsoft.nipunatha.bean.TradsmenJobConfirm;
import com.globalsoft.nipunatha.bean.TradsmenJobbean;
import com.globalsoft.nipunatha.bean.Tradsmenprofile;
import com.globalsoft.nipunatha.bean.Tradsmenregister;
import com.globalsoft.nipunatha.bean.customerChangePwd;
import com.globalsoft.nipunatha.bean.customerForgot;
import com.globalsoft.nipunatha.bean.customerLogin;
import com.globalsoft.nipunatha.bean.customerProfileEdit;
import com.globalsoft.nipunatha.bean.customerregister;
import com.globalsoft.nipunatha.bean.tradesmenJobMatch;
import com.globalsoft.nipunatha.bean.tradsmenLogin;
import com.globalsoft.nipunatha.bean.verifyotp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Tradsmen {

    @POST("tradesmenRegister")
    @FormUrlEncoded
    Call<Tradsmenregister> tradsmenregister(@Field("appId") String appid, @Field("appKey") String appKey, @Field("fname") String fname, @Field("lname") String lname, @Field("email") String email, @Field("mobile_no") String mobile_no, @Field("state") String state, @Field("city") String city, @Field("area") String area, @Field("pincode") String pincode, @Field("primary_trade") String primary_trade, @Field("no_of_employees") String no_of_employees);

    @POST("tradesmenVerify")
    @FormUrlEncoded
    Call<verifyotp> tradesmenVerify(@Field("appId") String appid, @Field("appKey") String appKey, @Field("uid") int uid, @Field("otp") String otp);

    @POST("tradesmenLogin")
    @FormUrlEncoded
    Call<tradsmenLogin> tradesmenLogin(@Field("appId") String appid, @Field("appKey") String appKey, @Field("email") String email, @Field("pwd") String pwd);

    @POST("tradesmenForgot")
    @FormUrlEncoded
    Call<customerForgot> tradesmenForgot(@Field("appId") String appid, @Field("appKey") String appKey, @Field("email") String email);

    @POST("tradesmenProfile")
    @FormUrlEncoded
    Call<Tradsmenprofile> tradesmenProfile(@Field("appId") String appid, @Field("appKey") String appKey, @Field("uid") int uid);

    @POST("tradesmenChangePwd")
    @FormUrlEncoded
    Call<customerChangePwd> tradesmenChangePwd(@Field("appId") String appid, @Field("appKey") String appKey, @Field("opwd") String opwd, @Field("npwd") String npwd, @Field("uid") int uid);

    @POST("tradesmenProfileEdit")
    @FormUrlEncoded
    Call<customerProfileEdit> tradsmenProfileEdit(@Field("appId") String appid, @Field("appKey") String appKey, @Field("fname") String fname, @Field("lname") String lname, @Field("mobile_no") String mobile_no, @Field("state") String state, @Field("city") String city, @Field("area") String area, @Field("profile_pic") Uri profile_pic, @Field("uid") int uid, @Field("pincode") String pincode, @Field("no_of_employees") String no_of_employees);

    @POST("tradesmenJob")
    @FormUrlEncoded
    Call<TradsmenJobbean> tradesmenJob(@Field("appId") String appid, @Field("appKey") String appKey, @Field("service_id") String service_id, @Field("reg_id") String reg_id);

    @POST("tradesmenJobConfirm")
    @FormUrlEncoded
    Call<TradsmenJobConfirm> tradesmenJobConfirm(@Field("appId") String appid, @Field("appKey") String appKey, @Field("service_id") String service_id, @Field("reg_id") String reg_id);

    @POST("tradesmenJobMatch")
    @FormUrlEncoded
    Call<tradesmenJobMatch> tradesmenJobMatch(@Field("appId") String appid, @Field("appKey") String appKey, @Field("service_id") String service_id, @Field("reg_id") String reg_id);


}
