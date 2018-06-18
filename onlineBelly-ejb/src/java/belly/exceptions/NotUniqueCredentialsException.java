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
public class NotUniqueCredentialsException extends Exception {

    /**
     * Creates a new instance of <code>NotUniqueCredentialsException</code>
     * without detail message.
     */
    public NotUniqueCredentialsException() {
    }

    /**
     * Constructs an instance of <code>NotUniqueCredentialsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotUniqueCredentialsException(String msg) {
        super(msg);
    }
    public NotUniqueCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public NotUniqueCredentialsException(Throwable cause) {
        super(cause);
    }
}
