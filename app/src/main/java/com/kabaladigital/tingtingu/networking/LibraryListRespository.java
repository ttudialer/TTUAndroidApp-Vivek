package com.kabaladigital.tingtingu.networking;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.service.SharesPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryListRespository {
    private ApiInterface apiInterface;
    private static LibraryListRespository libraryListRepository;

    public static LibraryListRespository getInstance(){
        if (libraryListRepository == null){
            libraryListRepository = new LibraryListRespository();
        }
        return libraryListRepository;
    }

    public LibraryListRespository(){
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

//    public LiveData<List<LibraryGetResponse>> getLibraryList() {
//        final MutableLiveData<List<LibraryGetResponse>> responseMutableLiveData = new MutableLiveData<>();
//        apiInterface.getLibraryGet()
//                .enqueue(new Callback<List<LibraryGetResponse>>() {
//                    @Override
//                    public void onResponse(Call<List<LibraryGetResponse>> call,
//                                           Response<List<LibraryGetResponse>> response) {
//                        if (response.code() == 200) {
//                            responseMutableLiveData.setValue(response.body());
//                            Log.d("result::: ", "onResult::: "+response.body().toString() );
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<List<LibraryGetResponse>> call, Throwable t) {
//                        Log.d("Errorrr", "onFailure: " + t.getMessage());
//                    }
//                });
//        return responseMutableLiveData;
//    }
}

