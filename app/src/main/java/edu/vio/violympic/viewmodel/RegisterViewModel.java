package edu.vio.violympic.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.vio.violympic.model.UserFr;
import edu.vio.violympic.utils.firebase.FirebaseUtil;

public class RegisterViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";
    private FirebaseAuth mAuth;

    // region -> LiveData

    private final MutableLiveData<Boolean> stateUsernameIsExists = new MutableLiveData<>(false);

    public LiveData<Boolean> getStateUsernameIsExists() {
        return stateUsernameIsExists;
    }

    private final MutableLiveData<Boolean> stateRegisterObserver = new MutableLiveData<>(false);

    public LiveData<Boolean> getStateRegisterObserver() {
        return stateRegisterObserver;
    }

    // endregion

    public void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(Activity activity, String emailText, String passwordText) {
        if (emailText.contains("@")) {
            loginFirebase(activity, emailText, passwordText);
        } else {
            Query query = FirebaseUtil.getUserByUsername(emailText);
            query.get().addOnSuccessListener(dataSnapshot -> {
//                if (dataSnapshot.exists()) {
//                    User user = new User(dataSnapshot.getChildren().iterator().next());
//                    loginFirebase(user.getEmail(), passwordText);
//                }
            });
        }
    }

    private void loginFirebase(Activity activity, String emailText, String passwordText) {
        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(activity, task -> {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (task.isSuccessful() && user != null) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");

                        Query query = FirebaseUtil.getUserByEmail(user.getEmail());
                        query.get().addOnSuccessListener(snapshot -> {
                            if (snapshot.exists()) {
//                                User user1 = new User(snapshot.getChildren().iterator().next());
//                                Toast.makeText(LoginActivity.this, "Welcome back, FirebaseUser: " + user1.getFullname(), Toast.LENGTH_SHORT).show();
//                                finishLogin(LOGIN_SUCCESS, user1);
                            } else {
//                                finishLogin(LOGIN_FAIL, null);
                            }
                        }).addOnFailureListener(e -> {
                            Log.e(TAG, "loginFirebase: " + e.getMessage());
//                            finishLogin(LOGIN_FAIL, null);
                        });
                    } else {
                        // Toast.makeText(activity, "Đăng nhập thất bại, vui lòng kiểm tra lại thông tin đăng nhập", Toast.LENGTH_SHORT).show();
//                        password.setError("Vui lòng kiểm tra lại mật khẩu");
                    }
                });
    }

    private void loginUsername() {

    }

    public void register(String email, String password, String fullName, String username, String phone) {
//        Query query = FirebaseUtil.getUserByUsername(username);
//        query.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                if (task.getResult().getChildrenCount() > 0) {
//                    stateUsernameIsExists.postValue(true);
//                } else {
//                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task1 -> {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        }
//                    }).addOnFailureListener(e -> {
//
//                    });
//                }
//            }
//        });
        UserFr user = new UserFr(email, password, fullName, username, phone);
        FirebaseFirestore.getInstance()
                .collection("users")
                .where(Filter.or(Filter.equalTo("username", username), Filter.equalTo("email", email)))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && !task.getResult().isEmpty()) {
                        stateUsernameIsExists.postValue(true);
                    } else {
                        FirebaseFirestore.getInstance().collection("users").add(user).addOnCompleteListener(task1 -> {
                            stateRegisterObserver.postValue(true);
                        }).addOnFailureListener(e1 -> {
                            stateRegisterObserver.postValue(true);
                        });
                    }
                }).addOnFailureListener(e -> {
                    stateRegisterObserver.postValue(true);
                });
    }
}
