package com.webook.books.mapper;

import com.webook.books.domain.BooksMark;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Mapper
public interface BooksMarkMapper {
    @Insert("insert into books_bookmark(id, books_seq, books_mark) values(#{booksMark.id}, #{booksMark.books_seq}, #{booksMark.books_mark}) on duplicate key update books_mark = #{booksMark.books_mark}")
    public void save(@Param("booksMark") BooksMark booksMark);

    @Select("select * from books_bookmark where id = #{id} and books_seq = #{seq} order by ins_date desc")
    public List<BooksMark> findByBooks_seq(@Param("id") String id, @Param("seq") int seq);

    @Delete("delete from books_bookmark where id = #{booksMark.id} and books_seq = #{booksMark.books_seq} and books_mark = #{booksMark.books_mark}")
    public void delete(@Param("booksMark") BooksMark booksMark);
}
