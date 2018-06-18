/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.exceptions;

/**
 *
 * @author toon1
 */
public class InvalidCredentialsException extends Exception {

    /**
     * Creates a new instance of <code>invalidCredentialsException</code>
     * without detail message.
     */
    public InvalidCredentialsException() {
    }

    /**
     * Constructs an instance of <code>invalidCredentialsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCredentialsException(String msg) {
        super(msg);
    }
    public InvalidCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
}

