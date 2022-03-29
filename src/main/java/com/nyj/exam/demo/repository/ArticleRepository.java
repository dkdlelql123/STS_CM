package com.nyj.exam.demo.repository;
 
import java.util.List;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nyj.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	 
	public void writeArticle(@Param("title") String title,@Param("body") String body, @Param("memberId") int memberId) ;
	
	public List<Article> getArticles();

	public List<Article> getForPrintArticles();
	
	@Select("""
	<script>
	SELECT A.*, B.nickname as extra_actorName 
	FROM article A 
	LEFT JOIN `member` B 
	ON A.memberId = B.id 
	<if test="boardId != 0">
			WHERE A.boardId = #{boardId}
	</if>
	ORDER BY A.id DESC
	</script>
	""")
	public List<Article> getForPrintArticlelist(@Param("boardId") int boardId);

	public Article getForPrintArticle(@Param("id") int id) ;
	
	public Article getArticle(@Param("id") int id) ;
	
	public void deleteArticle(@Param("id") int id) ;  

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	
	public int last_insert_id();
}
