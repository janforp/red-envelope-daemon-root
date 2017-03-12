package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public class ReCoinItem implements java.io.Serializable {

    // Fields

    // 商品id
    private Long itemId;
    // 标题
    private String itemTitle;
    // 图片
    private String itemImg;
    // 金币
    private Integer itemCoin;
    private String itemDesc;
    // 商品类型0:实物，1:虚拟商品，将来会扩展
    private Integer itemType;

    // Constructors

    /**
     * default constructor
     */
    public ReCoinItem() {
    }

    /**
     * full constructor
     */
    public ReCoinItem(String itemTitle, String itemImg, Integer itemCoin, String itemDesc, Integer itemType) {
        this.itemTitle = itemTitle;
        this.itemImg = itemImg;
        this.itemCoin = itemCoin;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
    }

    // Property accessors

    /**
     * 商品id
     */
    public Long getItemId() {
        return this.itemId;
    }

    /**
     * 商品id
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 标题
     */
    public String getItemTitle() {
        return this.itemTitle;
    }

    /**
     * 标题
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * 图片
     */
    public String getItemImg() {
        return this.itemImg;
    }

    /**
     * 图片
     */
    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    /**
     * 金币
     */
    public Integer getItemCoin() {
        return this.itemCoin;
    }

    /**
     * 金币
     */
    public void setItemCoin(Integer itemCoin) {
        this.itemCoin = itemCoin;
    }

    public String getItemDesc() {
        return this.itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 商品类型0:实物，1:虚拟商品，将来会扩展
     */
    public Integer getItemType() {
        return this.itemType;
    }

    /**
     * 商品类型0:实物，1:虚拟商品，将来会扩展
     */
    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

}