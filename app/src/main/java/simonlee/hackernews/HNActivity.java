package simonlee.hackernews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import simonlee.hackernews.R;
import simonlee.hackernews.services.ApiClient;

public class HNActivity extends AppCompatActivity {

    private static final String TAG = "HNActivity";

    @BindView(R.id.activity_hn_tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hn);
        ButterKnife.bind(this);

        ApiClient.getHackerNewsServices()
                .fetchTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(new Function<List<Long>, Observable<? extends Long>>() {
                    @Override
                    public Observable<? extends Long> apply(@NonNull List<Long> ids)
                            throws Exception {
                        return Observable.fromIterable(ids);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long id) {
                        tvText.append("; " + id);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        Toast.makeText(HNActivity.this, "error", Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onComplete() {
                        tvText.append(".\nCOMPLETED!!");
                    }
                });
    }
}
