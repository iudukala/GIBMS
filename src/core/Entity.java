/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import handlers.dbConcurrent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Isuru Udukala
 */

class OverrideString {}

class OverrideObject {}

public class Entity
{
    private final dbConcurrent nbconn;
    private final Map<String, Object> data = new LinkedHashMap<>();
    private String tablename;
    private String ag_column = null;
    private Integer ag_key = null;
    private String consolidate_string = null;
    private String update_string = null;


    public Entity(String tablename, dbConcurrent nbconn)
    {
        this.nbconn = nbconn;
        this.tablename = tablename;
    }

    public static List<Entity> parseFromRS(ResultSet rs, dbConcurrent nbconn)
    {
        //resetting RS in case next() has been called before being passed
        try
        {
            rs.beforeFirst();
        }
        catch (SQLException e)
        {
            System.out.println("Error in passed resultset\n" + e);
        }

        List<Entity> entities = new ArrayList<>();
        String newtable = null;
        int count = 0;

        try
        {
            newtable = rs.getMetaData().getTableName(1);
            while (rs.next())
            {
                Entity temp_entity = new Entity(newtable, nbconn);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
                {
                    Class col_class = null;
                    try
                    {
                        col_class = Class.forName(rs.getMetaData().getColumnClassName(i + 1));
                    }
                    catch (ClassNotFoundException e)
                    {
                        System.out.println("parseFromRS error : " + e);
                    }
                    String column_name = rs.getMetaData().getColumnName(i + 1).toLowerCase();

                    //null handling
                    if (rs.getString(column_name) == null)
                        continue;

                    temp_entity.add(column_name, recastSQLObject(rs.getObject(column_name)));
                }
                entities.add(temp_entity);

                System.out.print("\rFetching [" + newtable + "] records.. " + (count++) + " records parsed");
            }
            System.out.println("\r[" + newtable + "] : parsed " + count + " records.");
        }
        catch (SQLException e)
        {
            System.out.println("Error encountered while parsing Entities [" + newtable + "] :\n" + e);
        }
        return entities;
    }

    public static List<Entity> parseFromQuery(String query, dbConcurrent nbconn)
    {
        ResultSet rs = null;
        try
        {
            PreparedStatement prp = nbconn.get().prepareStatement(query);
            rs = prp.executeQuery();
        }
        catch (Exception e)
        {
            return null;
        }
        return parseFromRS(rs, nbconn);
    }

    private static Object recastSQLObject(Object obj)
    {
        if (obj.getClass().equals(String.class))
            return obj.toString();
        else if (obj.getClass().equals(java.sql.Date.class))
            return ((java.sql.Date) obj).toLocalDate();
        else if (obj.getClass().equals(Integer.class))
            return obj;
        else if (obj.getClass().equals(Double.class))
            return obj;
        else if (obj.getClass().equals(Long.class))
            return obj;
        else if (obj.getClass().equals(java.math.BigDecimal.class))
            return (obj.toString());
        else if (obj.getClass().equals(java.sql.Timestamp.class))
            return ((java.sql.Timestamp) obj).toLocalDateTime().toLocalDate();
        else
        {
            System.out.println("Unexpected type encoutered while recasting : " + obj.getClass());
            return null;
        }
    }

    public static Object recastJavaObject(Object obj)
    {
        if (obj.getClass().equals(LocalDate.class))
            return java.sql.Date.valueOf((LocalDate) obj);
        else if (obj.getClass().equals(String.class))
            return obj;
        else if (obj.getClass().equals(Integer.class))
            return obj;
        else if (obj.getClass().equals(Double.class))
            return obj;
        else if (obj.getClass().equals(Long.class))
            return obj;
        else
        {
            System.out.println("Unexpected type encoutered in recastJO");
            return null;
        }
    }

    //public <E> void add(String key, E value)
    public void add(String key, Object value)
    {
        //avoiding case conflicts on hashmap keys
        key = key.toLowerCase();
        if (value != null)
            data.put(key, value);
    }

    public void add(Object entries[][])
    {
        for (int i = 0; i < entries.length; i++)
        {
            Object key = entries[i][0];
            Object value = entries[i][1];

            add(key.toString(), value);
        }
    }

    private Object get(String key, Class reqClass)
    {
        key = key.toLowerCase();
        if (data.containsKey(key))
        {
            if (reqClass.equals(OverrideObject.class))
                return data.get(key);
            else if (reqClass.equals(OverrideString.class))
                return data.get(key).toString();
            else if (data.get(key).getClass().equals(reqClass))
                return data.get(key);
            else
            {
                System.out.print("Requesting <" + Manipulator.getClassStr(reqClass) + "> cast on <" + Manipulator.getClassStr(data.get(key).getClass()) + ">");
                System.out.println("{Key : " + key + " - Value : " + data.get(key));
            }
        }
        return null;
    }

    public void overrideTableName(String tablename)
    {
        this.tablename = tablename;
    }

    public String getAsString(String key)
    {
        return (String) get(key, OverrideString.class);
    }

    public Object getObject(String key)
    {
        return get(key, OverrideObject.class);
    }

    public String getString(String key)
    {
        return (String) get(key, String.class);
    }

    public int getInt(String key)
    {
        return (int) get(key, Integer.class);
    }

    public double getDouble(String key)
    {
        return (double) get(key, Double.class);
    }

    public LocalDate getLocalDate(String key)
    {
        return (LocalDate) get(key, LocalDate.class);
    }

    public Integer getAGKey()
    {
        return ag_key;
    }

    public String getConsolidateString()
    {
        parseConsolidateStatement(new ArrayList<>());
        return consolidate_string;
    }

    public String getUpdateString()
    {
        return update_string;
    }

    public boolean recordExists()
    {
        List<String> primaryKeys = fetchPrimaryKeys();
        StringBuilder strb = new StringBuilder("select * from " + tablename);
        strb.append(synthesizeWhereClause(primaryKeys, false));

        try
        {
            int counter = 0;
            PreparedStatement prp = nbconn.get().prepareStatement(strb.toString());
            for (Iterator<String> iterator = primaryKeys.listIterator(); iterator.hasNext(); )
                prp.setObject(++counter, recastJavaObject(data.get(iterator.next())));
            ResultSet rs = prp.executeQuery();

            return rs.next();
        }
        catch (Exception e)
        {
            System.out.println("Encoutered error while checking existence\n + " + e);
            return false;
        }
    }

    public List<List> fetchTableStructure()
    {
        List<List> tdata = new ArrayList<>();
        try
        {
            ResultSet rs = nbconn.get().prepareStatement("show columns from " + tablename + ";").executeQuery();

            for (int i = 0; rs.next(); i++)
            {
                tdata.add(new ArrayList<>());
                tdata.get(i).add(rs.getString("Field").toLowerCase());
                String typestr = rs.getString("Type").toLowerCase();
                if (typestr.contains("("))
                    tdata.get(i).add(typestr.substring(0, typestr.indexOf("(")));
                else
                    tdata.get(i).add(typestr);

                if (typestr.startsWith("varchar"))
                {
                    int sizestr = Integer.parseInt(typestr.substring(typestr.indexOf("(") + 1, typestr.indexOf(")")));
                    tdata.get(i).add(sizestr);
                }
                else
                    tdata.get(i).add(0);
            }
            //end datafetch
        }
        catch (Exception e)
        {
            System.out.println("error fetching table data\n" + e);
        }

        return tdata;
    }

    public List<String> getColumnNamesFromEntity()
    {
        List<String> columns = new ArrayList<>();
        for (Iterator<Entry<String, Object>> it = data.entrySet().iterator(); it.hasNext(); )
        {
            Entry<String, Object> entry = it.next();
            columns.add(entry.getKey());
        }

        return columns;
    }

    public int consolidate(Boolean verbose)
    {
        boolean successful = true;
        List<Object> fields = new ArrayList<>();
        String sql = parseConsolidateStatement(fields);

        if (!validate(false) && verbose)
            System.out.println("Warning: Consolidating to database with validation failure on [" + tablename + "].");

        String failure_str = "Consolidation failure [" + tablename + "] : ";
        PreparedStatement prp;
        try
        {
            prp = nbconn.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int counter = 0;
            for (Object field : fields)
                prp.setObject(++counter, recastJavaObject(field));
            prp.executeUpdate();
        }
        catch (MySQLIntegrityConstraintViolationException e)
        {
            successful = false;
            if (verbose)
                System.out.println(failure_str + "Integrity constraint violation\n" + e);
            return 1;
        }
        catch (MySQLSyntaxErrorException e)
        {
            successful = false;
            if (verbose)
                System.out.println(failure_str + "Syntax error\n" + e);
            return 2;
        }
        catch (SQLException e)
        {
            successful = false;
            if (verbose)
                System.out.println(failure_str + "\n" + e);
            return 3;
        }

        try
        {
            ResultSet rs = prp.getGeneratedKeys();
            if (rs.next())
            {
                ag_key = rs.getInt(1);
                rs.close();

                ag_column = fetchQueryColumnList("show columns from `" + tablename + "` where `Extra`='auto_increment';").get(0);
                data.put(ag_column, ag_key);
            }
        }
        catch (Exception e)
        {
            successful = false;
            System.out.println("Error encountered while fetching auto generated values : \n" + e);
            ag_key = null;
            ag_column = null;
        }

        StringBuilder strb = new StringBuilder();
        strb.append("Consolidation successful : [").append(tablename).append("] -  AG data : ");
        if (ag_key != null)
            strb.append(ag_key).append(" on ").append(ag_column).append("\n");
        else
            strb.append("none");

        if (verbose && successful)
            System.out.println(strb);
        return 0;
    }

    public int consolidate()
    {
        return consolidate(true);
    }

    private String parseConsolidateStatement(List<Object> fields)
    {
        StringBuilder strb1 = new StringBuilder("insert into ").append(tablename).append("(");
        StringBuilder strb2 = new StringBuilder(") values(");
        int counter = 1;
        for (Entry<String, Object> entry : data.entrySet())
        {
            strb1.append("`").append(entry.getKey()).append("`");
            fields.add(entry.getValue());
            strb2.append("?");

            if (counter++ != data.size())
            {
                strb1.append(",");
                strb2.append(",");
            }
        }
        strb1.append(strb2.append(");"));

        consolidate_string = strb1.toString();
        return consolidate_string;
    }

    public boolean validate(boolean verbose)
    {
        StringBuilder strb = new StringBuilder();
        boolean table_valid = true;
        try
        {
            PreparedStatement prp = nbconn.get().prepareStatement("show tables like ?;");
            prp.setString(1, tablename);
            ResultSet rs = prp.executeQuery();

            List<String> tables = new ArrayList<>();
            while (rs.next())
            {
                tables.add(rs.getString(1));
            }
            if (!tables.contains(tablename))
            {
                strb.append("Validation failure: - [").append(tablename).append("] not found in database.");
                table_valid = false;
            }
        }
        catch (SQLException e)
        {
            strb.append("Table validation error : ").append(tablename).append("\n").append(e);
            table_valid = false;
        }
        if (!table_valid && verbose)
        {
            System.out.println(strb);
            return false;
        }
        //table existence confirmation complete


        boolean data_valid = false;
        List<List> tdata = fetchTableStructure();

        try
        {
            //start with all keys and remove valid ones while iterating
            List<String> columnMismatches = new ArrayList<>();
            for (Entry<String, Object> entry : data.entrySet())
                columnMismatches.add(entry.getKey());
            List<String> typeMismatches = new ArrayList<>();
            List<String> sizeMismatches = new ArrayList<>();

            for (Entry<String, Object> entry : data.entrySet())
            {
                boolean valid_temp;
                for (int i = 0; i < tdata.size(); i++)
                {
                    if (entry.getKey().equals(tdata.get(i).get(0)))
                    {
                        columnMismatches.remove(entry.getKey());

                        Class eclass = entry.getValue().getClass();
                        String rtype = tdata.get(i).get(1).toString();

                        if (eclass.equals(String.class) && rtype.equalsIgnoreCase("varchar"))
                        {
                            if (entry.getValue().toString().length() > (int) tdata.get(i).get(2))
                                sizeMismatches.add(entry.getKey());
                            valid_temp = true;
                        }
                        else if (eclass.equals(Double.class) && rtype.equalsIgnoreCase("double"))
                            valid_temp = true;
                        else if (eclass.equals(LocalDate.class) && rtype.equalsIgnoreCase("date"))
                            valid_temp = true;
                        else
                            valid_temp = eclass.equals(Integer.class) && rtype.equalsIgnoreCase("int");

                        if (!valid_temp)
                            typeMismatches.add(entry.getKey());
                    }
                }
            }

            int totalinvalid = typeMismatches.size() + columnMismatches.size() + sizeMismatches.size();
            data_valid = (typeMismatches.isEmpty() && columnMismatches.isEmpty() && sizeMismatches.isEmpty());

            strb.append("Field verification : [").append(tablename).append("] - ")
                    .append(data.size() - totalinvalid).append(" valid, ")
                    .append(totalinvalid).append(" invalid");
            if (!data_valid)
            {
                strb.append("\nType mismatches\t\t: ").append(typeMismatches);
                strb.append("\nColumn mismatches\t: ").append(columnMismatches);
                strb.append("\nSize mismatches\t\t: ").append(sizeMismatches);
            }
        }
        catch (Exception e)
        {
            strb.append("\nColumn validation error: ").append(tablename).append("\n").append(e);
        }
        if (verbose)
            System.out.println(strb);
        return table_valid && data_valid;
    }

    @Override
    public String toString()
    {
        final int COL_COUNTER = 1, COL2 = 2, COL1, COL3;
        int max_col_length = 0, max_val_length = 0;
        for (Entry<String, Object> entry : data.entrySet())
        {
            int keylen = entry.getKey().length();
            if (max_col_length < keylen)
                max_col_length = keylen;

            int vallen = entry.getValue().toString().length();
            if (max_val_length < vallen)
                max_val_length = vallen;
        }
        COL1 = (max_col_length + 2) / 8 + 1;
        COL3 = max_val_length / 8 + 1;

        //generating head
        StringBuilder strb = new StringBuilder("\n");
        int total = COL_COUNTER + COL1 + COL2 + COL3;
        int lheader = (total * 8 - (tablename.length() + 2)) / 2;
        for (int i = 0; i < lheader; i++)
            strb.append("-");
        strb.append("[")
                .append(tablename)
                .append("]");
        for (int i = 0; i < total * 8 - (lheader + tablename.length() + 2); i++)
            strb.append("-");

        //generating body
        strb.append(Manipulator.formatTabs("\n#", COL_COUNTER, true))
                .append(Manipulator.formatTabs("| Column", COL1, true))
                .append(Manipulator.formatTabs("Type", COL2, true))
                .append("Value\n");

        for (int i = 0; i < total * 8; i++)
            strb.append("-");

        int counter = 1;
        for (Entry<String, Object> entry : data.entrySet())
        {
            strb.append("\n");

            if (entry.getKey().equals(ag_column))
                strb.append(Manipulator.formatTabs(counter++ + "[AG]", COL_COUNTER, true));
            else
                strb.append(Manipulator.formatTabs(counter++, COL_COUNTER, true));

            strb.append(Manipulator.formatTabs("| " + entry.getKey(), COL1, true))
                    .append(Manipulator.formatTabs(Manipulator.getClassStr(entry.getValue()), COL2, true))
                    .append(entry.getValue());
        }
        return strb.append("\n").toString();
    }

    private List<String> fetchQueryColumnList(String query)
    {
        List<String> column_list = new ArrayList<>();
        try
        {
            ResultSet rs = nbconn.get().createStatement().executeQuery(query);
            while (rs.next())
                column_list.add(rs.getString("Field").toLowerCase());
        }
        catch (Exception e)
        {
            System.out.println("Error fetching column list\n" + e);
        }
        return column_list;
    }

    public boolean update()
    {
        boolean status;

        List<String> primaryKeys = fetchPrimaryKeys();
        List<Object> fields = new ArrayList<>();

        //getting primarykey count in entity object
        int keycount = 0;
        for (Entry<String, Object> entry : data.entrySet())
            if (primaryKeys.contains(entry.getKey()))
                keycount++;

        //generating update dml statement
        StringBuilder strb = new StringBuilder("update ").append(tablename).append(" set");
        int counter = 0;
        for (Entry<String, Object> entry : data.entrySet())
        {
            if (!primaryKeys.contains(entry.getKey()))
            {
                strb.append(" `").append(entry.getKey()).append("`=?");
                fields.add(entry.getValue());
                if (++counter != data.size() - keycount)
                    strb.append(",");
            }
        }
        counter = 0;
        strb.append(synthesizeWhereClause(primaryKeys, false));
        //dml statement generation complete
        update_string = strb.toString();

        strb = new StringBuilder("[" + tablename + "] update ");
        PreparedStatement prp;
        try
        {
            prp = nbconn.get().prepareStatement(update_string);
            for (Iterator<Object> iterator = fields.iterator(); iterator.hasNext(); )
            {
                Object nextobj = iterator.next();
                prp.setObject(++counter, recastJavaObject(nextobj));
            }
            for (Iterator<String> iterator = primaryKeys.iterator(); iterator.hasNext(); )
            {
                Object nextobj = iterator.next();
                prp.setObject(++counter, recastJavaObject(data.get(nextobj)));
            }
            prp.executeUpdate();

            strb.append("executed successfully\nPrimary keyset : ");
            for (Object key : primaryKeys)
                strb.append("{ ").append(key).append(" : ").append(data.get(key).toString()).append(" }\t");

            status = true;
        }
        catch (Exception e)
        {
            System.out.println("Error assigning objects to preparedStatement");
            strb.append("failure \n").append(e);
            status = false;
        }

        System.out.println(strb);
        return status;
    }

    //executeAsSearch() will not work with columns containing periods in their name.
    //the period, if exists is used to recognize the table identifier
    private String synthesizeWhereClause(List<String> keylist, boolean wildcards)
    {
        String search_op = wildcards ? " like " : " = ";
        StringBuilder strb = new StringBuilder(" where ");

        int counter = 0;
        for (String key : keylist)
        {
            if (key.contains("."))
            {
                boolean found = false;
                for (char ch : key.toCharArray())
                {
                    if (ch == '.' && !found)
                    {
                        found = true;
                        strb.append(".`");
                    }
                    else
                        strb.append(ch);
                }
                strb.append('`');
            }
            else
                strb.append("`").append(key).append("`");

            strb.append(search_op).append("?");
            if (++counter != keylist.size())
                strb.append(" and ");
        }
        return strb.toString();
    }

    private Class getType(String key)
    {
        String type = null;
        for (List list : fetchTableStructure())
        {
            if (list.get(0).equals(key))
            {
                type = list.get(1).toString();
                break;
            }
        }
        return Manipulator.translateClass(type);
    }

    private List<String> getLocalFieldList()
    {
        List<String> fields = new ArrayList<>();
        for (Entry<String, Object> entry : data.entrySet())
            fields.add(entry.getKey());

        return fields;
    }

    public ResultSet executeAsSearch()
    {
        tablename = tablename.replace(';', ' ');
        List<String> searchFields = getLocalFieldList();
        for (Iterator<String> iter = searchFields.listIterator(); iter.hasNext(); )
        {
            String field = iter.next();
            if (data.get(field).equals(""))
                iter.remove();
        }

        for (Entry<String, Object> entry : data.entrySet())
        {
            data.put(entry.getKey(), "%" + entry.getValue() + "%");
        }

        StringBuilder strb = new StringBuilder();
        if (tablename.toLowerCase().startsWith("select "))
            strb.append(tablename);
        if (!searchFields.isEmpty())
            strb.append(synthesizeWhereClause(searchFields, true));
        strb.append(";");

        System.out.println("Searchfields : " + searchFields);
        System.out.println("strb : " + strb.toString());

        try
        {
            PreparedStatement prp = nbconn.get().prepareStatement(strb.toString());
            int counter = 0;
            for (Iterator<String> iter = searchFields.listIterator(); iter.hasNext(); )
            {
                String field = iter.next();
                prp.setObject(++counter, recastJavaObject(data.get(field)));
            }
            ResultSet rs = prp.executeQuery();
            return rs;
        }
        catch (Exception e)
        {
            System.err.println("Error executing search for query : " + strb.toString());
            return null;
        }
    }

    public List<String> fetchPrimaryKeys()
    {
        return fetchQueryColumnList("show columns from `" + tablename + "` where `key`='pri';");
    }

    public boolean deleteFromDB()
    {
        List<String> primaryKeys = fetchPrimaryKeys();
        StringBuilder strb = new StringBuilder("delete from " + tablename);
        strb.append(synthesizeWhereClause(primaryKeys, false));

        int counter = 0;
        try
        {
            PreparedStatement prp = nbconn.get().prepareStatement(strb.toString());
            for (Iterator<String> iterator = primaryKeys.listIterator(); iterator.hasNext(); )
                prp.setObject(++counter, recastJavaObject(data.get(iterator.next())));

            prp.executeUpdate();

            strb = new StringBuilder("[" + tablename + "] Record deleted successfully: ");
            for (Object key : primaryKeys)
                strb.append("{").append(key).append(" : ").append(data.get(key)).append("}\t");
            //testingmarker
            System.out.println(strb);

            return true;
        }
        catch (SQLException e)
        {
            System.err.println("Encoutered error while deleting records: " + e);
            return false;
        }
    }
}