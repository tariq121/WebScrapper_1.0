import java.math.BigDecimal;

public class HackerNewsItem {
    private String title;

    private String url ;

    private BigDecimal price;


//getters and setters

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setTitle( String title) {
        this.title = title;
    }
    public void setUrl( String url) {
        this.url = url;
    }

    public void setPrice( BigDecimal price) {
        this.price = price;
    }
}
