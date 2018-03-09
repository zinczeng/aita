package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.atandroid.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import Adapter.charityProjectAdapter;
import bean.charityProject;
import util.AsyncTaskHelper;
import util.AsyncTaskHelper.OnDataDownloadListener;
import util.FastJsonTools;
import util.MyConfig;

/**
 * Created by Administrator on 2017/5/6.
 */

public class Charity_project extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, OnRefreshListener2<ListView>, OnPullEventListener<ListView> {
    private ListView listView;
    private TextView tv_charity_project_back, tv_charity_project_issue;
    private charityProjectAdapter adapter;
    private PullToRefreshListView lv;
    private int page = 0;
    private boolean isPullDown = false;// 是否下拉
    private boolean isPullUp = false;// 是否上拉
    private AsyncTaskHelper taskHelper;// 网络加载对象
    private OnDataDownloadListener downloadListener;// 接口回调对象
    private List<charityProject> list; // 存放解析Json的结果集合

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_project);
        init();
        showListView();
    }

    private void showListView() {
        downloadListener = new OnDataDownloadListener() {
            @Override
            public void onDataDownload(byte[] result) {
                // 将结果转换成Json字符串
                String jsonString = new String(result);
                // 解析Json字符串
                list = FastJsonTools.getBeans(jsonString, charityProject.class);
                if (!isPullUp) { // 初始化列表或者下拉刷新时
                    adapter.setData(list);
                    if (isPullDown) {
                        lv.onRefreshComplete(); // 列表刷新完成恢复原样
                        isPullDown = false;
                    }
                } else { // 上拉加载
                    adapter.addData(list);
                    lv.onRefreshComplete();
                    isPullUp = false;
                }
            }
        };
        // 执行AsyncTask，加载url
        taskHelper.downloadData(this, MyConfig.URL_CharityProject + page, downloadListener);
    }

    private void init() {
        lv = (PullToRefreshListView) findViewById(R.id.charity_project_lv);
        tv_charity_project_back = ((TextView) findViewById(R.id.tv_charity_project_back));
        tv_charity_project_issue = ((TextView) findViewById(R.id.tv_charity_project_issue));
        tv_charity_project_back.setOnClickListener(this);
        tv_charity_project_issue.setOnClickListener(this);
        adapter = new charityProjectAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        taskHelper = new AsyncTaskHelper();// 加载网络使用封装好AsyncTask的Helper类
        lv.setAdapter(adapter);
        // 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
        // 如果需要两种模式，必须要调用setMode方法设置
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        // 设置下拉刷新和上拉加载的监听器
        lv.setOnRefreshListener(this);
        lv.setOnPullEventListener(this);
    }
    // 下来刷新回调方法，当用户在列表顶端往下拖动列表在释放后，完成一次刷新时调用此方法

    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = 1;
        isPullDown = true;
        taskHelper.downloadData(this, MyConfig.URL_CharityProject + page, downloadListener);
    }

    // 上拉加载回调方法，当用户在列表底部往下拖动列表在释放后，完成一次刷新时调用此方法
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page++;
        isPullUp = true;
        taskHelper.downloadData(this, MyConfig.URL_CharityProject + page, downloadListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_charity_project_back:
                Intent intent1 = new Intent();
                intent1.setClass(this, Charity.class);
                startActivity(intent1);
                this.finish();
            case R.id.tv_charity_project_issue:
                Intent intent2 = new Intent();
                intent2.setClass(this, Charity_project_hold_Activity.class);
                startActivity(intent2);
        }
    }

    //点击一个内容后，跳转到详细页面
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Charity_project_listviewitem.class);
        intent.putExtra("getProject_id", list.get(position).getProject_id());
        startActivity(intent);
        this.finish();
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onPullEvent(PullToRefreshBase<ListView> refreshView, State state, Mode direction) {
        if (state.equals(State.PULL_TO_REFRESH)) {
            if (direction.equals(Mode.PULL_DOWN_TO_REFRESH)) {
                refreshView.getLoadingLayoutProxy().setPullLabel(
                        getString(R.string.pull_down_refresh));// 设置下拉刷新时文本显示
            }
            if (direction.equals(Mode.PULL_UP_TO_REFRESH)) {
                refreshView.getLoadingLayoutProxy().setPullLabel(
                        getString(R.string.pull_up_refresh));// 设置上拉加载时文本显示
            }
            refreshView.getLoadingLayoutProxy().setReleaseLabel(
                    getString(R.string.release_to_refresh));// 设置释放时文本显示
            refreshView.getLoadingLayoutProxy().setRefreshingLabel(
                    getString(R.string.loading)); // 设置刷新时文本显示
            String label = DateUtils.formatDateTime(getApplicationContext(),
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    getString(R.string.updated) + " : " + label); //  设置更新时间
        }
    }
}


