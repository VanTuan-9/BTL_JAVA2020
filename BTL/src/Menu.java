import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    public static void IntroduceProduct(KhoHang khoHang)
    {
        int dem =0;
        String maSP;
        System.out.println("Nhập mã sản phẩm: ");
        maSP = sc.nextLine();
        for (int i = 0; i < khoHang.getDsHang().size(); i++) {
            if(maSP.equalsIgnoreCase(khoHang.getDsHang().get(i).getMaSP()))
            {
                System.out.println("Giới thiệu chi tiết về mĩ phẩm " + khoHang.getDsHang().get(i).getTenMP());
                for (int j = 0; j < khoHang.getDsHang().get(i).getGtHang().size(); j++) {
                    System.out.println("  " + khoHang.getDsHang().get(i).getGtHang().get(j));
                }
                dem = 1;
            }
        }
        if(dem == 0)
        {
            System.out.println("Không có Mĩ phẩm này trong kho hàng!\n");
        }
    }

    public static void AddProduct(KhoHang khoHang,HoaDon hoaDon)
    {
        ArrayList<HangMua> hangMuas = hoaDon.getHangMuas();
        String maSP;
        System.out.println("Nhập mã sản phẩm: ");
        int dem =0,dem1=0;
        maSP = sc.nextLine();
        for (int i = 0; i < khoHang.getDsHang().size(); i++) {
            if(maSP.equalsIgnoreCase(khoHang.getDsHang().get(i).getMaSP()))
            {
                for (int j = 0; j < hangMuas.size(); j++) {
                    if(maSP.equalsIgnoreCase(hangMuas.get(j).getMaMP()) && khoHang.getDsHang().get(i).getSoLuong() != 0)
                    {
                        hangMuas.get(j).setSoLuong(hangMuas.get(j).getSoLuong()+1);
                        hoaDon.setHangMuas(hangMuas);
                        khoHang.getDsHang().get(i).setSoLuong(khoHang.getDsHang().get(i).getSoLuong()-1);
                        dem=1;
                        break;
                    }
                }
                if(dem == 0)
                {
                    if(khoHang.getDsHang().get(i).getSoLuong() == 0)
                    {
                        System.out.println("Sản phẩm đã hết hàng!");
                        dem1 =1;
                    }
                    else
                    {
                        HangMua a = new HangMua();
                        a.setMaMP(khoHang.getDsHang().get(i).getMaSP());
                        a.setLoaiMP(khoHang.getDsHang().get(i).getLoaiMP());
                        a.setTenMP(khoHang.getDsHang().get(i).getTenMP());
                        a.setGiaNhap(khoHang.getDsHang().get(i).getGiaNhap());
                        hangMuas.add(a);
                        dem=1;
                        khoHang.getDsHang().get(i).setSoLuong(khoHang.getDsHang().get(i).getSoLuong()-1);
                    }
                    break;
                }

            }
            hoaDon.setHangMuas(hangMuas);
        }
        if(dem == 0 && dem1 == 0) System.out.println("Bạn nhập sai mã sản phẩm!");
        if(dem != 0 )   System.out.println("Đã thêm Mĩ phẩm này vào giỏ hàng!");
    }

    public static void DeleteProduct(KhoHang khoHang,HoaDon hoaDon)
    {
        int dem = 0;
        System.out.println("Nhập mã sản phẩm cần xóa khỏi giỏ hàng:");
        String maSP = sc.nextLine();
        for (int i = 0; i < hoaDon.getHangMuas().size(); i++) {
            if(maSP.equalsIgnoreCase(hoaDon.getHangMuas().get(i).getMaMP()))
            {
                for (int j = 0; j < khoHang.getDsHang().size(); j++) {
                    if(maSP.equalsIgnoreCase(khoHang.getDsHang().get(j).getMaSP())) {
                        khoHang.getDsHang().get(j).setSoLuong(khoHang.getDsHang().get(j).getSoLuong() + hoaDon.getHangMuas().get(i).getSoLuong());
                        break;
                    }
                }
                hoaDon.getHangMuas().remove(i);
                System.out.println("Đã xóa mặt hàng ra khỏi giỏ hàng!\n");
                dem++;
                break;
            }
        }
        if(dem == 0) System.out.println("Không có mặt hàng này trong giỏ hàng!\n");
    }


    public static void ListProducts(HoaDon hoaDon)
    {
        System.out.println("Danh sách mĩ phẩm đã thêm vào giỏ hàng:");
        if(hoaDon.getHangMuas().size() == 0) System.out.println("Không có mặt hàng nào trong giỏ hàng!\n");
        else
        {
            double tongTien =0;
            for (int i = 0; i < hoaDon.getHangMuas().size(); i++)
            {
                System.out.println("\nSP thứ "+ (i+1)+": ");
                System.out.println(hoaDon.getHangMuas().get(i));
                tongTien+= hoaDon.getHangMuas().get(i).getGiaNhap()*hoaDon.getHangMuas().get(i).getSoLuong();
            }
            System.out.println("Tổng tiền = "+tongTien);
        }
    }

    public static int PayProducts(HoaDon hoaDon,ArrayList<KhachHang> khachHangs,String maTV, int x)
    {
        if(hoaDon.getHangMuas().size() == 0) System.out.println("Không có mặt hàng nào trong giỏ!\nMời bạn tiếp tục mua sắm.");
        else
        {
            System.out.println("Bạn có muốn thanh toán không (Ấn 1 để thanh toán, Ấn 0 để hủy bỏ): ");
            String chon;
            chon =sc.nextLine();
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            if(chon.equals("1"))
            {
                hoaDon.setTenHD("HD"+ x++);
                hoaDon.setNgayMua(date.toString());
                hoaDon.OutputDSHang();
                System.out.println("Bạn có muốn dùng điểm để thanh toán không?(Ấn 1 để đồng ý, phám bất kì để hủy bỏ.)");
                chon = sc.nextLine();
                if(chon.equals("1"))
                {
                    for (int i = 0; i < khachHangs.size(); i++) {
                        if(maTV.equals(khachHangs.get(i).getId()))
                        {
                            System.out.println("Bạn có "+khachHangs.get(i).getScore()+" điểm tích lũy.");
                            if(khachHangs.get(i).getScore() == 0)
                                System.out.println("Mời bạn dùng tiền mặt để thanh toán!");
                            else
                            {
                                System.out.println("Bạn muốn dùng bao nhiêu điểm để thanh toán( 0 < điểm <= " +khachHangs.get(i).getScore()+" ):");
                                try
                                {
                                    int alpha = 0;
                                    for (int j = 0;; j++) {
                                        alpha = sc.nextInt();
                                        sc.nextLine();
                                        if(alpha > 0 && alpha <= khachHangs.get(i).getScore())
                                            break;
                                        else
                                            System.out.println("Nhập sai!");
                                    }
                                    khachHangs.get(i).setScore(khachHangs.get(i).getScore()-alpha);
                                    System.out.println("Số tiền còn lại cần phải thanh toán = "+ (hoaDon.getTongTien() - alpha*1000) + " đồng.");
                                    System.out.println("Số điểm còn lại = " + khachHangs.get(i).getScore());
                                }
                                catch (InputMismatchException e)
                                {
                                    System.out.println("Nhập sai!");
                                }
                            }
                        }
                    }
                }
            }
            int alpha = 0;
            for (int i = 0; i < hoaDon.getHangMuas().size(); i++) {
                alpha+= hoaDon.getHangMuas().get(i).getSoLuong();
            }

            for (int i = 0; i < khachHangs.size(); i++) {
                if(maTV.equals(khachHangs.get(i).getId()))
                {
                    khachHangs.get(i).setScore(khachHangs.get(i).getScore()+alpha);
                    System.out.println("Số điểm sau khi mua "+ alpha +" mĩ phẩm = "+khachHangs.get(i).getScore()+" điểm.");
                    break;
                }
            }
            hoaDon.getHangMuas().clear();
            System.out.println("Đã thanh toán!\nMỜi bạn tiếp tục mua sắm.\n");
        }
        return x;
    }

    public static void SaveWareHouseData(KhoHang khoHang,HoaDon hoaDon)
    {
        if(hoaDon.getHangMuas().size() != 0){
            for (int i = 0; i < hoaDon.getHangMuas().size(); i++) {
                for (int j = 0; j < khoHang.getDsHang().size(); j++) {
                    if(hoaDon.getHangMuas().get(i).getMaMP().equalsIgnoreCase(khoHang.getDsHang().get(j).getMaSP()))
                    {
                        khoHang.getDsHang().get(j).setSoLuong(khoHang.getDsHang().get(j).getSoLuong()+ hoaDon.getHangMuas().get(i).getSoLuong());
                        break;
                    }
                }
            }
        }
        try {
            FileWriter fileWriter = new FileWriter("KhoHang.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(khoHang.getDsHang().size()+ "");
            bufferedWriter.newLine();
            bufferedWriter.write("------------------------------------------------------");
            bufferedWriter.newLine();
            for (int i = 0; i < khoHang.getDsHang().size(); i++) {
                bufferedWriter.write(khoHang.getDsHang().get(i).getMaSP());
                bufferedWriter.newLine();
                bufferedWriter.write(khoHang.getDsHang().get(i).getLoaiMP());
                bufferedWriter.newLine();
                bufferedWriter.write(khoHang.getDsHang().get(i).getTenMP());
                bufferedWriter.newLine();
                bufferedWriter.write(khoHang.getDsHang().get(i).getMauMP());
                bufferedWriter.newLine();
                bufferedWriter.write(khoHang.getDsHang().get(i).getKhoiLuong());
                bufferedWriter.newLine();
                bufferedWriter.write(khoHang.getDsHang().get(i).getSoLuong() + "");
                bufferedWriter.newLine();
                bufferedWriter.write(khoHang.getDsHang().get(i).getGiaNhap() + "");
                bufferedWriter.newLine();
                for (int j = 0; j<khoHang.getDsHang().get(i).getGtHang().size(); j++) {
                    bufferedWriter.write(khoHang.getDsHang().get(i).getGtHang().get(j));
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("*");
                bufferedWriter.newLine();
                bufferedWriter.write("------------------------------------------------------");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SaveCustomerData(ArrayList<KhachHang> khachHangs)
    {
        try {
            FileWriter fileWriter = new FileWriter("KHThanThiet.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < khachHangs.size(); i++) {
                bufferedWriter.write(khachHangs.get(i).getId());
                bufferedWriter.newLine();
                bufferedWriter.write(khachHangs.get(i).getScore() + "");
                bufferedWriter.newLine();
                bufferedWriter.write("*");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AddNumberItem(KhoHang khoHang)
    {
        if(khoHang.getDsHang().size() == 0){
            System.out.println("Kho hàng trống!\nMời lựa chọn số 3 để thêm hàng.\n");
        }
        else{
            String maMP = null;
            int dem=0;
            do {
                dem =0;
                System.out.println("Nhập mã Mĩ phẩm(ấn 0 để trở lại menu): ");
                maMP = sc.nextLine();
                if(maMP.equals("0"))    break;
                for (int i = 0; i < khoHang.getDsHang().size(); i++) {
                    if(maMP.equalsIgnoreCase(khoHang.getDsHang().get(i).getMaSP()))
                    {
                        int a = 0;
                        try {
                            do {
                                System.out.println("Nhập số lượng sản phẩm được thêm vào: ");
                                a = sc.nextInt();
                                sc.nextLine();
                            }
                            while (a<0);
                            khoHang.getDsHang().get(i).setSoLuong(khoHang.getDsHang().get(i).getSoLuong()+ a);
                            System.out.println("Số lượng mĩ phẩm mã "+ khoHang.getDsHang().get(i).getMaSP()+" sau khi thêm = "+khoHang.getDsHang().get(i).getSoLuong());
                        }
                        catch (InputMismatchException e){
                            System.out.println("Nhập sai!");
                            sc.nextLine();
                        }
                        dem =1;
                        break;
                    }
                }
                if(dem == 0) System.out.println("Nhập sai mã mĩ phẩm!\nMời nhập lại:");
            }
            while (dem == 0);
        }
    }

    public static void UpdatePrice(KhoHang khoHang)
    {
        if(khoHang.getDsHang().size() == 0){
            System.out.println("Kho hàng trống!\nMời lựa chọn số 3 để thêm hàng.\n");
        }
        else {
            String maMP = null;
            int dem=0;
            do {
                dem =0;
                System.out.println("Nhập mã Mĩ phẩm(ấn 0 để trở lại menu): ");
                maMP = sc.nextLine();
                if(maMP.equals("0"))    break;
                for (int i = 0; i < khoHang.getDsHang().size(); i++) {
                    if(maMP.equalsIgnoreCase(khoHang.getDsHang().get(i).getMaSP()))
                    {
                        double a = 0;
                        try {
                            do {
                                System.out.println("Nhập giá nhập mới của mĩ phẩm: ");
                                a = sc.nextDouble();
                                sc.nextLine();
                            }
                            while (a<0);
                            khoHang.getDsHang().get(i).setGiaNhap(a);
                            System.out.println("Giá mĩ phẩm mã "+ khoHang.getDsHang().get(i).getMaSP()+" sau khi cập nhật = "+khoHang.getDsHang().get(i).getGiaNhap());
                        }
                        catch (InputMismatchException e){
                            System.out.println("Nhập sai!");
                            sc.nextLine();
                        }
                        dem =1;
                        break;
                    }
                }
                if(dem == 0) System.out.println("Nhập sai mã mĩ phẩm!\nMời nhập lại:");
            }
            while (dem == 0);
        }
    }

    public static void AddItem(KhoHang khoHang)
    {
        String maMP = null;
        int dem=0;
        do{
            do {
                dem = 0;
                System.out.println("Nhập mã Mĩ phẩm cần thêm(ấn 0 để trở lại menu): ");
                maMP = sc.nextLine();
                if(maMP.equals("0"))    break;
                for (int i = 0; i < khoHang.getDsHang().size(); i++) {
                    if (maMP.equalsIgnoreCase(khoHang.getDsHang().get(i).getMaSP())) {
                        System.out.println("Đã tồn tại mĩ phẩm này!\nMời nhập lại:");
                        dem =1;
                    }
                }
            }
            while (dem == 1);
            if(maMP.equals("0"))    break;
            TTHang a = new TTHang();
            a.setMaSP(maMP);
            a.Input();
            khoHang.getDsHang().add(a);
            System.out.println("Đã thêm!");
        }
        while (dem == 1);
    }

    public static void DeleteItem(KhoHang khoHang)
    {
        if(khoHang.getDsHang().size() == 0) {
            System.out.println("Kho hàng trống!");
        }
        else {
            String maMP = null;
            int dem=0;
            do {
                dem = 0;
                System.out.println("Nhập mã Mĩ phẩm cần xóa(ấn 0 để trở lại menu): ");
                maMP = sc.nextLine();
                if(maMP.equals("0"))    break;
                for (int i = 0; i < khoHang.getDsHang().size(); i++) {
                    if (maMP.equalsIgnoreCase(khoHang.getDsHang().get(i).getMaSP())) {
                        dem =1;
                        System.out.println("Đã xóa!");
                        khoHang.getDsHang().remove(i);
                        break;
                    }
                }
                if(dem == 0)
                    System.out.println("Không tồn tại mĩ phẩm này!\nMời nhập lại:");
            }
            while (dem == 0);
        }
    }

    public static void main(String[] args){
        KhoHang khoHang = new KhoHang();
        HoaDon hoaDon = new HoaDon();
        ArrayList<HangMua> hangMuas = new ArrayList<>();
        hoaDon.setHangMuas(hangMuas);

        System.out.println("------------------------DANH SÁCH CÁC CHỨC NĂNG------------------------");
        while (true)
        {
            String choce;
            System.out.println("\t 1. Dành cho Khách hàng." +
                                "\n\t 2. Dành cho chủ shop." +
                                "\n\t 0. Exit.");
            System.out.print("Mời nhập lựa chọn: ");
            choce = sc.nextLine();
            switch (choce)
            {
                case "1":
                    try {
                        khoHang.InPut();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(khoHang.getDsHang().size() == 0) {
                        System.out.println("Cửa hàng tạm hết hàng!\nMời bạn quay lại sau.");
                        System.exit(0);
                    }
                    ArrayList<KhachHang> khachHangs = new ArrayList<>();
                    try {
                        FileReader fileReader = new FileReader("KHThanThiet.txt");
                        sc = new Scanner(fileReader);
                        for (int i = 0; ; i++) {
                            if(!sc.hasNext())   break;
                            else
                            {
                                String x = sc.nextLine();
                                if(x.equals("*"))   continue;
                                KhachHang a = new KhachHang();
                                a.setId(x);
                                a.setScore(Integer.parseInt(sc.nextLine()));
                                khachHangs.add(a);
                            }
                        }
                        sc.close();
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sc = new Scanner(System.in);
                    int dem =0;
                    String maTV = null;
                    while (dem == 0)
                    {
                        System.out.print("Mời nhập mã thành viên(Nhấn 1 để tạo mã mới nếu chưa có):");
                        maTV = sc.nextLine();
                        if(maTV.equals("1"))
                        {
                            KhachHang a = new KhachHang();
                            while (dem == 0)
                            {
                                int x =0 ;
                                System.out.print("Mời bạn nhập mã TV mới: ");
                                maTV = sc.nextLine();
                                for (int i = 0; i < khachHangs.size(); i++) {
                                    if(maTV.equals(khachHangs.get(i).getId()))
                                    {
                                        System.out.println("Mã thành viên này đã tồn tại!\n" +
                                                "Mời nhập lại:");
                                        x =1 ;
                                        break;
                                    }
                                }
                                if(x == 0)
                                {
                                    a.setId(maTV);
                                    a.setScore(0);
                                    khachHangs.add(a);
                                    System.out.println("Đăng kí thành công!");
                                    dem=1;
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < khachHangs.size(); i++)
                                if (maTV.equals(khachHangs.get(i).getId())) {
                                    System.out.println("Điểm tích lũy = " + khachHangs.get(i).getScore() + " (1đ = 1000 VND.)");
                                    dem = 1;
                                    break;
                                }
                            if (dem == 0) System.out.println("Không có trong ds KH Thân Thiết! \n" +
                                    "Mời kiểm tra lại: \n");
                        }
                        if(dem != 0 )
                            System.out.println("Mời quý khách mua hàng! \n");

                    }
                    System.out.println("Press any key to continue!");
                    sc.nextLine();
                    khoHang.Output();
                    System.out.println();
                    int x =1;
                    while (true){
                        System.out.println("\n\t---------------------------------------------------------------------------" +
                                "\n\t 1. Giới thiệu về sản phẩm k(Nhập mã sp k)." +
                                "\n\t 2. Thêm sản phẩm k vào giỏ hàng(Nhập mã sp k)." +
                                "\n\t 3. Xóa sản phẩm k ra khỏi giỏ hàng(Nhạp mã sp k)." +
                                "\n\t 4. Hiển thị danh sách các sản phẩm đã thêm vào giỏ hàng và tổng tiền." +
                                "\n\t 5. Xác nhận thanh toán hay không." +
                                "\n\t 6. Hiển thị lại thông tin các mặt hàng." +
                                "\n\t 0. Exit.");
                        String chon;
                        System.out.print("Nhập lựa chọn của bạn: ");
                        chon = sc.nextLine();
                        switch (chon)
                        {
                            case "1": IntroduceProduct(khoHang);    break;
                            case "2": AddProduct(khoHang,hoaDon);    break;
                            case "3": DeleteProduct(khoHang,hoaDon);    break;
                            case "4": ListProducts(hoaDon);    break;
                            case "5": x = PayProducts(hoaDon,khachHangs,maTV,x);    break;
                            case "6": khoHang.Output(); break;
                            case "0":
                                SaveWareHouseData(khoHang,hoaDon);
                                SaveCustomerData(khachHangs);
                                System.exit(0);
                            default:
                                System.out.print("Nhập sai!");
                        }
                    }
                case "2":
                    try {
                        khoHang.InPut();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    while (true)
                    {
                        System.out.println("\n\t---------------------------------------------------------------------------" +
                                "\n\t 1. Thêm số lượng mặt hàng k." +
                                "\n\t 2. Cập nhật giá mặt hàng k." +
                                "\n\t 3. Thêm mặt hàng k vào khỏi kho hàng." +
                                "\n\t 4. Xóa mặt hàng k ra kho hàng." +
                                "\n\t 0. Exit.");

                        System.out.print("Nhập lựa chọn của bạn: ");
                        String choice = sc.nextLine();
                        switch (choice)
                        {
                            case "1":
                                AddNumberItem(khoHang);
                                break;
                            case "2":
                                UpdatePrice(khoHang);
                                break;
                            case "3":
                                AddItem(khoHang);
                                break;
                            case "4":
                                DeleteItem(khoHang);
                                break;
                            case "0":
                                SaveWareHouseData(khoHang,hoaDon);
                                System.exit(0);
                            default:
                                System.out.println("Nhập sai!");
                        }
                    }
                case "0":
                    SaveWareHouseData(khoHang,hoaDon);
                    System.exit(0);
                default:
                    System.out.println("Nhập sai!");
            }
        }
    }
}
