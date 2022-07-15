package nyp_8;

public class StopWatch {

    private static long start;
    private static long end;
    private static boolean started;

    /**
     * Kronometre durmuþ vaziyette mi?
     */
    private static boolean isStopped() {
        return !started;
    }

    /**
     * Kronometre baþlamýþ vaziyette mi?
     * @return
     */
    private static boolean isStarted() {
        return started;
    }

    /**
     * Kronometreyi baþlatýr. Eðer zaten baþlamýþ durumda ise RunTimeException hatasý atar.
     */
    public static void start() {
        if (isStopped()) {
            startTimer();
        } else {
            throw new RuntimeException("Hata: Kronometre (Timer) zaten baþlatýlmýþ durumda!!!\n");
        }
    }

    private static void startTimer() {
        start = System.nanoTime();
        started = true;
    }

    /**
     * Kronometreyi durdurur. Eðer zaten baþlamamýþ ise RunTimeException hatasý atar.
     */
    public static void stop() {
        if (isStarted()) {
            stopTimer();
        } else {
            throw new RuntimeException("Hata: Kronometre (Timer) baþlatýlmadý!!!\n");
        }
    }

    private static void stopTimer() {
        end = System.nanoTime();
        started = false;
    }

    /**
     * Timer.start() ile Timer.stop() arasýnda geçen süreyi nanosaniye olarak döndürür.
     */
    public static long getElapsedTime() {
        if (isStopped()) {
            return end - start;
        } else {
            throw new RuntimeException("Hata: Kronometre (Timer) durdurulmadý!!!\n");
        }

    }


    /**
     * Timer.start() ile Timer.stop() arasýnda geçen süreyi saniye cinsinden döndürür.
     */
    public static double getElapsedSeconds() {
        double seconds = (double) getElapsedTime() / 1000000000.0;
        return seconds;
    }
}
