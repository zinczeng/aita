package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
 * Created by Administrator on 2017/5/6.
 */

public class Charity_project_listviewitem extends AppCompatActivity implements View.OnClickListener {
    public static String getProject_id;
    private TextView genzong, love_detail, rescueNum, project_synopsis, time_start, time_stop;
    private Button DonateCompassion;
    private ImageView photo_detail, charity_project_collect, tv_back_charity_project;
    private String Project_id, URL_charity_project_xiangxi;
    private OnDataDownloadListener downloadListener;
    private AsyncTaskHelper taskHelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_project_listviewitem);
        init();

    }

    private void init() {
        genzong = (TextView) findViewById(R.id.genzong);//项目跟踪
        love_detail = (TextView) findViewById(R.id.tv_charity_project_love_detail);//爱心值
        rescueNum = (TextView) findViewById(R.id.charity_project_rescueNum);//救助量
        project_synopsis = (TextView) findViewById(R.id.charity_project_synopsis);//项目简介
        time_start = (TextView) findViewById(R.id.tv_charity_project_time_start);//开始时间
        time_stop = (TextView) findViewById(R.id.tv_charity_project_time_stop);//结束时间
        DonateCompassion = (Button) findViewById(R.id.tv_charity_project_DonateCompassion);//献出爱心
        photo_detail = (ImageView) findViewById(R.id.iv_charity_project_photo_detail);//图片海报
        charity_project_collect = (ImageView) findViewById(R.id.charity_project_collect);//项目收藏
        tv_back_charity_project = (ImageView) findViewById(R.id.tv_back_charity_project);//返回前页
        genzong.setOnClickListener(this);
        DonateCompassion.setOnClickListener(this);
        charity_project_collect.setOnClickListener(this);
        tv_back_charity_project.setOnClickListener(this);
        getProject_id = this.getIntent().getStringExtra("getProject_id");//获取charity project传来的项目ID
        showCharityProject();
    }

    private void showCharityProject() {
        Project_id = getProject_id.toString();
        ecanpJson();//封装参数的方法
        downloadListener = new OnDataDownloadListener() {

            @Override
            public void onDataDownload(byte[] result) {
                String jsonString = new String(result); // 返回的字节数组转换为字符串
                Map<String, Object> map = new HashMap<String, Object>();
                map = FastJsonTools.getMap(jsonString);
                love_detail = map.
            }
        };
        taskHelper.downloadData(this, MyConfig.URL_login + URL_charity_project_xiangxi,
                downloadListener); // 发起服务器的访问
    }

    private void ecanpJson() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Project_id", Project_id);
        URL_charity_project_xiangxi = JSON.toJSONString(map);
        try {
            URL_charity_project_xiangxi = URLEncoder.encode(URL_charity_project_xiangxi, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(URL_charity_project_xiangxi);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.genzong://项目跟踪
                Intent intent1 = new Intent();
                intent1.setClass(this, Charity_project_genzong.class);
                startActivity(intent1);
            case R.id.tv_charity_project_DonateCompassion://献爱心
                Intent intent2 = new Intent();
                intent2.setClass(this,. class);
                startActivity(intent2);
            case R.id.charity_project_collect://收藏公益项目
                addCharityProject();
                break;
            case R.id.tv_back_charity_project://返回公益项目listview
                Intent intent4 = new Intent();
                intent4.setClass(this, Charity_project.class);
                startActivity(intent4);
                this.finish();
            default:
        }
    }

    private void addCharityProject() {
    }
}
