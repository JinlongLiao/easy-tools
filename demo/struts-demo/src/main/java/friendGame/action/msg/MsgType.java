package friendGame.action.msg;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MsgType {

    /**
     * 获取所有中心服务器列表
     */
    
    public static final int HANDLE_ALL_SERVER = 0xa001;

    /**
     * 设备激活
     */
    
     public final static int HANDLE_APP_ACTIVATE = 0x01;

    /**
     * 版本校验
     */
    
    public final static int HANDLE_VERSION_CHECK = 0x02;

    /**
     * 客户端微信登录
     */
    
    public final static int HANDLE_LOGIN = 0x03;

    /**
     * 获取中心服务器列表(NEED)
     */
    
    public static final int HANDLE_SERVER = 0x04;

    /**
     * 游戏登录(NEED)
     */
    
    public final static int HANDLE_GAME_LOGIN = 0x07;

    
    public final static int HANDLE_USER_PROP = 0x08;


    /**
     * 加入房间
     */
    
    public static final int HANDLE_JOIN_ROOM = 0x0b;


    /**
     * 搜索用户
     */
    
    public static final int HANDLE_SEARCH_USER = 0x1e;

    /**
     * 获取服务器配置文件
     */
    
    public final static int HANDLE_GAME_CONF = 0x20;


    /**
     * 检查有无绑定码
     */
    
    public static final int HANDLE_CHECK_INVITE = 0x24;

    /**
     * 游戏首次分享得房卡
     */
    
    public static final int HANDLE_SHARE_AWARD = 0x25;

    /**
     * 玩家经纬度解析
     */
    
    public static final int HANDLE_GPS_LOCATION = 0x26;

    /**
     * 玩家经纬度信息
     */
    
    public static final int HANDLE_GET_GPS = 0x27;

    /**
     * 完善玩家姓名,手机信息
     * 绑定手机信息
     */
    
    public static final int HANDLE_IMPROVE = 0x28;


    /**
     * 获取玩家头像地址
     */
    
    public static final int HANDLE_GET_IMGURL = 0x2a;

    /**
     * 获取游戏信息
     */
    
    public static final int HANDLE_GAME_INFO = 0x2c;

    /**
     * 验证身份证信息
     */
    
    public static final int HANDLE_ID_CARD_IMPROVE = 0x2d;

    /**
     * 获取手机验证码
     */
    
    public static final int HANDLE_MOBILE_CODE = 0x30;

    /**
     * 查看充值结果(NEED)
     */
    
    public static final int HANDLE_REFRESH_USER = 0x31;

    /**
     * 查看查看充值订单状态
     */
    
    public static final int HANDLE_INFULL_ORDER = 0x32;

    /**
     * 获取玩家防沉迷信息
     */
    
    public static final int HANDLE_GET_ADDICTION = 0x40;

    /**
     * 获取玩家防沉迷信息
     */
    
    public static final int HANDLE_MODIFY_ADDICTION = 0x41;

    /**
     * 网站应用接口_绑定邀请码
     */
    
    public static final int WEB_BIND_INVITE = 0x42;


    /**
     * 网站应用接口_绑定推广关系
     */
    
    public static final int WEB_BIND_SPREAD = 0x45;

    /**
     * 发放奖励(延迟红包,即时房卡)
     */
    
    public static final int HANDLE_DELAY_AWARD = 0x46;

    /**
     * 游戏异步获取数据接口
     */
    
    public static final int HANDLE_GAME_ASYNC = 0x47;

    /**
     * 推广新手礼包信息
     */
    
    public static final int HANDLE_NOVICE_SPREAD_INFO = 0x48;

    /**
     * 领取推广新手礼包奖励
     */
    
    public static final int HANDLE_NOVICE_SPREAD_AWARD = 0x49;

    /**
     * 大厅配置
     */
    
    public static final int HANDLE_LOBBY_CONF = 0x50;

    /**
     * 登录手机号
     */
    
    public static final int HANDLE_LOGIN_MOBILE_CHECK = 0x51;

    /**
     * 手机登录发送短信()
     */
    
    public static final int HANDLE_LOGIN_SMS = 0x52;

    /**
     * 手机登录
     */
    
    public static final int HANDLE_MOBILE_LOGIN = 0x53;

    /**
     * 新手活动信息
     */
    
    public static final int HANDLE_NOVICE_ACT_INFO = 0x54;

    /**
     * 领取新手活动奖励
     */
    
    public static final int HANDLE_NOVICE_ACT_AWARD = 0x55;

    /**
     * 代理服务器信息
     */
    
    public static final int HANDLE_PROXY_SERVER = 0x56;


    /**
     * 新增回放接口
     */
    
    public static final int HANDLE_NEW_GAME_REPLAY = 0x59;


    /**
     * 代理状态
     */
    
    public static final int HANDLE_AGENT_STATE = 0x66;

    /**
     * 奖品信息
     */
    
    public static final int HANDLE_AWARD_INFO = 0x67;

    /**
     * 道具信息
     */
    
    public static final int HANDLE_PROP_INFO = 0x68;

    /**
     * 用户道具信息
     */
    
    public static final int HANDLE_USER_PROP_INFO = 0x69;

    /**
     * 查询社团信息
     */
    
    public static final int HANDLE_SEARCH_LEAGUE = 0x6A;

    /**
     * 社团申请记录
     */
    
    public static final int HANDLE_LEAGUE_INVITE_MESSAGE = 0x6B;

    /**
     * 社团操作记录
     */
    
    public static final int HANDLE_LEAGUE_OPERATE = 0x6C;

    /**
     * 退出社团
     */
    
    public static final int HANDLE_LEAGUE_QUIT = 0x6D;

    /**
     * 我的社团查询
     */
    
    public static final int HANDLE_LEAGUE_QUERY = 0x6E;

    /**
     * 用户比赛信息
     */
    
    public static final int HANDLE_USER_COMPETE_INFO = 0x70;

    /**
     * 用户比赛服务器信息
     */
    
    public static final int HANDLE_USER_WR_COMPETE_INFO = 0x71;

    /**
     * 比赛附加信息
     */
    
    public static final int HANDLE_EX_COMPETE_MESSAGE = 0x72;

    /**
     * 用户建议
     */
    
    public static final int HANDLE_USER_SUGGEST = 0x73;

    /**
     * 用户账号密码登录
     * 通行证登录
     */
    
    public static final int HANDLE_ACCOUNT_LOGIN = 0x74;

    /**
     * 哈灵账号注册
     */
    
    public static final int HANDLE_ACCOUNT_REG = 0x75;

    /**
     * 身份验证
     */
    
    public static final int HANDLE_IDENTITY_IMPROVE = 0x76;

    /**
     * 绑定已有哈灵账号
     */
    
    public static final int HANDLE_ACCOUNT_UPDATE = 0x77;

    /**
     * 绑定新建哈灵账号
     */
    
    public static final int HANDLE_BIND_NEW_ACCOUNT = 0x78;

    /**
     * 哈灵账号账号微信授权
     */
    
    public static final int HANDLE_ACCOUNT_WX_AUTH = 0x79;

    /**
     * 哈灵账号账号完善
     */
    
    public static final int HANDLE_ACCOUNT_COMPLETE = 0x7A;

    /**
     * 哈灵账号短信发送
     */
    
    public static final int HANDLE_PASSPORT_MESSAGE = 0x7B;

    /**
     * 手机号快速登陆
     */
    
    public static final int HANDLE_MOBILE_MAC_LOGIN = 0x7C;

    /**
     * 发放账号绑定奖励
     */
    
    public static final int HANDLE_BIND_AWARD = 0x7D;

    /**
     * 社团信息列表查询
     */
    
    public static final int HANDLE_LEAGUE_MESSAGE_QUERY = 0x80;

    /**
     * 用户社团邀请和申请记录
     */
    
    public static final int HANDLE_LEAGUE_NEW_MESSAGE = 0x81;

    /**
     * 玩家申请/被邀请记录
     */
    
    public static final int HANDLE_LEAGUE_USER_INVITE_MESSAGE = 0x82;

    /**
     * 创建社团
     */
    
    public static final int HANDLE_LEAGUE_CREATE = 0x83;

    /**
     * 社团房间列表
     */
    
    public static final int HANDLE_LEAGUE_ROOM = 0x84;

    /**
     * 社团基本信息
     */
    
    public static final int HANDLE_LEAGUE_BASE_MESSAGE = 0x85;

    /**
     * 查询社团战绩
     */
    
    public static final int HANDLE_LEAGUE_GAME_LOG = 0x86;

    /**
     * 查看社团战绩
     */
    
    public static final int HANDLE_LEAGUE_CHECK_GAME_LOG = 0x87;

    /**
     * 社团排行
     */
    
    public static final int HANDLE_LEAGUE_USER_GAME = 0x88;

    /**
     * 社团成员信息
     */
    
    public static final int HANDLE_LEAGUE_MEMBER_MESSAGE = 0x89;

    /**
     * 创建者操作社员
     */
    
    public static final int HANDLE_LEAGUE_OPERATE_MEMBER = 0x8A;

    /**
     * 社团用户备注
     */
    
    public static final int HANDLE_LEAGUE_MEMBER_REMARK = 0x8B;

    /**
     * 社团玩法配置
     */
    
    public static final int HANDLE_LEAGUE_PLAY_RULE = 0x8C;

    /**
     * 社团查询用户
     */
    
    public static final int HANDLE_LEAGUE_SEARCH_USER = 0x8D;

    /**
     * 社团申请记录
     */
    
    public static final int HANDLE_LEAGUE_APPLY_MESSAGE = 0x8E;

    /**
     * 获取社团动态
     */
    
    public static final int HANDLE_LEAGUE_OPERATE_LOG = 0x8F;

    /**
     * 社团补充房卡
     */
    
    public static final int HANDLE_LEAGUE_CHARGE_CARD = 0x90;

    /**
     * 获取创建者头像
     */
    
    public static final int HANDLE_LEAGUE_GET_IMGURL = 0x91;

    /**
     * 切换社团
     */
    
    public static final int HANDLE_LEAGUE_CHANGE = 0x92;

    /**
     * 修改社团昵称和描述
     */
    
    public static final int HANDLE_LEAGUE_INFO_CHANGE = 0x93;

    /**
     * 社团玩法删除
     */
    
    public static final int HANDLE_LEAGUE_DELETE_GAME_CONFIG = 0x94;

    /**
     * 解散房间
     */
    
    public static final int HANDLE_LEAGUE_DISSLOVE_ROOM = 0x95;

    /**
     * 社团配置
     */
    
    public static final int HANDLE_LEAGUE_SYS_CONFIG = 0x96;

    /**
     * 社团任免管理员
     */
    
    public static final int HANDLE_LEAGUE_ADMIN_OPERATE = 0x97;

    /**
     * 社团成员信息(新)
     */
    
    public static final int HANDLE_LEAGUE_NEW_MEMBER_MESSAGE = 0x98;

    /**
     * IOS注册新用户
     */
    
    public static final int REG_USER = 0xa1;

    /**
     * 获取邮件列表
     */
    
    public static final int HANDLE_MAIL_LIST = 0xa2;

    /**
     * 获取邮件详情
     */
    
    public static final int HANDLE_MAIL_INFO = 0xa3;

    /**
     * 领取邮件附件
     */
    
    public static final int HANDLE_MAIL_RECEIVE = 0xa4;

    /**
     * 删除邮件
     */
    
    public static final int HANDLE_MAIL_DELETE = 0xa5;

    /**
     * 新版用户建议
     */
    
    public static final int HANDLE_NEW_USER_SUGGEST = 0xa6;

    /**
     * 用户建议列表
     */
    
    public static final int HANDLE_SUGGEST_LIST = 0xa7;

    /**
     * 用户建议阅读
     */
    
    public static final int HANDLE_SUGGEST_READ = 0xa8;

    /**
     * MCI用户注册
     */
    public static final int HANDLE_MCI_REG = 0xa9;

    /**
     * 玩家总游戏时间
     */
    public static final int HANDLE_USER_ALL_GAME_TIME = 0xaa;

    /**
     * 玩家行为分析
     */
    
    public static final int BEHAVIOR_LOG = 0xf1;

    /**
     * 内嵌页跳转
     */
    
    public static final int HANDLE_REDIRECT = 0xff;

    
    public static final int HANDLE_RAID_LOGIN = 0x901;
    
    public static final int HANDLE_RAID_NUM = 0x902;
    
    public static final int HANDLE_RAID_GET_USER_LUCKY_NUM_AWARD = 0x903;
    
    public static final int HANDLE_RAID_SET_USER_LUCKY_NUM = 0x904;
    
    public static final int HANDLE_RAID_GET_USER_ORDERS = 0x905;
    // 领取奖励
    
    public static final int HANDLE_RAID_RECEIVE_AWARD = 0x906;
    // 获取用户参与的所有期数
    
    public static final int HANDLE_RAID_GET_USER_PERIODS = 0x907;
    // 获取用户所有可领奖的记录
    
    public static final int HANDLE_RAID_GET_USER_WIN_ORDERS = 0x908;
    // 获取历史中奖信息
    
    public static final int HANDLE_RAID_GET_RAID_WINNERS = 0x909;
    // 下单
    
    public static final int HANDLE_RAID_REQUEST_ORDER = 0x910;
    // 获取用户某一期下单的单数
    
    public static final int HANDLE_RAID_GET_USER_ORDERS_NUM = 0x911;
    
    public static final int HANDLE_RAID_SHARE_AWARD = 0x912;
    // 获取所有需要展示的期数信息
    
    public static final int HANDLE_RAID_GET_ALL_RAID_INFO = 0x913;

    // 获取单个期数信息
    
    public static final int HANDLE_RAID_GET_RAID_INFO = 0x914;

    
    public static final int HANDLE_RAID_INFOS_TOTAL_NUM = 0x915;

    /**
     * boke哈灵麻将重新授权
     */
    
    public static final int HANDLE_RE_AUTH = 0x92E;

    /**
     * 公告接口
     */
    
    public static final int HANDLE_ACTIVITY_NOTICE = 0xff01;

    // 三要素验证
    
    public static final int HANDLE_THREE_ELEMENTS = 0xff02;

    // 三要素验证-获取验证码
    
    public static final int HANDLE_THREE_ELEMENTS_CODE = 0xff03;


}
