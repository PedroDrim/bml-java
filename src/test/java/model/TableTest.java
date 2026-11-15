package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    @Test
    public void testNewInstance() throws IOException {
        Table table = new Table("data/test.csv");
        assertInstanceOf(Table.class, table, "1. Devera ser inscanciavel caso possua valor valido");
    }

    @Test
    public void testGetUserInfoList() throws IOException {
        Table table = new Table("data/test.csv");
        assertEquals(table.getUserInfoList(), table.getUserInfoList(), "2. 'userInfoList' devera retornar valor corretamente");
    }
}
