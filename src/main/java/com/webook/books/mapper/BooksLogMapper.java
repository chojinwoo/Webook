package com.webook.books.mapper;

import com.webook.books.domain.BooksLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Mapper
public interface BooksLogMapper {
    @Insert("insert into books_log(id, books_seq, books_nm) values(#{booksLog.id}, #{booksLog.books_seq}, (select books_nm from books where books_seq = #{booksLog.books_seq})) on duplicate key update ins_date = now()")
    public void save(@Param("booksLog") BooksLog booksLog);

    @Select("select a.id, a.books_seq, a.ins_date, a.books_nm from (SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, log.* FROM books_log log, (SELECT @ROWNUM := 0) r WHERE id = #{id}) a where a.ROWNUM <= 20 order by ins_date desc\n")
    public List<BooksLog> findByid(@Param("id") String id);

    @Select("select * from books_log where id = #{id} order by ins_date desc")
    public List<BooksLog> findAll(@Param("id") String id);
}
