package org.gnuhpc.interview.systemdesign.practice.simplezk.test;

import lombok.extern.log4j.Log4j;
import org.gnuhpc.interview.systemdesign.practice.simplezk.ZkServer;
import org.gnuhpc.interview.systemdesign.practice.simplezk.exception.InvalidPathFormatException;
import org.gnuhpc.interview.systemdesign.practice.simplezk.exception.NodeAlreadyExistException;
import org.gnuhpc.interview.systemdesign.practice.simplezk.exception.PathNonExistException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Log4j
public class TestCase {
    private ZkServer zkServer;

    @Before
    public void before() {
        zkServer = new ZkServer();
    }

    @Test
    public void testValidFormatPath() throws IllegalAccessException, NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        assertNotNull(zkServer.create_path("///kafka", "v1"));
    }

    @Test(expected = PathNonExistException.class)
    public void testPathNonExist1() throws IllegalAccessException, NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        zkServer.create_path("/ab/cd", "v1");
    }

    @Test(expected = PathNonExistException.class)
    public void testPathNonExist2() throws InvalidPathFormatException, NodeAlreadyExistException, PathNonExistException {
        zkServer.set_value("/a", "v1");
    }

    @Test(expected = PathNonExistException.class)
    public void testPathNonExist3() throws IllegalAccessException, NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        zkServer.watch("/abcd/abcd", (n, newValue) -> {
        });
    }

    @Test(expected = InvalidPathFormatException.class)
    public void testInvalidPathFormat1() throws IllegalAccessException, NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        zkServer.create_path("abcd/abcd", "a");
    }

    @Test(expected = InvalidPathFormatException.class)
    public void testInvalidPathFormat2() throws IllegalAccessException, NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        zkServer.create_path("/abcd/abcd/", "v1");
    }

    @Test(expected = InvalidPathFormatException.class)
    public void testInvalidPathFormat3() throws IllegalAccessException, NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        zkServer.watch("/abcd/abcd/", (n, newValue) -> {
        });
    }

    @Test(expected = NodeAlreadyExistException.class)
    public void testNodeAlreadyExist1() throws InvalidPathFormatException, NodeAlreadyExistException, PathNonExistException {
        zkServer.create_path("/a", "v1");
        zkServer.create_path("/a", "v2");
    }

    @Test(expected = NodeAlreadyExistException.class)
    public void testNodeAlreadyExist2() throws InvalidPathFormatException, NodeAlreadyExistException, PathNonExistException {
        zkServer.create_path("/", "v1");
    }

    @Test
    public void testCreatePath() throws NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        assertNotNull(zkServer.create_path("/a", "v1"));
        assertNotNull(zkServer.create_path("/a/b", "v2"));
        assertNotNull(zkServer.create_path("/a/b/c", "v3"));
    }

    @Test
    public void testSetValue() throws NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        assertNotNull(zkServer.create_path("/a", "v1"));
        assertNotNull(zkServer.create_path("/a/b", "v2"));
        assertNotNull(zkServer.create_path("/a/b/c", "v3"));
        assertNotNull(zkServer.set_value("/a/b/c", "v1"));
    }

    @Test
    public void testGetValue() throws NodeAlreadyExistException, PathNonExistException, InvalidPathFormatException {
        assertNotNull(zkServer.create_path("/a", "v1"));
        assertNotNull(zkServer.create_path("/a/b", "v2"));
        assertNotNull(zkServer.create_path("/a/b/c", "v3"));
        assertNotNull(zkServer.set_value("/a/b/c", "v1"));
        assertEquals(zkServer.get_value("/a/b/c"), "v1");
    }

    /*
    Watch1 trigger: /a/b/c:newvalue
    Watch2 trigger: /a/b/c:newvalue
    Watch3 trigger: /a/b/c:newvalue
     */
    @Test
    public void testWatch() throws PathNonExistException, NodeAlreadyExistException, InvalidPathFormatException {
        zkServer.create_path("/a", "v1");
        zkServer.create_path("/a/b", "v2");
        zkServer.create_path("/a/b/c", "v3");
        zkServer.set_value("/a/b", "v1");
        zkServer.watch("/a", (path, newValue) -> {
            System.out.println("Watch1 trigger: " + path + ":" + newValue);
        });
        zkServer.watch("/a", (path, newValue) -> {
            System.out.println("Watch2 trigger: " + path + ":" + newValue);
        });
        zkServer.watch("/a/b", (path, newValue) -> {
            System.out.println("Watch3 trigger: " + path + ":" + newValue);
        });

        zkServer.set_value("/a/b/c", "newvalue");
    }

}

