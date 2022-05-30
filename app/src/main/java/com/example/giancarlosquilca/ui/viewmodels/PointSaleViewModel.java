package com.example.giancarlosquilca.ui.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.giancarlosquilca.data.OperationResult;
import com.example.giancarlosquilca.data.callback.Callable;
import com.example.giancarlosquilca.domain.models.PointSale;
import com.example.giancarlosquilca.domain.usecase.GetPointSalesUseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlos Quilca on 30/05/2022.
 */
public class PointSaleViewModel extends ViewModel {

    private final GetPointSalesUseCase getPointSalesUseCase;

    public PointSaleViewModel(@NonNull GetPointSalesUseCase getPointSalesUseCase) {
        this.getPointSalesUseCase = getPointSalesUseCase;
    }

    private MutableLiveData<List<PointSale>> _orders = new MutableLiveData<>();
    public LiveData<List<PointSale>> getPointSales() {
        return _orders;
    }

    public void fetchPointSales() {
        getPointSalesUseCase.execute(new Callable<OperationResult<PointSale>>() {
            @Override
            public void call(OperationResult<PointSale> orderOperationResult) {
                if (orderOperationResult.getSuccess() != null) {
                    List<PointSale> result = orderOperationResult.getSuccess();
                    if (result.isEmpty()) {

                    } else {
                        _orders.setValue(result);
                    }
                } else if (orderOperationResult.getFailure() != null) {

                }
            }

        });
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final GetPointSalesUseCase useCase;

        public Factory(@NonNull GetPointSalesUseCase useCase) {
            this.useCase = useCase;
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PointSaleViewModel(useCase);
        }
    }

}
