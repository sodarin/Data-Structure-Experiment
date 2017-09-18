package exception;

/**
 * Created by ZhangHaodong on 2017/9/11.
 */
public class QueueOverflowError extends Exception{
    private static final long serialVersionUID = 1L;

    public QueueOverflowError(){

    }

    public QueueOverflowError(String message){
        super(message);
    }
}
