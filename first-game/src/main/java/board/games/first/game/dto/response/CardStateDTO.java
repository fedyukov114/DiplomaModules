package board.games.first.game.dto.response;

public class CardStateDTO {
    private Long price;
    private Long fine;
    private String ownerName;
    private Integer level;
    private Integer collectionNumber;

    public CardStateDTO(Long price, Long fine, String ownerName, Integer level, Integer collectionNumber) {
        this.price = price;
        this.fine = fine;
        this.ownerName = ownerName;
        this.level = level;
        this.collectionNumber = collectionNumber;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getFine() {
        return fine;
    }

    public void setFine(Long fine) {
        this.fine = fine;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }
}
