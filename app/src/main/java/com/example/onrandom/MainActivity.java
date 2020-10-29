package com.example.onrandom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEdtEnter1, mEdtEnter2;
    Button mBtRandom, mBtReset;
    TextView mTvResult;
    List<Integer> mListRandom =new ArrayList();
    boolean mFlag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        mEdtEnter1=findViewById(R.id.edtEnter1);
        mEdtEnter2=findViewById(R.id.edtEnter2);
        mBtRandom=findViewById(R.id.btRandom);
        mTvResult=findViewById(R.id.tvResult);
        mBtReset=findViewById(R.id.btReset);

        //buttonRandom
        mBtRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterOneString= mEdtEnter1.getText().toString();
                String enterTwoString= mEdtEnter2.getText().toString();

                //kiểm tra xem đã điền đủ hai số để random chưa
                if (enterOneString.isEmpty() || enterTwoString.isEmpty()){
                    Toast.makeText(MainActivity.this, "PHẢI ĐIỀN ĐỦ HAI SỐ!", Toast.LENGTH_LONG).show();
                    return;
                }

                int numberStart=Integer.parseInt(enterOneString);
                int numberEnd=Integer.parseInt(enterTwoString);

                if (numberStart>=numberEnd){
                    Toast.makeText(MainActivity.this, "SỐ KẾT THÚC PHẢI LỚN HƠN SỐ BẮT ĐẦU!", Toast.LENGTH_LONG).show();
                    return;
                }

                //khởi tạo danh sách tất cả các kết quả có thể random
                if (mFlag) {
                    for (int i = numberStart; i <= numberEnd; i++) {
                        mListRandom.add(i);
                    }
                    mFlag=false;
                }
                //random 1 số bất kì
                Random random=new Random();
                int indexRandom=random.nextInt(mListRandom.size());
                int valueRandom=mListRandom.get(indexRandom);
                mListRandom.remove(indexRandom);

                if (mListRandom.size()<1){
                    mTvResult.append(valueRandom+"");
                    Toast.makeText(MainActivity.this, "ĐÃ RANDOM HẾT TẤT CẢ CÁC SỐ", Toast.LENGTH_LONG).show();
                    return;
                }

                mTvResult.append(valueRandom+"-");

            }
        });

        //buttonReset
        mBtReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlag=true;
                mEdtEnter1.setText("");
                mEdtEnter2.setText("");
                mTvResult.setText("");
            }
        });
    }
}