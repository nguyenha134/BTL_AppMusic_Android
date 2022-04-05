//Nguyen Van Cong - 191202433
package com.google.dunggiaobt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Topic {

@SerializedName("IdChuDe")
@Expose
private String idChuDe;
@SerializedName("TenChuDe")
@Expose
private String tenChuDe;
@SerializedName("HinhChuDe")
@Expose
private String hinhChuDe;

public String getIdChuDe() {
return idChuDe;
}

public void setIdChuDe(String idChuDe) {
this.idChuDe = idChuDe;
}

public String getTenChuDe() {
return tenChuDe;
}

public void setTenChuDe(String tenChuDe) {
this.tenChuDe = tenChuDe;
}

public String getHinhChuDe() {
return hinhChuDe;
}

public void setHinhChuDe(String hinhChuDe) {
this.hinhChuDe = hinhChuDe;
}

}