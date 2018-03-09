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

public class Charity_information extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout charity_information_item;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_charity_information);
        init();

    }

    private void init() {
        charity_information_item = (LinearLayout) findViewById(R.id.charity_information_listviewitem);
        charity_information_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.charity_information_listviewitem: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Charity_information_listviewitem.class);
                startActivity(intent1);
            }
                break;
        }

    }
}
