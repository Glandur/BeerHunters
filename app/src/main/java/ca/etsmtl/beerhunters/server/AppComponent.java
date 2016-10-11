package ca.etsmtl.beerhunters.server;

import com.google.gson.Gson;

import dagger.Component;

/**
 * Created by admin on 10/10/2016.
 *
 */
@Component(modules = {ApiModule.class, GsonModule.class})
public interface AppComponent {
    ApiManager manager();
    Gson gson();
}
