package corona;


import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import corona.business.Vaccine;
import corona.data.DBConnector;
import corona.data.PostgreSQLErrorCodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static corona.business.ReturnValue.*;


public class Solution {
    public static void createTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("CREATE TABLE Labs (" +
                        "Id int PRIMARY KEY ," +
                        "Name text NOT NULL ," +
                        "City text NOT NULL ," +
                        "Active boolean NOT NULL ," +
                        "CHECK(id > 0))");
                statement.execute();

                statement = connection.prepareStatement("CREATE TABLE Employees (" +
                        "Id int PRIMARY KEY ," +
                        "Name text NOT NULL ," +
                        "City text NOT NULL ," +
                        "CHECK(id > 0))");
                statement.execute();

                statement = connection.prepareStatement("CREATE TABLE Vaccines (" +
                        "Id int PRIMARY KEY ," +
                        "Name text NOT NULL ," +
                        "Cost int NOT NULL ," +
                        "Units int NOT NULL ," +
                        "Productivity int NOT NULL ," +
                        "Income int DEFAULT(0) ," +
                        "CHECK(id > 0 AND Cost >= 0 AND Units >= 0 AND Productivity >= 0 AND Productivity <= 100 " +
                        "AND Income >= 0))");
                statement.execute();

                statement = connection.prepareStatement("CREATE TABLE LabWorkers (" +
                        "Employee_Id int REFERENCES Employees(Id) ON DELETE CASCADE ," +
                        "Lab_Id int REFERENCES Labs(Id) ON DELETE CASCADE ," +
                        "Salary int NOT NULL ," +
                        "UNIQUE(Employee_Id,Lab_Id) ," +
                        "CHECK(Salary >= 0))");
                statement.execute();

                statement = connection.prepareStatement("CREATE TABLE LabProduces (" +
                        "Lab_Id int REFERENCES Labs(Id) ON DELETE CASCADE ," +
                        "Vaccine_Id int REFERENCES Vaccines(Id) ON DELETE CASCADE ," +
                        "UNIQUE(Lab_Id,Vaccine_Id))");
                statement.execute();

                statement = connection.prepareStatement("CREATE VIEW PopularLabs AS " +
                        "SELECT L.Id AS Lab_Id " +
                        "FROM Labs AS L " +
                        "WHERE L.Id NOT IN (SELECT P.Lab_Id FROM LabProduces AS P, Vaccines AS V WHERE " +
                        "L.Id = P.Lab_Id AND P.Vaccine_Id = V.Id AND V.Productivity <= 20)");
                statement.execute();
            }
        } catch (SQLException ignored){
        }
        finally {
            closeResources(connection, statement);
        }
    }

    public static void clearTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DELETE FROM Labs");
                statement.execute();

                statement = connection.prepareStatement("DELETE FROM Employees");
                statement.execute();

                statement = connection.prepareStatement("DELETE FROM Vaccines");
                statement.execute();

                statement = connection.prepareStatement("DELETE FROM LabWorkers");
                statement.execute();

                statement = connection.prepareStatement("DELETE FROM LabProduces");
                statement.execute();

                statement = connection.prepareStatement("DELETE FROM PopularLabs");
                statement.execute();
            }
        } catch (SQLException ignored){}
        finally {
            closeResources(connection, statement);
        }
    }

    public static void dropTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DROP VIEW IF EXISTS PopularLabs");
                statement.execute();

                statement = connection.prepareStatement("DROP TABLE IF EXISTS LabProduces");
                statement.execute();

                statement = connection.prepareStatement("DROP TABLE IF EXISTS LabWorkers");
                statement.execute();

                statement = connection.prepareStatement("DROP TABLE IF EXISTS Labs");
                statement.execute();

                statement = connection.prepareStatement("DROP TABLE IF EXISTS Employees");
                statement.execute();

                statement = connection.prepareStatement("DROP TABLE IF EXISTS Vaccines");
                statement.execute();
            }
        } catch (SQLException ignored){}
        finally {
            closeResources(connection, statement);
        }
    }

    public static ReturnValue addLab(Lab lab) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("INSERT INTO Labs VALUES (? ,? ,? ,?)");
                statement.setInt(1,lab.getId());
                statement.setString(2,lab.getName());
                statement.setString(3,lab.getCity());
                statement.setBoolean(4,lab.getIsActive());
                statement.execute();
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static Lab getLabProfile(Integer labID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        Lab lab = new Lab();
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT * FROM Labs where Id = ?");
                statement.setInt(1, labID);
                ResultSet results = statement.executeQuery();
                if(results.next()){
                    lab.setId(results.getInt("Id"));
                    lab.setName(results.getString("Name"));
                    lab.setCity(results.getString("City"));
                    lab.setIsActive(results.getBoolean("Active"));
                    return lab;
                }
            }
        } catch (SQLException e){
            return Lab.badLab();
        }
        finally {
            closeResources(connection, statement);
        }
        return Lab.badLab();
    }

    public static ReturnValue deleteLab(Lab lab) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DELETE FROM Labs where Id = ?");
                statement.setInt(1, lab.getId());
                int affectedRows = statement.executeUpdate();
                if(affectedRows < 1) return NOT_EXISTS;
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue addEmployee(Employee employee) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("INSERT INTO Employees VALUES (? ,? ,?)");
                statement.setInt(1,employee.getId());
                statement.setString(2,employee.getName());
                statement.setString(3,employee.getCity());
                statement.execute();
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static Employee getEmployeeProfile(Integer employeeID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        Employee employee = new Employee();
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT * FROM Employees where Id = ?");
                statement.setInt(1, employeeID);
                ResultSet results = statement.executeQuery();
                if(results.next()){
                    employee.setId(results.getInt("Id"));
                    employee.setName(results.getString("Name"));
                    employee.setCity(results.getString("City"));
                    return employee;
                }
            }
        } catch (SQLException e){
            return Employee.badEmployee();
        }
        finally {
            closeResources(connection, statement);
        }
        return Employee.badEmployee();
    }

    public static ReturnValue deleteEmployee(Employee employee) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DELETE FROM Employees where Id = ?");
                statement.setInt(1, employee.getId());
                int affectedRows = statement.executeUpdate();
                if(affectedRows < 1) return NOT_EXISTS;
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue addVaccine(Vaccine vaccine) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("INSERT INTO Vaccines VALUES (? ,? ,? ,? ,?)");
                statement.setInt(1,vaccine.getId());
                statement.setString(2,vaccine.getName());
                statement.setInt(3,vaccine.getCost());
                statement.setInt(4,vaccine.getUnits());
                statement.setInt(5,vaccine.getProductivity());
                statement.execute();
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static Vaccine getVaccineProfile(Integer vaccineID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        Vaccine vaccine = new Vaccine();
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT * FROM Vaccines where Id = ?");
                statement.setInt(1, vaccineID);
                ResultSet results = statement.executeQuery();
                if(results.next()){
                    vaccine.setId(results.getInt("Id"));
                    vaccine.setName(results.getString("Name"));
                    vaccine.setCost(results.getInt("Cost"));
                    vaccine.setUnits(results.getInt("Units"));
                    vaccine.setProductivity(results.getInt("Productivity"));
                    return vaccine;
                }
            }
        } catch (SQLException e){
            return Vaccine.badVaccine();
        }
        finally {
            closeResources(connection, statement);
        }
        return Vaccine.badVaccine();
    }

    public static ReturnValue deleteVaccine(Vaccine vaccine) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DELETE FROM Vaccines where Id = ?");
                statement.setInt(1, vaccine.getId());
                int affectedRows = statement.executeUpdate();
                if(affectedRows < 1) return NOT_EXISTS;
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }


    public static ReturnValue employeeJoinLab(Integer employeeID, Integer labID, Integer salary) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("INSERT INTO LabWorkers VALUES (? ,? ,?)");
                statement.setInt(1, employeeID);
                statement.setInt(2, labID);
                statement.setInt(3, salary);
                statement.execute();
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue employeeLeftLab(Integer labID, Integer employeeID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DELETE FROM LabWorkers where Employee_Id = ? AND " +
                        "Lab_Id = ?");
                statement.setInt(1, employeeID);
                statement.setInt(2, labID);
                int affectedRows = statement.executeUpdate();
                if(affectedRows < 1) return NOT_EXISTS;
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue labProduceVaccine(Integer vaccineID, Integer labID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("INSERT INTO LabProduces VALUES (? ,? )");
                statement.setInt(1, labID);
                statement.setInt(2, vaccineID);
                statement.execute();
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue labStoppedProducingVaccine(Integer labID, Integer vaccineID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("DELETE FROM LabProduces WHERE Lab_Id = ? AND " +
                                                                                            "Vaccine_Id = ?");
                statement.setInt(1, labID);
                statement.setInt(2, vaccineID);
                int affectedRows = statement.executeUpdate();
                if(affectedRows < 1) return NOT_EXISTS;
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue vaccineSold(Integer vaccineID, Integer amount) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("UPDATE Vaccines " +
                        "SET " +
                        "Units = Units - ? ," +
                        "Cost = Cost * 2 ," +
                        "Productivity = LEAST(Productivity + 15, 100) ," +
                        "Income = Income + Cost * ? " +
                        "WHERE Id = ?");
                statement.setInt(1, amount);
                statement.setInt(2, amount);
                statement.setInt(3, vaccineID);
                int affectedRows = statement.executeUpdate();
                if(affectedRows < 1) return NOT_EXISTS;
                return OK;
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static ReturnValue vaccineProduced(Integer vaccineID, Integer amount) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT NULL WHERE ? >= 0");
                statement.setInt(1, amount);
                ResultSet results = statement.executeQuery();
                if (results.next()) {
                    statement = connection.prepareStatement("UPDATE Vaccines " +
                            "SET " +
                            "Units = Units + ? ," +
                            "Cost = Cost / 2 ," +
                            "Productivity = GREATEST(Productivity - 15, 0) " +
                            "WHERE Id = ?");
                    statement.setInt(1, amount);
                    statement.setInt(2, vaccineID);
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows < 1) return NOT_EXISTS;
                    return OK;
                } else {
                    return BAD_PARAMS;
                }
            }
        } catch (SQLException e){
            return errorCodeHandler(Integer.valueOf(e.getSQLState()));
        }
        finally {
            closeResources(connection, statement);
        }
        return ERROR;
    }

    public static Boolean isLabPopular(Integer labID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT * FROM PopularLabs WHERE Lab_Id = ?");
                statement.setInt(1, labID);
                ResultSet results = statement.executeQuery();
                return results.next();
            }
        } catch (SQLException e){
            return false;
        }
        finally {
            closeResources(connection, statement);
        }
        return false;
    }

    public static Integer getIncomeFromVaccine(Integer vaccineID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT Income FROM Vaccines WHERE Id = ?");
                statement.setInt(1, vaccineID);
                ResultSet results = statement.executeQuery();
                if(results.next()) return results.getInt("Income");
            }
        } catch (SQLException e){
            return 0;
        }
        finally {
            closeResources(connection, statement);
        }
        return 0;
    }

    public static Integer getTotalNumberOfWorkingVaccines() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT SUM(Units) AS Sum_Units FROM Vaccines WHERE Productivity > 20");
                ResultSet results = statement.executeQuery();
                if(results.next()) return results.getInt("Sum_Units");
            }
        } catch (SQLException e){
            return 0;
        }
        finally {
            closeResources(connection, statement);
        }
        return 0;
    }

    public static Integer getTotalWages(Integer labID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT SUM(W.Salary) AS Sum_Wages FROM LabWorkers AS W ," +
                        "Labs AS L WHERE W.Lab_Id = ? AND W.Lab_Id = L.Id and L.Active = true " +
                        "AND (SELECT COUNT(Employee_Id) FROM LabWorkers AS W2 WHERE W2.Lab_Id = W.Lab_Id) > 1");
                statement.setInt(1, labID);
                ResultSet results = statement.executeQuery();
                if(results.next()) return results.getInt("Sum_Wages");
            }
        } catch (SQLException e){
            return 0;
        }
        finally {
            closeResources(connection, statement);
        }
        return 0;
    }

    public static Integer getBestLab() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT L.id as Id " +
                        "FROM LabWorkers AS W , Labs AS L, Employees AS E " +
                        "WHERE L.Id = W.Lab_Id AND W.Employee_Id = E.Id AND E.City = L.City " +
                        "GROUP BY L.Id ORDER BY COUNT(L.Id) DESC, L.Id ASC LIMIT 1");
                ResultSet results = statement.executeQuery();
                if(results.next()) return results.getInt("Id");
            }
        } catch (SQLException e){
            return 0;
        }
        finally {
            closeResources(connection, statement);
        }
        return 0;
    }

    public static String getMostPopularCity() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT E.city AS City " +
                        "FROM LabWorkers AS W ,Employees AS E " +
                        "WHERE W.Employee_Id = E.Id " +
                        "GROUP BY E.City ORDER BY COUNT(E.city) DESC, E.City DESC LIMIT 1");
                ResultSet results = statement.executeQuery();
                if(results.next()) return results.getString("City");
            }
        } catch (SQLException e){
            return null;
        }
        finally {
            closeResources(connection, statement);
        }
        return "";
    }

    public static ArrayList<Integer> getPopularLabs() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT Lab_Id FROM PopularLabs " +
                        "WHERE Lab_Id IN (SELECT Lab_Id FROM LabProduces)" +
                        "ORDER BY Lab_Id ASC LIMIT 3");
                ResultSet results = statement.executeQuery();
                while(results.next()){
                    list.add(results.getInt("Lab_Id"));
                }
            }
        } catch (SQLException e){
            return list;
        }
        finally {
            closeResources(connection, statement);
        }
        return list;
    }

    public static ArrayList<Integer> getMostRatedVaccines() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT Id FROM Vaccines " +
                        "ORDER BY (Productivity + Units - Cost) DESC, Id ASC LIMIT 10");
                ResultSet results = statement.executeQuery();
                while(results.next()){
                    list.add(results.getInt("Id"));
                }
            }
        } catch (SQLException e){
            return list;
        }
        finally {
            closeResources(connection, statement);
        }
        return list;
    }

    public static ArrayList<Integer> getCloseEmployees(Integer employeeID) {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = null;
        try {
            if(null != connection) {
                statement = connection.prepareStatement("SELECT E.Id as Id FROM Employees AS E " +
                        "WHERE ? IN (SELECT E1.Id FROM Employees AS E1) AND E.Id <> ? AND " +
                        "(SELECT COUNT(W1.Lab_Id) FROM LabWorkers AS W1 WHERE W1.Employee_Id = ?) <= " +
                        "2 * (SELECT COUNT(W1.Lab_Id) FROM LabWorkers AS W1 WHERE W1.Employee_Id = E.Id AND " +
                        "W1.Lab_Id IN " +
                        "(SELECT Lab_Id FROM LabWorkers AS W2 WHERE W2.Employee_Id = ?)) " +
                        "ORDER BY E.Id ASC LIMIT 10");
                statement.setInt(1,employeeID);
                statement.setInt(2,employeeID);
                statement.setInt(3,employeeID);
                statement.setInt(4,employeeID);
                ResultSet results = statement.executeQuery();
                while(results.next()){
                    list.add(results.getInt("Id"));
                }
            }
        } catch (SQLException e){
            return list;
        }
        finally {
            closeResources(connection, statement);
        }
        return list;
    }

    private static void closeResources(Connection connection, PreparedStatement statement){
        try {
            if(null != statement) {
                statement.close();
            }
        } catch (SQLException ignored) {}
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ignored) {}
    }

    private static ReturnValue errorCodeHandler(int violation) {
        if(violation == PostgreSQLErrorCodes.NOT_NULL_VIOLATION.getValue()) return BAD_PARAMS;
        else if (violation == PostgreSQLErrorCodes.FOREIGN_KEY_VIOLATION.getValue()) return NOT_EXISTS;
        else if (violation == PostgreSQLErrorCodes.UNIQUE_VIOLATION.getValue()) return ALREADY_EXISTS;
        else if (violation == PostgreSQLErrorCodes.CHECK_VIOLATION.getValue()) return BAD_PARAMS;
        return ERROR;
    }
}

