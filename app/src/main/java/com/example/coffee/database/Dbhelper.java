package com.example.coffee.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context) {
        super(context, "COFFEE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qLS = "create table Loai(maloai integer primary key autoincrement, tenloai text)";
        sqLiteDatabase.execSQL(qLS);

        sqLiteDatabase.execSQL("insert into Loai values(1,'Cà phê'),(2,'Trà sửa'),(3,'Trà')");

        String qND ="create table ThuNgan(matn text primary key, matkhau text, tentn text, loaitaikhoan text)";
        sqLiteDatabase.execSQL(qND);

        String qSize ="create table Size(masize integer primary key autoincrement, tensize text, dongia integer,madouong integer references DoUong(madouong))";
        sqLiteDatabase.execSQL(qSize);

        String qS = "create table DoUong(madouong integer primary key autoincrement, tendouong text, thongtin text,trangthai integer, " +
                "maloai integer references Loai(maloai), avatar text)";
        sqLiteDatabase.execSQL(qS);


        sqLiteDatabase.execSQL("insert into ThuNgan values ('Hoang','Hoang','Nguyễn Văn Bảo Hoàng','Admin')," +
                "('thungan','thungan','Nguyễn Văn A','ThuNgan')");



        sqLiteDatabase.execSQL("insert into DoUong values(1,'Cà phê đen','Tiếp theo, bạn châm tiếp 75ml nước sôi vào phin. Và đậy nắp chờ ra 50ml nước cốt cafe.',0,1,'ca_phe_den'),(2,'Trà sửa Thái','Tiếp theo, bạn châm tiếp 75ml nước sôi vào phin. Và đậy nắp chờ ra 50ml nước cốt cafe.',0,2,'tra_sua_thai'),(3,'Trà đào','abc',1,3,'tra_dao')," +
                "(4,'Cà phê sửa','Tiếp theo, bạn châm tiếp 75ml nước sôi vào phin. Và đậy nắp chờ ra 50ml nước cốt cafe.',0,1,'ca_phe_sua'),(5,'Trà chanh','Tiếp theo, bạn châm tiếp 75ml nước sôi vào phin. Và đậy nắp chờ ra 50ml nước cốt cafe.',0,3,'tra_chanh'),(6,'Trà sửa trân châu','Tiếp theo, bạn châm tiếp 75ml nước sôi vào phin. Và đậy nắp chờ ra 50ml nước cốt cafe.',1,2,'tra_sua_tran_chau'),(7,'Bạc xỉu','abc',0,1,'bac_xiu'),(8,'Trà sửa Hong Kong','Tiếp theo, bạn châm tiếp 75ml nước sôi vào phin. Và đậy nắp chờ ra 50ml nước cốt cafe.',0,2,'tra_sua_hong_kong')");

        sqLiteDatabase.execSQL("insert into Size values (1,'Size S',20000,1),(2,'Size M',30000,1),(3,'Size L',40000,1)," +
                "(4,'Size S',30000,2),(5,'Size M',40000,2),(6,'Size L',50000,2)," +
                "(7,'Size S',40000,3),(8,'Size M',50000,3),(9,'Size L',60000,3)," +
                "(10,'Size S',30000,4),(11,'Size M',50000,4),(12,'Size L',60000,4)," +
                "(13,'Size S',35000,5),(14,'Size M',45000,5),(15,'Size L',60000,5)," +
                "(16,'Size S',30000,6),(17,'Size M',40000,6),(18,'Size L',50000,6)," +
                "(19,'Size S',40000,7),(20,'Size M',50000,7),(21,'Size L',60000,7)," +
                "(22,'Size S',30000,8),(23,'Size M',55000,8),(24,'Size L',75000,8);");

        String qHD ="create table HoaDon(mahoadon integer primary key autoincrement, ngaymua text, matn text references ThuNgan(matn),trangthai integer)";
        sqLiteDatabase.execSQL(qHD);

        String qCTHD = "create table CTHD(macthd integer primary key autoincrement, mahoadon integer references HoaDon(mahoadon), soluong integer, ghichu text, giatien integer, masize integer references Size(masize))";
        sqLiteDatabase.execSQL(qCTHD);

        sqLiteDatabase.execSQL("insert into HoaDon values(1,'01/11/2023','hoang',1),(2,'02/11/2023','hoang',1),(3,'03/11/2023','hoang',1)");
        sqLiteDatabase.execSQL("insert into CTHD values(1,1,2,'Thêm đá',40000,1),(2,1,3,'Ít đường',120000,7),(3,2,2,'Thêm đá',80000,5),(4,3,2,'Thêm đá',80000,5)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("drop table if exists ThuNgan");
            db.execSQL("drop table if exists DoUong");
            db.execSQL("drop table if exists Loai");
            db.execSQL("drop table if exists HoaDon");
            db.execSQL("drop table if exists Size");
            db.execSQL("drop table if exists CTHD");
            onCreate(db);
        }

    }
}
