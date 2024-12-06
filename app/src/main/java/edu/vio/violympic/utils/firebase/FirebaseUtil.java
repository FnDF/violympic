package edu.vio.violympic.utils.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseUtil {
    public static final String TABLE_USERS = "users";
    private final static String TAG = "FirebaseUtil";
    private final static String FIREBASE_URL = "https://violympic-a876f-default-rtdb.asia-southeast1.firebasedatabase.app/";

    // Get Database reference
    public static DatabaseReference getDatabase(String tableName) {
        return FirebaseDatabase.getInstance(FIREBASE_URL).getReference(tableName);
    }

    // Get user by email
    public static Query getUserByEmail(String email) {
        DatabaseReference ref = FirebaseDatabase.getInstance(FIREBASE_URL).getReference(TABLE_USERS);
        return ref.orderByChild("email").equalTo(email).limitToFirst(1);
    }

    // Get user by username
    public static Query getUserByUsername(String username) {
        DatabaseReference ref = FirebaseDatabase.getInstance(FIREBASE_URL).getReference(TABLE_USERS);
        return ref.orderByChild("username").equalTo(username).limitToFirst(1);
    }

    //	get user by string like username
    public static Query getUserByStringLikeUsername(String username) {
        DatabaseReference ref = FirebaseDatabase.getInstance(FIREBASE_URL).getReference(TABLE_USERS);
        return ref.orderByChild("username").startAt(username).endAt(username + "\uf8ff");
    }
}
