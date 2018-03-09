package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.atandroid.R;

import java.util.List;

import bean.charityProject;
import util.ImageDownloadHelper;
import util.ImageDownloadHelper.OnImageDownloadListener;

/**
 * Created by Administrator on 2017/5/17.
 */

public class charityProjectAdapter extends BaseAdapter {
    List<charityProject> list;
    Context context;
    private ImageDownloadHelper downloadHelper;// 图片加载对象
    private OnImageDownloadListener imageDownloadListener;// 图片加载回调接口的对象

    public charityProjectAdapter(Context context, List<charityProject> list) {
        this.context = context;
        this.list = list;
    }
    // 更新适配器的数据
    public void setData(List<charityProject> list) {
        this.list = list;
        notifyDataSetChanged(); // 刷新适配器的数据
    }

    // 添加适配器的数据
    public void addData(List<charityProject> list) {
        this.list.addAll(list);
        notifyDataSetChanged(); // 刷新适配器的数据
    }

    // 配置适配器的行数量
    @Override

    public int getCount() {
        return list == null ? 0 : list.size(); // 当List为空时，适配器自动为空
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.activity_charity_project_listview, null);
        if (convertView != null) {
            holder.project_photo = ((ImageView) convertView.findViewById(R.id.iv_charity_project_photo));
            holder.project_title = ((TextView) convertView.findViewById(R.id.tv_charity_project_title));
            holder.project_locale = ((TextView) convertView.findViewById(R.id.tv_charity_project_locale));
            holder.project_love = ((TextView) convertView.findViewById(R.id.tv_charity_project_love));

            charityProject charityproject = list.get(position);


            holder.project_title.setText(charityproject.getProject_title());
            holder.project_locale.setText(charityproject.getProject_locale());
            holder.project_love.setText(charityproject.getProject_love());

            holder.project_photo.setTag(charityproject.getProject_photo());
            holder.project_photo.setImageResource(R.drawable.placeholder);

            String imgUrl =charityproject.getProject_photo();

            imageDownloadListener = new OnImageDownloadListener() {
                @Override
                //setImageResource会根据设备分辨率进行图片大小缩放适配
               //setImageBitmap(BitmapFactory.decodeResource(res,id))大小需要手动调。
                public void onImageDownload(Bitmap bitmap, String imgUrl) {
                    // TODO Auto-generated method stub
                    if (bitmap != null
                            && imgUrl.equals(holder.project_photo.getTag())) {
                        holder.project_photo.setImageBitmap(bitmap);
                        notifyDataSetChanged();
                    }
                }
            };
            // 加载图片
            // @project_photoStr:图片的地址
            // @holder:显示图片的控件变量
            // @imageDownloadListener:回调加载结果的接口对象
            downloadHelper.myDownloadImage(context, charityproject.getProject_photo(), holder.project_photo,
                    imageDownloadListener);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView project_photo;
        TextView project_title;
        TextView project_locale;//组织机构
        TextView project_love;
    }
}
