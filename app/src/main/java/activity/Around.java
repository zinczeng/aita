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

public class Around extends AppCompatActivity implements View.OnClickListener{

    private ImageView petshop;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_around);
        init();
    }

    private void init() {
        petshop = (ImageView) findViewById(R.id.around_petshop);
        petshop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.around_petshop: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Around_main.class);
                startActivity(intent1);
            }
            break;
        }
    }
}
