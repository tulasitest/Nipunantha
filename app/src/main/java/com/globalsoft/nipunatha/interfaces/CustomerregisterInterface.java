package com.globalsoft.nipunatha.interfaces;

import android.net.Uri;

import com.globalsoft.nipunatha.bean.createjob;
import com.globalsoft.nipunatha.bean.custmersendotp;
import com.globalsoft.nipunatha.bean.customerChangePwd;
import com.globalsoft.nipunatha.bean.customerForgot;
import com.globalsoft.nipunatha.bean.customerJobs;
import com.globalsoft.nipunatha.bean.customerLogin;
import com.globalsoft.nipunatha.bean.customerProfile;
import com.globalsoft.nipunatha.bean.customerProfileEdit;
import com.globalsoft.nipunatha.bean.customerjobtype;
import com.globalsoft.nipunatha.bean.customerregister;
import com.globalsoft.nipunatha.bean.statesbean;
import com.globalsoft.nipunatha.bean.citiesbean;
import com.globalsoft.nipunatha.bean.tradsmenprimarytrades;
import com.globalsoft.nipunatha.bean.verifyotp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CustomerregisterInterface {
    @POST("states")
   // appId=29032019&appKey=e180dd9db5c3af31bf66af67efb0454d&fname=sushu&lname=munaga&email=susmitha.adaveni@gmail.com&mobile_no=7396094082&state=2&city=90&area=adoni&pincode=518533
    //  @Streaming
    //  Call<statesbean> CustomerLogin();

    @FormUrlEncoded
    Call<statesbean> CustomerLogin(@Field("appId") String appid, @Field("appKey") String appKey);

    @POST("services")
    @FormUrlEncoded
    Call<tradsmenprimarytrades> servicestradsmenregister(@Field("appId") String appid, @Field("appKey") String appKey);

    @POST("cities")
    @FormUrlEncoded
    Call<citiesbean> Customercities(@Field("appId") String appid, @Field("appKey") String appKey, @Field("state_id") String state_id);


    @POST("customerRegister")
    @FormUrlEncoded
    Call<customerregister> login(@Field("appId") String appid, @Field("appKey") String appKey, @Field("fname") String fname, @Field("lname") String lname, @Field("email") String email, @Field("mobile_no") String mobile_no, @Field("state") String state, @Field("city") String city, @Field("area") String area, @Field("pincode") String pincode);

    @POST("customerVerify")
    @FormUrlEncoded
    Call<verifyotp> customerverifyotp(@Field("appId") String appid, @Field("appKey") String appKey, @Field("uid") int uid, @Field("otp") String otp);

    @POST("customersendOtp")
    @FormUrlEncoded
    Call<custmersendotp> customersendotp(@Field("appId") String appid, @Field("appKey") String appKey, @Field("mobile_no") String mobile_no);

    @POST("customerLogin")
    @FormUrlEncoded
    Call<customerLogin> customerLogin(@Field("appId") String appid, @Field("appKey") String appKey, @Field("email") String email, @Field("pwd") String pwd);

    @POST("customerForgot")
    @FormUrlEncoded
    Call<customerForgot> customerForgot(@Field("appId") String appid, @Field("appKey") String appKey, @Field("email") String email);

    @POST("customerChangePwd")
    @FormUrlEncoded
    Call<customerChangePwd> customerChangePwd(@Field("appId") String appid, @Field("appKey") String appKey, @Field("opwd") String opwd, @Field("npwd") String npwd, @Field("uid") int uid);

    @POST("customerProfile")
    @FormUrlEncoded
    Call<customerProfile> customerProfile(@Field("appId") String appid, @Field("appKey") String appKey, @Field("uid") int uid);

    @POST("customerProfileEdit")
    @FormUrlEncoded
    Call<customerProfileEdit> customerProfileEdit(@Field("appId") String appid, @Field("appKey") String appKey, @Field("fname") String fname, @Field("lname") String lname, @Field("mobile_no") String mobile_no, @Field("state") String state, @Field("city") String city, @Field("area") String area, @Field("profile_pic") Uri profile_pic, @Field("uid") int uid);

    @POST("jobTypes")
    @FormUrlEncoded
    Call<customerjobtype> jobTypes(@Field("appId") String appid, @Field("appKey") String appKey, @Field("service_id") String service_id);


    @POST("createJob")
    @FormUrlEncoded
    Call<createjob> createJob(@Field("appId") String appid, @Field("appKey") String appKey, @Field("job_summery") String job_summery, @Field("job_details") String job_details, @Field("timings") String timings, @Field("expected_date") String expected_date, @Field("service_id") String service_id, @Field("job_type_id") String job_type_id, @Field("uid") int uid);

    @POST("customerJobs")
    @FormUrlEncoded
    Call<customerJobs> customerJobs(@Field("appId") String appid, @Field("appKey") String appKey, @Field("uid") int uid);

}
