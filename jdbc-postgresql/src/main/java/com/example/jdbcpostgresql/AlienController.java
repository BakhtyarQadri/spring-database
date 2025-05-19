package com.example.jdbcpostgresql;

import com.example.jdbcpostgresql.AlienContract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aliens")
public class AlienController {

    @Autowired
    @Qualifier("readDataSource")
    private DataSource readDataSource;

    @Autowired
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;

//    public AlienController(DataSource readDataSource, DataSource writeDataSource) {
//        this.readDataSource = readDataSource;
//        this.writeDataSource = writeDataSource;
//    }

    @PostMapping
    public String addAlien(@RequestBody AddAlienReq reqBody) throws SQLException {
        try(
                Connection conn = writeDataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO public.aliens (cnic, name, age) VALUES (?, ?, ?)")
        ) {
            pstmt.setString(1, reqBody.cnic());
            pstmt.setString(2, reqBody.name());
            pstmt.setInt(3, reqBody.age());
            Integer affectedRows = pstmt.executeUpdate();
            return (affectedRows + " row/s affected");
        }
    }

    @GetMapping
    public List<AlienResp> getAllAliens() throws SQLException {
        try(
                Connection conn = readDataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM public.aliens")
        ) {
            ResultSet resultSet = pstmt.executeQuery();
            var aliensList = new ArrayList<AlienResp>();
            while(resultSet.next()) {
                aliensList.add(
                        new AlienResp(
                                resultSet.getInt("id"),
                                resultSet.getString("cnic"),
                                resultSet.getString("name"),
                                resultSet.getInt("age")
                        )
                );
            }
            return aliensList;
        }
    }

    @PutMapping("/{alienCnic}")
    public String updateAlien(@PathVariable String alienCnic, @RequestBody UpdateAlienReq reqBody) throws SQLException {
        try(
                Connection conn = writeDataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE public.aliens SET name=?, age=? WHERE cnic=?")
        ) {
            pstmt.setString(1, reqBody.name());
            pstmt.setInt(2, reqBody.age());
            pstmt.setString(3, alienCnic);
            Integer affectedRows = pstmt.executeUpdate();
            return (affectedRows + " row/s affected");
        }
    }

    @DeleteMapping("/{alienCnic}")
    public String deleteAlienByCnic(@PathVariable String alienCnic) throws SQLException {
        try(
                Connection conn = writeDataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM public.aliens WHERE cnic=?")
        ) {
            pstmt.setString(1, alienCnic);
            Integer affectedRows = pstmt.executeUpdate();
            return (affectedRows + " row/s affected");
        }
    }

    @DeleteMapping
    public String deleteAllAliens() throws SQLException {
        try(
                Connection conn = writeDataSource.getConnection(); // DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM public.aliens;") // TRUNCATE TABLE public.aliens;
        ) {
            Integer affectedRows = pstmt.executeUpdate();
            return (affectedRows + " row/s affected");
        }
    }

    // @PatchMapping

}
