package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.atandroid.R;

/**
 * Created by Administrator on 2017/5/6.
 */

public class Memorial_wish extends AppCompatActivity implements View.OnClickListener{

    private ImageView wish;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_memorial_wish);
        init();
    }

    private void init() {
        wish = (ImageView) findViewById(R.id.memorial_wish);
        wish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memorial_wish: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Memorial_wishin.class);
                startActivity(intent1);
            }
            break;
        }

    }
}
