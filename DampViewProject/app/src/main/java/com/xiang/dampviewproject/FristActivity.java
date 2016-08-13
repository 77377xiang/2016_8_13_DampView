package com.xiang.dampviewproject;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/8/12.
 */
public class FristActivity extends Activity  {
    private PullZoomScrollView mView;
    private RelativeLayout mImageContainer;
    private ImageView mImage;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        mImageContainer = (RelativeLayout)findViewById(R.id.topimagecontainer);
        mView = (PullZoomScrollView)findViewById(R.id.scrollview);
        mImage = (ImageView)findViewById(R.id.topimage);
        text= (TextView) findViewById(R.id.text);
       mImage.setImageBitmap(ImageCrop(BitmapFactory.decodeResource(getResources(),R.drawable.a)));
        mView.mImageView = mImageContainer;
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FristActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 将图片裁减为宽高比例符合 上部imageview的宽高比例,这个例子中，高度固定为200，这个自己可以调整
     */
    public Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int retX = 0;
        int retY = 0;
        int height = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float rate =  (float)dp2px(200,this)/(float)displayMetrics.widthPixels;
        height = (int)(h*rate);
        if(h>height){
            retY = (h-height)/2;
            retX = 0;
        }else {
            retY = 0;
            retX = (w-(int)(h/rate))/2;
        }
        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, w, height, null, false);
    }

    public  int dp2px(float value, Context context) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }
}
