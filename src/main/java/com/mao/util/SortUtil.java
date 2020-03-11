package com.mao.util;

import java.util.Random;

/**
 * 排序算法总结
 * @author mao by 10:24 2020/3/11
 */
public class SortUtil {

    /**
     * 基本排序  时间复杂度：O(n^2)
     * 每一个数（从最前面开始），跟后面所有的数进行比较。
     * 如果这个数比比较的数大，则替换。
     * 举例：（箭头标识比较大小确认是否需要交换）
     * ⑤ ④ ③ ② ①
     * ↓          ↓     交换
     * ⑤ ④ ③ ② ①
     * ↓       ↓
     * ① ④ ③ ② ⑤
     * ↓    ↓
     * ① ④ ③ ② ⑤
     * ↓ ↓
     * ① ④ ③ ② ⑤
     *    ↓       ↓
     * ① ④ ③ ② ⑤
     *    ↓    ↓        交换
     * ① ④ ③ ② ⑤
     *    ↓ ↓
     * ① ② ③ ④ ⑤
     *       ↓    ↓
     * ① ② ③ ④ ⑤
     *       ↓ ↓
     * ① ② ③ ④ ⑤
     *          ↓ ↓
     * ① ② ③ ④ ⑤
     * @param _arr array
     */
    private static void basicSort(int[] _arr){
        if (emptyArray(_arr))
            return;
        int[] arr = _arr.clone();
        int len = arr.length;
        for (int i = 0; i < len; i++){
            //从最后面数，直到arr[i+1]，与arr[i]进行比较
            for (int j = len - 1; j > i; j--){
                //如果大，则交换
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        printArray(arr);
    }

    /**
     * 冒泡排序  时间复杂度：O(n^2)
     * 从最后面开始，每个数与前一个数进行比较，大则替换，一直到将最小的数替换到最前面
     * 举例：（箭头标识比较大小确认是否需要交换）
     *
     *  ⑤ ④ ③ ② ①
     *           ↓ ↓    交换
     *  ⑤ ④ ③ ① ②
     *        ↓ ↓       交换
     *  ⑤ ④ ① ③ ②
     *     ↓ ↓          交换
     *  ⑤ ① ④ ③ ②
     *  ↓ ↓             交换
     *  ① ⑤ ④ ③ ②
     *           ↓ ↓    交换
     *  ① ⑤ ④ ② ③
     *        ↓ ↓       交换
     *  ① ⑤ ② ④ ③
     *     ↓ ↓          交换
     *  ① ② ⑤ ④ ③
     *           ↓ ↓    交换
     *  ① ② ⑤ ③ ④
     *        ↓ ↓       交换
     *  ① ② ③ ⑤ ④
     *           ↓ ↓
     *  ① ② ③ ④ ⑤
     * @param _arr array
     */
    private static void bubbleSort(int[] _arr){
        if (emptyArray(_arr))
            return;
        int[] arr = _arr.clone();
        int len = arr.length;
        for (int i = 0; i < len; i++){
            //从最后面数，直到arr[i+1]，与arr[i]进行比较
            for (int j = len - 1; j > i; j--){
                //如果大，则交换
                if (arr[j] < arr[j - 1]){
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        printArray(arr);
    }

    /**
     * 选择排序  时间复杂度：O(n^2)
     * 一个数 与后面（后面所有的数与这个数比较，小的则记录下来）记录的最小的数
     * 如果比这个数小，则交换。
     * 举例：（箭头标识比较大小确认是否需要交换）
     * ⑤ ④ ③ ② ①
     * ↓ ↓              min = 1     arr[min] = 4
     * ⑤ ④ ③ ② ①
     * ↓    ↓           min = 2     arr[min] = 3
     * ⑤ ④ ③ ② ①
     * ↓       ↓        min = 3     arr[min] = 2
     * ⑤ ④ ③ ② ①
     * ↓          ↓     min = 4     arr[min] = 1    ①和⑤交换
     * ⑤ ④ ③ ② ①
     *    ↓ ↓           min = 2     arr[min] = 2
     * ① ④ ③ ② ⑤
     *    ↓    ↓        min = 3     arr[min] = 2
     * ① ④ ③ ② ⑤
     *    ↓       ↓     min = 3     arr[min] = 2    ②和④交换
     * ① ④ ③ ② ⑤
     *       ↓ ↓        min = 3     arr[min] = 4
     * ① ② ③ ④ ⑤
     *       ↓    ↓     min = 3     arr[min] = 4    不交换
     * ① ② ③ ④ ⑤
     *          ↓ ↓     min = 4     arr[min] = 4    不交换
     * ① ② ③ ④ ⑤
     * @param _arr array
     */
    private static void selectSort(int[] _arr){
        if (emptyArray(_arr))
            return;
        int[] arr = _arr.clone();
        int len = arr.length;
        int min;
        for (int i = 0; i < len - 1; i++){
            min = i;
            for (int j = i + 1; j < len; j++){
                if (arr[min] > arr[j])
                    min = j;
            }
            if (min != i){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        printArray(arr);
    }

    /**
     * 插入排序  时间复杂度：O(n^2)
     * 默认将第一位的数看成是一个有序的数组，从第二位开始，依次从后往前比较
     * 若小于这个数，则往前插入一位，继续往前比较，如不小于，则终止内层循环
     * @param _arr array
     */
    private static void insertSort(int[] _arr){
        if (emptyArray(_arr))
            return;
        int[] arr = _arr.clone();
        int len = arr.length;
        for (int i = 1; i < len; i++){
            int j = i;
            //需要替换的数
            int need = arr[i];
            //当需要替换的数[need]比前面的数[pre]小，则[pre]向后移一位
            while (j > 0 && need < arr[j - 1]){
                arr[j] = arr[j - 1];
                j --;
            }
            //插入
            arr[j] = need;
        }
        printArray(arr);
    }

    /**
     * 快速排序 时间复杂度：O(n lg n)
     * @param _arr array
     */
    private static void quickSort(int[] _arr){
        if (emptyArray(_arr))
            return;
        int[] arr = _arr.clone();
        quickSortDo(arr,0,arr.length - 1);
        printArray(arr);
    }

    /**
     * 快速排序
     * @param arr array
     * @param low 最低位
     * @param high 最高位
     */
    private static void quickSortDo(int[] arr, int low, int high){
        if (low > high)
            return;
        //记录最低位和最高位
        int i = low,j = high;
        //基准
        int temp = arr[low];
        int t;
        while (i < j){
            while (temp <= arr[j] && i < j){
                j--;
            }
            while (temp >= arr[i] && i < j){
                i++;
            }
            if (i < j){
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[low] = arr[i];
        arr[i] = temp;
        quickSortDo(arr,low,j - 1);
        quickSortDo(arr,j + 1,high);
    }

    /**
     * 判断数组是否为空
     * @param arr array
     * @return boolean
     */
    private static boolean emptyArray(int[] arr){
        return arr == null || arr.length == 0;
    }

    /**
     * 打印数组
     * @param arr array
     */
    private static void printArray(int[] arr){
        String s = "{";
        for (int i : arr)
            s += i+",";
        s = s.substring(0,s.length() - 1);
        s += "}";
        System.out.println(s);
    }

    public static void main(String[] args) {
        int[] arr = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000 ; i++)
            arr[i] = random.nextInt(1000);
        System.out.println(System.currentTimeMillis());
        basicSort(arr);
        System.out.println(System.currentTimeMillis());
        bubbleSort(arr);
        System.out.println(System.currentTimeMillis());
        selectSort(arr);
        System.out.println(System.currentTimeMillis());
        insertSort(arr);
        System.out.println(System.currentTimeMillis());
        quickSort(arr);
        System.out.println(System.currentTimeMillis());
    }

}