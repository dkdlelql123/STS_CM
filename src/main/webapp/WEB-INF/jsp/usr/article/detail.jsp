<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 상세" />
<%@ include file="../common/head.jspf" %>
  
<script> const id = ${param.id}; </script> 
<script>
function increasedHit(){
	
	const localStorageKey = "article__"+id+"__Done";
	
	if(localStorage.getItem(localStorageKey)){
		return;
	}
	
	localStorage.setItem(localStorageKey, true);	
	
  $.ajax({
    url:'/usr/article/doIncreasedHit',
    data: {'id' : id},
  	type: 'get',
      success: function(data){
    	console.log(data.data1);  
    	$('#hitCount').empty().html(data.data1);
      }, 
    error:function(error){
      console.log(error)
    }
  })
}

$(function(){
	increasedHit();
})
</script>  
  
<div class="flex-grow">
  
  <div class="w-3/4 mx-auto"> 
       
    <div class="flex items-center gap-1 my-4">
      <a href="#" onClick="history.back(); return false;">뒤로가기</a>
      <div class="flex-grow"></div> 
        <a href="/usr/article/modify?id=${article.id}">수정</a>
        <a onclick="if(confirm('삭제하시겠습니까?') == false) return false;" href="/usr/article/delete?id=${article.id}">삭제</a>
    </div>
       
    <div class="flex flex-col items-center gap-2">
      <div class="title text-center size-la fontM">${article.title}</div>
    
      <div class="flex gap-2 text-gray-500 flex items-center "> 
        <span class="size-sm" >${article.extra_actorName}</span>
        <span class="size-sm" >|</span>      
        <span id="hitCount" class="size-sm" >조회 : ${article.hit}</span>
        <span class="size-sm" >|</span>      
        <span id="hitCount" class="size-sm" >추천 : ${article.goodReactionPoint}</span>
        <span class="size-sm" >|</span>    
        <span class="size-sm" >${article.getForPrintType1RegDate()}</span>      
    </div>
    
      <div class="bg-gray-100 p-2 rounded self-stretch">
        <div>${article.body}</div>
      </div> 
      
      <div class="flex items-center justify-center gap-2">
        <c:if test="${actorCanMakeReactionPoint}"> 
            <a 
            href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
            class="btn btn-xs btn-outline btn-primary">좋아요 👍</a>
            <a 
            href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
            class="btn btn-xs btn-outline">싫어요 👎</a>
        </c:if>
        
          <c:if test="${actorCanCancleGoodReactionPoint}"> 
            <a 
            href="/usr/reactionPoint/doCancleGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
            class="btn btn-xs btn-primary">좋아요 👍</a>
            <a 
            onclick="alert(this.title); return false;"
            title="좋아요 해제를 먼저해주세요"
            href="#"
            class="btn btn-xs btn-outline">싫어요 👎</a>
        </c:if>
        
         <c:if test="${actorCanCancleBadReactionPoint}"> 
            <a     
            onclick="alert(this.title); return false;"
            title="싫어요 해제를 먼저해주세요"
            href="#"
            class="btn btn-xs btn-outline btn-primary">좋아요 👍</a>
            <a 
            href="/usr/reactionPoint/doCancleBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
            class="btn btn-xs">싫어요 👎</a>
        </c:if>
      </div>
    </div>
    
    <article class="mt-4">
      <h3>댓글</h3>
      
      <div class="py-2"> 댓글 목록 </div>
      
      <c:if test="${rq.loginedCheck}">
        <form action="/usr/reply/doWrite" method="post" >
          <input type="hidden" name="relTypeCode" value="article" />
          <input type="hidden" name="relId" value="${article.id}" />
          <div class="flex flex-col">
            <div class="size-sm">${rq.loginedMember.nickname}</div>
            <div>
              <textarea name="body" id="" rows="2" class="w-full p-1 border"  placeholder="댓글을 입력해주세요."></textarea>
            </div>
            <div class="flex">
              <div class="flex-grow"></div>
              <input type="submit" class="btn btn-xs btn-outline" value="댓글작성" />
            </div>
          </div>
        </form>
      </c:if>
      <c:if test="${rq.notLogin}">
        <div class="text-sm">
          <a href="/usr/member/login" class="text-primary font-bold">로그인</a> 이후 이용해주세요
        </div>
      </c:if>
    </article>

  </div>
</div>
<%@ include file="../common/tail.jspf" %>  