package dao;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

import javax.naming.*;

public class BaseDao {

	DataSource dataSource;
    //��ݿ��ַ��jdbc:mysql://����������:�˿ں�/��ݿ���ơ�
    private String url = "jdbc:mysql://127.0.0.1:3306/fivecrowdsourcing?useUnicode=true&characterEncoding=utf-8";
    //�û���
    private String user = "root";
    //�û�����
    private String pwd = "123456";
    //��ݿ����Ӷ���
    private java.sql.Connection conn;
    //��ݿ�����ִ�ж���
    private PreparedStatement pstmt;
    //��ݿⷵ�ؽ��
    private java.sql.ResultSet rs;
    
    public BaseDao(){
    	try {
			Context context = new InitialContext();
			dataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/express");
		} catch (NamingException ne) {
			System.out.println("Exception:" + ne);
		}
    }
//    //��̬�����
//    static{
//        //1��������
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            
//            e.printStackTrace();
//        }
//    }
    
    //2����������
    public Connection getConnection() throws Exception {
		conn=dataSource.getConnection();
		return conn;
	}
    
    //ִ�ж���������
    public java.sql.ResultSet executeQuery(String query,
                    List<String> params) {
        
        try {
        	getConnection();
            //3����������ִ�ж���
            pstmt = conn.prepareStatement(query);
            //4��ִ��
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setString(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rs;
    }
    
    //ִ��д��������
    public int executeUpdate(String query,
            List<String> params){
        int result = 0;
        
        try {
        	getConnection();
            //3����������ִ�ж���
            pstmt = conn.prepareStatement(query);
            //4��ִ��
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setString(i+1, params.get(i));
                }
            }
            //5��������
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            //6���ͷ���Դ
            this.close();
        }
        return result;
    }

    
    //�ر���Դ
    public void close(){        
            try {
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if(pstmt!=null){
                    pstmt.close();
                    pstmt = null;
                }
                if(conn!=null){
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        }

}