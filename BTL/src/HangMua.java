public class HangMua {
    private String maMP;
    private String loaiMP;
    private String tenMP;
    private double giaNhap;
    private int soLuong;
    private double tongGia;

    public HangMua() {
        maMP ="";
        loaiMP = "";
        tenMP = "";
        giaNhap =0;
        soLuong =1;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
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

    public String getLoaiMP() {
        return loaiMP;
    }

    public void setLoaiMP(String loaiMP) {
        this.loaiMP = loaiMP;
    }

    public String getMaMP() {
        return maMP;
    }

    public void setMaMP(String maMP) {
        this.maMP = maMP;
    }

    public String getTenMP() {
        return tenMP;
    }

    public void setTenMP(String tenMP) {
        this.tenMP = tenMP;
    }


    @Override
    public String toString() {
        return "\t maSP = " + maMP +
                "\n\t loaiMP = " + loaiMP +
                "\n\t tenMP = " + tenMP +
                "\n\t GiaBan = " + giaNhap +
                "\n\t SoLuong = " + soLuong;
    }
}
