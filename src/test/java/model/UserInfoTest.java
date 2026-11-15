package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoTest {

    @Test
    public void testNewInstance() {
        UserInfo userInfo = new UserInfo("u1", "p1", 0.0);
        assertInstanceOf(UserInfo.class, userInfo, "1. Devera ser instanciavel corretamente");
    }

    @Test
    public void testSetUser() {
        String expected = "nu1";
        UserInfo userInfo = new UserInfo("u1", "p1", 0.0);
        userInfo.setUser(expected);
        assertEquals(expected, userInfo.getUser(), "2. 'username' devera ser alteravel");
    }

    @Test
    public void testSetPassword() {
        String expected = "np1";
        UserInfo userInfo = new UserInfo("u1", "p1", 0.0);
        userInfo.setPassword(expected);
        assertEquals("HASH1pn000", userInfo.getPassword(), "3. 'password' devera ser alteravel");
    }

    @Test
    public void testSetCredit() {
        double expected = 1.0;
        UserInfo userInfo = new UserInfo("u1", "p1", 0.0);
        userInfo.setCredit(expected);
        assertEquals(expected, userInfo.getCredit(), "4. 'credit' devera ser alteravel");
    }

    @Test
    public void testGetUser() {
        String expected = "nu1";
        UserInfo userInfo = new UserInfo(expected, "p1", 0.0);
        assertEquals(expected, userInfo.getUser(), "5. 'username' devera ser obtido corretamente");
    }

    @Test
    public void testGetPassword() {
        String expected = "HASH1p000";
        UserInfo userInfo = new UserInfo("u1", "p1", 0.0);
        assertEquals(expected, userInfo.getPassword(), "6. Metodo 'getPassword()' devera ser executado corretamente");
    }

    @Test
    public void testGetCredit() {
        double expected = 1.0;
        UserInfo userInfo = new UserInfo("u1", "p1", expected);
        assertEquals(expected, userInfo.getCredit(), "7. Metodo 'getCredit()' devera ser executado corretamente");
    }
}
