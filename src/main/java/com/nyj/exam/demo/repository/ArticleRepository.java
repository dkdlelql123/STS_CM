package com.nyj.exam.demo.repository;
 
import java.util.List;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; 
 
import com.nyj.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	 
	public void writeArticle(@Param("title") String title,@Param("body") String body, @Param("memberId") int memberId) ;
	
	public List<Article> getArticles();

	public List<Article> getForPrintArticles();

	public Article getForPrintArticle(@Param("id") int id) ;
	
	public Article getArticle(@Param("id") int id) ;
	
	public void deleteArticle(@Param("id") int id) ;  

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	
	public int last_insert_id();
}
