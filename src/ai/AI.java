package ai;

import java.util.Random;

public class AI {
    float[] k = new float[6];
    public AI(float[] k){
        for (int i = 0; i < 6; i++)
            this.k[i] = k[i];
    }
    AI(float delta, AI parent){
        Random rng =new Random();
        for (int i = 0; i < 6; i++)
            k[i] = parent.k[i] - delta + rng.nextFloat(delta) * 2;
    }

    public float snake_go_top(float O_t, float e_t) {
        return k[0]*O_t + k[3]*e_t;
    }

    public float snake_go_right(float O_r, float e_r) {
        return k[1]*O_r + k[4]*e_r;
    }

    public float snake_go_left(float O_l, float e_l) {
        return k[2]*O_l + k[5]*e_l;
    }

    void output(){
        for (int i = 0; i < 6; i++){
            System.out.print(k[i] + "f, ");
        }
        System.out.println();
    }
}