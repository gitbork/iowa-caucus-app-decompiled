package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzy;

final class zzc {
    static Rect zza(Text text) {
        Point[] cornerPoints;
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (Point point : text.getCornerPoints()) {
            i = Math.min(i, point.x);
            i3 = Math.max(i3, point.x);
            i2 = Math.min(i2, point.y);
            i4 = Math.max(i4, point.y);
        }
        return new Rect(i, i2, i3, i4);
    }

    static Point[] zza(zzy zzy) {
        Point[] pointArr = new Point[4];
        double sin = Math.sin(Math.toRadians((double) zzy.zzfb));
        double cos = Math.cos(Math.toRadians((double) zzy.zzfb));
        pointArr[0] = new Point(zzy.left, zzy.top);
        double d = (double) zzy.left;
        double d2 = (double) zzy.width;
        Double.isNaN(d2);
        double d3 = d2 * cos;
        Double.isNaN(d);
        int i = (int) (d + d3);
        double d4 = (double) zzy.top;
        double d5 = (double) zzy.width;
        Double.isNaN(d5);
        double d6 = d5 * sin;
        Double.isNaN(d4);
        pointArr[1] = new Point(i, (int) (d4 + d6));
        double d7 = (double) pointArr[1].x;
        double d8 = (double) zzy.height;
        Double.isNaN(d8);
        double d9 = d8 * sin;
        Double.isNaN(d7);
        int i2 = (int) (d7 - d9);
        double d10 = (double) pointArr[1].y;
        double d11 = (double) zzy.height;
        Double.isNaN(d11);
        double d12 = d11 * cos;
        Double.isNaN(d10);
        pointArr[2] = new Point(i2, (int) (d10 + d12));
        pointArr[3] = new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y));
        return pointArr;
    }
}
