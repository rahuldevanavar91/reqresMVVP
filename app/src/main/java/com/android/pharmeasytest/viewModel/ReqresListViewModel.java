package com.android.pharmeasytest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.android.pharmeasytest.service.model.model.ReqresResponse;
import com.android.pharmeasytest.service.model.repository.ReqresResponseRepository;

public class ReqresListViewModel extends AndroidViewModel {

    private LiveData<ReqresResponse> mReqresList;
    private int mPageNumber;

    public ReqresListViewModel(@NonNull Application application) {
        super(application);
        getUser();

    }

    public void getUser() {
        mReqresList = ReqresResponseRepository.getInstance().getUserList(++mPageNumber);
    }

    public LiveData<ReqresResponse> getObservableProject() {
        return mReqresList;
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ReqresListViewModel(application);
        }
    }
}
