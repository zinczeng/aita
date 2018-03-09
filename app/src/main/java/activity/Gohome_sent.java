package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.atandroid.R;

/**
 * Created by Administrator on 2017/5/6.
 */

public class Gohome_sent extends AppCompatActivity implements View.OnClickListener{

    private Button gohome_sentup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_gohome_sent);
        init();

    }

    private void init() {
        gohome_sentup = (Button) findViewById(R.id.gohome_sentup);
        gohome_sentup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gohome_sentup: {
                Intent intent1 = new Intent();
                intent1.setClass(this, Gohome_sentup.class);
                startActivity(intent1);
            }
            break;
        }
    }
}
