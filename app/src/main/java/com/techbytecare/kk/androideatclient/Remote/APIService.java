package com.techbytecare.kk.androideatclient.Remote;

import com.techbytecare.kk.androideatclient.Model.MyResponse;
import com.techbytecare.kk.androideatclient.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by kundan on 12/21/2017.
 */

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAArnT5fI0:APA91bGJsiQ0Ji58G7D1VtjSJNCw01VOB_J2V7STorAFOmvmDTob-_z_5ym9MFSz-yT4un3M0WKwrP32mhDQWrUfEmzS1qCYi92jOHlMckfZ5plVtr-RmHx7BE_2l221YN0X4phq04R-"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
