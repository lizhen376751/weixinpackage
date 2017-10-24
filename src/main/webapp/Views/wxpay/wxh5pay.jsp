<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="static/css/bootstrapzhifubao.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/commonzhifubao.css">
    <script type="text/javascript" src="static/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrap.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script type="text/javascript">

//        lmwxsq();
//        function lmwxsq() {
//            $.ajax({
//                url: "index.jsp?m=wxpay&c=WxPay&a=jsSDKSign",
//                type: 'POST',
//                data: {
//                    url: location.href.split('#')[0]
//                },
//                success: function (data1) {
//                    console.log("请求数据====data1"+data1);
//                    var data = eval('(' + data1 + ')');
//                    console.log("请求数据====data"+data.appId+","+data.timestamp+","+data.nonceStr+","+data.signature);
//                    wx.config({
//                        debug: false,//true为开启调试模式，数据会alert出来，false为关闭调试模式
//                        appId: data.appId, //公众号的唯一标示，必填
//                        timestamp: data.timestamp, //生成签名的时间戳，必填
//                        nonceStr: data.nonceStr,   //生成签名的随机串，必填
//                        signature: data.signature,  // 签名，必填
//                        jsApiList: ['checkJsApi', 'getNetworkType', 'openLocation', 'getLocation', 'scanQRCode', 'onMenuShareAppMessage', 'hideMenuItems', 'chooseWXPay']    //--------------------------------- // 所有要调用的 API 都要加到这个列表中
//                    });
//                }
//            });
//        }
//        wxpay();
//        function wxpay() {
//            alert("进入方法支付=======");
//            wx.chooseWXPay({
//                timestamp:  '${timeStamp}', // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
//                nonceStr: '${nonceStr}', // 支付签名随机串，不长于 32 位
//                package:'${prepay_id}', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
//                signType: "MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
//                paySign:  '${paySign}', // 支付签名
//                success: function (res) {
//                    // 支付成功后的回调函数
//                    alert(res);
//                    alert("支付成功");
//                },
//                error :function (res) {
//                    alert("支付失败,失败原因:"+res.errMsg)
//                }
//
//            });
//        }


        var out_trade_no = '${out_trade_no}';
        if (typeof WeixinJSBridge == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else{
            onBridgeReady();
        }
        //TODO 域名需要修改
        var common_url = '${common_url}';

        function onBridgeReady() {
            WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId": '${appId}',     //公众号名称，由商户传入
                    "timeStamp": '${timeStamp}',         //时间戳，自1970年以来的秒数
                    "nonceStr": '${nonceStr}', //随机串
                    "package": '${prepay_id}',
                    "signType": "MD5",         //微信签名方式:
                    "paySign": '${paySign}'    //微信签名
                },
                function (res) {
                    console.log(res+"!!!!!"+res.errMsg);
                    if (res.err_msg == "get_brand_wcpay_request:ok") {
//                        alert("支付成功");
                        window.location.href=common_url+"/app.ZMTManage/index.jsp?m=wxpay&c=WxPay&a=wxPaySuccess&out_trade_no="+out_trade_no;
                    }else {
//                        alert(common_url+"支付失败,失败原因:"+res.errMsg);
                        window.location.href=common_url+"/app.ZMTManage/index.jsp?m=wxpay&c=WxPay&a=wxPaySuccess";

                    }
                }
            );
        }


    </script>

</head>

</html>


