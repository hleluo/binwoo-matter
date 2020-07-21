package com.binwoo.matter;

/**
 * TopK文件大顶堆.
 *
 * @author hleluo
 * @date 2020/7/20 16:34
 */
public class TopKMaxHeap {


  /**
   * 各节点查询关键字.
   */
  private String[] keys;
  /**
   * 各节点对应的记录起始位置.
   */
  private long[] positions;

  /**
   * 构造函数.
   *
   * @param keys 初始化的查询关键字数组
   * @param positions 初始化的查询关键字起始位置
   * @param length 堆长度
   */
  public TopKMaxHeap(String[] keys, long[] positions, int length) {
    if (keys == null) {
      throw new IllegalArgumentException("key can not be null");
    }
    this.keys = new String[length];
    this.positions = new long[length];
    for (int i = 0; i < length; i++) {
      this.keys[i] = keys[i];
      this.positions[i] = positions[i];
    }
    build(this.keys.length);
  }

  /**
   * 构建最大堆.
   */
  private void build(int length) {
    if (length <= 1) {
      return;
    }
    // 完全二叉树只有数组下标小于或等于length / 2 - 1的元素有孩子结点，遍历这些结点.
    for (int i = length / 2 - 1; i >= 0; i--) {
      adapt(i, length);
    }
  }

  /**
   * 调整为最大堆.
   *
   * @param index 下标索引
   * @param length 待调整数组长度
   */
  private void adapt(int index, int length) {
    int leftIndex = getLeftChildIndex(index);
    int rightIndex = getRightChildIndex(index);
    int largestIndex = index;
    // 存在左结点，且左结点的值大于根结点的值.
    if (leftIndex < length && keys[leftIndex].compareTo(keys[index]) > 0) {
      largestIndex = leftIndex;
    }
    // 存在右结点，且右结点的值大于以上比较的较小值.
    if (rightIndex < length && keys[rightIndex].compareTo(keys[largestIndex]) > 0) {
      largestIndex = rightIndex;
    }
    // 左右结点的值都小于根节点，无需调整.
    if (index == largestIndex) {
      return;
    }
    // 交换根节点和左右结点中最小的那个值，把根节点的值替换下去.
    swap(index, largestIndex);
    // 继续调整受影响的节点
    adapt(largestIndex, length);
  }

  /**
   * 获取子节点左边元素下标索引.
   *
   * @param index 当前索引
   * @return 下标索引
   */
  private int getLeftChildIndex(int index) {
    return ((index + 1) << 1) - 1;
  }

  /**
   * 获取子节点右边元素下标索引.
   *
   * @param index 当前索引
   * @return 下标索引
   */
  private int getRightChildIndex(int index) {
    return (index + 1) << 1;
  }

  /**
   * 交换数据.
   *
   * @param i 索引
   * @param j 索引
   */
  private void swap(int i, int j) {
    String key = keys[i];
    long position = positions[i];
    keys[i] = keys[j];
    positions[i] = positions[j];
    keys[j] = key;
    positions[j] = position;
  }

  /**
   * 堆排序，从小到大.
   */
  public void sort() {
    for (int i = keys.length - 1; i > 0; i--) {
      // 交换根节点和最末节点，使得最大值至于末尾
      swap(0, i);
      // 剩余i个节点重新构建最大堆
      build(i);
    }
  }

  /**
   * 添加节点数据.
   *
   * @param key 查询关键词
   * @param position 起始位置
   */
  public void add(String key, long position) {
    key = key == null ? "" : key;
    if (key.compareTo(keys[0]) >= 0) {
      return;
    }
    keys[0] = key;
    positions[0] = position;
    adapt(0, keys.length);
  }

  /**
   * 获取所有起始位置.
   *
   * @return 起始位置数组
   */
  public long[] getPositions() {
    return positions;
  }
}
