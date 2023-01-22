package Main;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTime {
    int year;
    int month;
    int day;
    int hour;
    int min;
    int sec;
    int millsec;

    public DateTime() {
        LocalDateTime now = LocalDateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = now.getHour();
        this.min = now.getMinute();
        this.sec = now.getSecond();
        this.millsec = 100;
    }

    /**
     * @param dateTime format = yyyy-mm-ddThh:MM:ss
     */
    public DateTime(String dateTime) {
        this.year = Integer.valueOf(dateTime.substring(0, 4));
        this.month = Integer.valueOf(dateTime.substring(5, 7));
        this.day = Integer.valueOf(dateTime.substring(8, 10));
        this.hour = Integer.valueOf(dateTime.substring(11, 13));
        this.min = Integer.valueOf(dateTime.substring(14, 16));
        this.sec = Integer.valueOf(dateTime.substring(17, 19));
        this.millsec = 100;
    }

    public DateTime(LocalDateTime now) {
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = now.getHour();
        this.min = now.getMinute();
        this.sec = now.getSecond();
        this.millsec = 100;
    }

    public DateTime(DateTime dateTime) {
        this.year = dateTime.getYear();
        this.month = dateTime.getMonth();
        this.day = dateTime.getDay();
        this.hour = dateTime.getHour();
        this.min = dateTime.getMin();
        this.sec = dateTime.getSec();
        this.millsec = dateTime.getMillsec();
    }

    /**
     * Set date value and set time as 9.00
     *
     * @param now
     */
    public DateTime(LocalDate now) {
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = 9;
        this.min = 0;
        this.sec = 0;
        this.millsec = 100;
    }

    @Override
    public String toString() {
        String stringToReturn;
        stringToReturn = this.year + "-";
        if (this.getMonth() < 10) stringToReturn += "0";
        stringToReturn += this.month + "-";
        if (this.getDay() < 10) stringToReturn += "0";
        stringToReturn += this.day + "T";
        if (this.getHour() < 10) stringToReturn += "0";
        stringToReturn += this.hour + ":";
        if (this.getMin() < 10) stringToReturn += "0";
        stringToReturn += this.min + ":";
        if (this.getSec() < 10) stringToReturn += "0";
        stringToReturn += this.sec + ".";
        stringToReturn += this.millsec + "Z";
        return stringToReturn;
    }

    public static DateTime valueOf(String dataTime) {
        return new DateTime(dataTime);
    }

    public static DateTime valueOf(LocalDateTime localDateTime) {
        return new DateTime(localDateTime);
    }

    public static DateTime now() {
        return DateTime.valueOf(LocalDateTime.now());
    }

    public boolean isBefore(DateTime second) {
        boolean toReturn = false;
        if (this.getYear() <= second.getYear())
            if (this.getYear() < second.getYear())
                toReturn = true;
            else if (this.getMonth() <= second.getMonth())
                if (this.getMonth() < second.getMonth())
                    toReturn = true;
                else if (this.getDay() <= second.getDay())
                    if (this.getDay() < second.getDay())
                        toReturn = true;
                    else if (this.getHour() <= second.getHour())
                        if (this.getHour() < second.getHour())
                            toReturn = true;
                        else if (this.getMin() <= second.getMin())
                            if (this.getMin() <= second.getMin())
                                toReturn = true;
                            else if (this.getSec() <= second.getSec())
                                if (this.getSec() < second.getSec())
                                    toReturn = true;
        return toReturn;
    }

    public boolean isAfter(DateTime second) {
        return !this.isBefore(second);
    }

    public int differenceInDays(DateTime second) {
        return Math.abs(this.dayNumberInYear() - second.dayNumberInYear());
    }

    public int dayNumberInYear() {
        int result = 0;
        for (int i = 1; i < this.getMonth(); i++) {
            if (i == 2) {
                result += 28;
                if ((this.getYear() % 4) == 0) { //leap year
                    result += 1;
                }
            } else {
                result += 30;

                if (i < 8) {
                    result += i % 2;
                } else {
                    result += (i + 1) % 2;
                }
            }
        }

        return result + this.getDay();
    }

    public void plusDays(int days) {
        int v = (this.getYear() % 4 == 0) ? 1 : 0;
        int m = (this.getMonth() < 8) ? this.getMonth() % 2 : (this.getMonth() + 1) % 2;
        if (this.getMonth() == 2 && (this.getDay() + days > 28 + v)) {
            this.month += 1;
            plusDays(days - (28 + v - this.getDay()));
        } else if (this.getDay() + days > 30 + m) {
            if (this.month == 12) {
                this.month = 1;
                this.year += 1;
            } else this.month += 1;
            plusDays(days - (30 + m - this.getDay()));
        } else {
            this.day += days;
        }
    }

    /*
    Getters
     */

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public int getMillsec() {
        return millsec;
    }
}
