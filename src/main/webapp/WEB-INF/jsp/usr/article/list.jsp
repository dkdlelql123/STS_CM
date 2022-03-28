<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pageTitle" value="${board.name} 게시물 목록" />
<%@ include file="../common/head.jspf" %>
  

<div class="flex-grow">
  
  <div class="w-3/4 mx-auto">
    <h1 class="size-xl fontL">${board.name} 게시물 목록</h1>
    
    <table class="w-full">
      <colgroup>
        <col width="100"><col width=""><col width="100"><col width="200">
      </colgroup>
      <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>시간</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="article" items="${aritcles}">  
          <tr class="">
            <td class="text-center py-1" >${article.id} </td>
            <td class="py-1" ><a href="/usr/article/detail?id=${article.id}" class="flex">${article.title}</a></td>
            <td class="text-center py-1">${article.actorName}</td>
            <td class="text-center py-1 ">${fn:substring(article.regDate,0,16)}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
  </div>
  
</div>  
  

<%@ include file="../common/tail.jspf" %>  