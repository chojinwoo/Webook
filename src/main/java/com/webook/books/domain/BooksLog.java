package com.webook.books.domain;

/**
 * Created by bangae1 on 2016-11-26.
 */
public class BooksLog {
    public String id;
    public int books_seq;
    public String books_nm;
    public int finish;
    public String ins_date;

    public BooksLog() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBooks_seq() {
        return books_seq;
    }

    public void setBooks_seq(int books_seq) {
        this.books_seq = books_seq;
    }

    public String getBooks_nm() {
        return books_nm;
    }

    public void setBooks_nm(String books_nm) {
        this.books_nm = books_nm;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getIns_date() {
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    @Override
    public String toString() {
        return "BooksLog{" +
                "id='" + id + '\'' +
                ", books_seq=" + books_seq +
                ", ins_date='" + ins_date + '\'' +
                '}';
    }
}
