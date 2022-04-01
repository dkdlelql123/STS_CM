<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pageTitle" value="${board.name} 게시물 목록" />
<%@ include file="../common/head.jspf" %>
  

<div class="flex-grow">
  
  <div class="w-3/4 mx-auto">
    <form class="flex items-center justify-center gap-2 my-4">
      <input type="hidden" name="boardId" value="${board.id}" />
      <select data-value="${param.searchType}" name="searchType" class="select select-bordered select-sm max-w-xs">
        <option disabled >--선택--</option>
        <option value="title">제목</option>
        <option value="body">내용</option>
        <option value="title,body">제목,내용</option>
      </select>
      <input type="text" name="searchKeyword" value="${param.searchKeyword}" class="input input-bordered input-sm max-w-xs" placeholder="search...">
      <input type="submit" value="search" class="btn btn-sm lowercase"/>
    </form>
  
    <h1 class="size-sm py-4">총 ${articleCount}개</h1>
    
 
    <table class="table table-compact w-full table-fixed">
      <colgroup>
        <col width="100"><col width=""><col width="100"><col width="200">
      </colgroup>
      <thead>
      <tr>
        <th  class="text-center">번호</th>
        <th>제목</th>
        <th  class="text-center">작성자</th>
        <th  class="text-center">시간</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="article" items="${aritcles}">  
          <tr class="py-2">
            <td class="text-center"  >${article.id}</td>
            <td > 
              <a class="block w-full truncate" href="/usr/article/detail?id=${article.id}" >
                ${article.title}
              </a>
            </td>
            <td class="text-center">${article.extra_actorName}</td>
            <td class="text-center">${fn:substring(article.regDate,0,16)}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div id="pagenation" class="btn-group justify-center mt-4 mb-6">
      <c:set var ="pageMenuArmLen" value="2" />
      <c:set var="startPage" value="${page - pageMenuArmLen > 0 ? page - pageMenuArmLen : 1}"/>
      <c:set var="endPage" value="${page + pageMenuArmLen <= end ? page + pageMenuArmLen: end }"/>
      
      <c:set var="baseUri" value="?boardId=${board.id}"/>
      <c:set var="baseUri" value="${baseUri}&searchType=${param.searchType}"/>
      <c:set var="baseUri" value="${baseUri}&searchKeyword=${param.searchKeyword}"/>
       
      <c:if test="${startPage > 1}"> 
         <a class="btn btn-sm btn-outline border-gray-400" href="${baseUri}&page=1" >1</a>
        <c:if test="${startPage > 2}">
           <a class="btn btn-sm btn-outline btn-disabled border-gray-400">...</a>
        </c:if> 
      </c:if> 
      
      <c:forEach var="i"  begin="${startPage}" end="${endPage}" step="1" >  
        <a class="btn btn-sm btn-outline border-gray-400 ${page == i ? 'btn-active' :''}"  href="${baseUri}&page=${i}" >${i}</a>
      </c:forEach>
      
      <c:if test="${endPage < end}">
        <c:if test="${endPage < end-1 }">
         <a class="btn btn-sm btn-disabled btn-outline border-gray-400 ">...</a>
        </c:if> 
        <a class="btn btn-sm btn-outline border-gray-400 "  href="${baseUri}&page=${end}" >${end}</a>
      </c:if>  
    </div>
    
  </div>
</div>  
  

<%@ include file="../common/tail.jspf" %>  