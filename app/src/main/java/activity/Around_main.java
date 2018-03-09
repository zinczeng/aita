package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.atandroid.R;

/**
 * Created by Administrator on 2017/5/6.
 */

public class Around_main extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout around_shop;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_around_main);
        init();
    }

    private void init() {
        around_shop = (LinearLayout) findViewById(R.id.around_shop);
        around_shop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.around_shop: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Around_listviewitem.class);
                startActivity(intent1);
            }
        }
    }
}
