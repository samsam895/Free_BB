package mejia.sam.free_bb.util.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mejia.sam.free_bb.util.RetrofitHelper;

/**
 * Created by User on 4/1/2017.
 */
@Module
public class AppModule {

    private App   app;

    public AppModule(App app){this.app  =   app;}



    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper (){
        return new RetrofitHelper();
    }
}
