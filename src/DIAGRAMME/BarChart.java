package DIAGRAMME;

import CLASS.Client;
import CLASS.effectif_par_banque;
import DAO.DaoPret;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * A bar chart that uses a custom renderer to display different colors within a
 * single series. The colors use GradientPaint.
 */
public class BarChart extends ApplicationFrame {

    /**
     * A custom renderer that returns a different color for each item in a
     * single series.
     */
    static class CustomBarRenderer extends BarRenderer {

        /**
         * The colors.
         */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors the colors.
         */
        public CustomBarRenderer(Paint[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the paint for an item. Overrides the default behaviour
         * inherited from AbstractSeriesRenderer.
         *
         * @param row the series.
         * @param column the category.
         *
         * @return The item color.
         */
        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public BarChart(String title) {
        super(title);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<effectif_par_banque> Grid_effectif_par_banque;

        DaoPret dao = new DaoPret();

        Grid_effectif_par_banque = dao.Lister_effectif_par_banque(null);
        /* List<Livre> ls = moL.GetAllLivre(null);
        for (Livre l : ls) {
            dataset.addValue(l.getNbPret(), "Nb Livre", l.getNumLiv());
        }*/
        for (effectif_par_banque effectif : Grid_effectif_par_banque) {
            dataset.addValue(effectif.getTotale_payer(), "", effectif.getDesignation());
        }
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return a sample chart.
     */
    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart("MONTANT TOTALE  / BANQUE", null, null, dataset, PlotOrientation.VERTICAL, false, true, false);

        TextTitle title = chart.getTitle();
        title.setBorder(0, 0, 0, 0);

        title.setBackgroundPaint(new GradientPaint(0f, 0f, Color.white, 350f,
                0f, Color.white, true));
        title.setExpandToFitSpace(true);

        chart.setBackgroundPaint(new GradientPaint(0f, 0f, Color.white, 350f,
                0f, Color.white, true));

        //get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA!");
//        plot.setBackgroundPaint(null);
        plot.setInsets(new RectangleInsets(10, 5, 5, 5));
        plot.setOutlinePaint(Color.black);
//        plot.setRangeGridlinePaint(Color.GRAY);
//        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
//        
        plot.setRangeGridlinePaint(Color.blue);
        plot.setBackgroundPaint(Color.white);
        Paint[] colors = createPaint();
        CustomBarRenderer renderer = new CustomBarRenderer(colors);
//        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(true);
        renderer.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_HORIZONTAL));
        plot.setRenderer(renderer);

        // change the margin at the top of the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //rangeAxis.setRange(0.0,(100));
        rangeAxis.setTickMarkPaint(Color.black);

        return chart;

    }

    /**
     * Returns an array of paint objects that will be used for the bar colors.
     *
     * @return An array of paint objects.
     */
    private static Paint[] createPaint() {
        Paint[] colors = new Paint[5];
        colors[0] = new GradientPaint(0f, 0f, Color.red, 0f, 0f, Color.white);
        colors[1] = new GradientPaint(0f, 0f, Color.green, 0f, 0f, Color.white);
        colors[2] = new GradientPaint(0f, 0f, Color.blue, 0f, 0f, Color.white);
        colors[3] = new GradientPaint(0f, 0f, Color.orange, 0f, 0f, Color.white);
        colors[4] = new GradientPaint(0f, 0f, Color.magenta, 0f, 0f, Color.white);
        return colors;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

}
