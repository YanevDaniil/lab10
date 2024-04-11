package gr313.yanev.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Bitmap bmp1;
    Bitmap bmp2;
    int w, h;
    ImageView img1;
    ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        w = bmp1.getWidth();
        h = bmp1.getHeight();
        bmp2 = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        img1.setImageBitmap(bmp1);
        img2.setImageBitmap(bmp2);
    }

    public void copy(View v) {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                bmp2.setPixel(x,y, bmp1.getPixel(x,y));
            }
        }
    }

    public void invert(View v) {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int c0 = bmp1.getPixel(x,y);
                int r = 255 - Color.red(c0);
                int g = 255 - Color.green(c0);
                int b = 255 - Color.blue(c0);
                int c1 = Color.argb(255,r,g,b);
                bmp2.setPixel(x,y,c1);
            }
        }
    }

    public void grey(View v) {
        Canvas canvas = new Canvas(bmp2);
        ColorMatrix ma = new ColorMatrix();
        ma.setSaturation(0);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(ma));
        canvas.drawBitmap(bmp1, 0, 0, paint);
    }

    public void bw(View v) {
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = bmp1.getPixel(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel >> 0) & 0xff;
                double Y = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                if (Y < 128) {
                    bmp2.setPixel(x, y, Color.BLACK);
                } else {
                    bmp2.setPixel(x, y, Color.WHITE);
                }
            }
        }
    }
}