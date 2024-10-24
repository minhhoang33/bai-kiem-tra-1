package BaiKiemTra_1;

import java.util.ArrayList;

class SalesRecord {
    private Salesperson salesperson;
    private ArrayList<Product> productsSold;

    public SalesRecord(Salesperson salesperson) {
        this.salesperson = salesperson;
        this.productsSold = new ArrayList<>();
    }

    public Salesperson getSalesperson() {
        return salesperson;
    }

    public ArrayList<Product> getProductsSold() {
        return productsSold;
    }

    // Thêm mặt hàng bán
    public boolean addProduct(Product product) {
        if (productsSold.size() < 5) {
            for (Product p : productsSold) {
                if (p.getProductID() == product.getProductID()) {
                    System.out.println("Sản phẩm đã tồn tại trong danh sách.");
                    return false;
                }
            }
            productsSold.add(product);
            return true;
        } else {
            System.out.println("Nhân viên đã bán đủ 5 mặt hàng.");
            return false;
        }
    }

    public void displayRecord() {
        System.out.println("Nhân viên: " + salesperson.getName());
        for (Product p : productsSold) {
            p.displayInfo();
        }
    }

    // Tính doanh thu của nhân viên này
    public double calculateTotalRevenue() {
        double total = 0;
        for (Product p : productsSold) {
            total += p.getPrice();
        }
        return total;
    }
}
