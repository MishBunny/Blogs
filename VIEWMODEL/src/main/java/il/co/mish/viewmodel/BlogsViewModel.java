package il.co.mish.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import il.co.mish.model.BlogPost;
import il.co.mish.repository.BlogsRepository;

public class BlogsViewModel extends ViewModel{

    private MutableLiveData<Boolean> successOperation;
    private BlogsRepository repository;

    public BlogsViewModel() {
        //repository = new BlogsRepository(context);

    }

    public LiveData<Boolean> getSuccessOperation(){
        return successOperation;
    }



    public void add(BlogPost blogPost){
        repository.add(blogPost)
                .addOnSuccessListener(aBoolean ->
                {successOperation.setValue(true);})
                .addOnFailureListener(e ->
                {successOperation.setValue(false);});
    }



}
