/**
 * 
 */
package ch.kausoft.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;
import ch.kausoft.controller.UseCase;

/**
 * @author Heinz
 *
 */
public class Context {
   //@formatter:off
   @Getter @Setter private Properties properties = new Properties();  
   @Getter @Setter private Map<String, UseCase>  useCase = new HashMap<String, UseCase>();
   //@formatter:on
}
