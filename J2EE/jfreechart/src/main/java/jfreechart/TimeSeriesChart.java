package jfreechart;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
 
public class TimeSeriesChart {
 
	private TimeSeries prepareTimeSeries(String seriesName){
		TimeSeries timeSeries = new TimeSeries(seriesName);
		
		Random random = new Random();
		
		for(int i=0;i<10;i++){
			timeSeries.add(new Day(21,(i+1),2014),random.nextInt(100));
		}
		
		return timeSeries;
	}
	
    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
 
        dataset.addSeries(prepareTimeSeries("timeseries01"));
        dataset.addSeries(prepareTimeSeries("timeseries02"));
        dataset.addSeries(prepareTimeSeries("timeseries03"));
        return dataset;
    }
 
    private void setUpTheme(){
        //创建主题样式  
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
        //设置标题字体  
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
        //设置图例的字体  
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
        //设置轴向的字体  
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
        //应用主题样式  
        ChartFactory.setChartTheme(standardChartTheme); 
    }
    
    public void createTimeSeriesChart() throws IOException {
    	setUpTheme();
        
        JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart(
                "title", "xLabel", "yLabel", createDataset(), true,
                true, false);
        
        setStyle(timeSeriesChart);
        
        ChartUtilities.saveChartAsPNG(new File("timeseries.png"), timeSeriesChart, 600, 600);
    }
 
    private void setStyle(JFreeChart timeSeriesChart) {
    	XYPlot plot = timeSeriesChart.getXYPlot();
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        XYItemRenderer render = plot.getRenderer();
        if (render instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) render;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);

            // set the first time series 
            renderer.setSeriesLinesVisible(0, false);
            renderer.setSeriesPaint(0, Color.green);
        }
    }
 
    public static void main(String[] args) throws IOException {
        new TimeSeriesChart().createTimeSeriesChart();
    }
 
}
