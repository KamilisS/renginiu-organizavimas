/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import common.Event;
import common.EventType;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author gvt48
 */
public class MSSQL {
    
    private static final String DB_CON = 
            "jdbc:sqlserver://DESKTOP-H6FAGU6\\SQLEXPRESS:1433;databaseName=renginiu_organizavimas;";
    private static final String DB_USER = "sa";
    private static final String DB_PSW = "admin";
    
    private Connection connection;
   
    public MSSQL() {
        this.connectToDB();
    }
    
    private void connectToDB() {
        try {
             this.connection = DriverManager.getConnection(DB_CON, DB_USER, DB_PSW);
             Statement stmt = this.connection.createStatement();
             System.out.println("Successfully logged into database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean doesUserExist(String email) {
        String SQL = "SELECT el_pastas FROM Vartotojas WHERE el_pastas = '" + email + "'";
        ResultSet rs = this.getResultSet(SQL);
        try {
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean doesOrganiserExist(String email, long personalCode) {
        String SQL = "SELECT el_pastas FROM Organizatorius WHERE el_pastas = '" + email + "' OR asm_kodas = '" + personalCode + "'";
        ResultSet rs = this.getResultSet(SQL);
        try {
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean createNewUser(
        String name,
        String surname,
        String email,
        String pass,
        String birthday
    ) {
        String SQL = "INSERT INTO Vartotojas (vardas, pavarde, el_pastas, gimimo_data, slaptazodis)"
                + " VALUES "
                + "('" + name + "', '" + surname + "', '" + email + "', ";
        if (birthday != null) {
            SQL += "'" + birthday + "', ";
        } else {
            SQL += "null, ";
        }
        SQL += "'" + pass + "')";
        try {
            PreparedStatement ps = this.connection.prepareStatement(SQL);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean createNewOrganiser
        (
                String name,
                String surname,
                String email,
                String psw,
                String bankAcc,
                Long personalID,
                String birthday
        ) {
            try {
                PreparedStatement stmt = this.connection.prepareStatement(
                        "INSERT INTO Organizatorius (vardas, pavarde, el_pastas, slaptazodis, gimimo_data, banko_saskaita, asm_kodas, pinigu_suma) VALUES "
                                + "(?, ?, ?, ?, ?, ?, ?, 0)"
                );
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setString(3, email);
                stmt.setString(4, psw);
                stmt.setString(5, birthday);
                stmt.setString(6, bankAcc);
                stmt.setLong(7, personalID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    
    private ResultSet getResultSet(String SQL) {
        try {
            PreparedStatement ps = this.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public HashMap<String, String> loginUser(String email, String psw) {
        HashMap<String, String> user = new HashMap<>();
        
        String SQL = "SELECT vardas, pavarde, vartotojo_id FROM Vartotojas WHERE el_pastas = '" + email + "' AND slaptazodis = '" + psw + "'";
        ResultSet rs = this.getResultSet(SQL);
        try {
            while(rs.next()) {
                user.put("name", rs.getString("vardas"));
                user.put("surname", rs.getString("pavarde"));
                user.put("id", Integer.toString(rs.getInt("vartotojo_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public HashMap<String, String> loginOrganiser(String email, String psw) {
        HashMap<String, String> user = new HashMap<>();
        
        String SQL = "SELECT vardas, pavarde, organizatoriaus_id, pinigu_suma FROM Organizatorius WHERE el_pastas = '" + email + "' AND slaptazodis = '" + psw + "'";
        ResultSet rs = this.getResultSet(SQL);
        try {
            while(rs.next()) {
                user.put("name", rs.getString("vardas"));
                user.put("surname", rs.getString("pavarde"));
                user.put("id", Integer.toString(rs.getInt("organizatoriaus_id")));
                user.put("money", Double.toString(rs.getDouble("pinigu_suma")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public ArrayList<Event> getOrganiserEvents(int organiserId, HashMap<String, String> filters) {
        ArrayList<Event> list = new ArrayList<>();
        
        String SQL = "SELECT Renginys.renginio_id, Renginys.renginio_pavadinimas,Renginys.renginio_aprasymas,Renginys.organizatoriaus_id,Renginys.renginio_pradzia,Renginys.renginio_pabaiga,Renginys.renginio_kaina,Renginys.amziaus_limitas,Renginys.max_dalyviu_skaicius,Renginiu_tipai.renginio_tipo_pav,Renginiu_tipai.renginio_tipo_id, COUNT(Dalyvauja.renginio_id) AS dalyviu_skaicius " +
                "FROM Renginys " +
                "LEFT JOIN Dalyvauja ON Dalyvauja.renginio_id = Renginys.renginio_id " +
                "JOIN Renginiu_tipai ON Renginiu_tipai.renginio_tipo_id = Renginys.renginio_tipo_id " +
                "WHERE Renginys.organizatoriaus_id = " + organiserId;
        if (!filters.get("type").contentEquals("Visi")) {
            SQL += " AND Renginiu_tipai.renginio_tipo_pav = '" + filters.get("type") + "'";
        }
        if (filters.get("from") != null) {
            SQL += " AND Renginys.renginio_pradzia >= '" + filters.get("from") + "'";
        }
        if (filters.get("to") != null) {
            SQL += " AND Renginys.renginio_pradzia <= '" + filters.get("to") + " 23:59:59'";
        }
        SQL += " GROUP BY Renginys.renginio_id, Renginys.renginio_pavadinimas,Renginys.renginio_aprasymas,Renginys.organizatoriaus_id,Renginys.renginio_pradzia,Renginys.renginio_pabaiga,Renginys.renginio_kaina,Renginys.amziaus_limitas,Renginys.max_dalyviu_skaicius,Renginiu_tipai.renginio_tipo_pav,Renginiu_tipai.renginio_tipo_id";
        SQL += " ORDER BY " + filters.get("order");
        ResultSet rs = this.getResultSet(SQL);
        try {
            while(rs.next()) {
                Event event = new Event();
                event.setName(rs.getString("renginio_pavadinimas"));
                event.setId(rs.getInt("renginio_id"));
                event.setEventTypeName(rs.getString("renginio_tipo_pav"));
                event.setDescription(rs.getString("renginio_aprasymas"));
                event.setStartsAt(rs.getTimestamp("renginio_pradzia"));
                event.setEndsAt(rs.getTimestamp("renginio_pabaiga"));
                event.setEventTypeId(rs.getInt("renginio_tipo_id"));
                event.setAttendantsCount(rs.getInt("dalyviu_skaicius"));
                
                int ageLimit = rs.getInt("amziaus_limitas");
                event.setAgeLimit(ageLimit == 0 ? null : ageLimit);
                
                int maxAmount = rs.getInt("max_dalyviu_skaicius");
                event.setMaxAmount(maxAmount == 0 ? null : maxAmount);
                
                double price = rs.getDouble("renginio_kaina");
                event.setPrice(price == 0. ? null : price);
                
                event.setOrganiserId(organiserId);
                list.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public ArrayList<String> getAllEventTypeNames() {
        String SQL = "SELECT renginio_tipo_pav FROM Renginiu_tipai";
        
        ResultSet rs = this.getResultSet(SQL);
        ArrayList<String> names = new ArrayList<>();
        try {
            while(rs.next()) {
                names.add(rs.getString("renginio_tipo_pav"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }
    
    public Integer getEventTypeIdByName(String name) {
        String SQL = "SELECT renginio_tipo_id FROM Renginiu_tipai WHERE renginio_tipo_pav = '" + name + "'";
        ResultSet rs = this.getResultSet(SQL);
        
        try {
            while(rs.next()) {
                return rs.getInt("renginio_tipo_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean createEvent(
            int organiserId,
            String name,
            String description,
            String start,
            String end,
            Integer maxAmount,
            Integer ageLimit,
            Double price,
            int eventType
    ) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Renginys (organizatoriaus_id, renginio_tipo_id, renginio_pradzia, renginio_pabaiga, renginio_pavadinimas, renginio_aprasymas, max_dalyviu_skaicius, amziaus_limitas, renginio_kaina)"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, organiserId);
            stmt.setInt(2, eventType);
            stmt.setString(3, start);
            stmt.setString(4, end);
            stmt.setString(5, name);
            stmt.setString(6, description);
            
            if (maxAmount == null) {
            stmt.setNull(7, 0);
            } else stmt.setInt(7, maxAmount);
            
            if (ageLimit == null) {
                stmt.setNull(8, 0);
            } else stmt.setInt(8, ageLimit);

            if (price == null) {
                stmt.setNull(9, 0);
            } else stmt.setDouble(9, price);
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateEvent(
            int eventId,
            String name,
            String description,
            String start,
            String end,
            Integer maxAmount,
            Integer ageLimit,
            Double price,
            int eventType
    ) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                    "UPDATE Renginys SET renginio_tipo_id=?, renginio_pavadinimas=?, renginio_aprasymas=?, renginio_pradzia=?, renginio_pabaiga=?, max_dalyviu_skaicius=?, amziaus_limitas=?, renginio_kaina=? WHERE renginio_id=?"
            );
            stmt.setInt(1, eventType);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setString(4, start);
            stmt.setString(5, end);
            
            if (maxAmount == null) {
            stmt.setNull(6, 0);
            } else stmt.setInt(6, maxAmount);
            
            if (ageLimit == null) {
                stmt.setNull(7, 0);
            } else stmt.setInt(7, ageLimit);

            if (price == null) {
                stmt.setNull(8, 0);
            } else stmt.setDouble(8, price);
            
            stmt.setInt(9, eventId);
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void deleteEvent(int eventId) {
        String SQL = "DELETE FROM Renginys WHERE renginio_id = " + eventId;
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Renginys WHERE renginio_id = ?");
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Event> getAllEvents(HashMap<String, String> filters) {
        ArrayList<Event> list = new ArrayList<>();
        
        boolean whereUsed = false;
        
        String SQL = "SELECT Renginys.renginio_id, Renginys.renginio_pavadinimas,Renginys.renginio_aprasymas,Renginys.organizatoriaus_id,Renginys.renginio_pradzia,Renginys.renginio_pabaiga,Renginys.renginio_kaina,Renginys.amziaus_limitas,Renginys.max_dalyviu_skaicius,Renginiu_tipai.renginio_tipo_pav,Renginiu_tipai.renginio_tipo_id, Organizatorius.vardas,Organizatorius.pavarde, Renginys.organizatoriaus_id,  COUNT(Dalyvauja.renginio_id) AS dalyviu_skaicius " +
                "FROM Renginys " +
                "LEFT JOIN Dalyvauja ON Dalyvauja.renginio_id = Renginys.renginio_id " +
                "JOIN Renginiu_tipai ON Renginiu_tipai.renginio_tipo_id = Renginys.renginio_tipo_id " +
                "JOIN Organizatorius ON Organizatorius.organizatoriaus_id = Renginys.organizatoriaus_id ";
        if (!filters.get("type").contentEquals("Visi")) {
            if (whereUsed) {
                SQL += " AND Renginiu_tipai.renginio_tipo_pav = '" + filters.get("type") + "'";
            } else {
                SQL += " WHERE Renginiu_tipai.renginio_tipo_pav = '" + filters.get("type") + "'";
                whereUsed = true;
            }
        }
        if (filters.get("from") != null) {
            if (whereUsed) {
                SQL += " AND Renginys.renginio_pradzia >= '" + filters.get("from") + "'";
            } else {
                SQL += " WHERE Renginys.renginio_pradzia >= '" + filters.get("from") + "'";
                whereUsed = true;
            }
        }
        if (filters.get("to") != null) {
            if (whereUsed) {
                SQL += " AND Renginys.renginio_pradzia <= '" + filters.get("to") + " 23:59:59'";
            } else {
                SQL += " WHERE Renginys.renginio_pradzia <= '" + filters.get("to") + " 23:59:59'";
                whereUsed = true;
            }
        }
        SQL += " GROUP BY Renginys.renginio_id, Renginys.renginio_pavadinimas,Renginys.renginio_aprasymas,Renginys.organizatoriaus_id,Renginys.renginio_pradzia,Renginys.renginio_pabaiga,Renginys.renginio_kaina,Renginys.amziaus_limitas,Renginys.max_dalyviu_skaicius,Renginiu_tipai.renginio_tipo_pav,Renginiu_tipai.renginio_tipo_id,Organizatorius.vardas,Organizatorius.pavarde,Renginys.organizatoriaus_id";
        SQL += " ORDER BY " + filters.get("order");
        ResultSet rs = this.getResultSet(SQL);
        try {
            while(rs.next()) {
                Event event = new Event();
                event.setOrganiserName(rs.getString("vardas") + " " + rs.getString("pavarde"));
                event.setName(rs.getString("renginio_pavadinimas"));
                event.setId(rs.getInt("renginio_id"));
                event.setEventTypeName(rs.getString("renginio_tipo_pav"));
                event.setDescription(rs.getString("renginio_aprasymas"));
                event.setStartsAt(rs.getTimestamp("renginio_pradzia"));
                event.setEndsAt(rs.getTimestamp("renginio_pabaiga"));
                event.setEventTypeId(rs.getInt("renginio_tipo_id"));
                event.setAttendantsCount(rs.getInt("dalyviu_skaicius"));
                event.setOrganiserName(rs.getString("vardas"));
                event.setOrganiserLastName(rs.getString("pavarde"));
                
                int ageLimit = rs.getInt("amziaus_limitas");
                event.setAgeLimit(ageLimit == 0 ? null : ageLimit);
                
                int maxAmount = rs.getInt("max_dalyviu_skaicius");
                event.setMaxAmount(maxAmount == 0 ? null : maxAmount);
                
                double price = rs.getDouble("renginio_kaina");
                event.setPrice(price == 0. ? null : price);
                
                event.setOrganiserId(rs.getInt("organizatoriaus_id"));
                list.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public Event getEventForUser(int eventId) {
        Event event = new Event();
        
        String SQL = "SELECT Renginys.renginio_id, Renginys.renginio_pavadinimas,Renginys.renginio_aprasymas,Renginys.organizatoriaus_id,Renginys.renginio_pradzia,Renginys.renginio_pabaiga,Renginys.renginio_kaina,Renginys.amziaus_limitas,Renginys.max_dalyviu_skaicius,Renginiu_tipai.renginio_tipo_pav,Renginiu_tipai.renginio_tipo_id, Organizatorius.vardas,Organizatorius.pavarde, Renginys.organizatoriaus_id,  COUNT(Dalyvauja.renginio_id) AS dalyviu_skaicius " +
                "FROM Renginys " +
                "LEFT JOIN Dalyvauja ON Dalyvauja.renginio_id = Renginys.renginio_id " +
                "JOIN Renginiu_tipai ON Renginiu_tipai.renginio_tipo_id = Renginys.renginio_tipo_id " +
                "JOIN Organizatorius ON Renginys.organizatoriaus_id = Organizatorius.organizatoriaus_id " + 
                "WHERE Renginys.renginio_id = " + eventId;
        SQL += " GROUP BY Renginys.renginio_id, Renginys.renginio_pavadinimas,Renginys.renginio_aprasymas,Renginys.organizatoriaus_id,Renginys.renginio_pradzia,Renginys.renginio_pabaiga,Renginys.renginio_kaina,Renginys.amziaus_limitas,Renginys.max_dalyviu_skaicius,Renginiu_tipai.renginio_tipo_pav,Renginiu_tipai.renginio_tipo_id,Organizatorius.vardas,Organizatorius.pavarde,Renginys.organizatoriaus_id";
        ResultSet rs = this.getResultSet(SQL);
        try {
            while(rs.next()) {
                event.setName(rs.getString("renginio_pavadinimas"));
                event.setId(rs.getInt("renginio_id"));
                event.setEventTypeName(rs.getString("renginio_tipo_pav"));
                event.setDescription(rs.getString("renginio_aprasymas"));
                event.setStartsAt(rs.getTimestamp("renginio_pradzia"));
                event.setEndsAt(rs.getTimestamp("renginio_pabaiga"));
                event.setEventTypeId(rs.getInt("renginio_tipo_id"));
                event.setAttendantsCount(rs.getInt("dalyviu_skaicius"));
                event.setOrganiserName(rs.getString("vardas"));
                event.setOrganiserLastName(rs.getString("pavarde"));
                
                int ageLimit = rs.getInt("amziaus_limitas");
                event.setAgeLimit(ageLimit == 0 ? null : ageLimit);
                
                int maxAmount = rs.getInt("max_dalyviu_skaicius");
                event.setMaxAmount(maxAmount == 0 ? null : maxAmount);
                
                double price = rs.getDouble("renginio_kaina");
                event.setPrice(price == 0. ? null : price);
                
                event.setOrganiserId(rs.getInt("organizatoriaus_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return event;
    }
    
    public boolean isAttendant(int eventId, int userId) {
        boolean participant = false;
        String SQL = "SELECT * FROM Dalyvauja WHERE renginio_id = " + eventId + " AND vartotojo_id = " + userId;
        ResultSet rs = this.getResultSet(SQL);
        try {
            while(rs.next()) {
                participant = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participant;
    }
    
    public void createAttendant(int eventId, int userId) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Dalyvauja (renginio_id, vartotojo_id) VALUES (?, ?)");
            stmt.setInt(1, eventId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            
            Event event = this.getEventForUser(eventId);
            if (event.getPrice() != null) {
                
                stmt = this.connection.prepareStatement("UPDATE Organizatorius SET pinigu_suma = pinigu_suma + ? WHERE organizatoriaus_id = ?");
                stmt.setDouble(1, event.getPrice());
                stmt.setInt(2, event.getOrganiserId());
                stmt.executeUpdate();
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
