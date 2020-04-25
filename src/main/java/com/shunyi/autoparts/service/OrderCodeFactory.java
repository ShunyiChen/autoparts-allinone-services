package com.shunyi.autoparts.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description:
 * @Author: 陈顺谊
 * @CreateDate: 2020/4/25
 */
public class OrderCodeFactory {

    /**
     * 采购订单类别头
     */
    private static final String PURCHASE_CODE = "CG";
    /**
     * 采购退货类别头
     */

    private static final String PURCHASE_RETURN_ORDER = "CGTH";
    /**
     * 随即编码
     */

    private static final int[] r = new int[]{7, 9, 6, 2, 8, 1, 3, 0, 5, 4};
    /**
     * 用户id和随机数总长度
     */

    private static final int maxLength = 14;

    /**
     * 更具id进行加密+加随机数组成固定长度编码
     */


    private static String toCode(Long id) {
        String idStr = id.toString();
        StringBuilder idsbs = new StringBuilder();
        for (int i = idStr.length() - 1; i >= 0; i--) {
            idsbs.append(r[idStr.charAt(i) - '0']);
        }
        return idsbs.append(getRandom(maxLength - idStr.length())).toString();
    }

    /**
     * 生成时间戳
     */


    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 生成固定长度随机码
     *
     * @param n 长度
     */
    private static long getRandom(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min;
        return rangeLong;
    }

    /**
     * 生成不带类别标头的编码
     *
     * @param userId
     */


    private static synchronized String getCode(Long userId) {
        userId = userId == null ? 10000 : userId;
        return getDateTime() + toCode(userId);
    }

    /**
     * 生成采购订单单号
     *
     * @param userId
     */
    public static String getPurchaseOrderCode(Long userId) {
        return PURCHASE_CODE + getCode(userId);
    }

    /**
     * 生成采购退货订单单号
     *
     * @param userId
     */
    public static String getPurchaseReturnOrderCode(Long userId) {
        return PURCHASE_RETURN_ORDER + getCode(userId);
    }

    public static void main(String[] args) {
        System.out.println(OrderCodeFactory.getPurchaseOrderCode(111L));
    }
}
