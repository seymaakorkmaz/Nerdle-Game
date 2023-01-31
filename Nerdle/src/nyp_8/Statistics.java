package nyp_8;

import java.io.Serializable;

public class Statistics implements Serializable{
	//Istatistik bilgileri tutulur.
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int aborted;
    private int unsuccessful;
    private int successful;
    private int averageNumberRows;
    private int averageTime;

    public Statistics(int aborted, int unsuccessful, int successful, int averageNumberRows, int averageTime) {
        super();
        this.aborted = aborted;
        this.unsuccessful = unsuccessful;
        this.successful = successful;
        this.averageNumberRows = averageNumberRows;
        this.averageTime = averageTime;
    }

    public int getAborted() {
        return aborted;
    }

    public void setAborted(int aborted) {
        this.aborted = aborted;
    }

    public int getUnsuccessful() {
        return unsuccessful;
    }

    public void setUnsuccessful(int unsuccessful) {
        this.unsuccessful = unsuccessful;
    }

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    public int getAverageNumberRows() {
        return averageNumberRows;
    }

    public void setAverageNumberRows(int averageNumberRows) {
        this.averageNumberRows = averageNumberRows;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }




}