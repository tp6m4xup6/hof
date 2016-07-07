/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spright.hof;

import java.util.ArrayList;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.WriteRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author CDCLAB
 */
public class HdfsUserTest {
  private static User user;
  private static HdfsUser hdfsUser;

  public HdfsUserTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    user = Mockito.mock(User.class);
    Mockito.when(user.getName()).thenReturn("user");
    Mockito.when(user.getPassword()).thenReturn("pwd");
    Mockito.when(user.getAuthorities()).thenReturn(null);
    Mockito.when(user.getMaxIdleTime()).thenReturn(123);
    Mockito.when(user.getHomeDirectory()).thenReturn("/home");
    Mockito.when(user.getEnabled()).thenReturn(false);

    hdfsUser = new HdfsUser(user);
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
  public void testGetGroups() {
    System.out.println("getGroups");
    HdfsUser instance = new HdfsUser(user);
    ArrayList<String> expResult = new ArrayList<String>();
    expResult.add("g1");
    instance.setGroups(expResult);

    assertEquals(expResult, instance.getGroups());
  }

  /**
   * Test of getMainGroup method, of class HdfsUser.
   */
  @Test
  public void testGetMainGroup() {
    System.out.println("getMainGroup");
    HdfsUser instance = new HdfsUser(user);
    ArrayList<String> expResult = new ArrayList<String>();
    expResult.add("g1");
    expResult.add("g2");
    instance.setGroups(expResult);

    assertEquals("g1",instance.getMainGroup());
  }

  /**
   * Test of isGroupMember method, of class HdfsUser.
   */
  @Test
  public void testIsGroupMember() {
    System.out.println("isGroupMember");
    HdfsUser instance = new HdfsUser(user);
    ArrayList<String> groups = new ArrayList<String>();
    groups.add("g1");
    groups.add("g2");
    instance.setGroups(groups);

    assertTrue(instance.isGroupMember("g2"));
    assertFalse(instance.isGroupMember("g3"));
  }

  /**
   * Test of setGroups method, of class HdfsUser.
   */
  @Test
  public void testSetGroups() {
    System.out.println("setGroups");
    HdfsUser instance = new HdfsUser(user);
    ArrayList<String> groups = new ArrayList<String>();
    groups.add("g1");
    instance.setGroups(groups);

    assertEquals(groups,instance.getGroups());
  }

  /**
   * Test of getName method, of class HdfsUser.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    HdfsUser instance = new HdfsUser(user);

    assertEquals(user.getName(), instance.getName());
  }

  /**
   * Test of setName method, of class HdfsUser.
   */
  @Test
  public void testSetName() {
    System.out.println("setName");
    String name = "";
    HdfsUser instance = new HdfsUser(user);
    instance.setName("user2");

    assertEquals("user2", instance.getName());
  }

  /**
   * Test of getPassword method, of class HdfsUser.
   */
  @Test
  public void testGetPassword() {
    System.out.println("getPassword");
    HdfsUser instance = new HdfsUser(user);

    assertEquals(user.getPassword(), instance.getPassword());
  }

  /**
   * Test of setPassword method, of class HdfsUser.
   */
  @Test
  public void testSetPassword() {
    System.out.println("setPassword");
    HdfsUser instance = new HdfsUser(user);
    instance.setPassword("444");

    assertEquals("444", instance.getPassword());
  }

  /**
   * Test of getAuthorities method, of class HdfsUser.
   */
  @Test
  public void testGetAuthorities_0args() {
    System.out.println("getAuthorities");
    Authority[] authorities = new Authority[0];
    HdfsUser instance = new HdfsUser(user);

    instance.setAuthorities(authorities);
    assertArrayEquals(authorities,instance.getAuthorities());

    instance.setAuthorities(null);
    assertNull(instance.getAuthorities());
  }

  /**
   * Test of setAuthorities method, of class HdfsUser.
   */
  @Test
  public void testSetAuthorities() {
    System.out.println("setAuthorities");
    Authority[] authorities = new Authority[0];
    HdfsUser instance = new HdfsUser(user);

    instance.setAuthorities(authorities);
    assertArrayEquals(authorities,instance.getAuthorities());

    instance.setAuthorities(null);
    assertNull(instance.getAuthorities());
  }

  /**
   * Test of getMaxIdleTime method, of class HdfsUser.
   */
  @Test
  public void testGetMaxIdleTime() {
    System.out.println("getMaxIdleTime");
    HdfsUser instance = new HdfsUser(user);

    assertEquals(user.getMaxIdleTime(), instance.getMaxIdleTime());
  }

  /**
   * Test of setMaxIdleTime method, of class HdfsUser.
   */
  @Test
  public void testSetMaxIdleTime() {
    System.out.println("setMaxIdleTime");
    HdfsUser instance = new HdfsUser(user);
    instance.setMaxIdleTime(5566);
    assertEquals(5566, instance.getMaxIdleTime());

    instance.setMaxIdleTime(-5566);
    assertEquals(0, instance.getMaxIdleTime());
  }

  /**
   * Test of getEnabled method, of class HdfsUser.
   */
  @Test
  public void testGetEnabled() {
    System.out.println("getEnabled");
    HdfsUser instance = new HdfsUser(user);

    assertEquals(user.getEnabled(), instance.getEnabled());
  }

  /**
   * Test of setEnabled method, of class HdfsUser.
   */
  @Test
  public void testSetEnabled() {
    System.out.println("setEnabled");
    HdfsUser instance = new HdfsUser(user);
    instance.setEnabled(false);

    assertFalse(instance.getEnabled());
  }

  /**
   * Test of getHomeDirectory method, of class HdfsUser.
   */
  @Test
  public void testGetHomeDirectory() {
    System.out.println("getHomeDirectory");
    HdfsUser instance = new HdfsUser(user);

    assertEquals(user.getHomeDirectory(), instance.getHomeDirectory());
  }

  /**
   * Test of setHomeDirectory method, of class HdfsUser.
   */
  @Test
  public void testSetHomeDirectory() {
    System.out.println("setHomeDirectory");
    HdfsUser instance = new HdfsUser(user);
    instance.setHomeDirectory("/home/home/home");

    assertEquals("/home/home/home", instance.getHomeDirectory());
  }

  /**
   * Test of toString method, of class HdfsUser.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    HdfsUser instance = new HdfsUser(user);

    assertEquals(user.getName(), instance.toString());
  }

  /**
   * Test of authorize method, of class HdfsUser.
   */
  @Test
  public void testAuthorize() {
    System.out.println("authorize");
    AuthorizationRequest request = new WriteRequest();
    HdfsUser instance = new HdfsUser(user);

    assertNull(instance.authorize(request));

    Authority[] authorityArray = new Authority[1];
    Authority authority = Mockito.mock(Authority.class);
    Mockito.when(authority.canAuthorize(request)).thenReturn(true);
    Mockito.when(authority.authorize(request)).thenReturn(request);
    authorityArray[0] = authority;
    instance.setAuthorities(authorityArray);

    assertEquals(request,instance.authorize(request));
  }

}
