package BaiKiemTra_1;

class Salesperson extends Person {
    private static int idCounter = 1000;
    private int employeeID;
    private String contractDate;

    public Salesperson(String name, String address, String phoneNumber, String contractDate) {
        super(name, address, phoneNumber);
        this.contractDate = contractDate;
        this.employeeID = idCounter++;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public void displayInfo() {
        System.out.println("Mã NV: " + employeeID + ", Họ tên: " + name + ", Địa chỉ: " + address + ", Số ĐT: " + phoneNumber + ", Ngày ký hợp đồng: " + contractDate);
    }
}
