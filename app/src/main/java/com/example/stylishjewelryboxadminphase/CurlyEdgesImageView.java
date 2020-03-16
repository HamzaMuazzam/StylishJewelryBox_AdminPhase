package com.example.stylishjewelryboxadminphase;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class CurlyEdgesImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int width;
    private int height;

    private ArrayList<FloatPoint> points = new ArrayList<>();

    private final Path curlyEdgesPath = new Path();
    private final Paint curlyEdgesPaint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
        }
    };

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        }
    };

    private Canvas curlyEdgesCanvas;
    private Bitmap curlyEdgesBitmap;

    private Canvas canvas;
    private Bitmap bitmap;

    private float offset;
    private float curlyEdgesCount = 20f;

    public CurlyEdgesImageView(Context context) {
        super(context);
    }

    public CurlyEdgesImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurlyEdgesImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w;
        this.height = h;

        this.offset = this.width / (curlyEdgesCount * 2);

        bitmap = drawableToBitmap(getDrawable());
        canvas = new Canvas(bitmap);

        curlyEdgesBitmap = Bitmap.createBitmap(w, (int) offset, Bitmap.Config.ARGB_8888);
        curlyEdgesCanvas = new Canvas(curlyEdgesBitmap);

        drawCurlyEdges();

        canvas.drawBitmap(curlyEdgesBitmap,
                0,
                getHeight() - (int) offset,
                paint
        );

        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void drawCurlyEdges() {
        final float innerOffset = curlyEdgesCount / offset;

        int counterY = 1;
        for (float i = -offset; i < this.width + offset; i += offset) {
            if (counterY++ % 2 == 0) {
                points.add(new FloatPoint(i + innerOffset, offset));
            } else {
                points.add(new FloatPoint(i + innerOffset, 0));
            }
        }

        points.add(new FloatPoint(this.width + offset, (int) offset * 2));
        points.add(new FloatPoint(-offset, (int) offset * 2));

        if (points.size() > 1) {
            FloatPoint prevPoint = null;
            for (int i = 0; i < points.size(); i++) {
                FloatPoint point = points.get(i);

                if (i == 0) {
                    curlyEdgesPath.moveTo(point.x, point.y);
                } else {
                    float midX = (prevPoint.x + point.x) / 2f;
                    float midY = (prevPoint.y + point.y) / 2f;

                    if (i == 1) {
                        curlyEdgesPath.lineTo(midX, midY);
                    } else {
                        curlyEdgesPath.quadTo(prevPoint.x, prevPoint.y, midX, midY);
                    }
                }
                prevPoint = point;
            }
            curlyEdgesPath.lineTo(prevPoint.x, prevPoint.y);
        }

        curlyEdgesCanvas.drawPath(curlyEdgesPath, curlyEdgesPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private Bitmap drawableToBitmap(final Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return convertToMutable(getContext(), ((BitmapDrawable) drawable.mutate()).getBitmap());
        }

        final Bitmap bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return convertToMutable(getContext(), bitmap);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static Bitmap convertToMutable(final Context context, final Bitmap imgIn) {
        final int width = imgIn.getWidth(), height = imgIn.getHeight();
        final Bitmap.Config type = imgIn.getConfig();

        File outputFile = null;
        final File outputDir = context.getCacheDir();
        try {
            outputFile = File.createTempFile(Long.toString(System.currentTimeMillis()), null, outputDir);
            outputFile.deleteOnExit();

            final RandomAccessFile randomAccessFile = new RandomAccessFile(outputFile, "rw");
            final FileChannel channel = randomAccessFile.getChannel();
            final MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, imgIn.getRowBytes() * height);

            imgIn.copyPixelsToBuffer(map);
            imgIn.recycle();

            final Bitmap result = Bitmap.createBitmap(width, height, type);

            map.position(0);
            result.copyPixelsFromBuffer(map);

            channel.close();
            randomAccessFile.close();

            outputFile.delete();
            return result;
        } catch (final Exception e) {
        } finally {
            if (outputFile != null)
                outputFile.delete();
        }
        return null;
    }

    private class FloatPoint {
        public float x;
        public float y;

        private FloatPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}