package board.games.first.game.entity;

import board.games.first.game.enums.CardType;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "common_card")
public class CommonCard {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "image_type", nullable = false)
    private String imageType;

    @Column(name = "card_type", nullable = false)
    @Enumerated(STRING)
    private CardType type;

    @Column(name = "price")
    private Long price;

//    @Column(name = "position", nullable = false)
//    private Integer position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    public String getImageType() { return imageType; }

    public void setImageType(String imageType) { this.imageType = imageType; }
}
