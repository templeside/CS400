//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////
//
// Title: P4
// Files: Graph, PackageManager, GraphTest, PackageManagerTest, CycleException, GraphADT, Package, PackageNotFoundException
// Course:CS400, Spring2019        
//
// Author: Chanwoong Jhon, Chaiyeen Oh
// Email:  cjhon@wisc.edu, coh26@wisc.edu
// Lecturer's Name: Andrew L KUEMMEL (004)
// Due Date: 4/19/2019
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X  Write-up states that pair programming is allowed for this assignment.
//   X  We have both read and understand the course Pair Programming Policy.
//   X  We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * PackageManger Test Class is testing methods in PackageManager.java
 * by constructing temporary graph
 * 
 * @author chaiy and jhon
 *
 */
class PackageManagerTest {

   /**
    * Testing if basic graph is being constructed properly without throwing any errors
    */
   @Test
   void test_ConstructGraph1() {
      PackageManager pm= new PackageManager();
      try {
         pm.constructGraph("valid.json");
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         fail("TEST1 FILE_NOT_FOUND_EXCEPTION SHOULDN'T HAPPEN");
      } catch (IOException e) {
         e.printStackTrace();
         fail("TEST1 IOException SHOULDN'T HAPPEN");
      } catch (ParseException e) {
         e.printStackTrace();
         fail("TEST1 ParseException SHOULDN'T HAPPEN");
      }
   }

   /**
    * Testing if the method constructggraph will appropriately notices that the wrong filepath was given
    */
   @Test
   void test2_ConstructGraph_null_file() {
      PackageManager pm= new PackageManager();
      try {
         pm.constructGraph(" ");
      }
      catch(FileNotFoundException e){
         return; // this should happen with inappropriate jsonfilepath
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
      fail("TEST2 exception should have happened");
   }

   /**
    * testing getAllPackages method after constructing a graph
    */
   @Test
   void test3_getAllPackages() {
      PackageManager pm= new PackageManager();
      Set<String> actual_result = new HashSet<String>();
      actual_result.add("A");
      actual_result.add("B");
      actual_result.add("C");
      actual_result.add("D");
      actual_result.add("E");

      try {
         pm.constructGraph("valid.json");   
         Set<String> result = pm.getAllPackages();
         if(result.equals(actual_result)) { // compare with actual answer
            return;
         }
         fail("shouldn't happen");
      }
      catch(Exception e) {
         e.printStackTrace();
         fail("TEST2 FAILED");
      }
   }

   /**
    * testing getInstallationOrder method given A as an input
    */
   @Test
   void test4_getInstallationOrder_of_A() {
      PackageManager pm= new PackageManager();

      // result that I expect
      List<String> actual_result = new ArrayList<String>();
      actual_result.add("C");
      actual_result.add("D");
      actual_result.add("B");
      actual_result.add("A");

      try {
         pm.constructGraph("valid.json");

         if(pm.getInstallationOrder("A").equals(actual_result)) {
            return; // if they are equal it means it's a right answer
         }
         fail("shouldn't happen");
      }
      catch(Exception e) {
         e.printStackTrace();
         fail("Exception shouldn't happen");         
      }
   }

   /**
    * testing toInstall method in packageManager class
    */
   @Test
   void test5_toInstall() {
      try {
         PackageManager pm= new PackageManager();
         pm.constructGraph("valid.json");

         List<String> actual_result = new ArrayList<String>();
         actual_result.add("A"); // this is answer that i expects

         if(pm.toInstall("A", "B").equals(actual_result)) {
            return; //TRUE!
         }
         fail("shouldn't happen");
      }
      catch(Exception e) {
         e.printStackTrace();
         fail("fail");
      }
   }

   /**
    * testing getInstallationOrderForAllPackages method with valid.json file
    */
   @Test
   void test6_getInstallationOrderForAllPackages() {
      try {
         PackageManager pm= new PackageManager();
         pm.constructGraph("valid.json");

         List<String> actual_result = new ArrayList<String>();
         actual_result.add("C");
         actual_result.add("D");
         actual_result.add("B");
         actual_result.add("A");
         actual_result.add("E");

         if(pm.getInstallationOrderForAllPackages().equals(actual_result)){
            return; //passed!
         }
         fail("shouldn't happen");
      }
      catch(Exception e) {
         e.printStackTrace();
         fail("this expection shouldn't happen");
      }

   }

   /**
    * checking if getPackageWithMaxDependencies method properly works
    * it should return A in valid.json file
    */
   @Test
   void test7_getPackageWithMaxDependencies() {
      try {
         PackageManager pm= new PackageManager();
         pm.constructGraph("valid.json");

         String actual_result = "A"; // "A" string is answer that i expect
         if(pm.getPackageWithMaxDependencies().equals(actual_result)){
            return; // see if the methods runs as I intended
            // TRUE!
         }
         fail("shouldn't happen");
      }
      catch(Exception e) {
         e.printStackTrace();
         fail("this expection shouldn't happen");
      }

   }

}