package exceptions;

import javax.ws.rs.core.Response.Status;

/**
 * Custom Response. 
 * The user has introduced an invalid value. 
 */
public class InvalidData extends AbstractException {

  /**
   * Constructor. 
   * @param reasonPhrase Message to show in the response. 
   */
  public InvalidData(final String reasonPhrase) {
      super(Status.NOT_ACCEPTABLE, reasonPhrase);
  }
}