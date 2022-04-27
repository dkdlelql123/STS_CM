<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../common/head.jspf" %>

<script>
let articleModify__submitDone = false;
function articleModify__submit(form){
	
	if(articleModify__submitDone){ 
		return;
	}
	 
	let titleText = form.title.value.trim();
	let bodyText = form.body.value.trim();
	
	if(titleText.length <= 1){
		alert("제목을 두글자 이상 작성해주세요.");
		form.title.focus();
		return;
	}
	
	if(bodyText.length <= 1){
		alert("내용을 2글자 이상 작성해주세요.");
		form.body.focus();
		return;
	} 
	
	articleModify__submitDone = true;
	form.submit();
}

</script>
  
<div class="flex-grow">
  
  <div class="w-3/4 mx-auto"> 
       
    <div class="flex items-center gap-1 my-4">
      <div class="flex-grow"></div>
      <a class="border text-sm pt-1 px-2 rounded rounded-sm" href="/usr/article/detail?id=${article.id}">게시물 돌아가기</a>
    </div>
       
     <form action="/usr/article/doModify" method="post" onsubmit="articleModify__submit(this); return false;">
      <table class="table border border-1 flex w-full">
      <colgroup>
        <col width="150"><col>
      </colgroup>
        <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            번호
          </td>
          <td>
            <input type="text" name="id" id="id" readonly value="${article.id}" /> 
          </td>
        </tr> 
        <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            게시판
          </td>
          <td> 
            <select name="boardId" class="select select-sm select-bordered">
              <option disabled>선택해주세요</option>
              <c:forEach var="board" items="${board}">
             	<c:choose> 
                    <c:when test="${article.boardId == board.id}">
                        <option value="${board.id}" selected >${board.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${board.id}" >${board.name}</option>
                    </c:otherwise>
                </c:choose>
                
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            작성일
          </td>
          <td>
            ${article.getForPrintType1RegDate()} 
          </td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            수정일
          </td>
          <td>
            ${article.getForPrintType1UpdateDate()} 
          </td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            제목
          </td>
          <td>
            <input type="text" id="title" name="title" class="w-full p-1 bg-gray-100 my-1" value="${article.title}"/>
          </td>
        </tr>
        <tr>
          <td class="text-center py-2">
            내용
          </td>
          <td>
            <textarea id="body" name="body" class="w-full p-1 bg-gray-100 my-1" rows=5 >${article.body}</textarea>
          </td>
        </tr>
      </table> 
      
      <div class="flex items-center my-4 justify-center">  
        <button class="btn btn-sm">수정</button>
      </div>
     
    </form>
  </div>
</div>
<%@ include file="../common/tail.jspf" %>  