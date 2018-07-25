package simonlee.hackernews.utils;

public class DateUtil {

    public static String difference(long a, long b) {
        long diff = Math.abs(a - b);

        // less than 1s
        if (diff < 1000) {
            return "<1s";
        }
        // less than 1 minute
        else if (diff < 60_000) {
            return (diff / 1000) + "s";
        }
        // less than 1 hour
        else if (diff < 3600_000) {
            return (int) Math.floor(diff / 1000 / 60) + "min";
        }
        // otherwise
        else {
            return (int) Math.floor(diff / 1000 / 3600) + "h";
        }
    }


}
