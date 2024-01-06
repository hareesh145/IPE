package com.indiapoliticaledge.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    private static ProgressDialog progressBar;

    public static void showProgessBar(Context context) {
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading");
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }


    public static void hideProgessBar() {
        if (progressBar != null && progressBar.isShowing()) progressBar.dismiss();
    }

    public static String convertDateFormat(Date inputDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(inputDate);
        return dateString;
    }

    public static String convertDateAMPM(String dateValue) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            SimpleDateFormat oldformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Log.d(TAG, "sdf.format(oldformat.parse(date)) " + sdf.format(oldformat.parse(dateValue)));
            return sdf.format(oldformat.parse(dateValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static int calculateMidColor(int leftColor, int rightColor, float process, ColorChangeType colorChangeType) {
//        if (colorChangeType.equals(ColorChangeType.RGB)) {
//            return Color.argb(
//                    Color.alpha(leftColor) + (int) ((Color.alpha(rightColor) - Color.alpha(leftColor)) * process),
//                    Color.red(leftColor) + (int) ((Color.red(rightColor) - Color.red(leftColor)) * process),
//                    Color.green(leftColor) + (int) ((Color.green(rightColor) - Color.green(leftColor)) * process),
//                    Color.blue(leftColor) + (int) ((Color.blue(rightColor) - Color.blue(leftColor)) * process));
//        } else if (colorChangeType.equals(ColorChangeType.HSV)) {
//            return getHSVColor(toHsvVector(leftColor), toHsvVector(rightColor), process);
//        }
//        return 0;
//    }

    private static final float ERROR = 0.001f;

    public static float[] toHsvVector(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        float[] vector = new float[3];
        double rad = Math.PI * hsv[0] / 180;
        vector[0] = (float) Math.cos(rad) * hsv[1];
        vector[1] = (float) Math.sin(rad) * hsv[1];
        vector[2] = hsv[2];
        return vector;
    }

    public static int getHSVColor(float[] vector0, float[] vector1, float delta) {
        float[] vector = new float[3];
        vector[0] = (vector1[0] - vector0[0]) * delta + vector0[0];
        vector[1] = (vector1[1] - vector0[1]) * delta + vector0[1];
        vector[2] = (vector1[2] - vector0[2]) * delta + vector0[2];

        float[] hsv = new float[3];
        hsv[1] = (float) Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
        hsv[0] = hsv[1] < ERROR ? 0 :
                (float) (Math.atan2(vector[1] / hsv[1], vector[0] / hsv[1]) * 180 / Math.PI);
        if (hsv[0] < 0)
            hsv[0] += 360f;
        hsv[2] = vector[2];

        return Color.HSVToColor(hsv);
    }

    /**
     * Notice that x is in (0, 1].
     * Should firstly, x = totalLength * x.
     * Then get y = A * e ^ (- B * x) * sin(C * x + D).
     *
     * @param x
     * @param totalLength
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public static float vibration(float x, float totalLength, float A, float B, float C, float D) {
        x *= totalLength;
        return (float) (A * Math.exp(-B * x) * Math.sin(C * x + D));
    }

    public static float limitOffset(float offset, float totalLength) {
        offset = offset > 0 ? offset : 0;
        offset = offset > totalLength ? totalLength : offset;
        return offset;
    }

    public static String getTwoCharName(String nameString) {
        String twoCharString = "";
        String[] strings = nameString.split("\\s+");
        for (int i = 0; i < strings.length; i++) {
            twoCharString += strings[i].charAt(0);
        }
        return twoCharString;
    }

    public static String getAlphaNumericString(int n) {
        StringBuilder stringBuilder = null;
        try {
            String AlphaNumericString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
            stringBuilder = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                int index = (int) (AlphaNumericString.length() * Math.random());
                stringBuilder.append(AlphaNumericString.charAt(index));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackBarAlert(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
