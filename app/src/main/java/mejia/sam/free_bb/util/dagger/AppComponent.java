package mejia.sam.free_bb.util.dagger;

import javax.inject.Singleton;

import dagger.Component;
import mejia.sam.free_bb.ListActivity;

/**
 * Created by User on 4/1/2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject  (ListActivity activity);
}