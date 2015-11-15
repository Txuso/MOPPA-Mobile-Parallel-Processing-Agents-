package exception;

import java.io.Serializable;

public class MoppaException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public MoppaException() {
		 super();
	}
    
    public MoppaException(String msg)   {
        super(msg);
    }

}
