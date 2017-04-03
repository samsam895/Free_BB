package mejia.sam.free_bb.util.dagger;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

/**
 * Created by User on 4/1/2017.
 */

public class App   extends Application {
    private AppComponent    component;

    @VisibleForTesting
    protected AppComponent  createComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        if (app.component == null) {
            app.component = app.createComponent();
        }
        return app.component;
    }}
