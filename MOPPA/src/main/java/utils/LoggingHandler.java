package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class LoggingHandler {
  
  private static Logger log = Logger.getLogger(LoggingHandler.class);
  
  public final static void writeErrorToLog(final Exception e) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    
    String error = sw.toString();
    log.error(error);
  }

  public static Logger getLogger() {
    return log;
  }
 
}
