package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoTest {

    @Test
    public void testNewInstance() {
        UserInfo userInfo = new UserInfo("u1", "p1");
        assertInstanceOf(UserInfo.class, userInfo, "1. Devera ser instanciavel corretamente");
    }

    @Test
    public void testSetUser() {
        String expected = "nu1";
        UserInfo userInfo = new UserInfo("u1", "p1");
        userInfo.setUser(expected);
        assertEquals(expected, userInfo.getUser(), "2. 'username' devera ser alteravel");
    }

    @Test
    public void testSetPassword() {
        String expected = "np1";
        UserInfo userInfo = new UserInfo("u1", "p1");
        userInfo.setPassword(expected);
        assertEquals("HASH1pn000", userInfo.getPassword(), "3. 'password' devera ser alteravel");
    }

    @Test
    public void testGetUser() {
        String expected = "nu1";
        UserInfo userInfo = new UserInfo(expected, "p1");
        assertEquals(expected, userInfo.getUser(), "4. 'username' devera ser obtido corretamente");
    }

    @Test
    public void testGetPassword() {
        String expected = "HASH1p000";
        UserInfo userInfo = new UserInfo("u1", "p1");
        assertEquals(expected, userInfo.getPassword(), "5. Metodo 'getPassword()' devera ser executado corretamente");
    }
}
