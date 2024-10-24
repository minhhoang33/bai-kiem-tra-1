package BaiKiemTra_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SalesManagementSystem {
    private static ArrayList<Product> productList = new ArrayList<>();
    private static ArrayList<Salesperson> salespersonList = new ArrayList<>();
    private static ArrayList<SalesRecord> salesRecordList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static java.util.Comparator<? super SalesRecord> Comparator;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Quản lý Bán hàng ----");
            System.out.println("1 - Nhập danh sách mặt hàng mới");
            System.out.println("2 - In danh sách mặt hàng");
            System.out.println("3 - Nhập danh sách nhân viên");
            System.out.println("4 - In danh sách nhân viên");
            System.out.println("5 - Lập bảng danh sách bán hàng");
            System.out.println("6 - Sắp xếp bảng danh sách bán hàng");
            System.out.println("7 - Lập bảng kê doanh thu cho mỗi nhân viên");
            System.out.println("0 - Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    addNewSalesperson();
                    break;
                case 4:
                    displayAllSalespersons();
                    break;
                case 5:
                    createSalesRecord();
                    break;
                case 6:
                    sortSalesRecords();
                    break;
                case 7:
                    displayRevenueReport();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayRevenueReport() {
        System.out.println("\n---- Bảng kê doanh thu cho mỗi nhân viên ----");

        // Danh sách để lưu doanh thu cho mỗi nhân viên
        ArrayList<Salesperson> distinctSalespersons = new ArrayList<>();
        ArrayList<Double> revenueList = new ArrayList<>();


        for (SalesRecord sr : salesRecordList) {
            Salesperson salesperson = sr.getSalesperson();
            double revenue = sr.calculateTotalRevenue();


            int index = distinctSalespersons.indexOf(salesperson);
            if (index == -1) {

                distinctSalespersons.add(salesperson);
                revenueList.add(revenue);
            } else {

                double currentRevenue = revenueList.get(index);
                revenueList.set(index, currentRevenue + revenue);
            }
        }


        for (int i = 0; i < distinctSalespersons.size(); i++) {
            Salesperson salesperson = distinctSalespersons.get(i);
            double totalRevenue = revenueList.get(i);
            System.out.println("Nhân viên: " + salesperson.getName() + " (Mã NV: " + salesperson.getEmployeeID() + ") - Doanh thu: " + totalRevenue + " VND");
        }
    }

    // Nhập danh sách mặt hàng mới
    private static void addNewProduct() {
        System.out.print("Nhập tên mặt hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập nhóm mặt hàng (Điện tử, Điện lạnh, Máy tính, Thiết bị văn phòng): ");
        String group = scanner.nextLine();
        System.out.print("Nhập giá bán: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        productList.add(new Product(name, group, price, quantity));
        System.out.println("Thêm mặt hàng thành công!");
    }

    // In ra danh sách các mặt hàng đã có
    private static void displayAllProducts() {
        System.out.println("Danh sách các mặt hàng:");
        for (Product p : productList) {
            p.displayInfo();
        }
    }

    // Nhập danh sách nhân viên
    private static void addNewSalesperson() {
        System.out.print("Nhập tên nhân viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        System.out.print("Nhập ngày ký hợp đồng: ");
        String contractDate = scanner.nextLine();

        salespersonList.add(new Salesperson(name, address, phone, contractDate));
        System.out.println("Thêm nhân viên thành công!");
    }

    // In ra danh sách nhân viên đã có
    private static void displayAllSalespersons() {
        System.out.println("Danh sách các nhân viên:");
        for (Salesperson sp : salespersonList) {
            sp.displayInfo();
        }
    }

    // Lập bảng danh sách bán hàng
    private static void createSalesRecord() {
        System.out.print("Nhập mã nhân viên: ");
        int employeeID = Integer.parseInt(scanner.nextLine());

        Salesperson salesperson = null;
        for (Salesperson sp : salespersonList) {
            if (sp.getEmployeeID() == employeeID) {
                salesperson = sp;
                break;
            }
        }

        if (salesperson == null) {
            System.out.println("Nhân viên không tồn tại.");
            return;
        }

        SalesRecord salesRecord = new SalesRecord(salesperson);

        while (true) {
            System.out.print("Nhập mã mặt hàng (0 để dừng): ");
            int productID = Integer.parseInt(scanner.nextLine());

            if (productID == 0) {
                break;
            }

            Product product = null;
            for (Product p : productList) {
                if (p.getProductID() == productID) {
                    product = p;
                    break;
                }
            }

            if (product == null) {
                System.out.println("Mặt hàng không tồn tại.");
            } else {
                if (salesRecord.addProduct(product)) {
                    System.out.println("Thêm mặt hàng vào bảng bán hàng thành công!");
                }
            }
        }

        salesRecordList.add(salesRecord);
    }

    // Sắp xếp bảng danh sách bán hàng
    private static void sortSalesRecords() {
        System.out.println("Sắp xếp theo:");
        System.out.println("1 - Tên nhân viên");
        System.out.println("2 - Nhóm mặt hàng");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                // Sắp xếp theo tên nhân viên
                Collections.sort(salesRecordList, java.util.Comparator.comparing(sr -> sr.getSalesperson().getName()));
                System.out.println("Danh sách bán hàng sau khi sắp xếp theo tên nhân viên:");
               displayRevenueReport();
                break;
        }

    }
}


