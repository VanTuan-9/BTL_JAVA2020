import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KhoHang {
    private String maSP;
    private String loaiMP;
    private String tenMP;
    private String mauMP;
    private String khoiLuong;
    private int soLuong;
    private double giaNhap;
    private ArrayList<TTHang> dsHang;

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

    public ArrayList<TTHang> getDsHang() {
        return dsHang;
    }

    public void setDsHang(ArrayList<TTHang> dsHang) {
        this.dsHang = dsHang;
    }

    public void InPut() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("KhoHang.txt");
        Scanner sc = new Scanner(file);
        int n = Integer.parseInt(sc.nextLine());
        dsHang = new ArrayList<>();
        if(n != 0){
            for (int i = 0; i < n; i++) {
                sc.nextLine();
                maSP = sc.nextLine();
                loaiMP = sc.nextLine();
                tenMP = sc.nextLine();
                mauMP = sc.nextLine();
                khoiLuong = sc.nextLine();
                soLuong = Integer.parseInt(sc.nextLine());
                giaNhap = Double.parseDouble(sc.nextLine());
                ArrayList<String> gtHang = new ArrayList<>();
                for (int j = 0; ; j++) {
                    String x= sc.nextLine();
                    if(x.equals("*"))
                        break;
                    else
                        gtHang.add(x);

                }
                TTHang a = new TTHang(maSP,loaiMP,tenMP,mauMP,khoiLuong,soLuong,giaNhap,gtHang);
                if(a.getSoLuong() != 0)
                    dsHang.add(a);
            }
        }
        try {
            file.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Output()
    {
        System.out.println("Danh sách các mặt hàng: ");
        int x = 1;
        for (int i = 0; i < dsHang.size(); i++) {
            if(dsHang.get(i).getSoLuong() != 0)
            {
                System.out.println("Thông tin mặt hàng thứ "+(x++)+": ");
                System.out.println(dsHang.get(i));
            }
        }
    }
}
