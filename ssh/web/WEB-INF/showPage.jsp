<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/4/16
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div>
    <form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
        <ul>
            <s:iterator var="p" value="pageBean.list">
                <s:property value="#p.username"/>
            </s:iterator>
        </ul>
        <div class="pagination">
            <span>第 <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/> 页</span>
            <s:if test="pageBean.page != 1">
                <a href="${ pageContext.request.contextPath }/user_showPage?page=1" class="firstPage">&nbsp;</a>
                <a href="${ pageContext.request.contextPath }/user_showPage?page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>
            </s:if>

            <s:iterator var="i" begin="1" end="pageBean.totalPage">
                <s:if test="pageBean.page != #i">
                    <a href="${ pageContext.request.contextPath }/user_showPage?page=<s:property value="#i"/>"><s:property value="#i"/></a>
                </s:if>
                <s:else>
                    <span class="currentPage"><s:property value="#i"/></span>
                </s:else>
            </s:iterator>

            <s:if test="pageBean.page != pageBean.totalPage">
                <a class="nextPage" href="${ pageContext.request.contextPath }/user_showPage?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
                <a class="lastPage" href="${ pageContext.request.contextPath }/user_showPage?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
            </s:if>
        </div>
    </form>
</div>
