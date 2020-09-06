package org.lj;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "prices" Kafka topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class TranslationForwarder {
                                
    @Outgoing("messaging-demo")                         
    @Broadcast                                          
    public String forward(String message) {
        String tempStart = '{"text":"';
        String tempEnd = '"}';
        return tempStart + message + tempEnd;
    }

}
