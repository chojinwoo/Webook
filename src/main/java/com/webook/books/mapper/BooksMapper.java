package com.webook.books.mapper;

import com.webook.books.domain.Books;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Mapper
public interface BooksMapper {
    @Select("select * from books")
    public List<Books> findAll();
    @Select("select * from books where books_seq = #{seq}")
    public Books findByBookSeq(@Param("seq") int seq);
    @Select("select * from books where books_kind_cd = #{boos_kind_cd}")
    public List<Books> findByBooksKindCd(@Param("boos_kind_cd") String boos_kind_cd);
    @Insert("insert into books(books_kind_cd, books_nm, books_url, books_file_nm) values(#{books.books_kind_cd}, #{books.books_nm}, #{books.books_url}, #{books.books_file_nm})")
    public void save(@Param("books")Books books);
    @Update("update books set books_nm = #{books.books_nm}, books_url = #{books.books_url}, books_file_nm = #{books.books_file_nm} where books_seq = #{books.books_seq}")
    public void update(@Param("books")Books books);
}
