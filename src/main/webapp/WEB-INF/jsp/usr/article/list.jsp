<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pageTitle" value="${board.name} 게시물 목록" />
<%@ include file="../common/head.jspf" %>
  

<div class="flex-grow">
  
  <div class="w-3/4 mx-auto">
    <h1 class="size-xl fontL py-8">${board.name} 게시물 목록 총 ${articleCount}개</h1>
    
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
            <td class="text-center py-1" >${article.id}</td>
            <td class="py-1" ><a href="/usr/article/detail?id=${article.id}" class="flex">${article.title}</a></td>
            <td class="text-center py-1">${article.extra_actorName}</td>
            <td class="text-center py-1 ">${fn:substring(article.regDate,0,16)}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div class="btn-group">
      <c:set var ="pageMenuArmLen" value="2" />
      <c:set var="startPage" value="${page - pageMenuArmLen > 0 ? page - pageMenuArmLen : 1}"/>
      <c:set var="endPage" value="${page + pageMenuArmLen <= end ? page + pageMenuArmLen: end }"/>
       
      <c:if test="${startPage > 1}">
        <button class="btn btn-sm" type="button">
           <a href="/usr/article/list?page=1" >1</a>
         </button> 
          <c:if test="${startPage > 2}">
             <button class="btn btn-sm btn-disabled">...</button>
          </c:if> 
      </c:if> 
      
      <c:forEach var="i"  begin="${startPage}" end="${endPage}" step="1" >  
        <button class="btn btn-sm ${page == i ? 'btn-active' :''}" type="button" >
           <a href="/usr/article/list?page=${i}" >${i}</a>
         </button> 
      </c:forEach>
      
       <c:if test="${endPage < end}">
          <c:if test="${endPage < end-1 }">
             <button class="btn btn-sm btn-disabled">...</button>
          </c:if> 
        <button class="btn btn-sm" type="button">
           <a href="/usr/article/list?page=${end}" >${end}</a>
         </button> 
      </c:if> 
       
    </div>
    
  </div>
</div>  
  

<%@ include file="../common/tail.jspf" %>  