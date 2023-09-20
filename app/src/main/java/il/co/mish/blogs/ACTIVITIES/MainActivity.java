package il.co.mish.blogs.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import il.co.mish.blogs.R;

public class MainActivity extends BaseActivity
{

    private Button btnAddPost;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    @Override
    protected void initializeViews()
    {
        btnAddPost = findViewById(R.id. btnAddPost);

        setListeners();

    }

    @Override
    protected void setListeners() {
        // Add listener to btnAddPost
    }

}