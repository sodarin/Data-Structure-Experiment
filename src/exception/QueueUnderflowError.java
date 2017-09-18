package exception;

/**
 * Created by ZhangHaodong on 2017/9/11.
 */
public class QueueUnderflowError extends Exception{
    private static final long serialVersionUID = 1L;

    public QueueUnderflowError(){

    }

    public QueueUnderflowError(String message){
        super(message);
    }
}
