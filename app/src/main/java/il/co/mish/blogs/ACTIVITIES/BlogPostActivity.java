package il.co.mish.blogs.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import il.co.mish.blogs.R;
import il.co.mish.helper.DateUtil;
import il.co.mish.helper.inputValidators.DateRule;
import il.co.mish.helper.inputValidators.NameRule;
import il.co.mish.helper.inputValidators.RuleOperation;
import il.co.mish.helper.inputValidators.Rule;
import il.co.mish.helper.inputValidators.TextRule;
import il.co.mish.helper.inputValidators.Validator;
import il.co.mish.model.BlogPost;

public class BlogPostActivity extends AppCompatActivity {

    private ImageView ivCalendar;
    private EditText etDate;
    private EditText etAuthor;
    private EditText etTitle;
    private EditText etContent;
    private Button btnSave;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);

        initializeViews();
        setValidation();
        setListeners();
    }


    protected void initializeViews()
    {
        ivCalendar = findViewById(R.id.ivClendar);
        etDate = findViewById(R.id.etDate);
        etAuthor = findViewById(R.id.etAuthor);
        etTitle=findViewById(R.id.etTitle);
        etContent=findViewById(R.id.etContent);
        btnSave=findViewById(R.id.btnSave);
        btnCancel =findViewById(R.id.btnCancel);
    }
    public void setValidation() {

        Validator.add(new Rule(etAuthor,
                RuleOperation.REQUIRED,
                "Please enter author name"));

        Validator.add(new NameRule(etAuthor,
                RuleOperation.NAME,
                "Author name is wrong"));

        Validator.add(new Rule(etTitle,
                RuleOperation.REQUIRED,
                "Please enter title"));

        Validator.add(new TextRule(etTitle,
                RuleOperation.TEXT,
                "Title is wrong", 4, 50, true));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Validator.add(new DateRule(etDate,
                    RuleOperation.DATE, "Wrong date",
                    LocalDate.now().minusDays(10), LocalDate.now()));
        };

        Validator.add(new Rule(etContent,
                RuleOperation.REQUIRED,
                "Please enter content"));

        Validator.add(new TextRule(etContent,
                RuleOperation.TEXT,
                "Wrong content", 10, 1000, true));
    }

    public boolean validate() {
        return Validator.validate();
    }


    protected void setListeners()
    {
        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v)
            {
                MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select date");
                builder.setTextInputFormat(new SimpleDateFormat("dd/MM/yyyy"));

                // הגבלת טווח התאריכים לבחירה
                CalendarConstraints constraint = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    constraint = DateUtil.buidCalendarConstrains(LocalDate.now().minusDays(10), LocalDate.now());
                }
                builder.setCalendarConstraints(constraint);

                if (!etDate.getText().toString().isEmpty())
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        LocalDateTime date =
                                LocalDate.parse(etDate.getText().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
                        ZonedDateTime zdt = ZonedDateTime.of(date, ZoneId.systemDefault());
                        builder.setSelection(zdt.toInstant().toEpochMilli());
                    }

                MaterialDatePicker picker = builder.build();
                // Set a listener to update etDate when a date is chosen
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        etDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(selection)));
                    }
                });
                picker.show(getSupportFragmentManager(), picker.toString());

            }

        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dateString = etDate.getText().toString();
                Long daysSinceEpoch = null;
                DateTimeFormatter formatter = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                }
                LocalDate localDate = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    localDate = LocalDate.parse(dateString, formatter);
                }

                // Calculate number of days elapsed since January 1, 1970
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    daysSinceEpoch = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), localDate);
                }


                if (validate()) {
                    BlogPost blogPost = new BlogPost();
                    blogPost.setAuthor(etAuthor.getText().toString());
                    blogPost.setTitle(etTitle.getText().toString());
                    blogPost.setContent(etContent.getText().toString());
                    
                    blogPost.setDate(daysSinceEpoch);
                    //blogPost.setDate(Long.parseLong(etDate.getText().toString()));


                    // הצגת Toast
                    showToast("ready");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close the current activity
                finish();
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}