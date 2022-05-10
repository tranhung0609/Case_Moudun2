package multithreadthing;

import login.Login;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MultithreadThing  {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        Date now = new Date();
        System.out.println( ANSI_YELLOW+"Xin chào quý khách đã đến với khách sạn ラブホテル.\nThời gian hiện tại bây giờ là : " + now.getHours()+"giờ : "+ now.getMinutes()+"phút");
        TimerTask timerTask = new TimerTask() {
            int count = 5;
            @Override
            public void run() {
                if (count > 0){
                    System.out.println(count);
                    count--;
                } else {
                    System.out.println("Chào mừng bạn đến với hệ thống quản lý của Khách sạn ラブホテル"+ ANSI_RESET);
                    Login login = new Login();
                    login.loginSystems();
                    timer.cancel();
                }
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2022);
        calendar.set(Calendar.MONTH,Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH,20);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        timer.schedule(timerTask , 0 , 400);

    }
}