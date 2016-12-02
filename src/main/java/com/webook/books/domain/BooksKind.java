package com.webook.books.domain;

/**
 * Created by bangae1 on 2016-11-26.
 */
public class BooksKind {
    private String books_kind_cd;
    private String books_kind_nm;
    private String books_kind_path;
    private String books_category_cd;
    private String ins_date;

    public BooksKind() {
    }

    public String getBooks_kind_cd() {

        return books_kind_cd;
    }

    public void setBooks_kind_cd(String books_kind_cd) {
        this.books_kind_cd = books_kind_cd;
    }

    public String getBooks_kind_nm() {
        return books_kind_nm;
    }

    public void setBooks_kind_nm(String books_kind_nm) {
        this.books_kind_nm = books_kind_nm;
    }

    public String getBooks_kind_path() {
        return books_kind_path;
    }

    public void setBooks_kind_path(String books_kind_path) {
        this.books_kind_path = books_kind_path;
    }

    public String getBooks_category_cd() {
        return books_category_cd;
    }

    public void setBooks_category_cd(String books_category_cd) {
        this.books_category_cd = books_category_cd;
    }

    public String getIns_date() {
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    @Override
    public String toString() {
        return "BooksKind{" +
                "books_kind_cd='" + books_kind_cd + '\'' +
                ", books_kind_nm='" + books_kind_nm + '\'' +
                ", ins_date='" + ins_date + '\'' +
                '}';
    }
}
