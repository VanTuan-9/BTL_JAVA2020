import java.util.ArrayList;
import java.util.Scanner;

public class TTHang {
    private String maSP;
    private String loaiMP;
    private String tenMP;
    private String mauMP;
    private String khoiLuong;
    private int soLuong;
    private double giaNhap;
    private ArrayList<String> gtHang = new ArrayList<>();

    public ArrayList<String> getGtHang() {
        return gtHang;
    }

    public void setGtHang(ArrayList<String> gtHang) {
        this.gtHang = gtHang;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getLoaiMP() {
        return loaiMP;
    }

    public void setLoaiMP(String loaiMP) {
        this.loaiMP = loaiMP;
    }

    public String getTenMP() {
        return tenMP;
    }

    public void setTenMP(String tenMP) {
        this.tenMP = tenMP;
    }

    public String getMauMP() {
        return mauMP;
    }

    public void setMauMP(String mauMP) {
        this.mauMP = mauMP;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public void Input()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin sản phẩm mới(không có thông tin ghi None): ");
        System.out.print("Loại sản phẩm: ");
        loaiMP = sc.nextLine();
        System.out.print("Tên sản phẩm: ");
        tenMP = sc.nextLine();
        System.out.print("Màu sản phẩm: ");
        mauMP = sc.nextLine();
        System.out.print("Khối lượng sản phẩm: ");
        khoiLuong = sc.nextLine();
        System.out.print("Số lượng sản phẩm: ");
        soLuong = sc.nextInt();
        sc.nextLine();
        System.out.print("Giá nhập của sản phẩm: ");
        giaNhap = sc.nextDouble();
        sc.nextLine();
        System.out.println("Thông tin sản phẩm(nhập xong nhập * để dừng):");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; ; i++) {
            String a = sc.nextLine();
            if(a.equals("*"))   break;
            arrayList.add(a.trim());
        }
        String a ="";
        for (int i = 0; i < arrayList.size(); i++) {
            a = a.concat(" ");
            a = a.concat(arrayList.get(i));
        }
        a.trim();
        String[] alpha =a.split(" ");
        for (int i = 0; i < alpha.length; i++)
            alpha[i].trim();
        for (int i = 0;
                alpha.length%22 == 0?i<alpha.length/22:i<=alpha.length/22
                ; i++) {
            a="";
            int y =alpha.length - 22*i;
            if(y>=22)
                for (int j = i*22; j < i*22+22; j++) {
                    a = a.concat(" ");
                    a = a.concat(alpha[j]);
                }
            else
                for (int j = i*22; j < alpha.length; j++) {
                    a = a.concat(" ");
                    a = a.concat(alpha[j]);
                }
            gtHang.add(a);
        }
    }

    public TTHang() {
    }

    public TTHang(String maSP, String loaiMP, String tenMP, String mauMP, String khoiLuong, int soLuong, double giaNhap, ArrayList<String> gtHang) {
        this.maSP = maSP;
        this.loaiMP = loaiMP;
        this.tenMP = tenMP;
        this.mauMP = mauMP;
        this.khoiLuong = khoiLuong;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.gtHang = gtHang;
    }

    @Override
    public String toString() {
        return  "\t maSP = " + maSP +
                "\n\t loaiMP = " + loaiMP +
                "\n\t tenMP = " + tenMP +
                "\n\t mauMP = " + mauMP +
                "\n\t khoiLuong = " + khoiLuong +
                "\n\t soLuong = " + soLuong +
                "\n\t giaNhap = " + giaNhap;
    }
}
