/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import uit.tkorg.utilitys.ConnectionManager;
import uit.tkorg.entities.PaperType;

/**
 *
 * @author Dao
 */
public class PaperTypeBO {
    private static HashMap<Integer,Integer> setPaperType = new HashMap<>();
    private static int id = 1;
    public static void insertPaperType(String paperTypeName) throws SQLException
    {
        Integer hashCode = paperTypeName.hashCode();
        if (!setPaperType.containsKey(hashCode))
        {
            PaperType paperType = new PaperType();
            paperType.setIdPaperType(id);
            paperType.setNameType(paperTypeName);
            setPaperType.put(hashCode, id);
            id++;
            Connection connection = ConnectionManager.getInstance().getConnection();
            if (connection != null)
            {
                String sql = "INSERT into paper_type (idPaperType, nameType) VALUES" + "(?,?)"; 
                try
                {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, paperType.getIdPaperType());
                    preparedStatement.setString(2, paperType.getNameType());
                    preparedStatement.executeUpdate();
                }
                catch(Exception ex)
                {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    connection.close();
                }
            }
        }
    }
    public static Integer findPaperTypeId(String paperTypeName)
    {
        Integer hashCode = paperTypeName.hashCode();
        return setPaperType.get(hashCode);
    }
}
