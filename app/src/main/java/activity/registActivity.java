package activity;

import android.content.Intent;
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

/**
 * Created by Administrator on 2017/5/17.
 */

public class registActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_rg_regist;
    private TextView tv_rg_userId, tv_rg_userPW, tv_rg_userPW2;
    private String rg_UserId, rg_UserPW, rg_UserPW2, regist_url;
    private OnDataDownloadListener downloadListener;
    private AsyncTaskHelper taskHelper;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login_regist);
        init();
    }

    private void init() {
        bt_rg_regist = ((Button) findViewById(R.id.bt_rg_regist));
        tv_rg_userId = ((TextView) findViewById(R.id.tv_rg_userId));
        tv_rg_userPW = ((TextView) findViewById(R.id.tv_rg_userPW));
        tv_rg_userPW2 = ((TextView) findViewById(R.id.tv_rg_userPW2));
        bt_rg_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        user_regist();
        this.finish();
    }

    private void user_regist() {
        rg_UserId = tv_rg_userId.getText().toString();
        rg_UserPW = tv_rg_userPW.getText().toString();
        rg_UserPW2 = tv_rg_userPW2.getText().toString();
        if (rg_UserId.equals("") || rg_UserPW.equals("")
                || rg_UserPW2.equals("")) {
            Toast.makeText(this, "账号和密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!rg_UserPW.equals(rg_UserPW2)) {
            Toast.makeText(this, "两次密码不同", Toast.LENGTH_SHORT).show();
            return;
        }
        encapJson(); // 封装参数，作为访问服务器的完整url中的结尾部分链接
        downloadListener = new OnDataDownloadListener() { // 访问服务器后获取返回数据

            @Override
            public void onDataDownload(byte[] result) {
                String jsonString = new String(result); // 返回的字节数组转换为字符串
                Map<String, Object> map = new HashMap<String, Object>();
                map = FastJsonTools.getMap(jsonString);
                String code = map.get("code").toString();
                String msg = map.get("msg").toString();
                if (code.equals("0")) {
                    Toast.makeText(registActivity.this, "注册成功！",
                            Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(registActivity.this, login.class);
                    startActivity(intent2);
                } else if (code.equals("1")) {
                    Toast.makeText(registActivity.this, "注册失败！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        taskHelper.downloadData(this, MyConfig.URL_regist + regist_url,
                downloadListener); // 发起服务器的访问

    }

    private void encapJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UserId", rg_UserId);
        map.put("UserPassword", rg_UserPW);
        regist_url = JSON.toJSONString(map);
        try {
            regist_url = URLEncoder.encode(regist_url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(regist_url);
    }
}

