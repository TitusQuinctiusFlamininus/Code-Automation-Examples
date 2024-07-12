public class OHCETimeManager {
    public String startOHCE(TimeNow morningTime) {
        String[] thetime = morningTime.getTheTime().split(":");
        String hour = thetime[0];
        String min = thetime[1];
        String sec = thetime[2].split("\\.")[0];
        String milisec = thetime[2].split("\\.")[1];
        if (((Integer.parseInt(hour) == 12) && (Integer.parseInt(min) == 0) && (Integer.parseInt(sec) == 0) && (Integer.parseInt(milisec) == 0)) ||
             ((Integer.parseInt(hour) == 6) && (Integer.parseInt(min) == 0) && (Integer.parseInt(sec) == 0)  && (Integer.parseInt(milisec) == 0))  ||
             ((Integer.parseInt(hour) < 12) && ((Integer.parseInt(hour) > 6)))) {
            return "Buenas días";
        }
        else if (((Integer.parseInt(hour) >= 12) && ((Integer.parseInt(hour) < 20))) ||
                ((Integer.parseInt(hour) == 20) && (Integer.parseInt(min) == 0) && (Integer.parseInt(sec) == 0) && (Integer.parseInt(milisec) == 0))) {
            return "Buenas tardes";
        }else {
            return "Buenas noches";
        }
    }
}
