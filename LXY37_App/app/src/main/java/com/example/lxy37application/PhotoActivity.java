package com.example.lxy37application;

import android.graphics.Bitmap;

import android.os.Bundle;


import android.content.Intent;

import android.provider.MediaStore;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileOutputStream;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_comment;
    //拍照控件
    private ImageButton iv_add;

    //拍照后显示缩略图控件
    private ImageView iv_thumb;
    //图片存储的路径
    private String imagePath;

    private static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        iv_add = findViewById(R.id.imageViewAdd);
        et_comment = findViewById(R.id.editTextComment);
        iv_thumb = findViewById(R.id.imageViewThumb);
        findViewById(R.id.buttonSubmit).setOnClickListener(this);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.example.lxy37application");
        intent.putExtra("message", "已提交评价");
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            File file = new File("/sdcard/pic/");

            file.mkdirs();// 创建文件夹

            String fileName = "/sdcard/pic/img.jpg";

            try {
                FileOutputStream b = new FileOutputStream(fileName);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件,其中第一个参数表示图片格式，
                //第二个参数指压缩率。100表示不压缩
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    FileOutputStream b = new FileOutputStream(fileName);
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ImageView ic = (ImageView) findViewById(R.id.imageViewThumb);

            ic.setImageBitmap(bitmap);

        } else {
            finish();
        }
    }
}