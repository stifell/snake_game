package ai;

public class Education {
    static int result(AI ai){
        int res = 0;
        for (int i =0; i < 3; i++){
            res += new Simulation().run(ai);
        }
        return res/3;
    }

    public static void main(String[] args){
        AI start = new AI(new float[]{0f,0f,0f,0f,0f,0f,0f});
        AI best = start;
        int res = result(best);
        while (res < 1000000){
            for (int i = 0; i < 50; i ++){
                AI child = new AI(1f,start);
                int childres = result(child);
                if (childres > res){
                    res = childres;
                    best = child;
                }
               // System.out.println((i + 1) + "...");
            }
            start = best;
            start.output();
            System.out.println(res);
        }
    }

}