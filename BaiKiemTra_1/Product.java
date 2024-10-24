package BaiKiemTra_1;

class Product {
    private static int productIDCounter = 1000;
    private int productID;
    private String productName;
    private String productGroup;
    private double price;
    private int quantity;

    public Product(String productName, String productGroup, double price, int quantity) {
        this.productID = productIDCounter++;
        this.productName = productName;
        this.productGroup = productGroup;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public double getPrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println("Mã hàng: " + productID + ", Tên hàng: " + productName + ", Nhóm hàng: " + productGroup + ", Giá bán: " + price + ", Số lượng: " + quantity);
    }
}
