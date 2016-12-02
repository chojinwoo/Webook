package com.webook.books.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bangae1 on 2016-11-26.
 */
public class Books {

    private Integer books_seq;
    private String books_kind_cd;
    private String books_nm;
    private String ins_date;
    private String books_url;
    private String books_file_nm;
    private MultipartFile file;

    public Books() {
    }

    public Integer getBooks_seq() {

        return books_seq;
    }

    public void setBooks_seq(Integer books_seq) {
        this.books_seq = books_seq;
    }

    public String getBooks_kind_cd() {
        return books_kind_cd;
    }

    public void setBooks_kind_cd(String books_kind_cd) {
        this.books_kind_cd = books_kind_cd;
    }

    public String getBooks_nm() {
        return books_nm;
    }

    public void setBooks_nm(String books_nm) {
        this.books_nm = books_nm;
    }

    public String getIns_date() {
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    public String getBooks_url() {
        return books_url;
    }

    public void setBooks_url(String books_url) {
        this.books_url = books_url;
    }

    public String getBooks_file_nm() {
        return books_file_nm;
    }

    public void setBooks_file_nm(String books_file_nm) {
        this.books_file_nm = books_file_nm;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Books{" +
                "books_seq=" + books_seq +
                ", books_kind_cd='" + books_kind_cd + '\'' +
                ", books_nm='" + books_nm + '\'' +
                ", ins_date='" + ins_date + '\'' +
                ", books_url='" + books_url + '\'' +
                '}';
    }
}
