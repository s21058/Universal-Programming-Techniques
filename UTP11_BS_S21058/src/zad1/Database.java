package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Database {
    private TravelData data;
    private String url;
    private Connection connection;
    private  Integer id=1;
    Database(String url,TravelData travelData){
        this.url=url;
        this.data=travelData;
    }
    public void create(){
        try {
            connection= DriverManager.getConnection(url,"root","root");
            if(connection==null){
            System.err.println("Failed connection");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.createStatement().execute("CREATE TABLE Travel("
                    + "id_Travel int PRIMARY KEY NOT NULL , "
                    + "kraj varchar(40), "
                    + "dataWyjazd Date, "
                    + "dataPowrot Date, "
                    + "miejsce varchar(45), "
                    + "cena varchar(45), "
                    + "symbolWaluty varchar(45))"
            );
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Travel VALUES(?,?,?,?,?,?,?) ");

        for(ArrayList<String> l:data.getAnotherList()){
            preparedStatement.setInt(1,id);
            id++;
            preparedStatement.setString(2,l.get(0));
            preparedStatement.setString(3,l.get(1));
            preparedStatement.setString(4,l.get(2));
            preparedStatement.setString(5,l.get(3));
            preparedStatement.setString(6,l.get(4));
            preparedStatement.setString(7,l.get(5));
            preparedStatement.executeUpdate();
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
//            try {
//                connection.createStatement().execute("DROP TABLE Travel");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }

    }
    public void showGui(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Travel");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount =  resultSetMetaData.getColumnCount();
            Vector columns = new Vector();
            for (int i = 1; i <= columnCount; i++)
                columns.add(resultSetMetaData.getColumnName(i));
            JFrame jFrame = new JFrame();
            JTable jTable = new JTable();
            DefaultTableModel defaultTableModel = new DefaultTableModel(columns,0);
            while (resultSet.next()){
                Vector row = new Vector();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }
                defaultTableModel.addRow(row);
            }
            jTable.setModel(defaultTableModel);
            JScrollPane sp = new JScrollPane(jTable);
            jFrame.add(sp);
            jFrame.setSize(700,700);
            jFrame.setVisible(true);
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
