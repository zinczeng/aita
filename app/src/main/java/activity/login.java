package activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.atandroid.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import util.AsyncTaskHelper;
import util.AsyncTaskHelper.OnDataDownloadListener;
import util.FastJsonTools;
import util.MyConfig;
import util.UserInfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class login extends AppCompatActivity implements View.OnClickListener {

    public TextView tv_UserId, tv_UserPW;
    public Button bt_login, bt_regist;
    private String UserId, UserPW, url_login;
    private OnDataDownloadListener downloadListener;
    private AsyncTaskHelper taskHelper;

    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        tv_UserId = (TextView) findViewById(R.id.tv_UserId);
        tv_UserPW = (TextView) findViewById(R.id.tv_UserPW);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_regist = (Button) findViewById(R.id.bt_regist);
        bt_login.setOnClickListener(this);
        bt_regist.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                user_login();
                this.finish();
            case R.id.bt_regist:
                user_regist();
                this.finish();
        }
    }

    private void user_regist() {
        Intent intent2 = new Intent(this, registActivity.class);
        //  intent2.setClass(this,registActivity.class);
        startActivity(intent2);
    }

    private void user_login() {
        UserId = tv_UserId.getText().toString();
        UserPW = tv_UserPW.getText().toString();
        if (UserPW.equals("") || UserId.equals("")) {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        ecanpJson();//封装参数的方法
        downloadListener = new OnDataDownloadListener() {
            @Override

            public void onDataDownload(byte[] result) {
                String jsonString = new String(result); // 返回的字节数组转换为字符串
                Map<String, Object> map = new HashMap<String, Object>();
                map = FastJsonTools.getMap(jsonString);
                String code = map.get("code").toString();
                String msg = map.get("msg").toString();
                if (code.equals("1")) {
                    Toast.makeText(login.this, "登录成功！",
                            Toast.LENGTH_SHORT).show();
                    UserInfo.setId(UserId);
                    UserInfo.setPassword(UserPW);
                    //通过SharedPreferences 将用户信息封装在UserInfo里面
                    SharedPreferences pref = login.this.getSharedPreferences("util.UserInfo",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("IsLogin",true);
                    editor.commit();

                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                } else if (code.equals("0")) {
                    Toast.makeText(login.this, "登录失败！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        taskHelper.downloadData(this, MyConfig.URL_login + url_login,
                downloadListener); // 发起服务器的访问
    }

    private void ecanpJson() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UserId", UserId);
        map.put("UserPassword", UserPW);
        url_login = JSON.toJSONString(map);
        try {
            url_login = URLEncoder.encode(url_login, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(url_login);
    }
}
