package team.max.jsonplaceholder.main.network;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceApiFactori {

    private static final String SERVER_URL = "https://jsonplaceholder.typicode.com/";

    public static ServiceApi getService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ServiceApi service = retrofit.create(ServiceApi.class);
        return service;
    }
}
