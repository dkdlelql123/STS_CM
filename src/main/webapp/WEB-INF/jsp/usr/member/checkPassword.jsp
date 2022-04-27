<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="비밀번호 확인" />
<%@ include file="../common/head.jspf"%>


<script>
let doCheckPassword__submitDone = false;
function doCheckPassword__submit(form){
	if (doCheckPassword__submitDone) return;
	
	let loginPw = form.loginPw.value.trim();
	if(loginPw <= 0){
		alert("비밀번호를 작성해주세요.");
		form.loginPw.focus();
		return;
	}
	 
	doCheckPassword__submitDone = true;
	form.submit();
}
</script>

<div class="flex-grow mx-auto mt-8">

  <form action="/usr/member/doCheckPassword" method="post" onsubmit="doCheckPassword__submit(); return false;">
    <input type="hidden" name="replaceUri" id="replaceUri" value="${param.replaceUri}" />
  
    <div class="mx-auto card w-96 bg-base-100 shadow-xl">
      <div class="card-body text-center items-center">
        <p class="mb-2">비밀번호를 입력해주세요!</p>
        <div >
                ${rq.loginedMember.loginId}
        </div>
        <div>
          <input type="password" name="loginPw" autofocus class="bg-gray-200 p-2" />
        </div>
        
        <div class="card-actions">
          <button class="btn btn-sm btn-primary">확인</button>
        </div>
      </div>
    </div>
  </form> 

</div>

<%@ include file="../common/tail.jspf"%>
