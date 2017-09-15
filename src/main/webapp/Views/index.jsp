<%@ page language="java" pageEncoding="utf-8" %>


<html>
<head>


<meta content="yes" name="apple-mobile-web-app-capable"/>
<meta content="black" name="apple-mobile-web-app-status-bar-style"/>
<meta content="telephone=no" name="format-detection"/>
<title>嘟嘟车网</title>
<meta name="keywords" content="keyword ..."/>
<meta name="Description" content="description ..."/>
<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
<script language="JavaScript">


    //百度地图
    function baiduMap() {
        window.location.href = "/baiduMap";
    }

    //微信内部页面跳转
    function testjump() {
        window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd4e76e01e4a6e3b7&redirect_uri=http://lm.wx.dev.duduchewang.cn/testjump?shopcode=0533001&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    }

    //微信授权方法请求
    function weixin() {
        window.location.href = "/goAuthor" ;
    }

    //验证appsecret
    function getToken() {
        var appid = $(".appid").val();
        var appsecret = $(".appsecret").val();
        $.ajax({
            type    : 'GET',
            url     : '/getToken?appid='+appid+'&appsecret='+appsecret ,
            success:function(jsondata){
                var json = JSON.parse(jsondata);
                alert(jsondata);
            },
            error:function(eee){
                alert("我有点懵,您稍后再试!");
            }
        });
    }


        //创建菜单
        function creatMenu() {
            var regbtns = $(".regbtn");
            var a = '';
            for (var i = 0; i < regbtns.length; i++) {
                a += '/' + $(regbtns[i]).val()
            }
            console.log(a);
            $.ajax({
                type    : 'GET',
                url     : '/createMenu' + a,

                success:function(jsondata){
                    var json = JSON.parse(jsondata);
                    alert(jsondata);
                },
                error:function(eee){
                    alert("我有点懵,您稍后再试!");
                }
            });
//        window.location.href = "/createMenu" + a;
        }




</script>
</head>
<body>

<form action="http://exampleurl/" method="post" enctype="multipart/form-data">
    <input type="text" name="usrId" value="usrId" id="usrId"/>
    <input type="file" name="file" value="file"/>
    <input name="submit" type="submit" id="submit"/>
</form>

<form name="form1" method="post" action="checkAction.jsp">
    <div class="regform">
        <ul>
            <li class="btnli">店铺编码:<input name="type" class="regbtn" value=""/></li>
            <li class="btnli">菜单类型:
                <select name="code" class="regbtn">
                    <option value="shop">店管家微信</option>
                    <option value="lianmeng">联盟微信</option>
                    <option value="yilubang">易路邦</option>
                    <option value="chuangke">创客</option>
                    <option value="oldShop">老版店管家</option>
                </select>
            </li>
            <li class="btnli">appid:<input name="appid" class="regbtn" value=""/></li>
            <li class="btnli">appsecret:<input name="appSecret" class="regbtn" value=""/></li>
            <li class="btnli">服务器地址:
                <select name="code" class="regbtn">
                    <option value="6t2gqvu.hk1.mofasuidao.">本地测试</option>
                    <option value="lm.wx.dev.duduchewang.">测试环境</option>
                    <option value="wx.pre.duduchewang.">预生产环境</option>
                    <option value="wx.pdu.duduchewang.">正式环境</option>
                    <option value="shop">随机环境</option>
                </select>
            </li>
            <li class="btnli">服务器域名:
                <select name="yuming" class="regbtn">
                    <option value="cn">cn</option>
                    <option value="com">com</option>
                    <option value="cc">cc</option>
                </select>

            </li>
            <li class="btnli"><input type="button" class="regbtns" value="创建菜单" onclick="creatMenu()"/></li>

            <li class="btnli">appid:<input name="type" class="appid" value=""/></li>
            <li class="btnli">appsecret:<input name="type" class="appsecret" value=""/></li>
            <li class="btnli"><input type="button" class="regbtns" value="验证appsecret" onclick="getToken()"/></li>
        </ul>
        <input type="button"  value="微信授权页面" onclick="weixin()"/>
        <input type="button"  value="微信内部页面跳转获取授权code" onclick="testjump()"/>
        <input type="button"  value="百度地图" onclick="baiduMap()"/>

    </div>
</form>
</body>
</html>

