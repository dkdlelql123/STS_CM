package com.nyj.exam.demo.repository;
 
import java.util.List;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nyj.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	 
	public void writeArticle(@Param("title") String title,@Param("body") String body, @Param("boardId") int boardId, @Param("memberId") int memberId) ;
	
	public List<Article> getArticles();

	public List<Article> getForPrintArticles();
	
	@Select("""
	<script>
	SELECT A.*,
	IFNULL(SUM(RP.point), 0) AS `extra_sumPoint`,
	IFNULL(SUM(IF(RP.point &gt; 0, RP.point, 0)), 0) AS `extra_goodPoint`,
	IFNULL(SUM(IF(RP.point &lt; 0, RP.point, 0)), 0) AS `extra_badPoint` 
	FROM (
		SELECT A.*, B.nickname as `extra_actorName`
		FROM article A 
		LEFT JOIN `member` B 
		ON A.memberId = B.id 
		<if test="boardId != 0">
			WHERE A.boardId = #{boardId}
			<if test="searchKeyword != ''">
				<choose>
					<when test="searchType == 'title'">
						AND title LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<when test="searchType == 'body'">
						AND body LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<otherwise>
						AND(
							title LIKE CONCAT('%',#{searchKeyword},'%')
						OR
							body LIKE CONCAT('%',#{searchKeyword},'%')
						)
					</otherwise>	
				</choose>
			</if>
		</if>
		<if test="limitCount != -1">
		LIMIT #{limitStart}, #{limitCount}
		</if>
	) AS A
	LEFT JOIN `reactionPoint` AS RP
	ON A.id = RP.relId 
	AND RP.relTypeCode = 'article'  
	GROUP BY A.id
	ORDER BY A.id DESC
	</script>
	""")
	public List<Article> getForPrintArticlelist(int boardId,int limitStart,int limitCount, String searchType, String searchKeyword);

	@Select("""
	<script>
	SELECT A.*, B.nickname as `extra_actorName`,
	IFNULL(SUM(RP.point), 0) AS `extra_sumPoint`,
	IFNULL(SUM(IF(RP.point &gt; 0, RP.point, 0)), 0) AS `extra_goodPoint`,
	IFNULL(SUM(IF(RP.point &lt; 0, RP.point, 0)), 0) AS `extra_badPoint`  
	FROM article A 
	LEFT JOIN `member` B 
	ON A.memberId = B.id
	LEFT JOIN `reactionPoint` AS RP
	ON A.id = RP.relId 
	AND RP.relTypeCode = 'article'  
	WHERE A.id = #{id} 
	</script>
	""")
	public Article getForPrintArticle(@Param("id") int id) ;
	
	public Article getArticle(@Param("id") int id) ;
	
	public void deleteArticle(@Param("id") int id) ;  

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	
	public int last_insert_id();

	@Select("""
    <script>
	SELECT count(*) as count 
	FROM article  
	<if test="boardId != 0">
		WHERE boardId = #{boardId}
		<if test="searchKeyword != ''">
			<choose>
				<when test="searchType == 'title'">
					AND title LIKE CONCAT('%',#{searchKeyword},'%')
				</when>
				<when test="searchType == 'body'">
					AND body LIKE CONCAT('%',#{searchKeyword},'%')
				</when>
				<otherwise>
					AND(
						title LIKE CONCAT('%',#{searchKeyword},'%')
					OR
						body LIKE CONCAT('%',#{searchKeyword},'%')
					)
				</otherwise>	
			</choose>
		</if>
	</if>
	</script>
	""")
	public int getArticleListCount(int boardId, String searchType, String searchKeyword);

	@Update("""
	UPDATE article
	SET hit = hit + 1
	WHERE id = #{id}
	""")
	public int hitIncreased(int id);

	@Select("""
	SELECT hit
	FROM article
	WHERE id = #{id}
	""")
	public int findHitCount(int id);

	@Select("""
	SELECT IFNULL(SUM(RP.point), 0) AS `extra_sumPoint`
	FROM `reactionPoint` AS RP
	WHERE RP.relTypeCode = 'article'
	AND RP.relId = #{articleId}
	AND RP.memberId = #{memberId}
	""")
	public int actorCanMakeReactionPoint(int articleId, int memberId);
}
