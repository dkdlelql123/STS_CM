<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="댓글 수정" />
<%@ include file="../common/head.jspf"%>

<script>

let replyModify_submitDone = false;
function ReplyModify__submit(form){
	
	if(replyModify_submitDone){ // 연속 수정 클릭 방지
		return; 
	}
	
	let bodyText = form.body.value.trim();
	
	if(bodyText.length == 0){
		alert("내용을 작성해주세요.");
		form.body.focus();
		return;
	}
	
	if(bodyText.length <= 1){
		alert("내용을 2글자 이상 작성해주세요.");
		form.body.focus();
		return;
	}
	
	replyModify_submitDone = true;
    form.submit(); 
    
}
</script>

<div class="flex-grow">

  <div class="w-3/4 mx-auto">

    <div class="flex items-center gap-1 my-4">
      <a href="#" onClick="history.go(-1);">뒤로가기</a>
    </div>

    <form action="/usr/reply/doModify" method="post" onsubmit="ReplyModify__submit(this); return false;">

      <table class="table border border-1 flex w-full">
        <colgroup>
          <col width="150">
          <col>
        </colgroup>
        <tr class="border-b border-gray-100">
          <td class="text-center ">게시물 번호</td>
          <td>
            <input type="text" name="relId" id="relId" readonly value="${reply.relId}" />
          </td>
        </tr>
           <tr class="border-b border-gray-100">
          <td class="text-center ">게시물 제목</td>
          <td>
            ${relDataTitle}
          </td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center ">댓글 번호</td>
          <td>
            <input type="text" name="id" id="id" readonly
              value="${reply.id}" />
          </td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center">작성일</td>
          <td>${reply.regDate}</td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center">수정일</td>
          <td>${reply.updateDate}</td>
        </tr>
        <tr>
          <td class="text-center">내용</td>
          <td>
            <textarea name="body" id="body"
              class="w-full bg-gray-200 p-1" autofocus rows="5">${reply.printBody}</textarea>
          </td>
        </tr>
      </table>

      <div class="flex items-center my-4 justify-center">
        <button class="btn btn-sm">댓글 수정</button>
      </div>

    </form>
  </div>
</div>
<%@ include file="../common/tail.jspf"%>
