package project.mvpdemo.ServiceCall.ProductDetails;


import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import project.mvpdemo.ApiManager.ApiManager;
import project.mvpdemo.Utils.Injector;
import project.mvpdemo.ecommerce.Model.ProductModelMain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */

public class CallProductDetails {
    EventBus bus;

    public void callProductDetails(final ApiManager apiManager) {
        bus = Injector.provideEventBus();

        Call<ProductModelMain> travelDataList = apiManager.getService().getSampleData();
        travelDataList.enqueue(new Callback<ProductModelMain>() {
            @Override
            public void onResponse(Call<ProductModelMain> call, Response<ProductModelMain> response) {
                try {
                    if (response.isSuccessful()) {
                        ProductModelMain productModelMain = response.body();
                        bus.post(new ProductDetailsSuccessEvent(response.isSuccessful(), productModelMain));
                    } else {
                        Converter<ResponseBody, Error> errorConverter =
                                apiManager.retrofit.responseBodyConverter(Error.class, new Annotation[0]);
                        // Convert the error body into our Error type.
                        try {
                            Error error = errorConverter.convert(response.errorBody());
                            String errorMessageDisplay = error.message;
                            bus.post(new ProductDetailsFailureEvent(false, errorMessageDisplay));

                        } catch (Exception e) {
                            e.printStackTrace();
                            bus.post(new ProductDetailsFailureEvent(false, null));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    bus.post(new ProductDetailsFailureEvent(false, null));
                }
            }

            @Override
            public void onFailure(Call<ProductModelMain> call, Throwable t) {
                t.printStackTrace();
                bus.post(new ProductDetailsFailureEvent(false, null));
            }
        });

    }

    static class Error {
        String message;
    }
}
