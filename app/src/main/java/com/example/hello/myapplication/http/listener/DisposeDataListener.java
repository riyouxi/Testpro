package com.example.hello.myapplication.http.listener;


import com.example.hello.myapplication.http.exception.OkHttpException;

/**********************************************************
 * @文件名称：DisposeDataListener.java
 * @文件描述：业务逻辑层真正处理的地方，包括java层异常和业务层异常
 **********************************************************/
public interface DisposeDataListener<T> {

	/**
	 * 请求成功回调事件处理
	 */
	public void onSuccess(T t);

	/**
	 * 请求失败回调事件处理
	 */
	public void onFailure(OkHttpException e);

}
