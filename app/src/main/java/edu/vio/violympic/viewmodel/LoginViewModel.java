package edu.vio.violympic.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;

import edu.vio.violympic.utils.firebase.FirebaseUtil;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";
    private FirebaseAuth mAuth;

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
}
