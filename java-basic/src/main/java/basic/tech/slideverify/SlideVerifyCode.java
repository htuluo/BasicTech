//package basic.tech.slideverify;
//
//import com.google.common.collect.Lists;
//import com.mysql.jdbc.log.LogUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.RandomUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @author ： luoleiming
// * @date ：Created in 2021/1/6
// * @description： //滑动验证码破解,https://blog.csdn.net/micro_hz/article/details/107120292
// */
//@Slf4j
//public class SlideVerifyCode {
//    /**
//     * 处理滑动验证码破解
//     */
//    public boolean skipRobotTest() {
//        try {
//            // 下载图片
//            ImageDownload imageDownload = new ImageDownload();
//            WebElement img = getRobotTestImage();
//            String imgUrl = img.getAttribute("src");
//            imageDownload.dowloadImage(imgUrl, CURRENT_ROBOT_TEST_IMAGE);
//
//            // 获取比例
//            double slideRate = getSlideRate(CURRENT_ROBOT_TEST_IMAGE);
//            // 滑动滑条
//            moveButton(slideRate);
//        } catch (Throwable e) {
//            return true;
//        }
//        return false;
//    }
//
//    private double getSlideRate(String imageName) {
//        try {
//            /*
//            获取图片的长宽，构建二维数组遍历
//             */
//            BufferedImage image = ImageIO.read(new File(ChromeSupport.INS_PATH + imageName + ".jpeg"));
//            int width = image.getWidth();
//            int height = image.getHeight();
//
//            // 标记较白的点为凹陷轮廓边缘点
//            List<Integer> widthEdgeList = Lists.newArrayList();
//
//            for (int i = 0; i < width; i++) {
//                for (int j = 0; j < height; j++) {
//                    // 这里分别获取RGB值的，红，绿，蓝 的值
//                    int rgb = image.getRGB(i, j);
//                    int redV = (rgb & 0xff0000) >> 16;
//                    int greenV = (rgb & 0xff00) >> 8;
//                    int blueV = (rgb & 0xff);
//
//                    // 分析发现，数值越大，越接近白色，因此这里分别判断三个值，达到230即可标记为一个较白的点
//                    if (redV > 230 && greenV > 230 && blueV > 230) {
//                        widthEdgeList.add(i);
//                    }
//                }
//            }
//
//            /*
//            统计凹陷最多的横坐标
//             */
//            Map<Integer, List<Integer>> map = widthEdgeList.stream().collect(Collectors.groupingBy(Integer::intValue));
//            ArrayList<List<Integer>> lists = Lists.newArrayList(map.values());
//
//            Collections.sort(lists, (o1, o2) -> {
//                if (o1.size() > o2.size()) {
//                    return -1;
//                } else if (o1.size() < o2.size()) {
//                    return 1;
//                }
//                return 0;
//            });
//            // 左竖线横坐标
//            int leftEdge = lists.get(0).get(0);
//            // 右竖线横坐标
//            int rightEdge = lists.get(1).get(0);
//            // 得到凹陷正中央
//            int slidePixel = (rightEdge + leftEdge) / 2;
//            // 滑动比例
//            double rate = Double.valueOf(slidePixel) / Double.valueOf(width);
//            // 柔性调整
//            return zoomRate(rate);
//        } catch (IOException e) {
//            log.error("get kill robot rate error", e);
//        }
//        return 0;
//    }
//
//    /**
//     * 发现会根据50%的偏移量需要增量
//     *
//     * @param rate
//     * @return
//     */
//    private double zoomRate(double rate) {
//        double originRate = rate;
//        if (rate < 0.45) {
//            rate -= 0.02;
//        }
//        if (rate >= 0.45 && rate < 0.6) {
//            return rate;
//        } else if (rate >= 0.6 && rate < 0.67) {
//            rate += 0.02;
//        } else if (rate >= 0.67 && rate < 0.75) {
//            rate += 0.02;
//        } else if (rate >= 0.75 && rate < 0.8) {
//            rate += 0.05;
//        } else {
//            rate += 0.07;
//        }
//        log.info("kill robot slide rate {}, zoom rate {}", originRate, rate);
//        return rate;
//    }
//
//    private void moveButton(double slideRate) {
//        // 获取滑动条点击样式元素
//        WebElement moveButtonEl = webDriver.findElement(By.xpath("xxxxxxx"));
//        Actions moveAction = new Actions(webDriver);
//        moveAction.clickAndHold(moveButtonEl);
//
//        // 540为滑动条的全部长度，随机滑动步数
//        int targetMoveCount = (int) (540 * slideRate);
//
//        // getRandomStep获得随机长度移动数组
//        for (Integer count : getRandomStep(targetMoveCount)) {
//            moveAction.moveByOffset(count, 0);
//            moveAction.perform();
//        }
//        moveAction.release(moveButtonEl).perform();
//    }
//
//    public List<Integer> getRandomStep(int targetMoveCount) {
//        List<Integer> list = Lists.newArrayList();
//        while (targetMoveCount > 0) {
//            // 剩余长度生成随机数
//            int count = RandomUtils.nextInt(0, targetMoveCount + 1);
//            list.add(count);
//            // 得到剩余长度
//            targetMoveCount -= count;
//        }
//        return list;
//    }
//
//}
