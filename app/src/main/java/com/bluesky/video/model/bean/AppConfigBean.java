package com.bluesky.video.model.bean;

/**
 * Created by duchao on 2017/5/6.
 */

public class AppConfigBean {
    private String code_baiduyun;
    private int pay_sms_switch;
    private int pay_switch;
    private int pay_switch_ali;
    private int pay_switch_wx;
    private int pay_switch_wxsm;
    private int play_num;
    private ConfigPriceBean price;
    private String[] qq;
    private ConfigUrlBean url;
    private String url_baiduyun;

    public String getCodeBaiduYun() {
        return code_baiduyun;
    }

    public void setCodeBaiduYun(String codeBaiduYun) {
        this.code_baiduyun = codeBaiduYun;
    }

    public int getPaySmsSwitch() {
        return pay_sms_switch;
    }

    public void setPaySmsSwitch(int paySmsSwitch) {
        this.pay_sms_switch = paySmsSwitch;
    }

    public int getPaySwitch() {
        return pay_switch;
    }

    public void setPaySwitch(int paySwitch) {
        this.pay_switch = paySwitch;
    }

    public int getPaySwitchAli() {
        return pay_switch_ali;
    }

    public void setPaySwitchAli(int paySwitchAli) {
        this.pay_switch_ali = paySwitchAli;
    }

    public int getPaySwitchWx() {
        return pay_switch_wx;
    }

    public void setPaySwitchWx(int paySwitchWx) {
        this.pay_switch_wx = paySwitchWx;
    }

    public int getPaySwitchWxsm() {
        return pay_switch_wxsm;
    }

    public void setPaySwitchWxsm(int paySwitchWxsm) {
        this.pay_switch_wxsm = paySwitchWxsm;
    }

    public int getPlayNum() {
        return play_num;
    }

    public void setPlayNum(int playNum) {
        this.play_num = playNum;
    }

    public ConfigPriceBean getPrice() {
        return price;
    }

    public void setPrice(ConfigPriceBean price) {
        this.price = price;
    }

    public String[] getQQ() {
        return qq;
    }

    public void setQQ(String[] qq) {
        this.qq = qq;
    }

    public ConfigUrlBean getUrl() {
        return url;
    }

    public void setUrl(ConfigUrlBean url) {
        this.url = url;
    }

    public String getUrlBaiduYun() {
        return url_baiduyun;
    }

    public void setUrlBaiduYun(String urlBaiduYun) {
        this.url_baiduyun = urlBaiduYun;
    }
}
