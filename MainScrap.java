import com.fasterxml.jackson.core.JsonProcessingException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


public class MainScrap {
    //"https://www19.gogoanime.io/category/gleipnir"
    private static final String baseUrl = "https://www19.gogoanime.io/category/gleipnir";
    public static void main(String[] args)  {

/*
        try {
            File file = new File("C:\\Users\\tariq\\OneDrive\\Desktop\\vvvv\\Test.mp4");
            Path targetPath = Paths.get("C:\\Users\\tariq\\OneDrive\\Desktop\\vvvv\\Test.mp4");
            InputStream in = new URL("https://scontent.xx.fbcdn.net/v/t39.24130-2/10000000_2499308190308425_2032992991868031374_n.mp4?_nc_cat=105&_nc_sid=985c63&efg=eyJ2ZW5jb2RlX3RhZyI6Im9lcF9oZCJ9&_nc_ohc=Wfij5TJshC0AX9iAFhi&_nc_ht=video-lga3-1.xx&oh=2e0369cdf4eb6237f26a3422c4541003&oe=5EEC0A96&jparams=MTA4LjIxNi4yNDIuODU=&upx=TW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzgxLjAuNDA0NC4xMzggU2FmYXJpLzUzNy4zNg").openStream();
            long copy = Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(copy);
        } catch (IOException e) {
            e.getStackTrace();
        }
*/


        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);




         WebClient client = new WebClient(BrowserVersion.CHROME);

         client.getOptions().setJavaScriptEnabled(true);
         client.getOptions().setCssEnabled(true);
         client.getOptions().setUseInsecureSSL(true);
         CookieManager cookie = new CookieManager();
         cookie.setCookiesEnabled(true);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);


         try {
             HtmlPage page = client.getPage(baseUrl);
             //this xpath takes you to on the links to each video
             List<HtmlElement> list = page.getByXPath("//ul[@id='episode_related']//li//a");
             if (list.isEmpty()) {
                 System.out.println("No Episodes");
             } else {
                 for (int i = 0; i<1;i++) {
                     HtmlAnchor episodeAnchor = (HtmlAnchor) list.get(i).getFirstByXPath("/html/body/div/div[@id='container']//tbody/a");
                     //HtmlElement itemPrice = (HtmlElement) items.getFirstByXPath(".//a/span[@class='result-price'] ");

                    try {
                        //goes to website before current episode the CAPTCHA
                        page = client.getPage(episodeAnchor.getHrefAttribute());
                        HtmlAnchor linkToTheCurrentEpisode = (HtmlAnchor) page.getByXPath("//a[@class='specialButton']");

                        //goes to current episiode link
                        page = client.getPage(linkToTheCurrentEpisode.getHrefAttribute());
                        List<HtmlElement> episodeList = page.getByXPath("//video[@class='vjs-tech']");
                        HtmlImage episodeSource = episodeList.get(0).getFirstByXPath("//video[@class='vjs-tech']");


                         File file = new File("C:/Users/tariq/Glep/Test.mp4");
                         Path targetPath = Paths.get("C:/Users/tariq/Glep/Test.mp4");
                         InputStream in = new URL(episodeSource.getSrcAttribute()).openStream();
                         long copy = Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
                         System.out.println("Finish");
                     } catch (IOException e) {
                         e.getStackTrace();
                     }

                     /*String price = itemPrice == null ? "0.0": itemPrice.asText();


                     HackerNewsItem car = new HackerNewsItem();
                     car.setTitle(itemAnchor.asText());
                     //car.setUrl();
                     car.setPrice(new BigDecimal(price.replace("$","")));

                     ObjectMapper mapper = new ObjectMapper();
                     String jsonStr = mapper.writeValueAsString(car);
                     System.out.println(jsonStr);*/
                 }
             }

         } catch (IOException e) {
             e.printStackTrace();

        }
    }
}