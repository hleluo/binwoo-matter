package com.binwoo.matter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件中存在大量数据，亿级别的数据，需要按照某一列进行排序.
 *
 * @author hleluo
 * @date 2020/7/20 16:34
 */
public class TopK {

  /**
   * 文件读模式.
   */
  private static final String FILE_READ_MODE = "r";

  public static void main(String[] args) {
    /*现有一个文本文件，文件中存在大量数据，数据条数达到亿级别的量，需要按照某一列进行排序。
    样例数据如下：
    buyer1,item1,1381234****,123,2020-01-01 01:00:00,1...
    buyer2,item2,1381234****,123,2020-01-01 01:00:00,2...
    buyer2,item2,1381234****,123,2020-01-01 01:00:00,3...
    要求：
    1：可以按照其中某几列进行排序，按照手机号码或者用户ID进行排序。
    2：内存使用限制不超过64M，高效快速完成排序，并输出前100万数据。*/
    String filepath = "/Users/hleluo/Documents/demo.txt";
    int top = 10;
    try {
      TopKMaxHeap heap = getMaxHeap(filepath, top);
      heap.sort();
      long[] positions = heap.getPositions();
      printRows(filepath, positions);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 读取文件，构建最大堆.
   *
   * @param filepath 文件路径
   * @param top 前top个关键词
   * @return 最大堆
   * @throws IOException IO异常
   */
  private static TopKMaxHeap getMaxHeap(String filepath, int top) throws IOException {
    File file = new File(filepath);
    if (!file.exists()) {
      throw new FileNotFoundException("file not exist");
    }
    try (RandomAccessFile raf = new RandomAccessFile(filepath, FILE_READ_MODE)) {
      String line = null;
      String key = null;
      // 记录行号，从0开始.
      int row = 0;
      String[] keys = new String[top];
      long[] positions = new long[top];
      TopKMaxHeap heap = null;
      long position = 0;
      while ((line = raf.readLine()) != null) {
        // 将用户id作为查询关键词
        if (line.indexOf(',') < 0) {
          row++;
          position = raf.getFilePointer();
          continue;
        }
        key = line.substring(0, line.indexOf(','));
        if (row < top) {
          keys[row] = key;
          positions[row] = position;
        } else {
          // 构建长度为top的堆.
          if (heap == null) {
            heap = new TopKMaxHeap(keys, positions, top);
          }
          heap.add(key, position);
        }
        row++;
        position = raf.getFilePointer();
      }
      // 如果行数小于top，直接构建长度为row的堆.
      if (row < top) {
        heap = new TopKMaxHeap(keys, positions, row);
      }
      return heap;
    }
  }

  /**
   * 输出行数据.
   *
   * @param filepath 文件路径
   * @param positions 行起始指针位置
   * @throws IOException IO异常
   */
  private static void printRows(String filepath, long[] positions) throws IOException {
    try (RandomAccessFile raf = new RandomAccessFile(filepath, FILE_READ_MODE);) {
      for (long position : positions) {
        raf.seek(position);
        String line = raf.readLine();
        System.out.println(line);
      }
    }
  }

}
