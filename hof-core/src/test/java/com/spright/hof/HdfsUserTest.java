package com.spright.hof;

import org.apache.ftpserver.ftplet.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class HdfsUserTest {
  
  public HdfsUserTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getGroups method, of class HdfsUser.
   */
  @Test
  public void testConstructor() {
    System.out.println("testConstructor");
    User user = Mockito.mock(User.class);
    Mockito.when(user.getName()).thenReturn("user");
    Mockito.when(user.getPassword()).thenReturn("pwd");
    Mockito.when(user.getAuthorities()).thenReturn(null);
    Mockito.when(user.getMaxIdleTime()).thenReturn(123);
    Mockito.when(user.getHomeDirectory()).thenReturn("/home");
    Mockito.when(user.getEnabled()).thenReturn(false);
    HdfsUser instance = new HdfsUser(user);
    assertEquals("user", instance.getName());
    assertEquals("pwd", instance.getPassword());
    assertNull(instance.getAuthorities());
    assertEquals(123, instance.getMaxIdleTime());
    assertEquals("/home", instance.getHomeDirectory());
    assertEquals(false, instance.getEnabled());
  }
}
