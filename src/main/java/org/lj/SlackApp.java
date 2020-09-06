package org.lj;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/slack/events")
public class SlackApp extends SlackAppServlet {
  private static final long serialVersionUID = 1L;
  
  public SlackApp() throws IOException { super(initSlackApp()); }
  public SlackApp(App app) { super(app); }

  private static App initSlackApp() throws IOException {
    App app = new App();
    app.command("/piglatin", (req, ctx) -> {
      
      //Translate the input text
      PigLatin pigLatin = new PigLatin();
      String textToTranslate = req.getPayload().getText();
      String translatedText = pigLatin.translateToPigLatin(textToTranslate);
      
       //Send result to Kafka
      TranslatorResource translator = new TranslatorResource();
      translator.addTranslation(translatedText);
            
      //Send response back to Slack from app
    //  ctx.respond(textToTranslate + " in Pig Latin is " + translatedText + "! :tada:");
      //Tell Slack we got the message.
      return ctx.ack("got it");
    });
    return app;
  }
}
