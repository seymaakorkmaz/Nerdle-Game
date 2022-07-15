package nyp_8;

public class StopWatch {

    private static long start;
    private static long end;
    private static boolean started;

    /**
     * Kronometre durmu� vaziyette mi?
     */
    private static boolean isStopped() {
        return !started;
    }

    /**
     * Kronometre ba�lam�� vaziyette mi?
     * @return
     */
    private static boolean isStarted() {
        return started;
    }

    /**
     * Kronometreyi ba�lat�r. E�er zaten ba�lam�� durumda ise RunTimeException hatas� atar.
     */
    public static void start() {
        if (isStopped()) {
            startTimer();
        } else {
            throw new RuntimeException("Hata: Kronometre (Timer) zaten ba�lat�lm�� durumda!!!\n");
        }
    }

    private static void startTimer() {
        start = System.nanoTime();
        started = true;
    }

    /**
     * Kronometreyi durdurur. E�er zaten ba�lamam�� ise RunTimeException hatas� atar.
     */
    public static void stop() {
        if (isStarted()) {
            stopTimer();
        } else {
            throw new RuntimeException("Hata: Kronometre (Timer) ba�lat�lmad�!!!\n");
        }
    }

    private static void stopTimer() {
        end = System.nanoTime();
        started = false;
    }

    /**
     * Timer.start() ile Timer.stop() aras�nda ge�en s�reyi nanosaniye olarak d�nd�r�r.
     */
    public static long getElapsedTime() {
        if (isStopped()) {
            return end - start;
        } else {
            throw new RuntimeException("Hata: Kronometre (Timer) durdurulmad�!!!\n");
        }

    }


    /**
     * Timer.start() ile Timer.stop() aras�nda ge�en s�reyi saniye cinsinden d�nd�r�r.
     */
    public static double getElapsedSeconds() {
        double seconds = (double) getElapsedTime() / 1000000000.0;
        return seconds;
    }
}
