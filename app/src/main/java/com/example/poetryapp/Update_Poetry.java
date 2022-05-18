package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update_Poetry extends AppCompatActivity {

    EditText poetry_data;
    Button btnUpdate;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__poetry);

        poetry_data = findViewById(R.id.update_poetryData);
        btnUpdate = findViewById(R.id.btnPoetryUpdate);

        Intent intent = getIntent();
        String poetryData = intent.getStringExtra("PoetryData");
        int id = intent.getIntExtra("Id",0);

        poetry_data.setText(poetryData);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poetry = poetry_data.getText().toString();
                if(TextUtils.isEmpty(poetry_data.getText().toString())){
                    poetry_data.setError("Filed is Empty");
                }
                else{
                    apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
                    apiInterface.updateData(poetry, id).enqueue(new Callback<Data>() {
                        @Override
                        public void onResponse(Call<Data> call, Response<Data> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(Update_Poetry.this, "Poetry Updated Successfully...", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Update_Poetry.this, "Poetry Not Updated Successfully...", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Data> call, Throwable t) {
                            Toast.makeText(Update_Poetry.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


    }
}