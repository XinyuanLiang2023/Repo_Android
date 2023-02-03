package com.example.lxy37application;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("DefaultLocale")
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username; // 声明一个编辑框对象

    private EditText et_password_first; // 声明一个编辑框对象
    private EditText et_password_second; // 声明一个编辑框对象

    private CheckBox checkbox01;
    private CheckBox checkbox02;

    private RadioButton radiobtn01;
    private RadioButton radiobtn02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = findViewById(R.id.editTextUsername);
        et_password_first = findViewById(R.id.editTextPasswordFirst);
        et_password_second = findViewById(R.id.editTextPasswordSecond);

        checkbox01=(CheckBox)findViewById(R.id.checkBox01);
        checkbox02=(CheckBox)findViewById(R.id.checkBox02);
        radiobtn01=(RadioButton) findViewById(R.id.radioButton01);
        radiobtn02=(RadioButton) findViewById(R.id.radioButton02);

        findViewById(R.id.buttonRegister).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        et_username = findViewById(R.id.editTextUsername);
        et_password_first = findViewById(R.id.editTextPasswordFirst);
        et_password_second = findViewById(R.id.editTextPasswordSecond);
        checkbox01=(CheckBox)findViewById(R.id.checkBox01);
        checkbox02=(CheckBox)findViewById(R.id.checkBox02);
        radiobtn01=(RadioButton) findViewById(R.id.radioButton01);
        radiobtn02=(RadioButton) findViewById(R.id.radioButton02);

        String username =et_username.getText().toString().trim();
        String password_first = et_password_first.getText().toString().trim();
        String password_second = et_password_second.getText().toString().trim();

            if (username.length() < 6) { // 账号不足6位
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "用户名"+username+"不足6位");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            }
            else if (password_first.length() < 6) { // 密码不足6位
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "密码"+password_first+"不足6位");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            }
            else if (!password_first.equals(password_second)) {
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "两次输入的密码不一致");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            }
            else if(!(radiobtn01.isChecked()||radiobtn02.isChecked())){
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "没有选择性别");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            }
            else if(!(checkbox01.isChecked()&&checkbox02.isChecked())){
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "没有勾选协议");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                return;
            }

            else { // 密码校验通过
                Database database = new Database(new MySQLiteHelper(RegisterActivity.this));
                Person person = database.findPersonFromSQLite(username,password_first);
                if (person != null){
                    // 以下弹出提醒对话框，提示用户已注册过
                    String desc = String.format("你已经注册过了，点击“返回登录”按钮返回登录页面",
                            et_username.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("注册失败");
                    builder.setMessage(desc);
                    builder.setPositiveButton("返回登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setClass(RegisterActivity.this,MainActivity.class);
                            intent.putExtra("message",et_username.getText().toString());
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show(); // 显示提醒对话框
                }
                else {
                    username = et_username.getText().toString().trim();
                    password_first = et_password_first.getText().toString().trim();
                    person = new Person(username,password_first);

                    database.insertPersonToSQLite(person);
                    // 以下弹出提醒对话框，提示用户注册成功
                    String desc = String.format("您的用户名是%s，恭喜你注册成功，点击“前往登录”按钮前往登录",
                            et_username.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("注册成功");
                    builder.setMessage(desc);
                    builder.setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setClass(RegisterActivity.this,MainActivity.class);
                            intent.putExtra("message",et_username.getText().toString());
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show(); // 显示提醒对话框
                }
            }
            }
}

