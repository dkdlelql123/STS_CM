<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="게시물 상세" />
<%@ include file="../common/head.jspf"%>

<script>
	const id = ${	param.id	};
</script>
<script>
	function increasedHit() {
		const localStorageKey = "article__" + id + "__Done";

		if (localStorage.getItem(localStorageKey)) {
			return;
		}

		localStorage.setItem(localStorageKey, true);

		$.ajax({
			url : '/usr/article/doIncreasedHit',
			data : {
				'id' : id
			},
			type : 'get',
			success : function(data) {
				console.log(data.data1);
				$('#hitCount').empty().html(data.data1);
			},
			error : function(error) {
				console.log(error)
			}
		})
	}

	$(function() {
		increasedHit();
	})
</script>

<div class="flex-grow">

  <div class="w-3/4 mx-auto">

    <div class="flex items-center gap-1 my-4">
      <a href="#" onClick="history.back(); return false;">뒤로가기</a>
      <div class="flex-grow"></div>
      <a href="/usr/article/modify?id=${article.id}">수정</a>
      <a onclick="if(confirm('삭제하시겠습니까?') == false) return false;"
        href="/usr/article/delete?id=${article.id}">삭제</a>
    </div>

    <div class="flex flex-col items-center gap-2">
      <div class="title text-center size-lg fontM">${article.title}</div>

      <div class="flex gap-2 text-gray-500 flex items-center ">
        <span class="size-sm">${article.extra_actorName}</span>
        <span class="size-sm">|</span>
        <span id="hitCount" class="size-sm">조회 : ${article.hit}</span>
        <span class="size-sm">|</span>
        <span id="hitCount" class="size-sm">추천 :
          ${article.goodReactionPoint}</span>
        <span class="size-sm">|</span>
        <span class="size-sm">${article.getForPrintType1RegDate()}</span>
      </div>

      <div class="bg-gray-100 p-2 rounded self-stretch"
        style="min-height: 140px">
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
          <a onclick="alert(this.title); return false;"
            title="좋아요 해제를 먼저해주세요" href="#"
            class="btn btn-xs btn-outline">싫어요 👎</a>
        </c:if>

        <c:if test="${actorCanCancleBadReactionPoint}">
          <a onclick="alert(this.title); return false;"
            title="싫어요 해제를 먼저해주세요" href="#"
            class="btn btn-xs btn-outline btn-primary">좋아요 👍</a>
          <a
            href="/usr/reactionPoint/doCancleBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
            class="btn btn-xs">싫어요 👎</a>
        </c:if>
      </div>
    </div>

    <article class="mt-4">
      <h3
        class="size-sm mb-2 border rounded rounded-full p-2 py-0 inline-block border-gray-700 text-gray-700">댓글
        ${repliesCount}개</h3>

      <c:if test="${repliesCount > 0}">
        <div class="mb-2 border-t">
          <ul>
            <c:forEach var="reply" items="${replies}">
              <li class="flex flex-col border-b py-1">
                <div class="size-sm">${reply.printBody}</div>
                <div class="flex itmes-center gap-1">
                  <span class="size-xs">${reply.extra_actorName}</span>
                  <span class="size-xs">${reply.getForPrintType1RegDate()}</span>
                  <c:if test="${reply.extra_actorCanModify}">
                    <a class="size-xs font-bold "
                      href="/usr/reply/modify?id=${reply.id}">수정</a>
                  </c:if>
                  <c:if test="${reply.extra_actorCanDelete}">
                    <a class="size-xs font-bold"
                      onclick="if(confirm('삭제하시겠습니까?') == false) return false;"
                      href="/usr/reply/doDelete?id=${reply.id}&relId=${reply.relId}">삭제</a>
                  </c:if>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <c:if test="${rq.loginedCheck}">
        <script>
									let replyWrite__submitFormDone = false;
									function replyWrite__submitForm(form) {

										if (replyWrite__submitFormDone)
											return;

										form.body.value = form.body.value
												.trim();

										if (form.body.value.length <= 1) {
											alert("댓글을 두 글자 이상 작성해주세요.");
											return;
										}

										replyWrite__submitFormDone = true;
										form.submit();
									}
								</script>

        <form action="/usr/reply/write" method="post"
          onsubmit="replyWrite__submitForm(this); return false;">
          <input type="hidden" name="relTypeCode" value="article" />
          <input type="hidden" name="relId" value="${article.id}" />
          <div class="flex flex-col">
            <div class="size-sm">${rq.loginedMember.nickname}</div>
            <div>
              <textarea name="body" id="" rows="2"
                class="w-full p-1 border" placeholder="댓글을 입력해주세요."
                required="required"></textarea>
            </div>
            <div class="flex">
              <div class="flex-grow"></div>
              <input type="submit" class="btn btn-xs btn-outline"
                value="댓글작성" />
            </div>
          </div>
        </form>
      </c:if>
      <c:if test="${rq.notLogin}">
        <div class="text-sm">
          댓글 작성은
          <a href="/usr/member/login" class="text-primary font-bold">로그인</a>
          이후 이용해주세요
        </div>
      </c:if>
    </article>

  </div>
</div>
<%@ include file="../common/tail.jspf"%>
