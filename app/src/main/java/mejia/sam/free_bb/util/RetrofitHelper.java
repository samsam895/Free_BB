package mejia.sam.free_bb.util;

import javax.inject.Inject;

import mejia.sam.free_bb.util.rest.PerService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static mejia.sam.free_bb.util.Constants.BASE_URL;

/**
 * Created by User on 4/1/2017.
 */

public class RetrofitHelper {

    public PerService create(){
        HttpLoggingInterceptor interceptor =   new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client  =   new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit    =   new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        PerService service =   retrofit.create(PerService.class);
        return service;
    }
}
