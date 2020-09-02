/**
 * Filename: PackageManagerTest.java Project: p4 Authors: Qinglang Ye Course: cs400 Email:
 * qye25@wisc.edu
 * 
 * JUnit Test PackageManager implementation
 */

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageManagerTest {

    /**
     * TestCase 1: valid.json (no cycles) { "packages": [ { "name": "A", "dependencies": ["B"] }, {
     * "name": "B", "dependencies": ["C","D","G"] }, { "name": "E", "dependencies": ["B","F"] }, {
     * "name": "D", "dependencies": ["K","F","G"] } ] }
     */

    /*
     * TestCase 2: shared_dependencies.json (with cycles) { "packages": [ { "name": "A",
     * "dependencies": ["B","C"] }, { "name": "B", "dependencies": ["D"] }, { "name": "C",
     * "dependencies": ["D","A"] }, { "name": "D", "dependencies": ["E","F"] }
     * 
     * ] }
     */
    PackageManager pm;

    @BeforeEach
    void setUp() throws Exception {
        pm = new PackageManager();
    }

    @AfterEach
    void tearDown() throws Exception {
        pm = null;
    }

    /**
     * Test packagemanager can phrase given json file and build a graph
     */
    @Test
    void test0_read_json_file() {
        try {
            pm.constructGraph("valid.json");
            // String[] pkg= {"A","B","C","D","E","F","G","K"};
            Set<String> expcted = new HashSet<>();
            expcted.add("A");
            expcted.add("B");
            expcted.add("C");
            expcted.add("D");
            expcted.add("E");
            expcted.add("F");
            expcted.add("G");
            expcted.add("K");
            assertEquals(expcted, pm.getAllPackages());
        } catch (FileNotFoundException e) {
            fail(""); // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            fail(""); // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Test get installation order when no cycles
     */
    @Test
    void test1_getInstallationOrder_when_no_cycle() {
        try {
            pm.constructGraph("valid.json");
            List<String> exp = new ArrayList<>();
            exp.add("K");
            exp.add("F");
            exp.add("G");
            exp.add("D");
            assertEquals(exp, pm.getInstallationOrder("D"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("");
            e.printStackTrace();
        }
    }

    /**
     * Test get installation order when package not exists
     * 
     * @throws PackageNotFoundException
     */
    @Test
    void test2_getInstallationOrder_throws_PackageNotFoundException() {
        try {
            pm.constructGraph("valid.json");
            pm.getInstallationOrder("z");
            fail("should throw PackageNotFoundException");

        } catch (PackageNotFoundException e) {
            // TODO: handle exception
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("");
            e.printStackTrace();
        }
    }

    /**
     * Test get installation order when there is a cycle
     * 
     * @throws CycleException
     */
    @Test
    void test3_getInstallationOrder_throws_CycleException() {
        try {
            pm.constructGraph("shared_dependencies.json");
            pm.getInstallationOrder("A");
            fail("should throw CycleException");

        } catch (CycleException e) {
            // TODO: handle exception
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("");
            e.printStackTrace();
        }
    }

    /**
     * Test toinstall returns correct list
     */
    @Test
    void test4_toinstall() {
        try {
            pm.constructGraph("shared_dependencies.json");
            List<String> exp = new ArrayList<>();
            exp.add("F");
            exp.add("D");
            exp.add("B");
            if (!exp.containsAll(pm.toInstall("B", "E"))
                            || !pm.toInstall("B", "E").containsAll(exp)) {
                fail("");
            }
        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Test toinstall returns empty list if both packages are already installed
     */
    @Test
    void test5_toinstall_if_both_exist() {
        try {
            pm.constructGraph("shared_dependencies.json");
            List<String> exp = new ArrayList<>();

            // System.out.println(pm.toInstall("E", "D"));
            if (!exp.containsAll(pm.toInstall("E", "D"))
                            || !pm.toInstall("E", "D").containsAll(exp)) {
                fail("");
            }
        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Test toinstall throws PackageNotFoundException
     */
    @Test
    void test6_toinstall_if_package_not_exist() {
        try {
            pm.constructGraph("shared_dependencies.json");

            pm.toInstall("notExist", "A");
            fail("should throw PackageNotFoundException");
        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block

        }
    }

    /**
     * Test getInstallationOrderForAllPackages
     */
    @Test
    void test7_getInstallationOrderForAllPackages_with_no_cycles() {
        try {
            pm.constructGraph("valid.json");

            List<String> actual = pm.getInstallationOrderForAllPackages();
            List<String> exp = new ArrayList<>();
            exp.add("C");
            exp.add("K");
            exp.add("F");
            exp.add("G");
            exp.add("D");
            exp.add("B");
            exp.add("A");
            exp.add("E");
            assertEquals(exp, actual);
        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            fail(""); // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Test getInstallationOrderForAllPackages if there is a cycle
     */
    @Test
    void test8_getInstallationOrderForAllPackages_with_cycles() {
        try {
            pm.constructGraph("shared_dependencies.json");

            List<String> actual = pm.getInstallationOrderForAllPackages();

            fail("should throw CycleException");
        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            fail(""); // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Test getPackageWithMaxDependencies
     */
    @Test
    void test9_getPackageWithMaxDependencies_with_no_cycles() {
        try {
            pm.constructGraph("valid.json");

            String actual = pm.getPackageWithMaxDependencies();
            assertEquals("A", actual);

        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            fail(""); // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Test getPackageWithMaxDependencies
     */
    @Test
    void test10_getPackageWithMaxDependencies_with_cycles() {
        try {
            pm.constructGraph("shared_dependencies.json");

            pm.getPackageWithMaxDependencies();

            fail("should throw CycleException");
        } catch (IOException | ParseException e) {
            fail("");// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            fail(""); // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
