package de.janbraunsdorff.ase.layer.presentation.console.printer.charts;

import java.util.Arrays;

public class BarChart {
    public static void main(String[] args) {
        int[] data = new int[]{901, 850, 550, 450, 100, 1130, 1601, 1300, 1340, 1200, 3000};
        String[] keys = new String[]{"01.11","05.11","08.11","12.11","17.11","18.11","21.11","23.11","27.11","29.11","30.11"};

        BarChart chart = new BarChart(new ChartData(data, keys, "Chart Test"));
        chart.print();
    }

    private final int chartHeight = 21;
    private final StringBuilder builder;
    private final ChartData data;
    private String[][] gird;
    int start;
    int steps;



    public BarChart(ChartData data){
        this.builder = new StringBuilder();
        this.data = data;
        // [spalten][Zeilen]
        this.gird = new String[data.getValue().length + 1][chartHeight+1];
    }

    public void print(){
        drawScale();
        for (int i = 0; i < this.data.getValue().length; i++){
            drawBar(this.data.getValue()[i], this.data.getKey()[i], i+1);
        }
        for (int i = 0; i < gird[0].length; i++){
            for (int j = 0; j < gird.length; j++){
                builder.append(gird[j][i]);
            }
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    private void drawBar(int value,String key, int column){
        double height = (double) value/ (double)start;
        int blocks = (int)(chartHeight * height)+1;

        int s =(int) (height * 1000) % 10;

        if (s > 5){
            blocks++;
        }

        int i = 0;
        for (;i < chartHeight - blocks; i++){
            gird[column][i] = empty();
        }

        if (s>5){
            gird[column][i] = half();
            i++;
        }
        for (;i < chartHeight-1; i++){
            gird[column][i] = full();
        }
        gird[column][chartHeight-1] = bottom();
        gird[column][chartHeight] = String.format(" %s ", key);
    }

    private String full(){
        return String.format("%c%c%c%c%c%c%c", ' ' ,' ' , ChartElement.Block.getSymbol(),ChartElement.Block.getSymbol(),ChartElement.Block.getSymbol(), ' ', ' ');
    }
    private String empty(){
        return String.format("%7s", "");
    }

    private String half() {
        return String.format("%c%c%c%c%c%c%c", ' ',' ', ChartElement.HalfBlock.getSymbol(), ChartElement.HalfBlock.getSymbol(), ChartElement.HalfBlock.getSymbol(), ' ',' ');
    }

    private String bottom() {
        return String.format("%c%c%c%c%c%c%c", ChartElement.Horizontal.getSymbol(),ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.HorizontalDataPoint.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol());
    }

    private void drawScale(){
        int maxValue = Arrays.stream(this.data.getValue()).max().getAsInt();
        int minValue = Math.min(Arrays.stream(this.data.getValue()).min().getAsInt(), 0);
        start = scale(maxValue);

        if (minValue == 0){
            steps = start / chartHeight+1;
        }



        for (int i = 0; i < chartHeight-1; i++){
            gird[0][i] = String.format("%8s%-3s", "", ChartElement.Vertical.getSymbol());
        }

        gird[0][0] = String.format("%5s€ %s%-3s", start, ChartElement.Horizontal.getSymbol(), ChartElement.VerticalDataPoint.getSymbol());
        gird[0][5] = String.format("%5s€ %s%-3s", (int) (start * 0.75), ChartElement.Horizontal.getSymbol(), ChartElement.VerticalDataPoint.getSymbol());
        gird[0][10] = String.format("%5s€ %s%-3s", (int) (start * 0.5), ChartElement.Horizontal.getSymbol(), ChartElement.VerticalDataPoint.getSymbol());
        gird[0][15] = String.format("%5s€ %s%-3s", (int) (start * 0.25), ChartElement.Horizontal.getSymbol(), ChartElement.VerticalDataPoint.getSymbol());
        gird[0][20] = String.format("%5s€ %s%s%s%s", 0 , ChartElement.Horizontal.getSymbol(), ChartElement.Edge.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol());
        gird[0][21] = String.format("%11s", "");
    }

    private int scale(int maxValue){
        for (int i = 10; i < 100; i+=10){
            if(maxValue / i == 0){
                return i;
            }
        }

        for (int i = 100; i < 1000; i+=100){
            if(maxValue / i == 0){
                return i;
            }
        }

        for (int i = 200; i < 10000; i+=200){
            if(maxValue / i == 0){
                return i;
            }
        }

        for (int i = 200; i < 100000; i+=1000){
            if(maxValue / i == 0){
                return i;
            }
        }

        return 0;
    }



}

