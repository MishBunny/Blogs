package il.co.mish.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import il.co.mish.model.BlogPost;

public class BlogsRepository {

    private FirebaseFirestore db;
    private CollectionReference collection;
    TaskCompletionSource<Boolean> taskCompletion;

    public BlogsRepository(Context context){
        try {
            db = FirebaseFirestore.getInstance();
        }
        catch (Exception e){
            FirebaseInstance instance =
                    FirebaseInstance.instance(context);

            db = FirebaseFirestore
                    .getInstance(FirebaseInstance.app);
        }

        collection = db.collection("Blogs");


    }


    public Task<Boolean> add (BlogPost blogPost){
        taskCompletion = new TaskCompletionSource<>();
        DocumentReference document = collection.document();
        blogPost.setIdFs(document.getId());
        document.set(blogPost).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                taskCompletion.setResult(true);
            }

        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                taskCompletion.setResult(false);
            }
            });

        return taskCompletion.getTask();
    }


}
