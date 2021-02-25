package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;

class GridTest {
    private String[][] field;
    private Grid grid;

    private void init(int width) throws Exception {
        this.grid = new Grid(width);
        Field f = this.grid.getClass().getDeclaredField("grid");
        f.setAccessible(true);
        this.field = (String[][]) f.get(this.grid);
    }

    @Test
    public void initFiled() throws Exception {
        init(1);
        assertThat(field.length, Matchers.is(1));
        assertThat(field[0].length, Matchers.is(22));
    }

    @Test
    public void drawY() throws Exception {
        init(1);
        this.grid.addY();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(ComposedChartElements.Y.getSymbol());
        }
        builder.append("null").append("null");
        assertThat(columnToString(field[0]), Matchers.is(builder.toString()));
    }

    @Test
    public void drawX() throws Exception {
        init(2);
        this.grid.addX(new String[]{"1200€"});
        StringBuilder builder = new StringBuilder();
        var expected = "    0€ ═╩═════╦═══";
        var values = "            1200€ ";

        assertThat(rowToString(20), Matchers.is(expected));
        assertThat(rowToString(21), Matchers.is(values));
    }

    @Test
    public void drawXTwoDataPoint() throws Exception {
        init(3);
        this.grid.addX(new String[]{"12.12", "13.13"});
        var expected = "    0€ ═╩═════╦══════╦═══";
        var values = "            12.12  13.13 ";

        assertThat(rowToString(20), Matchers.is(expected));
        assertThat(rowToString(21), Matchers.is(values));
    }

    @Test
    public void drawXKeyToLong() throws Exception {
        init(3);
        this.grid.addX(new String[]{"12.125", "13.13"});
        var expected = "    0€ ═╩═════╦══════╦═══";
        var values = "            12.12  13.13 ";

        assertThat(rowToString(20), Matchers.is(expected));
        assertThat(rowToString(21), Matchers.is(values));
    }

    @Test
    public void drawXKeyToSortByOne() throws Exception {
        init(3);
        this.grid.addX(new String[]{"12.1", "13.13"});
        var expected = "    0€ ═╩═════╦══════╦═══";
        var values = "            12.1   13.13 ";

        assertThat(rowToString(20), Matchers.is(expected));
        assertThat(rowToString(21), Matchers.is(values));
    }

    @Test
    public void addFullColumnUntil10() throws Exception {
        init(2);
        this.grid.addFullLine(1, 10, 20);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append("null");
        }
        for (int i = 0; i < 10; i++) {
            builder.append(ComposedChartElements.FULL.getSymbol());
        }
        builder.append("null").append("null");


        assertThat(columnToString(this.field[1]), Matchers.is(builder.toString()));
    }

    @Test
    public void addEmptyLine() throws Exception {
        init(2);
        this.grid.addEmptyLine(1, 0, 20);

        StringBuilder builder = new StringBuilder();
        builder.append("null").append("null");

        assertThat(columnToString(this.field[1]).replace(" ", ""), Matchers.is(builder.toString()));
    }

    @Test
    public void addHalfBlock() throws Exception {
        init(2);
        this.grid.addHalfBlock(1, 19);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 19; i++) {
            builder.append("null");
        }
        for (int i = 0; i < 1; i++) {
            builder.append(ComposedChartElements.HALF.getSymbol());
        }
        builder.append("null").append("null");

        assertThat(columnToString(this.field[1]), Matchers.is(builder.toString()));
    }

    @Test
    public void testToString() throws Exception {
        init(2);
        this.grid.addY();
        this.grid.addX(new String[]{"01.20"});
        this.grid.addFullLine(1, 10, 20);
        this.grid.addHalfBlock(1, 9);
        this.grid.addEmptyLine(1, 0, 9);

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 22; i++){
            if (i < 20){
                builder.append(ComposedChartElements.Y.getSymbol());
            }
            if (i < 9) {
                builder.append(ComposedChartElements.EMPTY.getSymbol());
            }
            if (i == 9) {
                builder.append(ComposedChartElements.HALF.getSymbol());
            }
            if (i > 9 && i < 20) {
                builder.append(ComposedChartElements.FULL.getSymbol());
            }
            if (i == 20){
                builder.append("    0€ ═╩══");
                builder.append(ComposedChartElements.X.getSymbol());
            }
            if (i == 21){
                builder.append("            01.20 ");
            }
            builder.append("\n");
        }

        assertThat(grid.toString(), Matchers.is(builder.toString()));
    }


    private String rowToString(int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.field.length; i++) {
            builder.append(this.field[i][index]);
        }
        return builder.toString();
    }

    private String columnToString(String[] column) {
        return String.join("", column);
    }
}
