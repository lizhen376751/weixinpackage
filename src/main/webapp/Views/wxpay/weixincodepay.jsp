<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="static/css/bootstrapzhifubao.min.css">
	<link rel="stylesheet" type="text/css" href="static/css/commonzhifubao.css">
	<script type="text/javascript" src="static/js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script>
		var payName ;
        IsWeixinOrAlipay();
        function IsWeixinOrAlipay(){
            var ua = window.navigator.userAgent.toLowerCase();
            //判断是不是微信
            if ( ua.match(/MicroMessenger/i) == 'micromessenger' ) {
                payName = "weixin";
                return "weixin";
            }
            //判断是不是支付宝
            if (ua.match(/AlipayClient/i) == 'alipayclient') {
                payName = "zhifubao";
                return "zhifubao";
            }
            //哪个都不是
            return "false";
        }

        var SellerId,total_fee;
        //TODO 更换域名
        var common_url = '${common_url}';
        function checkForm(){
            total_fee = $("#WIDtotal_amount").val();  //支付金额
            SellerId = $("#SellerId").val();  //窗口号
			//对支付金额做处理
            total_fee = changeTwoDecimal_f(total_fee);
            if (payName == "weixin"){
                total_fee=total_fee*100;
                var weixinurl = common_url+"/app.ZMTManage/index.jsp?m=wxpay&c=WxPay&a=weixinpayorder&SellerId="+ SellerId+"&total_fee="+total_fee;
                window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx04c734dc2294899f&redirect_uri="+ encodeURIComponent(weixinurl)+"&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";
            }else if(payName == "zhifubao"){
                $("#alipayment").attr("action", "?m=alipay&c=AliPay&a=zhifubaoPay");
                return true;
			}
			return false;
		}


        function changeTwoDecimal_f(x) {
            var f_x = parseFloat(x);
            if (isNaN(f_x)) {
                alert('请输入数字!!!!');
                return false;
            }
            var f_x = Math.round(x * 100) / 100;
            var s_x = f_x.toString();
            var pos_decimal = s_x.indexOf('.');
            if (pos_decimal < 0) {
                pos_decimal = s_x.length;
                s_x += '.';
            }
            while (s_x.length <= pos_decimal + 2) {
                s_x += '0';
            }
            return s_x;
        }

	</script>
</head>
<body>
<div class="head">
	<div class="head_title">向商户付款</div>
</div>
<form name="alipayment"  id="alipayment" onsubmit="return checkForm();" method="post">
	<div class="main" >
		<div class="store">
			<div class="store_center">
				<img src="static/images/weixinpay/dp.png" class="store_img">
				<div class="store_name">
					<div class="store_name_t">高校饮食在线支付（${sellerName}）</div>
				</div>
			</div>
		</div>
		<div class="money">
			<input required="required" type="text" name="WIDtotal_amount" id="WIDtotal_amount" class="inp" onkeyup="this.value=/^\d+\.?\d{0,2}$/.test(this.value) ? this.value : ''"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
			<div class="money_l">金额</div>
			<div class="money_r">￥</div>
		</div>
		<div class="recently">
			<div class="recently_left">常用付款金额：</div>
			<div class="recently_right">
				<ul class="recently_right_ul">
					<li>
						<span>${Je1}</span>元</li>
					<li>
						<span>${Je2}</span>元</li>
					<li>
						<span>${Je3}</span>元</li>
				</ul>
			</div>
		</div>
		<input type="text" style="display:none" name= "SellerId" id="SellerId" value="${SellerId}"/> <!--窗口号-->
		<div style="margin-top:2rem;text-align:center;">
			<button type="submit"  id="sure" style="font-size:2rem;color:#fff;margin:0 auto;display:inline-block;padding:0.5rem 5rem;border:none;background: #ff5600;border-radius:1rem; ">确 认 支 付</button>
		</div>
	</div>

</form>
<script>

    // 数字键盘
    $(function(){
        $(window).ready(function(){
            $('.inp').val('');
        });
        $('.recently_right_ul>li').click(function(){
            $('.inp').val('');
            var num_click=$(this).children().eq(0).text();
            var write=$('.inp').val();
            $('.inp').val(write+num_click);
        })
    })

    $(function(){
        $('.recently_right_ul>li').click(function(){
            $('.recently_right_ul>li').css('background','none')
            $('.recently_right_ul>li').css('color','#fd8b1d')
            $(this).css('background','#fd8b1d');
            $(this).css('color','#fff');
        })
    })
</script>
</body>
</html>


