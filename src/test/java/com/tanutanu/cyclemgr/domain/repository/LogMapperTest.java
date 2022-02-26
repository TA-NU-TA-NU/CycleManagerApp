package com.tanutanu.cyclemgr.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import javax.sql.DataSource;

import com.tanutanu.cyclemgr.config.DbConfig;
import com.tanutanu.cyclemgr.domain.model.Log;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

/**
 * TaskMapperTest
 */
@SpringBootTest
@Transactional
@Import(DbConfig.class)
public class LogMapperTest {

    @Autowired
    private LogMapper mapper;

    @Autowired
    private DataSource ds;
    private IDatabaseConnection dbcon;
    private IDataSet inputCsvDataSet;

    @BeforeEach
    public void setup() throws Exception{
        this.dbcon = new DatabaseConnection(this.ds.getConnection());
        this.inputCsvDataSet = new CsvDataSet(new File("src/test/resources/com/tanutanu/cyclemgr/domain/repository")); 
        DatabaseOperation.CLEAN_INSERT.execute(dbcon, inputCsvDataSet);
    }

    @AfterEach
    public void teardown()throws Exception {
        this.dbcon.close(); 
    }

    @DisplayName("SELECT TEST…観点:taskidから全logを取得できる事.")
    @Test
    public void testFindAllLogsByTaskId() {
        String task_id = "T1";
        List<Log> actuals = mapper.findAllLogsByTaskId(task_id);
        assertEquals(3, actuals.size());
    }

    @DisplayName("SELECT TEST…観点:taskidからlogの予測値平均を取得できる事.")
    @Test
    public void testGetLogDeclAve(){
        double expected =2.000000;
        double delta=0.0000001;
        String task_id = "T1";
        double actual = mapper.getLogDeclAve(task_id);
        assertEquals("delta:=0.0000001",Double.valueOf(expected),Double.valueOf(actual),Double.valueOf(delta));
    }

    @DisplayName("SELECT TEST…観点:taskidからlogの実測値平均を取得できる事.")
    @Test
    public void testGetLogRealAve(){
        double expected = 1.6666666;
        double delta=0.0000001;
        String task_id = "T1";
        double actual = mapper.getLogRealAve(task_id);
        assertEquals("delta:=0.0000001",Double.valueOf(expected),Double.valueOf(actual),Double.valueOf(delta));
    }

    @DisplayName("INSERT TEST…観点:logを1件追加できる事.")
    @Test
    public void testCreateLog() {
        int expected = 1;
        Log log = new Log();
        log.setTask_id("T1");
        log.setLog_decl(2);
        log.setLog_real(2);
        int actual = mapper.createLog(log);
        assertEquals(expected, actual);
    }

    @DisplayName("DELETE TEST…観点:task_idからlogを全件削除できる事.")
    @Test
    public void testDeleteAllLogsByTaskId() {
        int expected = 3;
        String task_id="T1";
        int actual=mapper.deleteAllLogsByTaskId(task_id);
        assertEquals(expected, actual);
    }

    @DisplayName("DELETE TEST…観点:task_idから100件を超えた分の古いlogを全件削除できる事.")
    @Test
    public void testDeleteOverfloatLogsByTaskId() {
        int expected = 1;
        String task_id="T2";
        int actual=mapper.deleteOverfloatLogsByTaskId(task_id);
        assertEquals(expected, actual);
    }
}