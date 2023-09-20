package il.co.mish.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseEntity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_entity);
    }
}