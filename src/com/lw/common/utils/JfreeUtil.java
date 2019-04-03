package com.lw.common.utils;

import java.awt.Font;
import java.awt.Rectangle;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class JfreeUtil {
    
    // 柱状图
    public static void setJfreeChart(JFreeChart chart) {
        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        // 获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // 获取X轴的对象
        CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot
                .getDomainAxis();
        // 获取Y轴的对象
        NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();
        // 处理X轴上的乱码
        categoryAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理X轴外的乱码
        categoryAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上的乱码
        numberAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴外的乱码
        numberAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上显示的刻度，以1作为1格
        numberAxis3D.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis3D.setTickUnit(unit);
        // 获取绘图区域对象
        BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot
                .getRenderer();
        // 设置柱形图的宽度
        barRenderer3D.setMaximumBarWidth(0.07);
        // 在图形上显示数字
        barRenderer3D
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barRenderer3D.setBaseItemLabelsVisible(true);
        barRenderer3D.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
    }
    

    // 饼状图
    public static void setJfreePie(JFreeChart chart) {
        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        // 获取图表区域对象
        PiePlot3D categoryPlot = (PiePlot3D) chart.getPlot();
        // 处理图像上的乱码
        categoryPlot.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 设置图形的生成格式为（上海 2 （10%））
        String format = "{0} {1} ({2})";
        categoryPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                format));
        // 使用ChartFrame对象显示图像
    }

    // 折线图
    public static void setJfreeLine(JFreeChart chart) {
        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        // 获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // 获取X轴的对象
        CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
        // 获取Y轴的对象
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        // 处理X轴上的乱码
        categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理X轴外的乱码
        categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上的乱码
        numberAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴外的乱码
        numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上显示的刻度，以1作为1格
        numberAxis.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis.setTickUnit(unit);
        // 获取绘图区域对象
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot
                .getRenderer();
        // 在图形上显示数字
        lineAndShapeRenderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer
                .setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
        // 在图形上添加转折点（使用小矩形显示）
        Rectangle shape = new Rectangle(10, 10);
        lineAndShapeRenderer.setSeriesShape(0, shape);
        lineAndShapeRenderer.setSeriesShapesVisible(0, true);
    }
}
