package com.example.lxy37application;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Database {
    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase database;

    public Database(MySQLiteHelper mySQLiteHelper){
        this.mySQLiteHelper = mySQLiteHelper;
    }

    public void insertPersonToSQLite(Person person){
        database = mySQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",person.getUsername());
        values.put("password",person.getPassword());

        long id = database.insert("person",null,values);
        database.close();
    }

    //此方法用来查询数据库中是否存在person
    //优先查询id，id==null，查询username
    //password为空时返回第一个username的信息 不为空时匹配用户名和密码
    //当查询不到时返回null
    public Person findPersonFromSQLite(String username,String password){
        database = mySQLiteHelper.getReadableDatabase();
        Person person = new Person(username,null);
        Cursor cursor ;
        if(username != null && (!username.isEmpty())){
            cursor = database.query("person",null,"username=?",new String[]{username},null,null,null);
            if (cursor.getCount() ==0){
                database.close();
                return null;
            } else if(cursor.getCount() ==1){
                cursor.moveToFirst();
                if (password != null){
                    if (password.equals(cursor.getString(1))){  //如果密码相等
                        person.setUsername(cursor.getString(0));
                        person.setPassword(cursor.getString(1));

                    } else {    //如果密码不相等
                        person = null;
                    }
                } else {
                    //person.setUsername(cursor.getString(0));
                    //person.setPassword(cursor.getString(1));
                    person = null;

                }
                cursor.close();
                database.close();
                return person;
            } else {
                cursor.moveToFirst();
                if (cursor.getString(1).equals(password)){

                    person.setUsername(cursor.getString(0));
                    person.setPassword(cursor.getString(1));

                } else {
                    boolean isExist = false;
                    while(cursor.moveToNext()){
                        if (cursor.getString(1).equals(password)){
                            cursor.moveToFirst();
                            person.setUsername(cursor.getString(0));
                            person.setPassword(cursor.getString(1));
                            isExist = true;
                            break;
                        }
                    }
                    if(!isExist) {
                        person = null;
                    }
                }
                cursor.close();
                database.close();
                return person;
            }

        }else {
            database.close();
            return null;
        }
    }



}

