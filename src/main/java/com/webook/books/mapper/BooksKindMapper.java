package com.webook.books.mapper;

import com.webook.books.domain.BooksKind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Mapper
public interface BooksKindMapper {
    @Select("select * from books_kind")
    public List<BooksKind> findAll();
    @Select("select * from books_kind where books_kind_cd = #{books_kind_cd}")
    public BooksKind findOne(String books_kind_cd);
    @Select("insert into books_kind(books_kind_cd, books_kind_nm, books_kind_path,books_category_cd, ins_date) values(#{booksKind.books_kind_cd}, #{booksKind.books_kind_nm}, #{booksKind.books_kind_path}, #{booksKind.books_category_cd}, #{booksKind.ins_date})")
    public void save(@Param("booksKind")BooksKind booksKind);
    @Update("update books_kind set books_kind_nm = #{booksKind.books_kind_cd}, books_kind_nm = #{booksKind.books_kind_nm} , books_kind_path = #{booksKind.books_kind_path}, books_category_cd = #{booksKind.books_category_cd} where books_kind_cd = #{booksKind.books_kind_cd}")
    public void update(@Param("booksKind")BooksKind booksKind);
}
