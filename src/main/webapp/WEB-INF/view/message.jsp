<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>WebChat | 聊天记录</title>
    <jsp:include page="include/commonfile.jsp"/>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="am-cf admin-main">
    <jsp:include page="include/sidebar.jsp"/>

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">聊天记录</strong> / <small>log</small></div>
        </div>
        <div class="am-tabs am-margin">
            <table class="am-table am-table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>from</th>
                    <th>to</th>
<%--                    <th>type</th>--%>
                    <th>content</th>
                    <th>keyword1</th>
                    <th>keyword2</th>
                    <th>keyword3</th>
                    <th>keyword4</th>
                    <th>keyword5</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="message" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${message.from}</td>
                        <td>${message.to}</td>
<%--                        <td>${message.type}</td>--%>
                        <td>${message.time}</td>
                        <td>${message.content}</td>
                        <td>${message.keyword1}</td>
                        <td>${message.keyword2}</td>
                        <td>${message.keyword3}</td>
                        <td>${message.keyword4}</td>
                        <td>${message.keyword5}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="page" style="float: right"></div>
        </div>
    </div>
    <!-- content end -->
</div>
<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
    <span class="am-icon-btn am-icon-th-list"></span>
</a>
<jsp:include page="include/footer.jsp"/>

<script>
    $(function(){
        laypage({
            cont: 'page',
            pages: ${count},
            curr: function(){
                var page = location.search.match(/page=(\d+)/);
                return page ? page[1] : 1;
            }(),
            skin: '#AF0000',
            groups: 5, //连续显示分页数
            jump: function(e, first){ //触发分页后的回调
                if(!first){ //一定要加此判断，否则初始时会无限刷新
                    location.href = '?page='+e.curr;
                }
            }
        });
    });
</script>
</body>
</html>
