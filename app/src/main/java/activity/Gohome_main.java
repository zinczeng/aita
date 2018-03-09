package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.atandroid.R;

/**
 * Created by Administrator on 2017/5/6.
 */

public class Gohome_main extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout gohome_listitem;
    private ImageView gohome_sent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_gohome_main);
        init();

    }

    private void init() {
        gohome_listitem = (LinearLayout) findViewById(R.id.gohome_listitem);
        gohome_sent = (ImageView) findViewById(R.id.gohome_sent);
        gohome_listitem.setOnClickListener(this);
        gohome_sent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gohome_listitem: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Gohome_listviewitem.class);
                startActivity(intent1);
            }
            break;
            case R.id.gohome_sent: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Gohome_sent.class);
                startActivity(intent1);
            }
            break;
        }
    }
}
