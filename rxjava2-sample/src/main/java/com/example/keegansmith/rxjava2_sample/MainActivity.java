package com.example.keegansmith.rxjava2_sample;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Branch-start
        //Additional Info

//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(0);
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//
//                e.onComplete();
//            }
//        });


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v(this.getClass().getSimpleName(),"onSubscribe on Thread: "+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Integer integer) {
                Log.v(this.getClass().getSimpleName(),"onNext: "+integer+" On Thread: "+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.v(this.getClass().getSimpleName(),"onError: "+e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.v(this.getClass().getSimpleName(),"onComplete!");
            }
        };

        Log.v(this.getClass().getSimpleName(),"Assigning observer from "+Thread.currentThread().getName());
        Observable.just(1,2,3)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
