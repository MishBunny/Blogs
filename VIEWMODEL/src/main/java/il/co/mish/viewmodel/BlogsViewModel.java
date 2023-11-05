package il.co.mish.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import il.co.mish.model.BlogPost;

public class BlogsViewModel extends ViewModel{

    private MutableLiveData<Boolean> successOperation;
    public BlogsViewModel() {
        successOperation = new MutableLiveData<>();
    }

    public LiveData<Boolean> getSuccessOperation(){
        return successOperation;
    }

    public void add(BlogPost blogPost){
        successOperation.setValue(true);
    }
}
