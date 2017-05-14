/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyEntities;

import core.Manipulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Resourceman
{

    final static String TABLE_NAME = "resource";
    public int resource_id;

    public Resourceman(int resource_id)

    {
        this.resource_id = resource_id;
    }

    public int getResource_id()
    {
        return resource_id;
    }

    @Override
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("\nResource_id\t :  ").append(resource_id);

        return strb.toString();
    }

    public void consolidate(Connection con)
    {
        try
        {
            String query = "insert into resource(resource_id)";
            PreparedStatement prp = con.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, Integer.toString(resource_id));

            prp.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Consolidation error[resource]:\n" + e);
        }
    }

}
