package com.users.web;

import com.users.web.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class Makul {
    
    private String KODE;
    public void setKODE(String KODE){
        this.KODE=KODE;
    }public String getKODE() {
        return KODE;
    }private String NAMA_MAKUL;
    public void setNAMA_MAKUL(String NAMA_MAKUL) {
        this.NAMA_MAKUL = NAMA_MAKUL;
    }public String getNAMA_MAKUL() {
        return NAMA_MAKUL;
    }private String NIP;
    public void setNIP(String NIP) {
        this.NIP = NIP;
    }public String getNIP() {
        return NIP;
    }private String NAMA_LENGKAP;
    public void setNAMA_LENGKAP(String NAMA_LENGKAP) {
        this.NAMA_LENGKAP = NAMA_LENGKAP;
    }public String getNAMA_LENGKAP() {
        return NAMA_LENGKAP;
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
        public String Edit_Makul(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
        String Field_KODE = params.get("action");
            try {
                koneksi obj_koneksi = new koneksi();
                Connection connection = obj_koneksi.get_connection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from mata_kuliah where KODE="+Field_KODE);
                Makul obj_Makul = new Makul();
                rs.next();
                obj_Makul.setKODE(rs.getString("KODE"));
                obj_Makul.setNAMA_MAKUL(rs.getString("NAMA_MAKUL"));
                obj_Makul.setNIP(rs.getString("NIP"));
                sessionMap.put("EditMakul", obj_Makul);  
            } catch (Exception e) {
                System.out.println(e);
            }return "/EditMakul.xhtml?faces-redirect=true";   
        }
//     
        public String Delete_Makul(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String Field_KODE = params.get("action");
        try {
            koneksi obj_koneksi = new koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("delete from mata_kuliah where KODE=?");
            ps.setString(1, Field_KODE);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }return "/makul.xhtml?faces-redirect=true";   
        }

    public String Update_Makul(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_KODE= params.get("Update_KODE");
        try {
            koneksi obj_koneksi = new koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update mata_kuliah set KODE=?, NAMA_MAKUL=?, NIP=? where KODE=?");
            ps.setString(1, KODE);
            ps.setString(2, NAMA_MAKUL);
            ps.setString(3, NIP);
            ps.setString(4, Update_KODE);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }return "/makul.xhtml?faces-redirect=true";
    }
    
    public ArrayList getGet_all_makul() throws Exception{
        ArrayList list_of_makul=new ArrayList();
             Connection connection=null;
        try {
            koneksi obj_koneksi = new koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from pengampu");
            while(rs.next()){
                Makul obj_Makul = new Makul();
                obj_Makul.setKODE(rs.getString("KODE"));
                obj_Makul.setNAMA_MAKUL(rs.getString("NAMA_MAKUL"));
                obj_Makul.setNAMA_LENGKAP(rs.getString("NAMA_LENGKAP"));
                list_of_makul.add(obj_Makul);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }return list_of_makul;
    } 
        public String Tambah_Makul(){
            try {
                Connection connection=null;
                koneksi obj_koneksi = new koneksi();
                connection = obj_koneksi.get_connection();
                PreparedStatement ps=connection.prepareStatement("insert into mata_kuliah(KODE, NAMA_MAKUL, NIP) value('"+KODE+"','"+NAMA_MAKUL+"','"+NIP+"')");
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } return "/makul.xhtml?faces-redirect=true";
        }
        
    public Makul() {
    } 
}


