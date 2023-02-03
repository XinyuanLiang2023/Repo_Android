package com.example.lxy37application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_username; // 声明一个编辑框对象

    private EditText et_password; // 声明一个编辑框对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent serviceIntent = new Intent(this, MusicService.class);
        startService(serviceIntent);

        et_username = findViewById(R.id.editTextUsername);
        et_password = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonRegister).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

        Intent intent = getIntent();
        String data = intent.getStringExtra("message");
        if(data!=null){
            et_username.setText(data);
        }
    }

    @Override
    public void onClick(View v) {
        et_username = findViewById(R.id.editTextUsername);
        et_password = findViewById(R.id.editTextPassword);
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (v.getId() == R.id.buttonRegister) { // 点击了“注册”按钮
            Intent intent0 = new Intent(Intent.ACTION_VIEW, Uri.parse("lxy37app://com.register"));
            startActivity(intent0);
        }
        else {//点击了“登录按钮"
            if (username.length() < 6) { // 用户名不足6位
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "用户名"+username+"不足6位");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            } else if (password.length() < 6) { // 密码不足6位
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "密码"+password+"不足6位");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            } else { // 合法性验证通过
                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();
                Database database = new Database(new MySQLiteHelper(MainActivity.this));
                Person person = database.findPersonFromSQLite(username, password);
                if (person != null) {
                    // 以下弹出提醒对话框，提示用户登录成功
                    String desc = String.format("验证通过，恭喜你登录成功，点击“前往商品主页”按钮前往商品主页",
                            et_username.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("登录成功");
                    builder.setMessage(desc);
                    builder.setPositiveButton("前往商品主页", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("lxy37app://com.home"));
                            startActivity(intent1);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show(); // 显示提醒对话框
                }
                else{
                    String desc = String.format("用户名或密码错误，请重新输入",
                            et_username.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("登录失败");
                    builder.setMessage(desc);
                    builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            //finish();
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show(); // 显示提醒对话框
                }
            }
        }
    }

}
