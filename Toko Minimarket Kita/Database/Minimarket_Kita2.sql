CREATE DATABASE Minimarket_Kita
ON
( NAME = Minimarket_Kita_dat,
FILENAME = 'D:\Aliya\Tugas\RPL\Database\Minimarket_Kita.mdf',
SIZE = 10,
MAXSIZE = 50,
FILEGROWTH =5)

LOG ON
( NAME = Minimarket_Kita_log,
FILENAME = 'D:\Aliya\Tugas\RPL\Database\Minimarket_Kita.ldf',
SIZE = 5 MB,
MAXSIZE = 25 MB,
FILEGROWTH =5 MB )




CREATE TABLE Barang(
Kd_Barang CHAR (5) PRIMARY KEY ,
Nama_Barang VARCHAR(50) NOT NULL,
Satuan INT,
Harga_Beli INT,
Harga_Jual INT,
Stok_Barang INT,
Kd_sup char (5) 
)



CREATE TABLE Supplier(
Kd_Sup CHAR (5) PRIMARY KEY ,
Nama_Sup VARCHAR(50) NOT NULL,
Alamat_Sup VARCHAR(50),
Telp_Sup VARCHAR(14),
)



CREATE TABLE Kasir(
Kd_Kasir CHAR (5) PRIMARY KEY ,
Nama_Kasir VARCHAR(50) NOT NULL,
Alamat vARCHAR (30) NOT NULL,
Telp Varchar (14) NOT NULL,
Username Varchar (30) NOT NULL,
Passwords Varchar (30),
Hak_Akses VARCHAR(30)
)
drop table KASIR

CREATE TABLE Pelanggan(
Kd_Pelanggan CHAR (5) PRIMARY KEY ,
Nama_Pelanggan VARCHAR(50) NOT NULL,
Alamat_Pelanggan VARCHAR(50),
Telp_Pelanggan VARCHAR(14)
)


CREATE TABLE Pembelian(
Kd_Pembelian CHAR (5) PRIMARY KEY ,
Kd_Sup CHAR (5) FOREIGN KEY REFERENCES Supplier(Kd_Sup), 
Kd_Kasir CHAR (5) FOREIGN KEY REFERENCES Kasir(Kd_Kasir),
Tgl_Pembelian DATETIME NOT NULL,
Total NUMERIC(30,0)
)


CREATE TABLE Penjualan(
Kd_Nota CHAR (5) PRIMARY KEY ,
Kd_Pelanggan CHAR (5) FOREIGN KEY REFERENCES Pelanggan(Kd_Pelanggan),
Kd_Kasir CHAR (5) FOREIGN KEY REFERENCES Kasir(Kd_Kasir),
Tanggal DATETIME NOT NULL,
Total NUMERIC(30,0),
)



CREATE TABLE Detail_Penjualan(
Kd_Nota CHAR (5) FOREIGN KEY REFERENCES PENJUALAN(Kd_Nota),
Kd_Barang CHAR (5) FOREIGN KEY REFERENCES BARANG(Kd_BARANG),
Harga_Jual Int,
Qty INT,
Sub_Total Int
)

CREATE TABLE Detail_Pembelian(
Kd_Pembelian CHAR (5) FOREIGN KEY REFERENCES PEMBELIAN(Kd_Pembelian),
Kd_Barang CHAR (5) FOREIGN KEY REFERENCES BARANG(KD_BARANG),
Harga_Beli NUMERIC(18,0),
Qty INT,
SubTotal INT
)

CREATE TABLE Pengeluaran(
Kd_Pengeluaran CHAR (5) PRIMARY KEY ,
Kd_Kasir CHAR (5) FOREIGN KEY REFERENCES Kasir(Kd_Kasir),
Nama VARCHAR (50) NOT NULL,
Tgl Datetime,
Nominal NUMERIC(18,0) 
)


select * from Supplier

Insert Into Supplier VALUES ('10001', 'Lana', 'Sewon, Bantul', 0827654321)
Insert Into Supplier VALUES ('10002', 'Mike', 'Parangtritis, Bantul', 0883737273)


select * from Barang
Insert Into Barang VALUES ('40001', 'Teh Kotak 500 ml', 1, 4700, 5000, 100, '10001')
Insert Into Barang VALUES ('40002', 'ABC Kacang Hijau 250 ml', 1, 4000, 4400, 100, '10001')
Insert Into Barang VALUES ('40003', 'Sari Roti Krim Coklat', 1, 4000, 4500, 50, '10002')


select * from Kasir

Insert Into Kasir VALUES ('20001', 'Risa M M', 'Imogiri', 0897766554, 'Risa', 'Rissy', 'Pemilik Toko')
Insert Into Kasir VALUES ('20002', 'Louis R J', 'Kotagede', 0887546334, 'Loui', 'Loaja', 'Kasir')
Insert Into Kasir VALUES ('20003', 'Marsha A B', 'Godean', 0865368999, 'Marsha', 'Mars', 'Kasir')
Insert Into Kasir VALUES ('20004', 'Rizal S', 'Giwangan', 0873527181, 'Rizal', 'Rizal132', 'Kasir')


SELECT * FROM Pelanggan

Insert Into Pelanggan VALUES ('30001', 'Rinda M', 'Condong Catur, Sleman', 08278391371)
Insert Into Pelanggan VALUES ('30002', 'Maulana N R S', 'Seturan', 08677272733)