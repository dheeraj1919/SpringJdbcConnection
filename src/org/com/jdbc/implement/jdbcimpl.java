package org.com.jdbc.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.com.jdbc.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class jdbcimpl {
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbctemplate;
	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	public String conection(int id)
	{
		String sql="select name from student where roll=?";
		this.jdbctemplate= new JdbcTemplate(getDatasource());
		return jdbctemplate.queryForObject(sql,new Object[]{id},String.class);
		//jdbctemplate.queryForO
	}
	public Circle getconnection(int id) {
		Circle circle =null;
		String name=null;
		
		try
		{
			 
			Connection conn=datasource.getConnection();
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select name from student where roll="+id);
			if(rs.next())
			{
				name=rs.getString(2);
			    //new Circle().setName(rs.getString(1));
				circle=new Circle(id,name);
			}
			
		}
		catch(SQLException er)
		{
			System.out.println("sql exception"+er.getMessage());
		}
		// TODO Auto-generated method stub
		return circle;
	}

}
