package il.co.mish.repository;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInstance {
    private static volatile FirebaseInstance _instance = null;
    public static FirebaseApp app;

    private FirebaseInstance(Context context) {
        FirebaseOptions options = new
                FirebaseOptions.Builder()
                .setProjectId("blogs-1a2ba")		// ApplicationId
                .setApplicationId("blogs-1a2ba")		// ProjectId
                .setApiKey("AIzaSyBbN1U5EzkYgNB7NLGsshD3XI8Molj1w1M")
                .setStorageBucket("blogs-1a2ba.appspot.com")
                .build();

        app = FirebaseApp.initializeApp(context, options);
    }

    public static FirebaseInstance instance(Context context) {
        if (_instance == null) {  // 1st check
            synchronized (FirebaseInstance.class) {
                if (_instance == null){ // 2nd check
                    _instance = new FirebaseInstance(context);
                }
            }
        }

        return _instance; }

}
