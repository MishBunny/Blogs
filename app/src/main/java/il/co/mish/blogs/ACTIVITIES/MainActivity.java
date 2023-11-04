package il.co.mish.blogs.ACTIVITIES;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import il.co.mish.blogs.R;
import il.co.mish.helper.DateUtil;

public class MainActivity extends BaseActivity
{

    private Button btnAddPost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        setListeners();
    }

    @Override
    protected void initializeViews()
    {
        btnAddPost = findViewById(R.id.btnAddPost);

    }


    @Override
    protected void setListeners()
    {
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BlogPostActivity.class);
                startActivity(intent);
            }
        });
    }


}