package Main;

import java.time.LocalDateTime;

public class DateTime {
    int year;
    int month;
    int day;
    int hour;
    int min;
    int sec;
    int millsec;

    public DateTime() { // TODO optimize wth second constructor
        LocalDateTime now = LocalDateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = now.getHour();
        this.min = now.getMinute();
        this.sec = now.getSecond();
        this.millsec = 100;
    }

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
