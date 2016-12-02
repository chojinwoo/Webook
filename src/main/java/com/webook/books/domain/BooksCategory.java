package com.webook.books.domain;

/**
 * Created by bangae1 on 2016-12-01.
 */
public class BooksCategory {
    private String books_category_cd;
    private String books_category_nm;

    public BooksCategory() {
    }

    public String getBooks_category_cd() {

        return books_category_cd;
    }

    public void setBooks_category_cd(String books_category_cd) {
        this.books_category_cd = books_category_cd;
    }

    public String getBooks_category_nm() {
        return books_category_nm;
    }

    public void setBooks_category_nm(String books_category_nm) {
        this.books_category_nm = books_category_nm;
    }

    @Override
    public String toString() {
        return "BooksCategory{" +
                "books_category_cd='" + books_category_cd + '\'' +
                ", books_category_nm='" + books_category_nm + '\'' +
                '}';
    }
}
