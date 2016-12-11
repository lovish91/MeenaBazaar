package com.app.meenabazaar.meenabazaar.model;

/**
 * Created by lovishbajaj on 07/12/16.
 */

public class Article {
    private String ArticleId;
    private String ArticleNo;
    private String Quantity;
    private String Description;
    private String Msp;
    private String Mrp;
    private String PurchasePrise;

    public Article(){
        super();
    }

    public Article(String ArticleId , String ArticleNo, String Quantity, String Description, String Msp, String Mrp, String PurchasePrise){
        super();
        this.ArticleId = ArticleId;
        this.ArticleNo = ArticleNo;
        this.Quantity = Quantity;
        this.Description = Description;
        this.Msp = Msp;
        this.Mrp = Mrp;
        this.PurchasePrise = PurchasePrise;
    }

    public int hashCode() {
        int result = ArticleId.hashCode();
        result = 31 * result + ArticleNo.hashCode();
        result = 31 * result + Quantity.hashCode();
        result = 31 * result + Description.hashCode();
        result = 31 * result + Msp.hashCode();
        result = 31 * result + Mrp.hashCode();
        result = 31 * result + PurchasePrise.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        if (ArticleId != other.ArticleId)
            return false;
        return true;
    }

    public String getArticleId(){
        return ArticleId;
    }
    public void setArticleId(String ArticleId){
        this.ArticleId = ArticleId;
    }
    public String getArticleNo(){
        return ArticleNo;
    }
    public void setArticleNo(String ArticleNo){
        this.ArticleNo = ArticleNo;
    }
    public String getQuantity(){
        return Quantity;
    }
    public void setQuantity(String Quantity){
        this.Quantity = Quantity;
    }
    public String getDescription(){
        return Description;
    }
    public void setDescription(String Description){
        this.Description = Description;
    }
    public String getMsp(){
        return Msp;
    }
    public void setMsp(String Msp){
        this.Msp = Msp;
    }
    public String getMrp(){
        return Mrp;
    }
    public void setMrp(String Mrp){
        this.Mrp = Mrp;
    }
    public String getPurchasePrise(){
        return PurchasePrise;
    }
    public void setPurchasePrise(String PurchasePrise){
        this.PurchasePrise = PurchasePrise;
    }

    @Override
    public String toString() {
        return "Product [id=" + ArticleId + ", name=" + ArticleNo + ", description="
                + Description + ", price=" + PurchasePrise + "]";
    }
}
