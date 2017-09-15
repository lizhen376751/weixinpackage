/**
 * Created by Administrator on 2017/5/24 0024.
 */
//微信授权方法
//arr 必须为数组，类似 ['scanQRCode']
//所有要调用的 API 都要加到这个列表:
// 'checkJsApi',基础接口,判断当前客户端版本是否支持指定JS接口
//'chooseImage',一次选择一张或多张图片，一般和uploadImage一起用
//'previewImage',是微信客户端给内置浏览器增加的一个Javascript Interface，通过调用这个API，可以调起微信客户端提供的大图片查看组件。
//'uploadImage'，微信上传图片
//'downloadImage',下载图片
// 'getNetworkType',//网络状态接口
// 'openLocation',//使用微信内置地图查看地理位置接口
//'getLocation' //获取地理位置接口
// 'scanQRCode',扫码接口，包括二维码和条形码



function lmwxsq() {
    $.ajax({
        url: "/getCommonAjax2",
        type: 'POST',
        data: {
            url: location.href.split('#')[0],
            fromflag: "jssdk"
        },
        success: function (data) {
            console.log(data);
            wx.config({
                debug: false,//true为开启调试模式，数据会alert出来，false为关闭调试模式
                appId: data.appId, //公众号的唯一标示，必填
                timestamp: data.timestamp, //生成签名的时间戳，必填
                nonceStr: data.nonceStr,   //生成签名的随机串，必填
                signature: data.signature,  // 签名，必填
                jsApiList: ['checkJsApi', 'getNetworkType', 'openLocation', 'getLocation', 'scanQRCode','onMenuShareAppMessage','hideMenuItems']    //--------------------------------- // 所有要调用的 API 都要加到这个列表中
            });
        },
        dataType: 'json',
        async: false
    });
}
    function shopwxsq() {
        $.ajax({
            url: "/shopAjax",
            type: 'POST',
            data: {
                url: location.href.split('#')[0],
                businessType: "jssdk"
            },
            success: function (data) {
                console.log(data);
                wx.config({
                    debug: false,//true为开启调试模式，数据会alert出来，false为关闭调试模式
                    appId: data.appId, //公众号的唯一标示，必填
                    timestamp: data.timestamp, //生成签名的时间戳，必填
                    nonceStr: data.nonceStr,   //生成签名的随机串，必填
                    signature: data.signature,  // 签名，必填
                    jsApiList: ['checkJsApi','getNetworkType','openLocation','getLocation','scanQRCode','onMenuShareAppMessage','hideMenuItems']    //--------------------------------- // 所有要调用的 API 都要加到这个列表中
                });
            },
            dataType: 'json',
            async: false
        });
}