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
import uit.tkorg.entities.Conference;

/**
 *
 * @author Dao
 */
public class ConferenceBO {
    private static HashMap<Integer,Integer> conferenceData = new HashMap<>();
    private static int id = 1;
    public static void insertConference(String conferenceName) throws SQLException
    {
        Integer hashCode = conferenceName.hashCode();
        if (!conferenceData.containsKey(hashCode))
        {
            Conference conference = new Conference();
            conference.setIdConference(id);
            conference.setConferenceName(conferenceName);
            conferenceData.put(hashCode, id);
            id++;
            Connection connection = ConnectionManager.getInstance().getConnection();
            if (connection != null)
            {
                String sql = "INSERT into conference (idConference, conferenceName) VALUES" + "(?,?)"; 
                try
                {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, conference.getIdConference());
                    preparedStatement.setString(2, conference.getConferenceName());
                    preparedStatement.executeUpdate();
                }
                catch(Exception ex)
                {
                    Logger.getLogger(ConferenceBO.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    connection.close();
                }
            }
        }
    }
    public static Integer findConferenceId(String ConferenceName)
    {
        Integer hashCode = ConferenceName.hashCode();
        return conferenceData.get(hashCode);
    }
}
