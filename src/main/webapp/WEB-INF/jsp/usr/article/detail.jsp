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
    
      <div class="flex gap-2 text-gray-500 "> 
        <span class="size-sm" >${article.extra_actorName}</span>
        <span class="size-sm" >|</span>      
        <span id="hitCount" class="size-sm" >${article.hit}</span>
        <span class="size-sm" >|</span>    
        <span class="size-sm" >${article.getForPrintType1RegDate()}</span>      
    </div>
    
      <div class="bg-gray-100 p-2 rounded self-stretch">
        <div>${article.body}</div>
      </div> 
    </div>

  </div>
</div>
<%@ include file="../common/tail.jspf" %>  