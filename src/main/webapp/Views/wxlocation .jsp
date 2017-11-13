<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script  type="text/javascript"  src="/scripts/wxsq.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script>
        //直接引入js文件
        shopwxsq();
    </script>
</head>
<body>
<button id="getBBS" style="width:1000px;height:600px;font-size:150px;" onclick="submitOrderInfoClick();">获取地理位置</button>
</body>
<script type="text/javascript">
    function submitOrderInfoClick(){
        wx.getLocation({
            success: function (res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var speed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度
                alert("小宝鸽获取地理位置成功，经纬度为：（" + latitude + "，" + longitude + "）" );
            },
            fail: function(error) {
                alert("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
            }
        });
    }

    //-------------------------------------------------------------------------------------------------扫一扫功能函数，扫出的结果放到该形参中
    function wx_sys(label) {
        wx.scanQRCode({
            needResult: 1, //-------------------------默认为0，扫描结果由微信处理，1则是自己处理结果
            desc: 'scanQRCode desc',
            success: function (res) {
                console.log(res);
                var url = res.resultStr;
                if (url.indexOf(",") >= 0) {
                    var tempArray = url.split(',');
                    var tempNum = tempArray[1];
                    label.val(tempNum);
                    car_tsk.hide();
                    password_tsk.hide();
                } else {
                    label.val(url);
                }
            }
        });
    }

    wx.onMenuShareTimeline({
        title: '', // 分享标题
        link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '', // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });


</script>
</html>















<%--<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
    <%--<meta name="viewport"--%>
          <%--content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>--%>
    <%--<title>滑动到底部加载下一页内容</title>--%>
    <%--<link href="/styles/pullToRefresh.css" rel="stylesheet" type="text/css"/>--%>
    <%--<script src="/scripts/login/js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>--%>
    <%--&lt;%&ndash;<script src="/scripts/zepto.min.js" type="text/javascript" charset="utf-8"></script>&ndash;%&gt;--%>
    <%--<script type="text/javascript" src="/scripts/iscroll.js"></script>--%>
    <%--<script type="text/javascript" src="/scripts/pullToRefresh.js"></script>--%>
    <%--<script type="text/javascript" src="/scripts/main.js"></script>--%>
    <%--<style>--%>
        <%--*{--%>
            <%--margin: 0;--%>
            <%--padding:0;--%>
            <%---webkit-tap-highlight-color:rgba(0,0,0,0);--%>
            <%---webkit-text-size-adjust:none;--%>
        <%--}--%>
        <%--html{--%>
            <%--font-size:10px;--%>
        <%--}--%>
        <%--body{--%>
            <%--background-color: #f5f5f5;--%>
            <%--font-size: 1.2em;--%>
        <%--}--%>
        <%--table {--%>
            <%--width: 100%;--%>
            <%--padding: 0 15px;--%>
            <%--background: #fff;--%>
            <%--border-collapse: collapse;--%>
        <%--}--%>

        <%--table td {--%>
            <%--padding: 6px 0;--%>
            <%--width: 33%;--%>
            <%--height: 200px;--%>
            <%--border-bottom: 1px solid #e1e1e1;--%>
        <%--}--%>

        <%--tr td:nth-child(2) {--%>
            <%--text-align: center;--%>
        <%--}--%>

        <%--tr td img {--%>
            <%--width: 24px;--%>
            <%--vertical-align: middle;--%>
        <%--}--%>

        <%--tr td:last-child {--%>
            <%--text-align: right;--%>
        <%--}--%>

        <%--td > div:first-child {--%>
            <%--/*margin-bottom: -6px;*/--%>
        <%--}--%>

        <%--td > div:last-child {--%>
            <%--color: #9C9C9C;--%>
        <%--}--%>
    <%--</style>--%>
<%--</head>--%>


<%--<body>--%>

<%--<input type="hidden" name="pageNo" id="pageNo" value="1"/>--%>
<%--<div id="wrapper">--%>
    <%--<table class="white">--%>

    <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--<script>--%>
    <%--var page = 1; //记录当前加载的页数--%>
    <%--var add_num = 0;//记录加载的次数--%>
    <%--$.ajax({--%>
        <%--type: 'POST',--%>
        <%--url: "/pagingquery",--%>
        <%--data: {--%>
            <%--businessType: "xialajiazai",--%>
            <%--page: "1",--%>
            <%--rows: "3"--%>
        <%--},--%>
        <%--async: false,--%>
        <%--success: function (json) {--%>
            <%--var data = JSON.parse(json);--%>
            <%--if(data.records % data.pageSize == 0){--%>
                <%--add_num = data.records/data.pageSize;--%>
            <%--}else{--%>
                <%--add_num = data.records/data.pageSize + 1;--%>
            <%--}--%>
            <%--console.log(data.rows);--%>
            <%--if (data != null && data.rows != null) {--%>
                <%--var content = "";--%>
                <%--for (var i = 0; i < data.rows.length; i++) {--%>
                    <%--content = content--%>
                        <%--+ '<tr>'--%>
                        <%--+ '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'--%>
                        <%--+ '</tr>';--%>
                <%--}--%>
                <%--$(".white").append(content);--%>
            <%--}--%>
        <%--},--%>
        <%--error: function () {--%>
            <%--alert("查询数据出错啦，请刷新再试");--%>
        <%--}--%>
    <%--});--%>
    <%--refresher.init({--%>
        <%--id:"wrapper",--%>
        <%--able:".white",--%>
        <%--pullDownAction:Refresh,--%>
        <%--pullUpAction:Load--%>
    <%--});--%>
    <%--function Refresh() {--%>
        <%--$.ajax({--%>
            <%--type: 'POST',--%>
            <%--url: "/pagingquery",--%>
            <%--data: {--%>
                <%--businessType: "xialajiazai",--%>
                <%--page: "1",--%>
                <%--rows: "3"--%>
            <%--},--%>
            <%--async: false,--%>
            <%--success: function (json) {--%>
                <%--page = 1;--%>
                <%--var data = JSON.parse(json);--%>
                <%--console.log(data.rows);--%>
                <%--if (data != null && data.rows != null) {--%>
                    <%--document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";--%>
                    <%--document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";--%>
                    <%--var content = "";--%>
                    <%--for (var i = 0; i < data.rows.length; i++) {--%>
                        <%--content = content--%>
                            <%--+ '<tr>'--%>
                            <%--+ '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'--%>
                            <%--+ '</tr>';--%>
                    <%--}--%>
                    <%--$(".white").children().remove();--%>
                    <%--$(".white").append(content);--%>
                    <%--wrapper.refresh();--%>
                    <%--document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";--%>
                    <%--$(".pullUpIcon").show();--%>
                    <%--refresher.info.loadingLable = "加载中...";--%>
                    <%--refresher.info.pullUpLable = "上拉加载更多"--%>
                    <%--refresher.info.pullingUpLable = "释放加载更多"--%>
                <%--}--%>
            <%--},--%>
            <%--error: function () {--%>
                <%--alert("查询数据出错啦，请刷新再试");--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
    <%--function Load() {--%>
        <%--page++;--%>
        <%--if(page <= add_num){--%>
            <%--$.ajax({--%>
                <%--type: 'POST',--%>
                <%--url: "/pagingquery",--%>
                <%--data: {--%>
                    <%--businessType: "xialajiazai",--%>
                    <%--page: ""+page+"",--%>
                    <%--rows: "3"--%>
                <%--},--%>
                <%--async: false,--%>
                <%--success: function (json) {--%>
                    <%--var data = JSON.parse(json);--%>
                    <%--console.log(data.rows);--%>
                    <%--if (data != null && data.rows != null) {--%>
                        <%--var content = "";--%>
                        <%--for (var i = 0; i < data.rows.length; i++) {--%>
                            <%--content = content--%>
                                <%--+ '<tr>'--%>
                                <%--+ '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'--%>
                                <%--+ '</tr>';--%>
                        <%--}--%>
                        <%--$(".white").append(content);--%>
                        <%--page_num(add_num)//必须添加--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function () {--%>
                    <%--alert("查询数据出错啦，请刷新再试");--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
        <%--wrapper.refresh();--%>
    <%--}--%>
    <%--function page_num(add_num) {--%>
        <%--if(page == add_num){--%>
            <%--$(".pullUpIcon").hide();--%>
            <%--$(".pullUpLabel").text("已经到了最底部了！");--%>
            <%--refresher.info.loadingLable = "已经到了最底部了!";--%>
            <%--refresher.info.pullUpLable = "已经到了最底部了!"--%>
            <%--refresher.info.pullingUpLable = "已经到了最底部了!"--%>
        <%--}--%>
    <%--}--%>
    <%----%>
<%--</script>--%>
<%--</html>--%>