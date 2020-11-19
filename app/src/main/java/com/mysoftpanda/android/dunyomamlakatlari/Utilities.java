package com.mysoftpanda.android.dunyomamlakatlari;

public class Utilities {
    public String milliSecondsToTimer(long j) {
        StringBuilder stringBuilder;
        String stringBuilder2;
        String str = "";
        int i = (int) (j / 3600000);
        j %= 3600000;
        int i2 = ((int) j) / 60000;
        int i3 = (int) ((j % 60000) / 1000);
        if (i > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(":");
            str = stringBuilder.toString();
        }
        if (i3 < 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(i3);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(i3);
            stringBuilder2 = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(i2);
        stringBuilder.append(":");
        stringBuilder.append(stringBuilder2);
        return stringBuilder.toString();
    }

    public int getProgressPercentage(long j, long j2) {
        Double.valueOf(0.0d);
        return Double.valueOf((((double) ((long) ((int) (j / 1000)))) / ((double) ((long) ((int) (j2 / 1000))))) * 100.0d).intValue();
    }

    public int progressToTimer(int i, int i2) {
        return ((int) ((((double) i) / 100.0d) * ((double) (i2 / 1000)))) * 1000;
    }
}
