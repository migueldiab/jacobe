/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package collectionsbenchmarks;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.procedure.TIntProcedure;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javolution.util.FastList;

/**
 *
 * @author migueldiab
 */
public class Main {

  private static final Random randGen = new Random();
  private static int items = 10000;
  private static int passes = 3;
  private static int temp = 0;
  private static long KILO_BYTE = 1024;
  private static long MEGA_BYTE = 1024 * KILO_BYTE;
  private static Runtime runtime = Runtime.getRuntime();
  private static final List<Integer> fastList = new FastList<Integer>();
  private static final List<Integer> googleList = com.google.common.collect.Lists.newArrayList();
  private static final List<Integer> arrayList = new ArrayList<Integer>();
  private static final TIntArrayList troveList = new TIntArrayList();
  private static double sum = 0;
  private static boolean verbose = false;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    final Date start = new Date();
    if (args.length<1) {
      System.out.println("Usage :");
      System.out.println("       java -jar CollectionsBenchmarks.jar (engine) [size] [passes] [verbose]");
      System.out.println("       engine :");
      System.out.println("                 trove, google, java or javolution.");
      System.out.println("       size :");
      System.out.println("                 Amount of elements to start with. default 10000");
      System.out.println("       passes :");
      System.out.println("                 Number of passes with 10x increment of items each. default 3");
      System.out.println("       verbose :");
      System.out.println("                 v");
      System.out.println("migueldiab@gmail.com");
      return;
    }
    try {
      items = Integer.parseInt(args[1]);
      passes = Integer.parseInt(args[2]);
      if (args[3].equals("v")) {
        verbose = true;
      }
    } catch (Exception e) {
    }
    if (args[0].equals("trove")) {
      troveInterface();
    } else {
      System.out.println("Memory      : " + usedMem());
      for (int i= 0; i < passes; i++) {
        List<Integer> testList = null;
        if (args[0].equals("google")) {
          testList = googleList();
        } else if (args[0].equals("java")) {
          testList = arrayList();
        } else if (args[0].equals("javolution")) {
          testList = fastList();
        }
        listInterfaces(testList);
        items = items * 10;
      }
      
    }
    final Date end = new Date();
    
  }

  private static List<Integer> fastList() {
    long startTime = System.nanoTime();
    
    for (int i = 0; i < items; i++) {
      final Integer nextInt = randGen.nextInt(10000);
      fastList.add(nextInt);
    }
    long endTime = System.nanoTime();
    if (verbose) {
      System.out.println("Items       : "+fastList.size());
    }
    System.out.println("Creation    : " + (endTime - startTime)/1000);
    return fastList;
  }

  private static List<Integer> arrayList() {
    long startTime = System.nanoTime();
    for (int i = 0; i < items; i++) {
      final Integer nextInt = randGen.nextInt(10000);
      arrayList.add(nextInt);
    }
    long endTime = System.nanoTime();
    if (verbose) {
      System.out.println("Items       : "+arrayList.size());
    }
    System.out.println("Creation    : " + (endTime - startTime)/1000);
    return arrayList;
  }

  private static List<Integer> googleList() {
    long startTime = System.nanoTime();
    for (int i = 0; i < items; i++) {
      final Integer nextInt = randGen.nextInt(10000);
      googleList.add(nextInt);
    }
    long endTime = System.nanoTime();
    if (verbose) {
      System.out.println("Items       : "+googleList.size());
    }
    System.out.println("Creation    : " + (endTime - startTime)/1000);
    return googleList;
  }

  private static TIntArrayList troveList() {
    long startTime = System.nanoTime();
    for (int i = 0; i < items; i++) {
      final Integer nextInt = randGen.nextInt(10000);
      troveList.add(nextInt);
    }
    long endTime = System.nanoTime();
    if (verbose) {
      System.out.println("Items       : "+troveList.size());
    }
    System.out.println("Creation    : " + (endTime - startTime)/1000);
    return troveList;
  }

  private static long addBench(List<Integer> testList) {
    long startTime = System.nanoTime();
    for (int i = 0; i < 1000; i++) {
      testList.add(randGen.nextInt(testList.size()), randGen.nextInt());
    }
    long endTime = System.nanoTime();
    long time = endTime - startTime;
    return time;
  }

  private static long addTBench(TIntArrayList testList) {
    long startTime = System.nanoTime();
    for (int i = 0; i < 1000; i++) {
      testList.insert(randGen.nextInt(testList.size()), randGen.nextInt());
    }
    long endTime = System.nanoTime();
    long time = endTime - startTime;
    return time;
  }

  private static long removeBench(List<Integer> testList) {
    long startTime = System.nanoTime();
    for (int i = 0; i < 1000; i++) {
      testList.remove(randGen.nextInt(testList.size()));
    }
    long endTime = System.nanoTime();
    long time = endTime - startTime;
    return time;
  }


  private static long removeTBench(TIntArrayList testList) {
    long startTime = System.nanoTime();
    for (int i = 0; i < 1000; i++) {
      testList.removeAt(randGen.nextInt(testList.size()));
    }
    long endTime = System.nanoTime();
    long time = endTime - startTime;
    return time;
  }

  private static long iterateBench(List<Integer> testList) {
    int temp = 0;
    long startTime = System.nanoTime();
    sum = 0;
    for (Integer integer : testList) {
      temp++;
      sum += integer;
    }
    if (verbose) {
      System.out.println("Sum : " + sum);
    }
    long endTime = System.nanoTime();
    long time = endTime - startTime;
    return time;
  }

  private static long iterateTBench(TIntArrayList testList) {
    long startTime = System.nanoTime();
    sum = 0;
    testList.forEach(new TIntProcedure() {
      @Override
      public boolean execute(final int value) {
        temp++;
        sum += value;
        return true;
      }
    });
    if (verbose) {
      System.out.println("Sum : " + sum);
    }
    long endTime = System.nanoTime();
    long time = endTime - startTime;
    return time;
  }

  private static void tempAdd() {
    temp++;
  }

  private static String usedMem() {
    return (String.valueOf((runtime.totalMemory() / KILO_BYTE) - (runtime.freeMemory() / KILO_BYTE)));
  }

  private static void listInterfaces(List<Integer> testList) {
    long addBench = 0;
    long removeBench = 0;
    long iterateBench = 0;
    long containsBench = 0;
    int runs = 10;
    for (int i = 0; i < runs; i++) {
      addBench += addBench(testList);
      removeBench += removeBench(testList);
      iterateBench += iterateBench(testList);
      containsBench += containsBench(testList);
    }
    System.out.println("Memory      : " + usedMem());
    System.out.println("Average add : " + addBench/10000);
    System.out.println("Average rem : " + removeBench/10000);
    System.out.println("Average ite : " + iterateBench/10000);
    System.out.println("Average con : " + containsBench);
  }

  private static void troveInterface() {
    System.out.println("Memory      : " + usedMem());
    for (int n = 0; n < passes; n++) {
      long addBench = 0;
      long removeBench = 0;
      long iterateBench = 0;
      long containsBench = 0;
      final TIntArrayList testList = troveList();
      int runs = 10;
      if (verbose) {
        System.out.println("Items       : "+testList.size());
        System.out.println("Runs        : "+runs);
      }
      for (int i = 0; i < runs; i++) {
        addBench += addTBench(testList);
        removeBench += removeTBench(testList);
        iterateBench += iterateTBench(testList);
        containsBench += containsTBench(testList);
      }
      System.out.println("Memory      : " + usedMem());
      System.out.println("Average add : " + addBench/10000);
      System.out.println("Average rem : " + removeBench/10000);
      System.out.println("Average ite : " + iterateBench/10000);
      System.out.println("Average con : " + containsBench);
      items = items * 10;
    }
  }

  private static long containsBench(List<Integer> testList) {
    int temp = 0;
    long startTime = System.nanoTime();
    int lookFor = randGen.nextInt(10000);
    int contains = testList.indexOf(lookFor);
    long endTime = System.nanoTime();
    long time = (endTime - startTime)/1000;
    if (verbose) {
      if (contains!=-1) {
        System.out.println("Found " + lookFor + " at " + contains + " in " + time);
      } else {
        System.out.println("Not Found " + lookFor + " in " + time);
      }
    }
    return time;
  }

  private static long containsTBench(TIntArrayList testList) {
    int temp = 0;
    long startTime = System.nanoTime();
    int lookFor = randGen.nextInt(10000);
    int contains = testList.indexOf(lookFor);
    long endTime = System.nanoTime();
    long time = (endTime - startTime)/1000;
    if (verbose) {
      if (contains!=-1) {
        System.out.println("Found " + lookFor + " at " + contains + " in " + time);
      } else {
        System.out.println("Not Found " + lookFor + " in " + time);
      }
    }
    return time;
  }


}
