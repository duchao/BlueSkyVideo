package com.bluesky.video.model.bean;

/**
 * Created by duchao on 2017/5/6.
 */

public class ConfigPriceBean {
    private float price_black;
    private float price_exit_diamond;
    private float price_exit_gold;
    private float price_normal_diamond;
    private float price_normal_gold;
    private float price_up_diamond;

    public float getPriceBlack() {
        return price_black;
    }

    public void setPriceBlack(float priceBlack) {
        this.price_black = priceBlack;
    }

    public float getPriceExitDiamond() {
        return price_exit_diamond;
    }

    public void setPriceExitDiamond(float priceExitDiamond) {
        this.price_exit_diamond = priceExitDiamond;
    }

    public float getPriceExitGold() {
        return price_exit_gold;
    }

    public void setPriceExitGold(float priceExitGold) {
        this.price_exit_gold = priceExitGold;
    }

    public float getPriceNormalDiamond() {
        return price_normal_diamond;
    }

    public void setPriceNormalDiamond(float priceNormalDiamond) {
        this.price_normal_diamond = priceNormalDiamond;
    }

    public float getPriceNormalGold() {
        return price_normal_gold;
    }

    public void setPriceNormalGold(float priceNormalGold) {
        this.price_normal_gold = priceNormalGold;
    }

    public float getPriceUpDiamond() {
        return price_up_diamond;
    }

    public void setPriceUpDiamond(float priceUpDiamond) {
        this.price_up_diamond = priceUpDiamond;
    }
}
