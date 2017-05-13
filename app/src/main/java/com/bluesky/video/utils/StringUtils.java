package com.bluesky.video.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by duchao on 2017/5/9.
 */

public class StringUtils {
    public static String getMD5(String str) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static String getVideoString(String str) {
        return RSAUtils.decryptByPublic(str);
    }

    //格式为  mm:ss
    public static String formatPlayTime(long time) {
        return new SimpleDateFormat("mm:ss").format(new Date(time));
    }

    public static String getPlayTime(int playTime) {
        try {
            String str = formatPlayTime(playTime);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "00";
    }


    public static String getSerchMsg(int paramInt) {
        switch (paramInt) {
            case 1: {
                return "请开通会员";
            }
            case 2: {
                return "请开通钻石会员";
            }
            case 3: {
                return "请开通黄钻会员";
            }
            case 4: {
                return "请开通黑金会员";
            }
            case 5: {
                return "请开通蓝钻会员";
            }
            case 6: {
                return "请开通皇冠会员";
            }
            case 7: {
                return "开通搜索功能,体验快进，秒开，无缓冲";
            }
            case 8: {
                return "规避风险，视频已移至海外，开通vpn功能";
            }
        }
        return "全部功能完全开放，开通vpn功能";
    }

    public static String[] getText() {
        return new String[] {"妹子的大奶子真饱满，看得直流口水", "瘦小的个子，却有不错的胸，看得让人勃起的骚货", "粉嫩乳头鲜美小鲍 看到就心潮澎湃", "风骚女，被操得胡言乱语", "很好，很强大，很黄很暴力，我喜欢！", "找到了很久之前就想看的苍井空教师系列", "晕，到最后终于看到想看了了...", "操少妇，最喜欢偷情剧情，妹子被操嫩逼还会喷水，非常淫荡", "冲冲冲，比其它的强太多了，不忽悠", "还可以哦，很多都是精彩片段", "总是那么爽，裤子都脱了，湿了", "越来越多啊，打枪必备", "有组织一起", "彩钻区，有一部偷拍的女的，我好像认识，操", "骚货的身材真棒，前凸后翘，真想从后面干一炮", "比市面上那些看片神器，强地太多了", "我靠，要是能下载就好了，我要躲在被子里面", "一群妹子玩群P，那叫声听着就要高潮", "很好，很强大，很黄很暴力，我喜欢！", "找到了很久之前就想看的苍井空教师系列", "晕，到最后终于看到想看了了...", "公交车上直接开干，骚妹子穿丁字裤，被操晕！", "妹子的大奶子真饱满，看得直流口水", "瘦小的个子，却有不错的胸，看得让人勃起的骚货", "粉嫩乳头鲜美小鲍 看到就心潮澎湃", "风骚女，被操得胡言乱语", "很好，很强大，很黄很暴力，我喜欢！", "找到了很久之前就想看的苍井空教师系列", "晕，到最后终于看到想看了了...", "操少妇，最喜欢偷情剧情，妹子被操嫩逼还会喷水，非常淫荡", "冲冲冲，比其它的强太多了，不忽悠", "还可以哦，很多都是精彩片段", "总是那么爽，裤子都脱了，湿了", "妹子的大奶子真饱满，看得直流口水", "瘦小的个子，却有不错的胸，看得让人勃起的骚货", "粉嫩乳头鲜美小鲍 看到就心潮澎湃", "风骚女，被操得胡言乱语", "很好，很强大，很黄很暴力，我喜欢！", "找到了很久之前就想看的苍井空教师系列", "晕，到最后终于看到想看了了...", "操少妇，最喜欢偷情剧情，妹子被操嫩逼还会喷水，非常淫荡", "还可以哦，很多都是精彩片段", "总是那么爽，裤子都脱了，湿了", "有胸器就是爽", "非常好，爱死她了", "胸器就是爽", "非常好，爱死她了", "货真价实，非常棒", "真是看片神器啊，爽爽爽", "高清无缓冲，不错", "非常不错，每晚看", "果断冲会员享大片快感", "女的真是限量版啊", "果然是万部珍藏大片，黑金会员值得拥有", "身材够我撸几天了", "不错不错", "很好看的一个女孩子哦", "好的，就在这里", "棒，真的能看", "女的每部都很好看", "方便了，立马充了会员", "高清、流畅、加载快，666", "身材不得了啊", "好厉害的样子", "吗？", "刺激了吧", "材不错，性感迷人，不错不错", "诱人了，求深交", "就是看看不说话", "好喜欢好喜欢啊", "了狗", "女的是睡着了吗", "功夫好棒啊666666", "女的水好多啊", "个无码的，好清晰", "，不错不错，速度挺快的，没什么缓冲", "里来的美女，快到怀里来，这腿可以玩五天", "好寂寞啊...", "男的怎么可以做这么久啊", "多做起来舒服", "口交足交家操逼全套啊", "看起来不错，女孩很可爱啊", "奶子和翘臀整的真不错好像插她屁股", "还没怎么玩下面的淫水就好多", "生的小骚逼", "都湿了", "好棒，好想插", "方便了，立马充个会员", "子好大啊决定撸她了", "嫩的粉b露出来了极品啊", "骚了，尤其是高潮时候的浪叫", "骚货挺能折腾的", "是要精尽人亡的节奏啊", "码无码东西不错", "干的节奏", "都湿了", "体已被掏空", "吃精子，据说很有营养", "丰满肤白太诱人了，对着撸一管", "我打一炮肯定爽死", "子也太大了极品", "看得想啪啪啪"};
    }

    public static String getVipString(int paramInt) {
        switch(paramInt) {
            case 1:
            {
                return "非会员不能快进";
            }
            case 2:
            {
                return "非钻石会员不能快进";
            }
            case 3:
            {
                return "非黄钻会员不能快进";
            }
            case 4:
            {
                return "开通黑金会员，体验快进功能，激情片段瞬间抵达";
            }
            case 5:
            {
                return "非蓝钻会员不能快进";
            }
            case 6:
            {
                return "非皇冠会员不能快进";
            }
        }
        return "升级顶级会员，流畅观看";
    }

    public static String getbottomMsg(int paramInt) {
        switch(paramInt) {
            case 1:
            {
                return "升级会员，查看更多";
            }
            case 2:
            {
                return "升级钻石会员，查看更多";
            }
            case 3:
            {
                return "升级黄钻会员，查看更多";
            }
            case 4:
            {
                return "升级黑金会员，查看更多";
            }
            case 5:
            {
                return "升级蓝钻会员，查看更多";
            }
            case 6:
            {
                return "升级皇冠会员，查看更多";
            }
            case 7:
            {
                return "升级顶级会员，查看更多";
            }
            case 8:
            {
                return "升级顶级会员，查看更多";
            }
        }
        return "升级顶级会员，查看更多";
    }

}
