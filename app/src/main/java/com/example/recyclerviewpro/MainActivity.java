package com.example.recyclerviewpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Model> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //自定义分割线
        // 自定义分割线
        recyclerView.addItemDecoration(new ModelDecoration(this));
        recyclerView.setAdapter(new ModelAdapter(modelList));
    }
    private void init(){
        modelList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0;  j< 20; j++) {
                if (i%2==0){
                    modelList.add(new Model("android"+j,"Android"+i));
                }else {
                    modelList.add(new Model("java"+j,"Java"+i));
                }
            }
        }
    }
}