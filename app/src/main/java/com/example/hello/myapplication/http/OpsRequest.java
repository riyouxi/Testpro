package com.example.hello.myapplication.http;


import com.example.hello.myapplication.http.client.CommonOkhttpClient;
import com.example.hello.myapplication.http.listener.DisposeDataHandle;
import com.example.hello.myapplication.http.listener.DisposeDataListener;
import com.example.hello.myapplication.http.request.CommonRequest;
import com.example.hello.myapplication.http.response.CommonJsonCallback;

import okhttp3.Request;

public class OpsRequest<Q,E> {

    private String url;

    private Q requestValue;

    private Class<?> responseContent;

    public OpsRequest(String url){
        this.url = url;
    }

    public static <Q,E> OpsRequest<Q,E> create(String url){
        return new OpsRequest<Q,E>(url);
    }

    public OpsRequest<Q,E> requestValue(Q value){
        this.requestValue = value;
        return this;
    }

    public OpsRequest<Q,E> setResponseValue(Class<?> responseContent){
        this.responseContent = responseContent;
        return this;
    }

    public void excute(DisposeDataListener<E> listener){
        Request request = CommonRequest.createPostRequest(this.url,this.requestValue);
        CommonJsonCallback jsonCallback = new CommonJsonCallback(new DisposeDataHandle(listener,this.responseContent));
        CommonOkhttpClient.sendRequest(request, jsonCallback);
    }

}
