package jp.ac.shohoku.s19b703.imagesample;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by utsumi on 2015/10/07.
 */
public class ChangeView extends View {
    private Paint mPaint = new Paint(); //描画用のスタイル設定など
    private Bitmap mBmp = null; //表示用の Bitmap
    private int mTop, mLeft, mW, mH; //画像の幅と高さ
    Resources rs = this.getResources(); //リソースを取得 (R クラスから取得)
    Bitmap image[] = {BitmapFactory.decodeResource(rs, R.drawable.image01),
                      BitmapFactory.decodeResource(rs, R.drawable.image02),
                      BitmapFactory.decodeResource(rs, R.drawable.image03),
                      BitmapFactory.decodeResource(rs, R.drawable.image04)};
    int Tap = 0;
    int N;


    /**
     * コンストラクタ
     * @param context
     * @param attrs
     */
    public ChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBmp = image[Tap];
        mTop = 100;
        mLeft = 100;
        mW = mBmp.getWidth();
        mH = mBmp.getHeight();
    }

    /**
     * このメソッドで描画をします．
     *
     * @param canvas 画像表示用のキャンバス
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBmp, mLeft, mTop, mPaint);
    }

    /*
     * タップされたときに実行されるメソッド<br />
     * タップされたときに，押されたかどうかの状態をチェックし，変化させる．
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Tap++;
        N = Tap%3;
        if(Tap%19 == 0){
            N = 3;
        }

        int x = (int) event.getX();
        int y = (int) event.getY();
        mLeft = x - mW/2; //描画場所を変更
        mTop = y - mH/2;
        mBmp = image[N];
        invalidate(); //再描画する
        return super.onTouchEvent(event);
    }
}