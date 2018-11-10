package com.users.web;

import com.users.web.koneksi;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
public class Pengampu {
        
    private String NIP;
    public void setNIP(String NIP) {
        this.NIP = NIP;
    }public String getNIP() {
        return NIP;
    }private String NAMA_LENGKAP;
    public void setNAMA_LENGKAP(String NAMA_LENGKAP) {
        this.NAMA_LENGKAP = NAMA_LENGKAP;
    }public String getNAMA_LENGKAP() {
        return NAMA_LENGKAP;
    }private String TPT_LAHIR;
    public void setTPT_LAHIR(String TPT_LAHIR) {
        this.TPT_LAHIR = TPT_LAHIR;
    }public String getTPT_LAHIR() {
        return TPT_LAHIR;
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
     public String Edit_Pengampu(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_NIP = params.get("action");
     try {
          koneksi obj_koneksi = new koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from dosen where NIP="+Field_NIP);
          Pengampu obj_Pengampu = new Pengampu();
          rs.next();
          obj_Pengampu.setNIP(rs.getString("NIP"));
          obj_Pengampu.setNAMA_LENGKAP(rs.getString("NAMA_LENGKAP"));
          obj_Pengampu.setTPT_LAHIR(rs.getString("TPT_LAHIR"));
          sessionMap.put("EditPengampu", obj_Pengampu);  
      } catch (Exception e) {
            System.out.println(e);
      }return "/Edit.xhtml?faces-redirect=true";   
    }
     
    public String Delete_Pengampu(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_NIP = params.get("action");
      try {
         koneksi obj_koneksi = new koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from dosen where NIP=?");
         ps.setString(1, Field_NIP);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }return "/index.xhtml?faces-redirect=true";   
    }

    public String Update_Pengampu(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_NIP= params.get("Update_NIP");
        try {
            koneksi obj_koneksi = new koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update dosen set NIP=?, NAMA_LENGKAP=?, TPT_LAHIR=? where NIP=?");
            ps.setString(1, NIP);
            ps.setString(2, NAMA_LENGKAP);
            ps.setString(3, TPT_LAHIR);
            ps.setString(4, Update_NIP);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }return "/index.xhtml?faces-redirect=true";   
    }
    
    public ArrayList getGet_all_pengampu() throws Exception{
        ArrayList list_of_pengampu=new ArrayList();
             Connection connection=null;
        try {
            koneksi obj_koneksi = new koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from dosen");
            while(rs.next()){
                Pengampu obj_Pengampu = new Pengampu();
                obj_Pengampu.setNIP(rs.getString("NIP"));
                obj_Pengampu.setNAMA_LENGKAP(rs.getString("NAMA_LENGKAP"));
                obj_Pengampu.setTPT_LAHIR(rs.getString("TPT_LAHIR"));
                list_of_pengampu.add(obj_Pengampu);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }return list_of_pengampu;
    } 
    public String Tambah_Pengampu(){
        try {
            Connection connection=null;
            koneksi obj_koneksi = new koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into dosen(NIP, NAMA_LENGKAP, TPT_LAHIR) value('"+NIP+"','"+NAMA_LENGKAP+"','"+TPT_LAHIR+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } return "/index.xhtml?faces-redirect=true";
    }

    public Pengampu() {
    } 
}