package com.tanutanu.cyclemgr.domain.repository;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.tanutanu.cyclemgr.config.*;
import com.tanutanu.cyclemgr.domain.model.Task;

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
public class TaskMapperTest {

    @Autowired
    private TaskMapper mapper;

    @Autowired
    private DataSource ds;
    private IDatabaseConnection dbcon;
    private IDataSet inputCsvDataSet;

    @BeforeEach
    public void setup() throws Exception{
        this.dbcon = new DatabaseConnection(this.ds.getConnection());
        this.inputCsvDataSet = new CsvDataSet(new File("src/test/resources/com/tanutanu/cyclemgr/domain/repository")); //テスト用CSVおいておくrepo↓
        DatabaseOperation.CLEAN_INSERT.execute(dbcon, inputCsvDataSet);
    }

    @AfterEach
    public void teardown()throws Exception {
        this.dbcon.close(); //後始末
    }

    @DisplayName("SELECT TEST…観点:useridから全taskを取得できる事.")
    @Test
    public void testSelectAll() {
        String user_id = "1";
        List<Task> actuals = mapper.findAllTasksByUserId(user_id);
        assertEquals(3, actuals.size());
    }

    @DisplayName("SELECT TEST…観点:taskidからtaskを取得できる事.")
    @Test
    public void testFindTaskById(){
        String task_id = "1";
        Task actual = mapper.findTaskById(task_id);

        assertEquals("1", actual.getTask_id());
        assertEquals("test1", actual.getTask_name());
        assertEquals("aaaaa", actual.getTask_desc());
        assertEquals("1", actual.getUser_id());
        assertEquals(1, actual.getUse_count());
        assertEquals(Timestamp.valueOf("2021-02-01 03:15:45"), actual.getLast_update());
        
        
    }
}