package com.app.meenabazaar.meenabazaar.model;

/**
 * Created by lovishbajaj on 02/12/16.
 */

public class ArticleModel {
    private String articleNo;
    private String pcs;

    public ArticleModel(String ArticleNo , String ExtDescription){
        this.articleNo = ArticleNo;
        this.pcs = ExtDescription;
    }

    public String getarticleNo(){
        return this.articleNo;
    }
    public void setArticleNo(String ArticleNo){
        this.articleNo=ArticleNo;
    }

    public String getpcs() {
        return this.pcs;
    }
    public void setPcs(String ExtDescription){
        this.pcs=ExtDescription;
    }

}