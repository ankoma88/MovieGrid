package com.ankoma88.app.moviegrid.api;


import com.ankoma88.app.moviegrid.util.Logger;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtils {
    public static final String TAG = RxUtils.class.getSimpleName();

	public static <T> Observable<T> wrapRetrofitCall(final Call<T> call) {
		return Observable.create(subscriber ->
		{
			final Response<T> execute;
			try {
                execute = call.execute();
			} catch (IOException e) {
				subscriber.onError(e);
				return;
			}

			if (execute.isSuccessful()) {
				subscriber.onNext(execute.body());
			} else {
				Logger.log("wrapRetrofitCall error", execute.raw().toString());
				subscriber.onError(new ApiError(execute.errorBody()));
			}
		});
	}

	public static <T> Observable<T> wrapAsync(Observable<T> observable) {
		return wrapAsync(observable, Schedulers.io());
	}

	public static <T> Observable<T> wrapAsync(Observable<T> observable, Scheduler scheduler) {
		return observable
				.subscribeOn(scheduler)
				.observeOn(AndroidSchedulers.mainThread());
	}


    public static <T> Observable<T> wrapRequest(final Call<T> call) {
		return wrapAsync(wrapRetrofitCall(call))
                .doOnNext(t -> Logger.log(TAG, "Movie of API request " + t.getClass().getSimpleName()+" is: " + t.toString()))
                .doOnError(throwable -> Logger.log(TAG, "Rx error: " + throwable));
    }

}
