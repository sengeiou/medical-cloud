<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频日志管理</title>
    <script type="text/javascript">
	var map = new Map();
	//为map添加值；key:对应filed="userName"中的字段名；value:对应数据库的字段
	map['mchtCode']='mcht_code';
	map['actionDate']='action_date';
	map['clickCount']='click_count';
	map['playCount']='play_count';
	map['favorCount']='favor_count';
	map['feedbackCount']='feedback_count';
	map['shareCount']='share_count';
	
	map['updateTime']='update_time';
	onSortColumn=function(param,map){
		//取出map中字段的映射关系值  
		var fieldSort=map[param.sort];
		if(fieldSort!='' && fieldSort!=undefined){
			//设置新的排序字段名，设置完之后，发送请求时一并会发送到服务端
			param.sort=fieldSort;
		}
	}

    var dataGrid;
    $(function() {

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/crm/mediaRecord/findMediaRecordPage.do',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            pageSize : 10,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '80',
                title : '商家',
                field : 'mchtCode',
				sortable : true
            }, {
				width : '120',
                title : '视频名称',
                field : 'mediaStore',
				formatter : function formatSubData(value, row){
					return value.name;
				}
            }, {
                width : '120',
                title : '日期',
                field : 'actionDate',
				sortable : true,
                formatter : FormatDate
            }, {
                width : '80',
                title : '点击数',
                field : 'clickCount',
				sortable : true
            }, {
                width : '80',
                title : '播放数',
                field : 'playCount',
				sortable : true
            },{
                width : '80',
                title : '收藏数',
                field : 'favorCount',
				sortable : true
            },{
                width : '120',
                title : '评论数',
                field : 'feedbackCount',
				sortable : true
            },{
                width : '120',
                title : '分享数',
                field : 'shareCount',
				sortable : true
            },{
                width : '120',
                title : '更新时间',
                field : 'updateTime',
                formatter : formatterdate,
                sortable : true
            },{
                field : 'action',
                title : '操作',
                width : 150,
                formatter : function(value, row, index) {
                    var str = '';
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                    return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
			onBeforeLoad:function(param){  
				onSortColumn(param,map);  
			},
            toolbar : '#toolbar'
        });

    });

    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前视频日志？', function(b) {
            if (b) {
                var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
                if (currentUserId != id) {
                    progressLoad();
                    $.post('${path }/crm/mediaRecord/delete.do', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            dataGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                } else {
                    parent.$.messager.show({
                        title : '提示',
                        msg : '不可以删除自己！'
                    });
                }
            }
        });
    }
    
     function searchFun() {
    	console.log($.serializeObject($('#searchForm')));
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    
    function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}
    function formatterdate(val) {
	   	if (val != null) {
		   	var date = new Date(val);
		   	var hours = '';
		   	var minutes = '';
		   	var seconds = '';
		   	if(date.getHours()<10){
		   		hours="0"+date.getHours();
		   	}else{
		   		hours=date.getHours();
		   	}
		   	if(date.getMinutes()<10){
		   		minutes="0"+date.getMinutes();
		   	}else{
		   		minutes=date.getMinutes();
		   	}
		   	if(date.getSeconds()<10){
		   		seconds="0"+date.getSeconds();
		   	}else{
		   		seconds=date.getSeconds();
		   	}
		   	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
		   	+ date.getDate()+" "+hours+":"+minutes+":"+seconds;
	   	}
   	}
 
   	
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchForm">
        <span>商家名称：<input name="mchtName" type="text" placeholder="请输入商家名称" style="width:120px" value="" ></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'视频日志列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar">
        
    </div>
     
</body>
</html>