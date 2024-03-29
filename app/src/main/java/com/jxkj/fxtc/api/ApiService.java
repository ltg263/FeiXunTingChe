package com.jxkj.fxtc.api;


import com.jxkj.fxtc.base.LoginBean;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.entity.AddChangeList;
import com.jxkj.fxtc.entity.AppointmentBean;
import com.jxkj.fxtc.entity.CarRecordListBean;
import com.jxkj.fxtc.entity.DefaultCarBean;
import com.jxkj.fxtc.entity.HomeBean;
import com.jxkj.fxtc.entity.InvoiceListBean;
import com.jxkj.fxtc.entity.ListApkInfo;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.entity.MessageListBean;
import com.jxkj.fxtc.entity.MessageListBeanDe;
import com.jxkj.fxtc.entity.OrdersDetailBean;
import com.jxkj.fxtc.entity.OrdersListBean;
import com.jxkj.fxtc.entity.PostCarData;
import com.jxkj.fxtc.entity.SeatParkbudBean;
import com.jxkj.fxtc.entity.UserBillListBean;
import com.jxkj.fxtc.entity.UserCarListBean;
import com.jxkj.fxtc.entity.UserDetailBean;
import com.jxkj.fxtc.entity.UserEnvelopesBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 获取token
     *
     * @return
     */
    @GET("api/v1/user/getSecuritytoken")
    Observable<Result> getSecuritytoken();


    /**
     * 获取短信验证码
     * @return type:类型0注册1修改密码2登录
     */
    @POST("api/v1/user/verify/sendMobileCode")
    Observable<Result> getVerifyCode(@Query("mobile") String mobile);


    /**
     *  修改默认车辆
     * @return type:类型0注册1修改密码2登录
     */
    @POST("api/v1/user/car/defaultCar")
    Observable<Result> postDefaultCar(@Query("id") String id);


    /**
     * 手机登录
     */
    @POST("api/v1/user/verify/login")
    Observable<Result<LoginBean>> postLogin(@Query("mobile") String mobile,
                                            @Query("verifyCode") String verifyCode,
                                            @Query("mobileType") String mobileType);


    /**
     * 获取首页
     */
    @GET("api/v1/home/getHome")
    Observable<Result<HomeBean>> getHome();

    /**
     * 安卓版本更新检查
     */
    @GET("api/v1/apk/list")
    Observable<Result<ListApkInfo>> getVersionUpdating();

    /**
     * 修改用户个人信息
     * @return
     */
    @POST("api/v1/user/update")
    Observable<Result> getUserUpdate(@Query("avatar") String avatar,@Query("birthday") String birthday,
                                         @Query("sex") String sex,@Query("nickName") String nickName);

    /**
     * 用户充值
     * @return
     */
    @POST("api/v1/user/amount/payAmount")
    Observable<Result> getUserPayAmount(@Body PostCarData.PayAmount payAmount);

    /**
     * 用户反馈信息
     * @return
     */
    @POST("api/v1/user/feedback/add")
    Observable<Result> postFeedback(@Body PostCarData.Feedback payAmount);

    /**
     * 用户个人中心
     */
    @GET("api/v1/user/getDetail")
    Observable<Result<UserDetailBean>> getUserDetail();

    /**
     * 获取用户默认车辆
     */
    @GET("api/v1/user/car/getDefaultCar")
    Observable<Result<DefaultCarBean>> getDefaultCar();

    /**
     * 用户消息列表
     */
    @GET("api/v1/user/message/list")
    Observable<Result<MessageListBean>> getMessageList(@Query("messageType") int messageType);

    /**
     * 用户消息列表
     */
    @GET("api/v1/user/message/read")
    Observable<Result> getMessageRead(@Query("id") String id);

    /**
     * 消息详情
     */
    @GET("api/v1/user/message/detail")
    Observable<Result<MessageListBeanDe>> getMessageDeList(@Query("id") String id);

    /**
     * 订单详情
     */
    @GET("api/v1/user/orders/detail")
    Observable<Result<OrdersDetailBean>> getOrdersDetail(@Query("id") String id);

    /**
     * 用户优惠券列表
     */
    @GET("api/v1/user/userEnvelopes/query")
    Observable<Result<UserEnvelopesBean>> getUserEnvelopes(@Query("status") int status);

    /**
     * 用户发票列表
     */
    @GET("api/v1/user/invoice/list")
    Observable<Result<InvoiceListBean>> getInvoiceList(@Query("status") String status, @Query("examine") String examine, @Query("type") String type);


    /**
     * 停车场列表
     */
    @GET("api/v1/lot/list")
    Observable<Result<LotListBean>> getLotList(@Query("regionID") String regionID,@Query("parkingName") String parkingName,
                                               @Query("lng") String lng,@Query("lat") String lat,@Query("type") String type);


    /**
     * 用户添加车牌
     */
    @POST("api/v1/user/car/add")
    Observable<Result> addCar(@Body PostCarData.PostAddCarInfo addCarInfo);

    /**
     *  预约车位
     */
    @POST("api/v1/user/seat/appointment")
    Observable<Result<AppointmentBean>> postAppointment(@Body PostCarData.AppointmentInfo addCarInfo);

    /**
     *  取消预约车位
     */
    @POST("api/v1/user/seat/overAppointment")
    Observable<Result> postCancelAppointment(@Query("orderNo") String orderNo);

    /**
     * 用户所有车牌
     */
    @GET("api/v1/user/car/list")
    Observable<Result<UserCarListBean>> getAllCar();

    /**
     * 用户账单列表
     * 停车1充值2,预约3
     */
    @GET("api/v1/user/bill/list")
    Observable<Result<UserBillListBean>> getBillList(@Query("billType") String billType);

    /**
     * 用户停车记录表
     */
    @GET("api/v1/user/carRecord/list")
    Observable<Result<CarRecordListBean>> getCarRecordList(@Query("lotID") String lotID);
    /**
     * 用户停车记录表
     */
    @GET("api/v1/user/orders/list")
    Observable<Result<OrdersListBean>> getOrdersList();

    /**
     * 停车导航
     */
    @GET("api/v1/user/seat/parkbud")
    Observable<Result<SeatParkbudBean>> getParkbud(@Query("license") String license);

    /**
     * 忘记密码
     */
    @POST("api/v1/user/verify/forgetPswd")
    Observable<Result> verifyForgetPswd(@Query("mobile") String mobile,
                                            @Query("loginPswd") String loginPswd,
                                        @Query("mobileCode") String mobileCode);
    /**
     *  支付订单
     */
    @POST("api/v1/user/orders/payOrders")
    Observable<Result> postPayOrders(@Body PostCarData.PayOrdersBaen payOrdersBaen);

    /**
     *  申请发票
     */
    @POST("api/v1/user/invoice/add")
    Observable<Result> postInvoice(@Body PostCarData.InvoiceBean mInvoiceBean);

    /**
     * 忘记密码
     */
    @POST("api/v1/user/update")
    Observable<Result> updateUserInfo(@Query("avatar") String avatar,
                                            @Query("sex") String sex,
                                            @Query("age") String age,
                                            @Query("weight") String weight,
                                        @Query("height") String mobileCode);




    /**
     * 添加设备记录列表
     */
    @POST("api/v1/user/device/addChangeList")
    Observable<Result> addChangeList(@Body AddChangeList addOrderData);


    /**
     *
     * 用户详情
     */
    @POST("api/v1/user/updateDefaultShow")
    Observable<Result> updateDefaultShow(@Query("defaultShow") String defaultShow);


    /**
     * 上传文件
     *
     * @return
     */
    @Multipart
    @POST("api/v1/files")
    Observable<Result> submitFiles(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

}
