package br.com.alura.clientelo.store.product.vo;

public class TopSellingProductsVO {

    private String category;
    private String product;
    private long quantity;

    public TopSellingProductsVO(String category, String product, long quantity) {
        this.category = category;
        this.product = product;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }
}
