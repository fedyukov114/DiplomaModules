package board.games.first.game.dto.response;

import java.util.List;


public class CardDetailDTO {
    private String title;
    private String sphere;
    private List<Long> fines;
    private Long price;
    private Long sale_price;
    private Long star_price;
    private String ownerName;
    private Integer collectionNumber;

    public CardDetailDTO(String title, String sphere, List<Long> fines, Long price, Long sale_price, Long star_price, String ownerName, Integer collectionNumber) {
        this.title = title;
        this.sphere = sphere;
        this.fines = fines;
        this.price = price;
        this.sale_price = sale_price;
        this.star_price = star_price;
        this.ownerName = ownerName;
        this.collectionNumber = collectionNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSphere() {
        return sphere;
    }

    public void setSphere(String sphere) {
        this.sphere = sphere;
    }

    public List<Long> getFines() {
        return fines;
    }

    public void setFines(List<Long> fines) {
        this.fines = fines;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSale_price() {
        return sale_price;
    }

    public void setSale_price(Long sale_price) {
        this.sale_price = sale_price;
    }

    public Long getStar_price() {
        return star_price;
    }

    public void setStar_price(Long star_price) {
        this.star_price = star_price;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }
}
