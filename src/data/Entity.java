/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Isuru Udukala
 */
public class Entity
{
    private final Map<String,Object> data = new HashMap<>();
    private final String tablename;
    private String ag_column=null;
    private Integer ag_key=null;
    
    public Entity(String tablename)
    {
        this.tablename = tablename;
    }
    
    //public <E> void add(String key, E value)
    public void add(String key, Object value)
    {
        data.put(key,value);
    }
    
    private Object get(String key, Class reqClass)
    {
        if(data.get(key).getClass().equals(reqClass))
            return data.get(key);
        else
        {
            System.out.println("Requesting <" + Manipulate.getClassStr(reqClass) + "> cast to <" + Manipulate.getClassStr(data.get(key).getClass()) + ">");
            return null;
        }
    }
    
    public String getString(String key)
    {
        return (String)get(key,String.class);
    }
    public int getInt(String key)
    {
        return (int)get(key,Integer.class);
    }
    public double getDouble(String key)
    {
        return (double)get(key,Double.class);
    }
    public LocalDate getLocalDate(String key)
    {
        return (LocalDate)get(key,LocalDate.class);
    }
    
    public int consolidate(Connection conn)
    {
        String sql = parseConsolidateStatement();
        if(!validate(conn,false))
            System.out.println("Warning:\nConsolidating to database with validation failure for [" + tablename + "].");
        
        try
        {
            PreparedStatement prp = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            int counter=1;
            for(Map.Entry<String,Object> entry : data.entrySet())
            {
                Class eclass = entry.getValue().getClass();
                if(eclass.equals(String.class) || eclass.equals(LocalDate.class))
                    prp.setString(counter++, entry.getValue().toString());
                else if(eclass.equals(Double.class))
                    prp.setDouble(counter++,(Double)entry.getValue());
                else if(eclass.equals(Integer.class))
                    prp.setInt(counter++,(Integer)entry.getValue());
            }
            
            prp.executeUpdate();
            ResultSet rs = prp.getGeneratedKeys();
            
            if(rs.next())
            {
                ag_key = rs.getInt(1);
                rs.close();
                
                ResultSet ag_rs=prp.executeQuery("show columns from `" + tablename + "` where `Extra`='auto_increment';");
                ag_rs.next();
                ag_column = ag_rs.getString("Field");
                data.put(ag_column,ag_key);
            }
            
            System.out.println("Consolidation successful in : " + tablename);
            return 0;
        }
//        catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
//        {
//            return 1;
//        }
//        catch(com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e)
//        {
//            return 2;
//        }
        catch(SQLException e)
        {
            System.out.println(tablename + " consolidation error\n" + e);
            return 2;
        }
    }
    
    public String parseConsolidateStatement()
    {
        StringBuilder strb1 = new StringBuilder("insert into ").append(tablename).append("(");
        StringBuilder strb2 = new StringBuilder(") values(");
        int counter = 1;
        for(Entry<String,Object> entry : data.entrySet())
        {
            strb1.append(entry.getKey());
            strb2.append("?");
            
            if(counter++!=data.size())
            {
                strb1.append(",");
                strb2.append(",");
            }
        }
        strb1.append(strb2.append(");"));
        //System.out.println(strb1);
        return strb1.toString();
    }
    
    public boolean validate(Connection conn, boolean verbose)
    {
        StringBuilder strb = new StringBuilder();
        boolean table_valid = true;
        try
        {
            PreparedStatement prp = conn.prepareStatement("show tables like ?;");
            prp.setString(1, tablename);
            ResultSet rs = prp.executeQuery();
            
            List<String> tables = new ArrayList<>();
            while(rs.next())
            {
                tables.add(rs.getString(1).toLowerCase());
                rs.next();
            }
            if(!tables.contains(tablename))
            {
                strb.append("Validation failure\nTable [").append(tablename).append("] not found in database.");
                table_valid = false;
            }
        }
        catch(SQLException e)
        {
            strb.append("Table validation error : ").append(tablename).append("\n").append(e);
            table_valid = false;
        }
        if(verbose)
        {
            System.out.println(strb);
            return false;
        }
        
        boolean data_valid = false;
        List<List> tdata = new ArrayList<>();
        try
        {
            //fetching column data for table
            ResultSet rs = conn.prepareStatement("show columns from " + tablename + ";").executeQuery();
            
            for(int i=0;rs.next();i++)
            {
                tdata.add(new ArrayList<>());
                tdata.get(i).add(rs.getString("Field"));
                String typestr = rs.getString("Type").toLowerCase();
                if(typestr.contains("("))
                    tdata.get(i).add(typestr.substring(0,typestr.indexOf("(")));
                else
                    tdata.get(i).add(typestr);
                
                if(typestr.startsWith("varchar"))
                {
                    int sizestr = Integer.parseInt(typestr.substring(typestr.indexOf("(") + 1, typestr.indexOf(")")));
                    tdata.get(i).add(sizestr);
                }
                else
                    tdata.get(i).add(0);
            }
            //end datafetch
            
            //start with all keys and remove valid ones while iterating
            List<String> columnMismatches = new ArrayList<>();
            for(Entry<String,Object> entry : data.entrySet())
                columnMismatches.add(entry.getKey());
            List<String> typeMismatches = new ArrayList<>();
            List<String> sizeMismatches = new ArrayList<>();
            
            for(Entry<String,Object> entry : data.entrySet())
            {
                boolean valid_temp;
                for(int i=0;i<tdata.size();i++)
                {
                    if(entry.getKey().equals(tdata.get(i).get(0)))
                    {
                        columnMismatches.remove(entry.getKey());
                        
                        Class eclass = entry.getValue().getClass();
                        String rtype = tdata.get(i).get(1).toString();
                        
                        if(eclass.equals(String.class) && rtype.equalsIgnoreCase("varchar"))
                        {
                            if(entry.getValue().toString().length() > (int)tdata.get(i).get(2))
                                sizeMismatches.add(entry.getKey());
                            valid_temp=true;
                        }
                        else if(eclass.equals(Double.class) && rtype.equalsIgnoreCase("double"))
                            valid_temp=true;
                        else if(eclass.equals(LocalDate.class) && rtype.equalsIgnoreCase("date"))
                            valid_temp=true;
                        else if(eclass.equals(Integer.class) && rtype.equalsIgnoreCase("int"))
                            valid_temp=true;
                        else
                            valid_temp=false;
                        
                        if(!valid_temp)
                            typeMismatches.add(entry.getKey());
                    }
                }
            }
            int totalinvalid = typeMismatches.size() + columnMismatches.size() + sizeMismatches.size();
            strb.append("[").append(tablename).append("] \n")
                    .append(data.size() - totalinvalid).append(" field(s) valid, ")
                    .append(totalinvalid).append(" field(s) invalid");
            data_valid = (typeMismatches.isEmpty() && columnMismatches.isEmpty() && sizeMismatches.isEmpty());
            if(!data_valid)
            {
                strb.append("\nType mismatches\t\t: ").append(typeMismatches);
                strb.append("\nColumn mismatches\t: ").append(columnMismatches);
                strb.append("\nSize mismatches\t\t: ").append(sizeMismatches);
            }
        }
        catch(SQLException e)
        {
            strb.append("\nColumn validation error: ").append(tablename).append("\n").append(e);
        }
        if(verbose)
            System.out.println(strb);
        
        return table_valid && data_valid;
    }
    
    public static List<Entity> parseFromRS(ResultSet rs, Connection conn)
    {
        //resetting the RS incase next() has been called on it before being passed
        try{rs.beforeFirst();}catch(SQLException e){System.out.println("Error in passed resultset\n" + e);}
        
        final String strclass = String.class.getName();
        List<Entity> entities = new ArrayList<>();
        String newtable = null;
        
        try
        {
            newtable = rs.getMetaData().getTableName(1);
            while(rs.next())
            {
                Entity temp_entity = new Entity(newtable);
                for(int i=0;i<rs.getMetaData().getColumnCount();i++)
                {
                    String col_class = rs.getMetaData().getColumnClassName(i+1);
                    String column_name = rs.getMetaData().getColumnName(i+1);
                    if(col_class.equals(String.class.getName()))
                        temp_entity.add(column_name, rs.getString(column_name));
                    else if(col_class.equals(java.sql.Date.class.getName()))
                        temp_entity.add(column_name, rs.getDate(column_name).toLocalDate());
                    else if(col_class.equals(Double.class.getName()))
                        temp_entity.add(column_name, rs.getDouble(column_name));
                    else if(col_class.equals(Integer.class.getName()))
                        temp_entity.add(column_name, rs.getInt(column_name));
                }
                entities.add(temp_entity);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error encountered while parsing Entities [" + newtable + "[");
        }
        return entities;
    }
    
    @Override
    public String toString()
    {
        final int COL_COUNTER = 1, COL2=2, COL1, COL3;
        int max_col_length = 0,max_val_length = 0;
        for(Entry<String, Object> entry : data.entrySet())
        {
            int keylen = entry.getKey().length();
            if(max_col_length < keylen)
                max_col_length = keylen;
            
            int vallen = entry.getValue().toString().length();
            if(max_val_length < vallen)
                max_val_length = vallen;
        }
        COL1 = (max_col_length + 2)/8 + 1;
        COL3 = max_val_length/8 + 1;
        
        //generating head
        StringBuilder strb = new StringBuilder("\n");
        int total = COL_COUNTER + COL1 + COL2 + COL3;
        int lheader = (total*8 - (tablename.length() + 2))/2;
        for(int i=0;i<lheader;i++)strb.append("-");
        strb.append("[")
                .append(tablename)
                .append("]");
        for(int i=0;i<total*8-(lheader + tablename.length() +2);i++)strb.append("-");
        
        //generating body
        strb.append(Manipulate.formatTabs("\n#",COL_COUNTER,true))
                .append(Manipulate.formatTabs("| Column", COL1, true))
                .append(Manipulate.formatTabs("Type", COL2, true))
                .append("Value\n");
        
        for(int i=0;i<total*8;i++)strb.append("-");
        
        int counter = 1;
        for(Entry<String, Object> entry : data.entrySet())
        {
            strb.append("\n");
            
            if(entry.getKey().equals(ag_column))
                strb.append(Manipulate.formatTabs(counter++ + "[AG]",COL_COUNTER,true));
            else
                strb.append(Manipulate.formatTabs(counter++,COL_COUNTER,true));
            
            strb.append(Manipulate.formatTabs("| " + entry.getKey(),COL1,true))
                    .append(Manipulate.formatTabs(Manipulate.getClassStr(entry),COL2,true))
                    .append(entry.getValue());
            
        }
        return strb.append("\n").toString();
    }
}
