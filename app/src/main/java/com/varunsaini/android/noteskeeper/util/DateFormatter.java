package com.varunsaini.android.noteskeeper.util;

public class  DateFormatter {

    public static String changeDateFormat(int date, int month){
        String fMonth;
        switch (month){
            case 1:
                fMonth = "Jan";
                break;
            case 2:
                fMonth = "Feb";
                break;
            case 3:
                fMonth = "March";
                break;
            case 4:
                fMonth = "April";
                break;
            case 5:
                fMonth = "May";
                break;
            case 6:
                fMonth = "June";
                break;
            case 7:
                fMonth = "July";
                break;
            case 8:
                fMonth = "Aug";
                break;
            case 9:
                fMonth = "Sept";
                break;
            case 10:
                fMonth = "Oct";
                break;
            case 11:
                fMonth = "Nov";
                break;
            case 12:
                fMonth = "Dec";
                break;
            default:
                fMonth = String.valueOf(month);

        }
        return date  + "-"  + fMonth;

    }

}
