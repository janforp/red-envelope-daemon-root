package com.hongbao.api.consts;

/**
 * Created by Summer on 16/8/3.
 */
public class CacheConsts {

    public static final long SECONDS_OF_ONE_MINUTE = 60;
    public static final long SECONDS_OF_TEN_MINUTE = 60 * 10;
    public static final long SECONDS_OF_HALF_HOUR = 60 * 30;
    public static final long SECONDS_OF_ONE_HOUR = 60 * 60;
    public static final long SECONDS_OF_ONE_DAY = 60 * 60 * 24;
    public static final long SECONDS_OF_THREE_DAY = 60 * 60 * 24 * 3;
    public static final long SECONDS_OF_ONE_WEEK = 60 * 60 * 24 * 7;
    public static final long SECONDS_OF_ONE_MONTH = 60 * 60 * 24 * 30;

    /**
     * keys for cache
     */
    // 用户秘钥对象
    public static final String CACHE_USER_KEY = "uk_";
    // 用户基本信息
    public static final String CACHE_USER = "usr";
    // 图形验证码
    public static final String CACHE_CAPTCHA = "captcha_";
    // 邀请人
    public static final String INVITATION_USER = "in_usr_";

    // 早餐红包
    public static final String MORNING_FIX_RED = "MORNING_FIX_RED";
    // 去重列表（money：0.01，userId：16）
    public static final String MORNING_RED_GET_LIST = "MORNING_RED_GET_LIST";
    // 午餐红包
    public static final String LUNCH_FIX_RED = "LUNCH_FIX_RED";
    public static final String LUNCH_RED_GET_LIST = "LUNCH_RED_GET_LIST";
    // 晚餐红包
    public static final String DINNER_FIX_RED = "DINNER_FIX_RED";
    public static final String DINNER_RED_GET_LIST = "DINNER_RED_GET_LIST";
    // 夜宵红包
    public static final String NIGHT_FIX_RED = "NIGHT_FIX_RED";
    public static final String NIGHT_RED_GET_LIST = "NIGHT_RED_GET_LIST";


    // 用户抢到定时红包 + fixId + _ + userId
    public static final String USER_FIXED = "USER_FIXED_";
    // 定时红包 + fixId
    public static final String FIXED_RED = "FIXED_RED_";
    // 用户金额 + fixId + _ + userId
    public static final String FIXED_MONEY = "FIXED_MONEY_";


    // 首页banner
    public static final String INDEX_BANNER = "INDEX_BANNER";
    // 福利banner
    public static final String WELFARE_BANNER = "WELFARE_BANNER";
    // 悬赏banner
    public static final String XS_BANNER = "XS_BANNER";
    // 首页滚动列表
    public static final String INDEX_ROLL_LIST = "INDEX_ROLL_LIST";
    // 5.0版本的任务入口导航
    public static final String INDEX_MISSION_NAVIGATION_IOS = "INDEX_MISSION_NAVIGATION_IOS";
    // 5.0版本的任务入口导航
    public static final String INDEX_MISSION_NAVIGATION_ANDROID = "INDEX_MISSION_NAVIGATION_ANDROID";


    /**
     * ios任务相关
     */
    // 首页ios任务
    public static final String INDEX_IOS_MISSION = "INDEX_IOS_MISSION";
    // 用户做过的ios任务id列表 + userId
    public static final String USER_IOS = "USER_IOS_";
    // ios任务详情
    public static final String IOS_MISSION_INFO = "IOS_MISSION_INFO_";
    // 用户完成ios任务情况
    public static final String FINISH_IOS_INFO = "FINISH_IOS_INFO_";
    // ios某一步任务详情
    public static final String STEP_INFO = "STEP_INFO_";
    // ios任务列表详情
    public static final String IOS_LIST = "IOS_LIST_";


    /**
     * android积分墙相关
     */
    // 用户正在做的积分墙任务id列表 + userId
    public static final String USER_UNDERWAY_WALL = "USER_UNDERWAY_WALL_";
    // 首页积分墙
    public static final String INDEX_INTEGRAL_WALL = "INDEX_INTEGRAL_WALL";
    // 用户做过的积分墙包名列表 + userId
    public static final String USER_WALL = "USER_WALL_";
    // 用户完成积分墙情况
    public static final String FINISH_INFO = "FINISH_INFO_";
    // 积分墙任务详情
    public static final String MISSION_INFO = "MISSION_INFO_";
    // 积分墙留存详情
    public static final String DEPTH_INFO = "DEPTH_INFO_";
    // 积分墙留存列表详情
    public static final String DEPTH_LIST = "DEPTH_LIST_";
    // 附属任务列表详情
    public static final String AUX_LIST = "AUX_LIST_";
    // 专属任务id列表
    public static final String EXC_LIST = "EXC_LIST_";
    // 用户完成附属情况
    public static final String FINISH_AUX_INFO = "FINISH_AUX_INFO_";
    // 某一天附属任务 详情
    public static final String AUX_INFO = "AUX_INFO_";
    // 某一天激活用户的留存指标
    public static final String KEEP_INFO = "KEEP_INFO_";


    //福利相关 start

    //精选福利IOS
    public static final String SELECTED_WELFARE_IOS = "SELECTED_WELFARE_IOS";
    //精选福利ANDROID
    public static final String SELECTED_WELFARE_ANDROID = "SELECTED_WELFARE_ANDROID";
    //福利导航IOS
    public static final String  WELFARE_NAVIGATION_IOS = "WELFARE_NAVIGATION_IOS";
    //精选福利ANDROID
    public static final String  WELFARE_NAVIGATION_ANDROID = "WELFARE_NAVIGATION_ANDROID";

    //IOS福利列表(有序集合，用于分页：welfareId-welfareId)
    public static final String WELFARE_LIST_IOS_SORTEDSET = "WELFARE_LIST_IOS_SORTEDSET";
    //ANDROID福利列表(有序集合，用于分页：welfareId-welfareId)
    public static final String WELFARE_LIST_ANDROID_SORTEDSET = "WELFARE_LIST_ANDROID_SORTEDSET";
    //IOS福利列表(哈希表：welfareId-welfare对象)
    public static final String WELFARE_LIST_IOS_HASH = "WELFARE_LIST_IOS_HASH";
    //ANDROID福利列表(哈希表：welfareId-welfare对象)
    public static final String WELFARE_LIST_ANDROID_HASH = "WELFARE_LIST_ANDROID_HASH";

    //按照不同的类型存福利Id列表，分页用，类型是sortedSet
    public static final String  WELFARE_ID_LIST_IOS_TYPE_="WELFARE_ID_LIST_IOS_TYPE_";
    public static final String  WELFARE_ID_LIST_ANDROID_TYPE_="WELFARE_ID_LIST_ANDROID_TYPE_";
    //存放福利对象的hash键
    public static final String  WELFARE_OBJECT = "WELFARE_OBJECT";
    //福利相关 end


    //悬赏任务相关 start

    //悬赏任务列表（xuanshangId-score）:id可能不连续，但是score是连续的，可以根据score来分页
    public static final String Z_XUANSHANG_ID_LIST = "Z_XUANSHANG_ID_LIST";
    //我参与的悬赏任务列表的详情id列表：detailId-score,PARTIN_ID_LIST_19:表示存的是19用户的参与列表
    public static final String Z_PARTIN_ID_LIST_ = "Z_PARTIN_ID_LIST_";
    //我参与的悬赏任务列表的详情id列表：xuanshangId-score,SEND_ID_LIST_19:表示存的是19用户发布的悬赏任务id列表
    public static final String Z_SEND_ID_LIST_ = "Z_SEND_ID_LIST_";
    //悬赏任务列表对象hash （xuanshangId-ReXuanshang）:对象中的部分数据需要java处理
    public static final String H_XUANSHANG_META_OBJECT = "H_XUANSHANG_META_OBJECT";
    //用户提交某个悬赏任务的记录：xuanshangId+"&"+userId，一个任务只能提交一次
    public static final String S_USER_ALREADY_SUBMIT = "S_USER_ALREADY_SUBMIT";
    //用户参与悬赏任务的详情记录:detailId-ReXuanshangDetail
    public static final String H_XUANSHANG_DETAIL_OBJECT = "H_XUANSHANG_DETAIL_OBJECT";

    //某个悬赏任务提交的数据xuanshangId-Integer,审核通过一个则把+1，生成该任务时，为0
    public static final String H_XUANSHANG_SUBMIT_NUM = "H_XUANSHANG_SUBMIT_NUM";
    //某个悬赏任务已审核通过量:xuanshangId-Integer,审核通过一个则把+1，生成该任务时，为0
    public static final String H_XUANSHANG_PASS_NUM = "H_XUANSHANG_PASS_NUM";
    //某个悬赏任务已审核未通过量:xuanshangId-Integer,审核未通过一个则把+1，生成该任务时，为0
    public static final String H_XUANSHANG_NOTPASS_NUM = "H_XUANSHANG_NOTPASS_NUM";
    //某个悬赏任务待审核量:xuanshangId-Integer,提交一个则把+1，生成该任务时，为0
    public static final String H_XUANSHANG_TOVERIFY_NUM = "H_XUANSHANG_TOVERIFY_NUM";
    //某个悬赏任务审核通过的记录：如：王小二完成任务，赚到0.10元,KEY的形式：XUANSHANG_REWARD_STRING_19:表示id为19的悬赏任务
    public static final String S_XUANSHANG_REWARD_STRING_ = "S_XUANSHANG_REWARD_STRING_";
    //悬赏任务xuanshangId若被userId提交了，则记录：xuanshangId&userId-score,score就是detailId
    public static final String Z_XUANSHANG_USER_DETAILID = "Z_XUANSHANG_USER_DETAILID";

    //悬赏任务相关 end



}
