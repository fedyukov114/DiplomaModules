package board.games.first.game.entity;

import static board.games.first.game.entity.CompanyCardToLevelFine.COMPANY_CARD_TO_LEVEL_FINE_TABLE_NAME;
import static board.games.first.game.entity.CompanyCardToLevelFine.ColumnName.CC2LF_COMPANY_CARD_ID;
import static board.games.first.game.entity.CompanyCardToLevelFine.ColumnName.CC2LF_LEVEL_FINE_ID;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity

@Table(name = "company_card")
public class CompanyCard {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "star_price", nullable = false)
    private Long starPrice;

    @Column(name = "sale_price", nullable = false)
    private Long salePrice;

    @Column(name = "collection_number", nullable = false)
    private Integer collectionNumber;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = COMPANY_CARD_TO_LEVEL_FINE_TABLE_NAME, joinColumns = @JoinColumn(name = CC2LF_COMPANY_CARD_ID),
            inverseJoinColumns = @JoinColumn(name = CC2LF_LEVEL_FINE_ID))
    private List<LevelFine> fines = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "common_card_id", nullable = false)
    private CommonCard commonCard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String sphere) {
        this.region = sphere;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getStarPrice() {
        return starPrice;
    }

    public void setStarPrice(Long starPrice) {
        this.starPrice = starPrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    public List<LevelFine> getFines() {
        return fines;
    }

    public void setFines(List<LevelFine> fines) {
        this.fines = fines;
    }

    public CommonCard getCommonCard() {
        return commonCard;
    }

    public void setCommonCard(CommonCard commonCard) {
        this.commonCard = commonCard;
    }
}
