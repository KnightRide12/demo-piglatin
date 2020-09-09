package org.lj;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.jboss.logging.Logger;

import javax.ws.rs.core.MediaType;

@Path("/piglatin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PigLatinResource {

    private PigLatin pigLatin;
    private static final Logger LOG = Logger.getLogger(PigLatinResource.class);


    public PigLatinResource() {
    }

    @POST
    public PigLatin translate(PigLatin input) {
        pigLatin = new PigLatin(input.inputText);
        pigLatin.translateToPigLatin();
        // stall a bit
        int i;
        BigInteger fact=BigInteger.valueOf(1); 
        int number=100;//It is the number to calculate factorial. 
        for(i=1;i<=number;i++){
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        LOG.info(input + " translated to " + pigLatin + " (" + fact + ")");
        return pigLatin;
    }
}
