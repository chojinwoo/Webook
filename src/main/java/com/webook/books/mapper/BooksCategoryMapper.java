package com.webook.books.mapper;

import com.webook.books.domain.BooksCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by bangae1 on 2016-12-01.
 */
@Mapper
public interface BooksCategoryMapper {
    @Select("select * from books_category")
    public List<BooksCategory> findAll();
}
