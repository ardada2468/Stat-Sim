import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.util.*;

public class Stats {
    int cardA = 1;
    int cardB = 2;
    int cardC = 3;
    int cardD = 4;
    int cardE = 5;
    int[] allCards = {cardA, cardB, cardC, cardD, cardE};
    ArrayList Cards = new ArrayList<Integer>();



    static int getCard(){
      int rand =  (int)(Math.random()*5) +1;
      return rand;
    }

    void add(int x){
        Cards.add(x);
    }

    boolean checkCompleteSet(){
        boolean isComplete = true;
        for (int i = 0; i < allCards.length; i++) {
            isComplete = Cards.contains(allCards[i]);
            if(!isComplete){
                return  false;
            }
        }
        return isComplete;
    }


    void keepOnAdd(){
        boolean run = checkCompleteSet();
        while (!run){
            add(getCard());
            run = checkCompleteSet();
        }
    }

    public static void main(String[] args) {
        int total = 1_000_000;
        int index = 23;
        int count = 0;
        int max = 0;

        Map<Integer, Integer> Data = new TreeMap<Integer, Integer>();
        for (int i = 0; i < total; i++) {
            System.out.println("Trial: " + (i+1)  +  " -----------------------------------");
            Stats s = new Stats();
            s.keepOnAdd();
            int cardSize = s.Cards.size();
            if(s.Cards.size() >= index){count++;}
            if(max < s.Cards.size()){max= s.Cards.size();}
            if(Data.containsKey(cardSize)){
                Data.replace(s.Cards.size(), Data.get(cardSize)+1);
            }else Data.put(cardSize,1);
            System.out.println(s.Cards + " | Length: " + s.Cards.size());


        }
        System.out.println("<----------------Summary--------------->");
        System.out.println("Percent Grater than " + index + " : " + ((count*1.0)/total)*100.0 + "%");
        System.out.println("Max : " + max);
        System.out.println("Total Trials: " + total);


        Set<Integer> keySet = Data.keySet();
        ArrayList<Integer> listOfKeys = new ArrayList<Integer>(keySet);
        Collection<Integer> values = Data.values();
        ArrayList<Integer> listOfValues = new ArrayList<>(values);


        final CategoryChart chart = new CategoryChartBuilder().width(1400).height(1000).title("Simulation Chart").xAxisTitle("# of Boxes Purchase to get all Cards").yAxisTitle("Frequency").build();
        chart.addSeries("Frequency Occurred",listOfKeys,listOfValues);
        chart.getStyler().setToolTipsEnabled(true);
        new SwingWrapper(chart).displayChart();


    }

}
