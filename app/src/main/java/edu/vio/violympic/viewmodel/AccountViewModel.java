package edu.vio.violympic.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class AccountViewModel extends ViewModel {

    // region -> LiveData

    private final MutableLiveData<Boolean> isLogged = new MutableLiveData<>(null);

    public LiveData<Boolean> getStateLogin() {
        return isLogged;
    }

    // endregion

    private FirebaseAuth mAuth;

    public void checkUser() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        isLogged.postValue(mAuth.getCurrentUser() != null);
    }
}
