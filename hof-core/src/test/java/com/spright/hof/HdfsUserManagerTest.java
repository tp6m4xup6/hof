/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spright.hof;

import java.io.File;
import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.Md5PasswordEncryptor;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication;
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
public class HdfsUserManagerTest {

  private static User user1;
  private static User user2;
  private static HdfsUser hdfsUser1;

  public HdfsUserManagerTest() {

  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println("setUpClass:");

    user1 = Mockito.mock(User.class);
    Mockito.when(user1.getName()).thenReturn("user1");
    Mockito.when(user1.getPassword()).thenReturn("pwd");
    Mockito.when(user1.getAuthorities()).thenReturn(null);
    Mockito.when(user1.getMaxIdleTime()).thenReturn(123);
    Mockito.when(user1.getHomeDirectory()).thenReturn("/home");
    Mockito.when(user1.getEnabled()).thenReturn(false);

    user2 = Mockito.mock(User.class);
    Mockito.when(user2.getName()).thenReturn("user2");
    Mockito.when(user2.getPassword()).thenReturn("pwd");
    Mockito.when(user2.getAuthorities()).thenReturn(null);
    Mockito.when(user2.getMaxIdleTime()).thenReturn(123);
    Mockito.when(user2.getHomeDirectory()).thenReturn("/home");
    Mockito.when(user2.getEnabled()).thenReturn(false);

    hdfsUser1 = new HdfsUser(user1);
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
   * Test of getFile method, of class HdfsUserManager.
   */
  @Test
  public void testGetFile() {
    System.out.println("getFile");
    HdfsUserManager instance = new HdfsUserManager();
    File expResult = new File("testFile");
    instance.setFile(expResult);
    File result = instance.getFile();
    assertEquals(expResult, result);
  }

  /**
   * Test of setFile method, of class HdfsUserManager.
   */
  @Test
  public void testSetFile() {
    System.out.println("setFile");
    File propFile = new File("testFile");
    HdfsUserManager instance = new HdfsUserManager();
    instance.setFile(propFile);
    assertEquals(propFile, instance.getFile());
  }

  /**
   * Test of getPasswordEncryptor method, of class HdfsUserManager.
   */
  @Test
  public void testGetPasswordEncryptor() {
    System.out.println("getPasswordEncryptor");
    HdfsUserManager instance = new HdfsUserManager();
    PasswordEncryptor expResult = new Md5PasswordEncryptor();
    instance.setPasswordEncryptor(expResult);
    PasswordEncryptor result = instance.getPasswordEncryptor();
    assertEquals(expResult, result);

  }

  /**
   * Test of setPasswordEncryptor method, of class HdfsUserManager.
   */
  @Test
  public void testSetPasswordEncryptor() {
    System.out.println("setPasswordEncryptor");
    PasswordEncryptor passwordEncryptor = new Md5PasswordEncryptor();
    HdfsUserManager instance = new HdfsUserManager();
    instance.setPasswordEncryptor(passwordEncryptor);
    assertEquals(passwordEncryptor, instance.getPasswordEncryptor());
  }

  /**
   * Test of configure method, of class HdfsUserManager.
   */
  @Test
  public void testConfigure() {
    System.out.println("configure");
    File file = new File("src/test/resources/usersTest.conf");
    HdfsUserManager instance = new HdfsUserManager();
    instance.setFile(file);
    instance.configure();
    User expUser = instance.getUserByName("firstuser");

    assertTrue(instance.doesExist("firstuser"));
    assertEquals(expUser.getName(), "firstuser");
    assertNotNull(expUser.authorize(new WriteRequest()));
    assertEquals(expUser.getMaxIdleTime(), 0);
    assertEquals(expUser.getHomeDirectory(), "/");
    assertTrue(expUser.getEnabled());

  }

  /**
   * Test of save method, of class HdfsUserManager.
   */
  @Test
  public void testSave() throws Exception {
    System.out.println("save");

    HdfsUserManager instance = new HdfsUserManager();
    instance.save(user1);
    User expUser = instance.getUserByName("user1");

    assertEquals(expUser.getName(), "user1");
//        assertEquals(expUser.getPassword(), "pwd");   //had encrypt and store.
    assertNull(expUser.authorize(new WriteRequest()));   //null means no permission
    assertEquals(expUser.getMaxIdleTime(), 123);
    assertEquals(expUser.getHomeDirectory(), "/home");
    assertFalse(expUser.getEnabled());

  }

  /**
   * Test of delete method, of class HdfsUserManager.
   */
  @Test
  public void testDelete() throws Exception {
    System.out.println("delete");
    String usrName = "user1";

    HdfsUserManager instance = new HdfsUserManager();
    instance.save(user1);
    instance.delete(usrName);
    assertNull(instance.getUserByName(usrName));

  }

  /**
   * Test of getAllUserNames method, of class HdfsUserManager.
   */
  @Test
  public void testGetAllUserNames() {
    System.out.println("getAllUserNames");
    HdfsUserManager instance = new HdfsUserManager();

    try {
      instance.save(user1);
      instance.save(user2);
    } catch (FtpException ex) {
      fail("Test fail when save users");
    }

    String[] result = instance.getAllUserNames();
    String[] expResult = {"user1", "user2"};
    assertArrayEquals(expResult, result);

  }

  /**
   * Test of getUserByName method, of class HdfsUserManager.
   */
  @Test
  public void testGetUserByName() {
    System.out.println("getUserByName");
    String userName = "user1";
    HdfsUserManager instance = new HdfsUserManager();
    try {
      instance.save(user1);
    } catch (FtpException ex) {
      fail("Test fail when save users");
    }

    User expUser = instance.getUserByName(userName);
    assertEquals("user1", expUser.getName());
//        assertEquals("pwd", expUser.getPassword());
    assertNull(expUser.authorize(new WriteRequest()));   //null means no permission
    assertEquals(123, expUser.getMaxIdleTime());
    assertEquals("/home", expUser.getHomeDirectory());
    assertEquals(false, expUser.getEnabled());

  }

  /**
   * Test of doesExist method, of class HdfsUserManager.
   */
  @Test
  public void testDoesExist() {
    System.out.println("doesExist");
    String name = "user1";
    HdfsUserManager instance = new HdfsUserManager();
    try {
      instance.save(user1);
    } catch (FtpException ex) {
      fail("Test fail when save users");
    }
    boolean result = instance.doesExist(name);
    assertTrue(result);
  }

  /**
   * Test of authenticate method, of class HdfsUserManager.
   */
  @Test
  public void testAuthenticate() throws Exception {
    System.out.println("authenticate");
    UsernamePasswordAuthentication authentication = new UsernamePasswordAuthentication("user1", "pwd");

    HdfsUserManager instance = new HdfsUserManager();
    instance.save(user1);
    User expUser = instance.authenticate(authentication);

    assertEquals(expUser.getName(), "user1");
    assertNull(expUser.authorize(new WriteRequest()));   //null means no permission
    assertEquals(123, expUser.getMaxIdleTime());
    assertEquals("/home", expUser.getHomeDirectory());
    assertEquals(false, expUser.getEnabled());
  }

  /**
   * Test of dispose method, of class HdfsUserManager.
   */
  @Test(expected = NullPointerException.class)
  public void testDispose() {
    System.out.println("test dispose");
    HdfsUserManager instance = new HdfsUserManager();
    try {
      instance.save(user1);
    } catch (FtpException ex) {
      fail("Test fail when save users");
    }
    assertTrue(instance.doesExist("user1"));
    instance.dispose();

    //Set userDataProp null,so many function will cause NullPointerException
    instance.doesExist("user1");
  }

}