package com.example.covid_19;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.example.covid_19.Model.Example;
import com.example.covid_19.Network.NetworkUtil;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Home_presenter {
    Home_interface home_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;

    public Home_presenter(Home_interface home_interface, Context context) {
        this.home_interface = home_interface;
        this.context = context;
    }

    void GetData() {
        showLoadingDialog();
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsee, this::handleError));
    }

    private void handleResponsee(List<Example> examples) {
        home_interface.getdata(examples);
        alertDialog.dismiss();
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
    }

    private void showLoadingDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

}