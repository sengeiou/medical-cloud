<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<c:set var="ctx" value="<%=path%>" />
<!doctype html>
<!–[if IE]>
<meta http-equiv=”X-UA-Compatible” content=”IE=8″ /> 
<![endif]–>
<!–[if IE 7]> 
<meta http-equiv=”X-UA-Compatible” content=”IE=7″ /> 
<![endif]–> 
<!–[if IE 6]> 
<meta http-equiv=”X-UA-Compatible” content=”IE=6″ /> 
<![endif]–> 
<html>
<head>
<meta charset="utf-8">
<title>医生档案</title>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
<script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">

<link rel="stylesheet" href="<%=path%>/skin/css/list.css">
<script src="<%=path%>/skin/js/jquery-1.10.2.min.js"></script>
<script src="<%=path%>/skin/js/kkpager.min.js"></script>

<link rel="stylesheet" href="<%=path%>/skin/css/kkpager_blue.css">

<style>
	.left_box input{
		width:100px;
	}
</style>

</head>

            
<script type="text/javascript">

//生成分页控件 
$(function(){
	kkpager.generPageHtml({
	    pno : '${page.pageIndex}',
	    mode : 'link', //设置为link模式
	    //总页码  
	    total : '${page.totalPage}',  
	    //总数据条数  
	    totalRecords : '${page.totalCount}',
	    //点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
	    //适用于不刷新页面，比如ajax
	    hrefFormer : 'pager_test',
	    //链接尾部  
	    hrefLatter : '.html',
	    //链接
	    getLink : function(n){
	    	return "javascript:search("+n+")";
	    },lang				: {
			firstPageText			: '首页',
			firstPageTipText		: '首页',
			lastPageText			: '尾页',
			lastPageTipText			: '尾页',
			prePageText				: '<',
			prePageTipText			: '<',
			nextPageText			: '>',
			nextPageTipText			: '>',
			totalPageBeforeText		: '共',
			totalPageAfterText		: '页',
			currPageBeforeText		: '当前第',
			currPageAfterText		: '页',
			totalInfoSplitStr		: '/',
			totalRecordsBeforeText	: '共',
			totalRecordsAfterText	: '条数据',
			gopageBeforeText		: ' 转到',
			gopageButtonOkText		: '确定',
			gopageAfterText			: '页',
			buttonTipBeforeText		: '第',
			buttonTipAfterText		: '页'
		}

	});

	
});


//copyright c by zhangxinxu v1.0 2009-09-05  
//http://www.zhangxinxu.com  
/* $(".test1").wordLimit(); 自动获取css宽度进行处理，如果css中未对.test1给定宽度，则不起作用 
//$(".test2").wordLimit(24); 截取字符数，值为大于0的整数，这里表示class为test2的标签内字符数最多24个 
*/
(function($){  
 $.fn.wordLimit = function(num){   
     this.each(function(){     
         if(!num){  
             var copyThis = $(this.cloneNode(true)).hide().css({  
                 'position': 'absolute',  
                 'width': 'auto',  
                 'overflow': 'visible'  
             });   
             $(this).after(copyThis);  
             if(copyThis.width()>$(this).width()){  
                 $(this).text($(this).text().substring(0,$(this).text().length-4));  
                 $(this).html($(this).html()+'...');  
                 copyThis.remove();  
                 $(this).wordLimit();  
             }else{  
                 copyThis.remove(); //清除复制  
                 return;  
             }     
         }else{  
             var maxwidth=num;  
             if($(this).text().length>maxwidth){  
                 $(this).text($(this).text().substring(0,maxwidth));  
                 $(this).html($(this).html()+'...');  
             }  
         }                      
     });  
 } 
})(jQuery);

function search(n) { //查询
	document.getElementById("pageIndex").value = n;
	
	$('#searchForm').submit(); //提交表单
}

$(function(){
	$(".wordLimit").wordLimit(20);
});
window.onload =search(0);
</script>
<body>
<div class="content" style="margin-left: 0px; border-left: 0px">
  <div class="header">
			<h1 class="page-title">医生档案</h1>
  </div>
<div class="main-content">
	        <form action="${ctx }/manage/adminDoctorUser/getDoctorInfo.action" name="form" method="post" id="searchForm">

				 <input type="button" value="查询" class="searchbtn" onclick="search(0)" />
				 <input type="hidden" name="pageIndex" id="pageIndex" value="${page.pageIndex}" /> 
				 <input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" /> 
			</form>
   
   <div class="btn-toolbar list-toolbar">
				<button type="button" class="btn btn-default" onclick="setFrameSrc('user.html');" >新建</button>
				<button type="button" class="btn btn-default">导出</button>
				<button type="button" class="btn btn-default">导入</button>
				<div class="btn-group"></div>
	</div>
   <table class="table" style="margin-top: 20px;">
	<tr class="title" >
		<td>序号</td>
		<td>医生id</td>
		<td>名字</td>
		<td>手机号</td>
		<td>就职机构</td>
		<td>所在科室</td>
		<td>职称</td>
		<td>操作</td>
	</tr>

	<c:forEach items="${ page.list}" var="doctorUser" varStatus="i">
	    <tr <c:choose><c:when test="${i.index % 2 !=  0 }">class="contene_box1" style=" background-color:#ffffff;"</c:when><c:otherwise>class="contene_box2" style=" background-color:#edf3ff;"</c:otherwise></c:choose>>
	    	<td style="width: 50px;"><c:out value="${i.index+1}"></c:out></td>
	        <td class="wordLimit" style="width: 200px;"><c:out value="${doctorUser.loginId}"></c:out></td>
	        <td class="wordLimit" style="width: 200px;"><c:out value="${doctorUser.name}"></c:out></td>
	        <td class="wordLimit" style="width: 200px;"><c:out value="${doctorUser.phone}"></c:out></td>
	        <td class="wordLimit" style="width: 300px;"><c:out value="${doctorUser.hospital}"></c:out></td>
	        <td class="wordLimit" style="width: 200px;"><c:out value="${doctorUser.department}"></c:out></td>
	        <td class="wordLimit" style="width: 200px;"><c:out value="${doctorUser.title}"></c:out></td>
	        <td class="wordLimit" style="width: 200px;"><c:out value="详情     修改"></c:out></td>
	    </tr>
	</c:forEach>
   </table>
   <div class="kkp" id="kkpager">
   		<div id="curr"></div>
   </div>
   
</div>
</div>

</body>
</html>
