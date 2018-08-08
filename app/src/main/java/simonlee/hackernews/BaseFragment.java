package simonlee.hackernews;

import android.content.Context;
import android.support.v4.app.Fragment;


public abstract class BaseFragment
        extends Fragment
        implements Injectable {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.inject(AppContext.getAppContext().getAppComponent());
    }

    @Override
    public void inject(AppComponent appComponent) {
        // override this method if there are fields that need injecting
        appComponent.inject(this);
    }
}
