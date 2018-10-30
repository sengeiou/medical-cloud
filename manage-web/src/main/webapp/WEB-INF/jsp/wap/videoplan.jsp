<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/wap/global_wap.jsp" %>
<!DOCTYPE html>
<head>
<%@ include file="/commons/wap/basejs_wap.jsp" %>
<link rel="stylesheet" href="${path}/view/wap/css/style.css">
<script language="JavaScript">

var mobilevalid='${mobilevalid}';//0:valid, 1:invalid mobile

$(function() {  
    <c:forEach items="${productlist}" var="mplist" varStatus="vs">
		//alert('${mplist.product.productId}');
		if(${vs.index==0})
		{
			//alert('${mplist.product.productId}');
			$("#selectproductname_3").html("${mplist.product.productName}");
			$("#selectproductdetail_3").html('${mplist.product.productDetail}');
			
			$("#selectproductname_1").html('${mplist.product.productName}');
			$("#selectproductname_2").html('${mplist.product.productName}');
			
			$("#desc_productname").html('${mplist.product.productName}');
			$("#desc_pricemchtname").html('${mplist.product.price}'+'元'+'${mplist.mchtName}');
			$("#desc_productprice").html('${mplist.product.price}'+'元');			

			$("#${mplist.product.productId}").addClass("product_checked");
			$("#inputproductid").val('${mplist.product.productId}');
			$("#mchturl").val('${mplist.mchtUrl}');
			$("#mpid").val('${mplist.id}');
		}
	</c:forEach>

	
	mobilevalid ='${mobilevalid}';
	if(mobilevalid=='0')
	{
		$("#divshowinputmobile").css('display', 'none');
		$("#divshowmobile").css('display','block');
	}
	else
	{
		$("#divshowinputmobile").css('display','block');;
		$("#divshowmobile").css('display','none');
	}
});

function getMobileSmsno()
{
	var mobile = $("#inputmobile").val();
	var reg = /1+[0-9]{10}/;
	if(mobile.length != 11 || reg.test(mobile) == false){
		alert("输入数字非法,确定后重新输入...");
		return false;
	}
	var jsondata = {
		"msgid":"10001003",
		"country_code":"86",
		"mobile": mobile
	};
	
	openWindowTips();

	var url = 'wap/getAuthSmsCode.do';
	invokeService(url, jsondata, function(result) {
		if (result.retcode == 0)
		{
			$("#mobile").val(mobile);
			//alert("请注意查收短信");
		}
		else
		{
			//alert(result.retmsg);
			$("#resultsms").html(result.retmsg);
		}
		closeWindowTips();
	});
}
function checkMobileSmsno()
{
	var mobile = $("#inputmobile").val();
	var reg = /1+[0-9]{10}/;
	if(mobile.length != 11 || reg.test(mobile) == false){
		alert("必须是11位移动手机号码,确定后重新输入...");
		return false;
	}
	
	var password = $("#smsno").val();
	if(password.length<=0)
	{
		alert("请输入短信验证码");
		return;
	}
	var jsondata = {
		"msgid":"10001005",
		"country_code":"86",
		"mobile": mobile,
		"password":password
	};
	openWindowTips();

	var url = 'wap/checkAuthSmsCode.do';
	invokeService(url, jsondata, function(result) {
		if (result.retcode == 0)
		{
			mobilevalid='0';
			$("#divshowinputmobile").css('display','none');
			$("#divshowmobile").css('display','block');
			$("#divshowmobile").html("您的号码为："+mobile);
		}
		else
		{
			//alert(result.retmsg);
			mobilevalid='1';
			$("#resultsms").html(result.retmsg);
			$("#divshowmobile").html("您的号码："+result.retmsg);
		}
		closeWindowTips();
	});
}

/*第一种形式 第二种形式 更换显示样式*/
function clickTab_1(){
	$("#one1").addClass('hover');
	$("#one2").removeClass();;
	$("#con_one_1").css('display','block');
	$("#con_one_2").css('display','none');
}
function clickTab_2(){
	$("#one2").addClass('hover');
	$("#one1").removeClass();
	$("#con_one_2").css('display','block');
	$("#con_one_1").css('display','none');
}
function chooseproduct(pid)
{
	
	$("div[name='divproductname']").each(function(index,item){
		$(item).attr('class', 'product_unchecked');
		//alert("fuck");
	});
	/*
	var elems = document.getElementsByName("divproductname");
	for(i = 0; i < elems.length; i++) {
		elems[i].className='product_unchecked';
	}
	*/		
	$("#"+pid).addClass('product_checked');

	<c:forEach items="${productlist}" var="mplist" varStatus="vs">
		//alert('${mplist.product.productId}');
		if('${mplist.product.productId}' == pid)
		{
			//alert('${mplist.product.productId}');
			$("#selectproductname_3").html('${mplist.product.productName}');
			$("#selectproductdetail_3").html('${mplist.product.productDetail}');
			$("#selectproductname_1").html('${mplist.product.productName}');
			$("#selectproductname_2").html('${mplist.product.productName}');
			
			$("#desc_productname").html('${mplist.product.productName}');
			$("#desc_pricemchtname").html('${mplist.product.price}'+'元'+'${mplist.mchtName}');
			$("#desc_productprice").html('${mplist.product.price}'+'元');

			$("#inputproductid").val('${mplist.product.productId}');
			$("#mchturl").val('${mplist.mchtUrl}');
			$("#mpid").val('${mplist.id}');
		}
	</c:forEach>

	$("#hasproudct_icon").css('display','none');
	$.each(myprodlist, function (n, value) {
		if(pid==myprodlist[n])
		{
			$("#hasproudct_icon").css('display','block');
		}
	});
}

function subscributeProduct()
{
	var mobile = $("#mobile").val();
	if(mobilevalid!='0' && mobile == "")
	{
		alert('获取手机号失败!');
		return;
	}
	if(mobile == "")
	{
		mobile = $("#inputmobile").val();//;
	}
	if(mobile=="")
	{
		alert('请输入手机号');
		return;
	}
	if($("#checkprotocol").prop("checked") != true)
	{
		alert("您必须同意流量包购买协议条款");
		return;
	}
	var pid = $("#inputproductid").val();
	if(pid=="")
	{
		alert('请选择您好订购的流量包产品');
		return;
	}
	//alert(pid);
	
	var mpid = $("#mpid").val();
	var jsondata = {
		"mpid":mpid,
		"mobile": mobile,
		"product_id": pid
	};
	
	openWindowTips();

	var url = 'wap/subscribeProduct.do';
	invokeService(url, jsondata, function(result) {
		if (result.retcode == 0 || result.retcode == 1 || result.retcode == 6 || result.retcode == 7)
		{
			//alert(result.retmsg);
			$("#divtipscontent").html(result.product_name+"办理成功<br>前往<a href='"+result.mcht_url+"' class='pro_return'>获取会员权益</a>");
		} else {
			var theErrMsg = result.retmsg || '未知错误类型';
			$("#divtipscontent").html(theErrMsg);
		}
	});
	
}
function openWindowTips()
{
	$("#divtranslucent").css('display','block');
	$("#divprompt").css('display','block');
	$("#divtipscontent").html("<img src='${path}/view/wap/images/load.gif'/><br>请耐心等候，正在处理中... ");
}

function closeWindowTips()
{
	$("#divtranslucent").css('display','none');
	$("#divprompt").css('display','none');
}


var myprodlist = new Array();
function queryMyOrder()
{
	var mobile = $("#mobile").val();
	if(mobilevalid!='0' && mobile == "")
	{
		alert('获取手机号失败!');
		return;
	}
	if(mobile == "")
	{
		mobile = $("#inputmobile").val();//;
	}
	if(mobile=="")
	{
		alert('请输入手机号');
		return;
	}
	
	timer1 = setInterval(closeTimerAndTipsWindows, 10000);

	var jsondata = {
		"msgid":"10010008",
		"mobile": mobile
	};
	
	openWindowTips();

	var url = 'wap/queryMobileOrder.do';
	invokeService(url, jsondata, function(result) {

		closeWindowTips();
		if (result.retcode == 0) {
			
			var ncount=0;
			var outhtml="";
			var obj = result.body;
			$.each(obj, function (n, value) {
				var product_id = value.product_id;					
				var product_name = value.product_name;
				var mcht_url = value.mcht_url;

				outhtml+="<div class='myproduct_div_left'><span>您已订购:"+product_name+"</span></div>";
				outhtml+="<div class='myproduct_div_right'><a href='"+mcht_url+"'><span>获取会员权益</span></a></div>";
				
				if($("#inputproductid").val()==product_id)
				{
					$("#hasproudct_icon").css('display','block');;
				}
				myprodlist.push(product_id);

				ncount++;
			});
			$("#id_myproductlist").html(outhtml);
			if(ncount == 0)
			{
				$("#id_myproductlist").html("<div class='myproduct_div_left'><span>您暂时没有订购视频流量包</span></div>");
			}
		} else {
			var theErrMsg = result.retmsg || '未知错误类型';
			outhtml=theErrMsg;
		}
		
	});
}



var timer1;
function closeTimerAndTipsWindows()
{
	closeWindowTips();
	clearInterval(timer1);
}
function queryMyCurrentOrder()
{
	var mobile = $("#mobile").val();
	if(mobilevalid!='0' && mobile == "")
	{
		alert('获取手机号失败!');
		return;
	}
	if(mobile == "")
	{
		mobile = $("#inputmobile").val();//;
	}
	if(mobile=="")
	{
		alert('请输入手机号');
		return;
	}
	
	timer1 = setInterval(closeTimerAndTipsWindows, 10000);

	var productids="";
	var mchtcode="";
	<c:forEach items="${productlist}" var="mplist" varStatus="vs">
		productids=productids+'${mplist.product.productId}';
		productids=productids+',';

		mchtcode='${mplist.mchtCode}';
	</c:forEach>
	var jsondata = {
		"msgid":"10010008",
		"mobile": mobile,
		"mcht_code":mchtcode,
		"productids": productids
	};	
	
	openWindowTips();

	var url = 'wap/queryMyCurrentOrder.do';
	invokeService(url, jsondata, function(result) {

		closeWindowTips();
		if (result.retcode == 0) {
			
			var ncount=0;
			var outhtml="";
			var obj = result.body;
			$.each(obj, function (n, value) {
				var product_id = value.product_id;					
				var product_name = value.product_name;
				var mcht_url = value.mcht_url;

				outhtml+="<div class='myproduct_div_left'><span>您已订购:"+product_name+"</span></div>";
				outhtml+="<div class='myproduct_div_right'><a href='"+mcht_url+"'><span>获取会员权益</span></a></div>";
				
				if($("#inputproductid").val()==product_id)
				{
					$("#hasproudct_icon").css('display','block');;
				}
				myprodlist.push(product_id);

				ncount++;
			});
			$("#id_myproductlist").html(outhtml);
			if(ncount == 0)
			{
				$("#id_myproductlist").html("<div class='myproduct_div_left'><span>您暂时没有订购视频流量包</span></div>");
			}
		} else {
			var theErrMsg = result.retmsg || '未知错误类型';
			outhtml=theErrMsg;
		}			
	});
}

function gotoMchtUrl()
{
	var url = $("#mchturl").val();
	window.location.href=url;
}

</script>
<title>订购流量快餐</title>
</head>

<body>
	<input type="hidden" id="mpid" name="mpid" value="">
	<input type="hidden" id="inputproductid" name="inputproductid" value="">
	<input type="hidden" id="mobile" name="mobile" value="${mobile}">
	<input type="hidden" id="mchturl" name="mchturl" value="">
	<div id="whole_div">
    	<!-- 头部-begin -->
    	<div class="header">
        	<!-- logo-begin -->
        	<div class="logo">
            	<img src="${path}/view/wap/images/ydlogo.png" width="28" height="28" alt="" />
                <span>广东移动</span>
            </div><!-- logo-end -->
            <!-- 登录-begin -->
            <div class="login">
            	<a href="javascript:void(0);">登录</a><span>|</span><a href="javascript:void(0);" class="search"></a>
            </div><!-- 登录-end -->
            <div class="clear"></div>
        </div><!-- 头部-end -->
        <!-- 中部-begin -->
        <div class="central">
        	<!-- 位置-begin -->
            <div class="location">
                <ul>
                    <li class="home"></li>
                    <li class="li_1"><a href="http://wap.gd.10086.cn/nwap/index.shtml">首页</a></li>
                    <li class="nav_arrow"></li>
                    <li class="li_1"><a href="http://wap.gd.10086.cn/nwap/products/products/index.jsps">办理</a></li>
                    <li class="nav_arrow"></li>
                    <li class="li_2" id="selectproductname_2">南方会员权益流量特惠包</li>
                    <li class="nav_arrow"></li>
                    <p class="clear"></p>
                </ul>
            </div><!-- 位置-end -->
            <!-- 南方会员权益流量特惠包-begin -->
            <div class="discount">
				<!--<div id='hasproudct_icon' class="already_handled" style='display:block'></div>-->
				<div id='hasproudct_icon' class="already_handled" style='display:none'></div>
            	<div class="llth">
                    <img src="${path}/view/wap/images/llth.png" width="74" height="74" alt="" >
                </div>
                <div class="l_name">
                    <p class="title" id="selectproductname_1">南方会员权益流量特惠包</p>					
                    <p class="abstract">全省 | 全球通 神州行 动感地带</p>
					<div id="divshowmobile" style="display:block">您的号码为：${mobile}</div>
					<div id="divshowinputmobile" style="display:none">
						<p class="abstract">
							<input type="number" class="mobileinput" id="inputmobile" name="inputmobile" value="${mobile}" oninput="if(value.length>11)value=value.slice(0,11)">
							<input type="button" class="btn_getsms" value="获取短信" onclick="javascript:getMobileSmsno();">
						</p>
						<p class="abstract">
							<input type="number" class="smsnoinput" id="smsno" name="smsno" value="" oninput="if(value.length>11)value=value.slice(0,11)">
							<input type="button" class="btn_getsms" value="短信验证" onclick="javascript:checkMobileSmsno();">
						</p>
						<span id="resultsms"></span>
					</div>
                </div>
            </div>
            <!--<div class="discount">
                <div class="llth">
                    <img src="${path}/view/wap/images/llth.png" width="74" height="74" alt="" >
                </div>
                <div class="l_name">
                    <p class="title">南方会员权益流量特惠包</p>
                    <p class="abstract">全省 | 全球通 神州行 动感地带</p>
                </div>
                <p class="clear"></p>
            </div>-->
            <!-- 南方会员权益流量特惠包-begin -->
            <!-- 业务办理、业务详情-begin -->
            <div class="functional">
                <div class="survicer"></div>
                <!-- 点击选择框-begin -->
                <div class="Menubox">
                    <ul>
                        <li id="one1" onclick="clickTab_1()" class="hover">业务办理</li>
                        <li id="one2" onclick="clickTab_2()">业务详情</li>
                        <p class="clear"></p>
                    </ul>
                </div><!-- 点击选择框-end -->
                <!-- 显示选择框-begin -->
                <div class="Contentbox">
                    <!-- 业务办理显示框-begin -->
                    <div id="con_one_1" class="hover">
                        <div class="choice">请选择套餐档次:</div>
                        <!-- 按钮-begin -->
                        <div class="but_div">
							<table class="table_white" align="center" width="100%" cellspacing="0" cellpadding="0">
							<c:forEach items="${productlist}" var="mplist" varStatus="vs">
							<c:if test="${vs.index%2==0}">
							<tr>
							</c:if>
								<td width="50%">
									<div class="product_unchecked" id="${mplist.product.productId}" name='divproductname' onclick="javascript:chooseproduct('${mplist.product.productId}')">${mplist.product.productTitle}</div>
								</td>
								<!--
								<td>
									<div class="product_unchecked" id="product2" onclick="javascript:choceproduct('2')">19.99元20G</div>
								</td>
								-->
							<c:if test="${vs.index%2==1}">
							</tr>
							</c:if>
							</c:forEach> 
							</table>
                            <p class="clear"></p>
                        </div><!-- 按钮-end -->
                        <!-- 选择的套餐-begin -->
                        <div class="dottedline">
                            <p>您已选择：<span id='selectproductname_3'><!--9.99元1G南方会员权益流量特惠包--></span></p>
							<p>产品介绍：<span id='selectproductdetail_3' style="color:#666"><!--9.99元1G南方会员权益流量特惠包--></span></p>
                            <!--<p style="letter-spacing:4.5px">有限期：<span id='id_selectproductdays'></span><span>天</span></p>-->
                        </div><!-- 选择的套餐-end -->
                        
                        <!-- 详细内容请阅读-begin -->
						<div class="read">
							<span class="span_1">详细内容请阅读</span>
							<span class="span_2">《</span>业务详情<span class="span_2">》</span>
							<input type="checkbox" id="checkprotocol" style="width:15px; height:15px;" /><span class="span_1">我已阅读</span>
						</div><!-- 详细内容请阅读-end -->
						<!-- 立即办理按钮-begin -->
						<div class="handle_btn">
							<a href="javascript:subscributeProduct();" class="yes_handle">立即办理</a>
						</div>
					</div><!-- 立即办理按钮-end -->
				</div><!-- 业务办理显示框-end -->
				<!-- 业务详情框-begin -->
				<div id="con_one_2" style="display:none"><!-- 业务详情框-end -->
					<div id="divproductdetail" class="flow_content">
					1、 扣费规则：<span id="desc_productname">9.99元1G央视咪咕奥运流量包</span>在有效期内限办理1次，仅能通过基本账户进行扣费。<br>					
					2、生效规则：办理立即生效，<span id="desc_pricemchtname">9.99元XX</span>会员流量包有效期7天，流量包内所含流量为省内2/3/4G通用流量。<br>					
					3、并存规则：<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1）不限量套餐、4G随心王客户不可办理。如需办理，需先主动取消上述套餐。<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2）与其他流量套餐可并存。<br>
					4、流量封顶规则：流量包所含流量遵循套外500元、流量15G双封顶，总流量50G封停规则（<span id="desc_productprice">9.99元和19.99元</span>不计入套外500元范围内）。<br>
					5、使用周期：在指定期限内流量有效，不受月结日影响，超过指定有效期流量清零。<br>
					6、权益规则：视频权益可通过指定链接观看奥运赛事，视频权益有效期至8月31日24时。
					</div>
				</div>					
				

				
            </div><!-- 业务办理、业务详情-end -->
        </div><!-- 中部-end -->
        
        <!-- 获取会员权益-begin -->
		<div class="equity">
			<a href="javascript:queryMyCurrentOrder();"> <img title='查询我的订购的流量包' style="cursor:pointer;" src='${staticPath}/images/view.gif' border='0'/>
			<span id="myproductmchturl">奥运专区</span></a>
		</div><!-- 获取会员权益-end -->
		<!-- 我订购的流量包-begin -->
		<div class="myproduct" id="id_myproductlist">
			
		</div>
		<!-- 我订购的流量包-end -->

		<!-- 办理成功灰色背景框-begin -->
        <div id="divtranslucent" class="translucent" style="display:none"></div><!-- 办理成功灰色背景框-end -->
        <!-- 办理成功提示框-begin -->
        <div id="divprompt" class="prompt" style="display:none">
        	<p>提示</p>
            <div class="pro_content" id="divtipscontent">
            	<!--9.99元1G南方会员权益流量特惠包办理成功。前往<a href="javascript:void(0);">前往获取会员权益。</a>-->
				<img src="${path}/view/wap/images/load.gif"/><br>请耐心等候，正在处理中... 
            </div>
            <div class="pro_button">
            	<a href="javascript:closeWindowTips()" class="pro_close">关闭</a>         
            </div>
        </div><!-- 办理成功提示框-end -->

    </div>
	<!-- 底部-begin -->
        <div class="bottom">
        	<div>
            	<a href="http://gd.10086.cn/wap2012/notice/zsymqd.shtml?WT.mc_id=GDALAPPX0521W0I0001">客户端</a>
                <a href="http://wap.gd.10086.cn/nwap/index.shtml">电脑版</a>
                <a href="http://wap.gd.10086.cn/nwap/index/serviceAndSupport.jsps">服务与支持</a>
            </div>
            <p>京ICP备05002571 | 中国移动版权</p>
        </div>
		<!-- 底部-end -->
</body>
</html>
