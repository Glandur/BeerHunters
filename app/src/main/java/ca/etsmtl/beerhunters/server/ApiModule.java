package ca.etsmtl.beerhunters.server;

import android.content.Context;

import com.google.gson.Gson;

import ca.etsmtl.beerhunters.R;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 10/10/2016.
 */
@Module
public class ApiModule {

    private Context mContext;

    public ApiModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public ApiManager provideManager(Gson gson) {
        return new ApiManager(gson,mContext.getString(R.string.api_key));
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

}



