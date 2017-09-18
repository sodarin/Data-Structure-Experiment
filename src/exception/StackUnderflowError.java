package exception;

/**
 * Created by ZhangHaodong on 2017/9/11.
 */
public class StackUnderflowError extends Exception {
    private static final long serialVersionUID = 1964667358170273791L;
    public StackUnderflowError(){}
    public StackUnderflowError(String message){super(message);}
}
