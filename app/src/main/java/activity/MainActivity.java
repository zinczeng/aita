package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.atandroid.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView charity,memorial,around,gohome,mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        charity = (ImageView) findViewById(R.id.charity);
        memorial = (ImageView) findViewById(R.id.memorial);
        around = (ImageView) findViewById(R.id.around);
        gohome = (ImageView) findViewById(R.id.gohome);
        mine = (ImageView) findViewById(R.id.mine);
        charity.setOnClickListener(this);
        memorial.setOnClickListener(this);
        around.setOnClickListener(this);
        gohome.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.charity:{
                Intent intent1 = new Intent();
                intent1.setClass(this,Charity.class);
                startActivity(intent1);
            }
            break;
            case R.id.memorial:{
                Intent intent2 = new Intent();
                intent2.setClass(this,Memorial.class);
                startActivity(intent2);
            }
            break;
            case R.id.around:{
                Intent intent3 = new Intent();
                intent3.setClass(this,Around.class);
                startActivity(intent3);
            }
            break;
            case R.id.gohome:{
                Intent intent4 = new Intent();
                intent4.setClass(this,Gohome.class);
                startActivity(intent4);
            }
            break;
            case R.id.mine:{
                Intent intent1 = new Intent();
                intent1.setClass(this,Mine.class);
                startActivity(intent1);
            }
            break;
        }
    }
}
