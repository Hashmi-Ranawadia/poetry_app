package com.example.poetryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    Data[] data;

    ApiInterface apiInterface;

    public MyAdapter(Context context, Data[] data) {
        this.context = context;
        this.data = data;
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.getpoetry_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data mydata = data[position];
        holder.name.setText(mydata.getPoet_name());
        holder.poetry.setText(mydata.getPoetry_data());

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.deleteData(mydata.getId()+"").enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Delete Successfully...", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update_Poetry.class);
                intent.putExtra("PoetryData", mydata.getPoetry_data());
                intent.putExtra("Id", mydata.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,poetry;
        Button del,update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            poetry = itemView.findViewById(R.id.txtPoetry);
            update = itemView.findViewById(R.id.btnupdate);
            del = itemView.findViewById(R.id.btnDelete);

        }

    }
}
