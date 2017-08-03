package com.kzmen.sczxjf.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kzmen.sczxjf.test.bean.AnserItemBean;
import com.kzmen.sczxjf.test.bean.QuestionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接数据库
 * Created by LGL on 2016/6/4.
 */
public class DBService {

    private SQLiteDatabase db;

    //构造方法
    public DBService() {
        //连接数据库
        db = SQLiteDatabase.openDatabase("/data/data/com.cardholder.adwlf/databases/question.db", null, SQLiteDatabase.OPEN_READWRITE);

    }

    //获取数据库的数据
    public List<Question> getQuestion() {
        List<Question> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                //ID
                question.ID = cursor.getInt(cursor.getColumnIndex("Field1"));
                //问题
                question.question = cursor.getString(cursor.getColumnIndex("Field2"));
                //四个选择
                question.answerA = cursor.getString(cursor.getColumnIndex("Field3"));
                question.answerB = cursor.getString(cursor.getColumnIndex("Field4"));
                question.answerC = cursor.getString(cursor.getColumnIndex("Field5"));
                question.answerD = cursor.getString(cursor.getColumnIndex("Field6"));
                //答案
                question.answer = cursor.getInt(cursor.getColumnIndex("Field7"));
                //解析
                question.explaination = cursor.getString(cursor.getColumnIndex("Field8"));
                //设置为没有选择任何选项
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        cursor.close();
        return list;

    }
    //获取数据库的数据
    public List<QuestionBean> getQuestion1() {
        List<QuestionBean> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                QuestionBean question = new QuestionBean();
                //ID
                question.ID = cursor.getInt(cursor.getColumnIndex("Field1"));
                //问题
                question.question = cursor.getString(cursor.getColumnIndex("Field2"));
                //四个选择
                AnserItemBean itemBean1=new AnserItemBean();
                itemBean1.setAnswer(cursor.getString(cursor.getColumnIndex("Field3")));
                AnserItemBean itemBean2=new AnserItemBean();
                itemBean2.setAnswer(cursor.getString(cursor.getColumnIndex("Field4")));
                AnserItemBean itemBean3=new AnserItemBean();
                itemBean3.setAnswer(cursor.getString(cursor.getColumnIndex("Field5")));
                AnserItemBean itemBean4=new AnserItemBean();
                itemBean4.setAnswer(cursor.getString(cursor.getColumnIndex("Field6")));
                question.answerList.add(itemBean1);
                question.answerList.add(itemBean2);
                question.answerList.add(itemBean3);
                question.answerList.add(itemBean4);
                //答案
                question.answer = cursor.getInt(cursor.getColumnIndex("Field7"));

                list.add(question);
            }
        }
        cursor.close();
        return list;

    }

}