<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common.jsp" %>
<div class="right">
            <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
            <form action="user?method=getAll" method="post" >
                <span>真实姓名：</span>
                <input type="text" name="real_name" value="${real_name }" placeholder="请输入姓名"/>
                <input type="submit" value="查询"/>
                <a href="userAdd.jsp">添加用户</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户账号</th>
                    <th width="20%">真实姓名</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="10%">头像</th>
                    <th width="20%">操作</th>
                </tr>
                <c:forEach items="${users }" var="us">
                <tr>
                    <td>${us.username }</td>
                    <td>${us.realname }</td>
                    <td>${us.sex }
                    
                    <c:if test="${us.sex == 1 }">男</c:if>
                    <c:if test="${us.sex == 2 }">女</c:if>
               
                    </td>
                    <td>${us.birthday }</td>
                    <td>${us.tel }</td>
                    <td>${us.type }
                    
                    <c:choose>
                    	<c:when test="${us.type == 1 }">1</c:when>
                    	<c:when test="${us.type == 2 }">2</c:when>
                    	<c:otherwise>3</c:otherwise>
                    </c:choose>
                    
                    
                    </td>
                   
                    <td><img  src="${us.photo }" width="30%"/>
                    <a href="${us.photo }">查看头像</a>
                    </td>
                    
                       <td>
                        <a href="usView.html"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="usUpdate.html"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a onclick="return confirm('您真的确认删除吗？')" href="user?method=delUserById&id=${us.id }" class="removeus"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                </c:forEach>
				<tr>
				<td colspan="8">
				  当前第1页，6条数据&nbsp;&nbsp;&nbsp;&nbsp;共2页
			         首页
			         尾页
			        上一页
			        下一页
				</td>
				</tr>
            </table>

        </div>
    </section>
    </form>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
    </footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>