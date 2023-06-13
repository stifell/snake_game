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
        //0.6630944f, 1.51136f, 0.14275694f, 2.858049f, 1.7509128f, 1.7592596f
        AI start = new AI(new float[]{0f,0f,0f,0f,0f,0f});
        AI best = start;
        int res = result(best);
        while (res < 100000){
            for (int i = 0; i < 100; i ++){
                AI child = new AI(1f,start);
                int childres = result(child);
                if (childres > res){
                    res = childres;
                    best = child;
                }
            }
            start = best;
            start.output();
            System.out.println(res);
        }
    }
}