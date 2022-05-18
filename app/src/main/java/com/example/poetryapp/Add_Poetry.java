package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class Add_Poetry extends AppCompatActivity {

    EditText poetry_data, poet_name;
    Button btnAdd;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__poetry);
        btnAdd = findViewById(R.id.btnAdd);
        poetry_data = findViewById(R.id.poetryData);
        poet_name = findViewById(R.id.poetName);

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poetry = poetry_data.getText().toString();
                String poet = poet_name.getText().toString();
                if (TextUtils.isEmpty(poetry_data.getText().toString())) {
                    poetry_data.setError("Field is Empty");
                } else {
                    if (TextUtils.isEmpty(poet_name.getText().toString())) {
                        poet_name.setError("Field is Empty");
                    } else {
                        apiInterface.insertData(poetry, poet).enqueue(new Callback<Data>() {
                            @Override
                            public void onResponse(Call<Data> call, Response<Data> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(Add_Poetry.this, "Poetry Added Successfully...", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Add_Poetry.this, "Poetry Not Added Successfully...", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Data> call, Throwable t) {
                                Toast.makeText(Add_Poetry.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });
    }
}