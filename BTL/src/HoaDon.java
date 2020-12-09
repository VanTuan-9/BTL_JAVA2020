import java.util.ArrayList;

public class HoaDon {
    private String tenHD;
    private String ngayMua;
    private double tongTien;
    private ArrayList<HangMua> hangMuas;

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public ArrayList<HangMua> getHangMuas() {
        return hangMuas;
    }

    public void setHangMuas(ArrayList<HangMua> hangMuas) {
        this.hangMuas = hangMuas;
    }

    public void OutputDSHang()
    {
        tongTien =0;
        System.out.println("\t Tên hóa đơn: " + tenHD +
                "\n\t Ngày mua: " + ngayMua);
        for (int i = 0; i < hangMuas.size(); i++) {
            System.out.println("Sản phẩm thứ "+(i+1)+": ");
            System.out.println(hangMuas.get(i));
            hangMuas.get(i).setTongGia(hangMuas.get(i).getGiaNhap()*hangMuas.get(i).getSoLuong());
            tongTien+=hangMuas.get(i).getTongGia();
        }
        System.out.println("Tổng tiền = "+tongTien +"\n");
    }

    @Override
    public String toString() {
        return  "\n\t tenHD = " + tenHD +
                "\n\t ngayMua = " + ngayMua ;
    }
}
