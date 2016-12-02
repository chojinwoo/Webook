package com.webook.books.domain;

/**
 * Created by bangae1 on 2016-11-26.
 */
public class BooksMark {
    public String id;
    public Integer books_seq;
    public String books_mark;
    public String ins_date;

    public BooksMark() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBooks_seq() {

        return books_seq;
    }

    public void setBooks_seq(Integer books_seq) {
        this.books_seq = books_seq;
    }

    public String getBooks_mark() {
        return books_mark;
    }

    public void setBooks_mark(String books_mark) {
        this.books_mark = books_mark;
    }

    public String getIns_date() {
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    @Override
    public String toString() {
        return "BooksMark{" +
                "books_seq=" + books_seq +
                ", books_mark='" + books_mark + '\'' +
                ", ins_date='" + ins_date + '\'' +
                '}';
    }
}
