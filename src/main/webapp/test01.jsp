<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2021/9/14
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>

<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
            $.ajax({
            url:"",
            data:{},
            type:"",
            dataType:"json",
            success:function (data){

            }
            })
String createTime= DateTimeUtil.getSysTime();
String createBy=((User)request.getSession().getAttribute("user")).getName();

//日历控件
$(".time").datetimepicker({
     minView: "month",
     language: 'zh-CN',
     format: 'yyyy-mm-dd',
     autoclose: true,
     todayBtn: true,
     pickerPosition: "bottom-left"
});
</body>
</html>
