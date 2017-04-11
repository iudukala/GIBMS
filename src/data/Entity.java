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
    private Map<String,Object> data = new HashMap<>();
    private final String tablename;
    
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
            System.out.println("Requesting <" + Retriever.getClassStr(reqClass) + "> cast to <" + Retriever.getClassStr(data.get(key).getClass()) + ">");
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
    
    public void consolidate(Connection conn)
    {
        String sql = parseConsolidateSQL();
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
            System.out.println(rs.getMetaData().getColumnCount());
            //System.out.println("safsaf");
            while(rs.next())
            {
                for(int i=1;i<rs.getMetaData().getColumnCount() + 1;i++)
                    System.out.println("XXXXXX: " + rs.getString(i));
            }
            System.out.println("Consolidation successful in : " + tablename);
        }
        catch(SQLException e)
        {
            System.out.println(tablename + " consolidation error\n" + e);
        }
    }
    
    public String parseConsolidateSQL()
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
    
    public boolean validateTable(Connection conn)
    {
        boolean valid = false;
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
            valid = tables.contains(tablename);
        }
        catch(SQLException e)
        {
            System.out.println("Table validation error : " + tablename + "\n" + e);
        }
        return valid;
    }
    public boolean validateData(Connection conn)
    {
        boolean valid = false;
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
                            valid=true;
                        }
                        else if(eclass.equals(Double.class) && rtype.equalsIgnoreCase("double"))
                            valid=true;
                        else if(eclass.equals(LocalDate.class) && rtype.equalsIgnoreCase("date"))
                            valid=true;
                        else if(eclass.equals(Integer.class) && rtype.equalsIgnoreCase("int"))
                            valid=true;
                        else
                            valid=false;
                        if(!valid)
                            typeMismatches.add(entry.getKey());
                    }
                }
            }
            int totalinvalid = typeMismatches.size() + columnMismatches.size() + sizeMismatches.size();
            System.out.println("[" + tablename + "] \n" + (data.size() - totalinvalid) + " field(s) valid, " + totalinvalid + " field(s) invalid");
            valid = (typeMismatches.isEmpty() && columnMismatches.isEmpty() && sizeMismatches.isEmpty());
            if(!valid)
            {
                System.out.println("Type mismatches\t\t: " + typeMismatches);
                System.out.println("Column mismatches\t: " + columnMismatches);
                System.out.println("Size mismatches\t\t: " + sizeMismatches);
            }
            System.out.println();
        }
        catch(SQLException e)
        {
            System.out.println("Column validation error: " + tablename + "\n" + e);
        }
        return valid;
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
        strb.append(Manipulator.formatTabs("\n#",COL_COUNTER,true))
                .append(Manipulator.formatTabs("| Column", COL1, true))
                .append(Manipulator.formatTabs("Type", COL2, true))
                .append("Value\n");
        
        for(int i=0;i<total*8;i++)strb.append("-");
        
        int counter = 1;
        for(Entry<String, Object> entry : data.entrySet())
        {
            strb.append("\n")
                    .append(Manipulator.formatTabs(counter++,COL_COUNTER,true))
                    .append(Manipulator.formatTabs("| " + entry.getKey(),COL1,true))
                    .append(Manipulator.formatTabs(Retriever.getClassStr(entry),COL2,true))
                    .append(entry.getValue());
            
        }
        return strb.append("\n").toString();
    }
}
