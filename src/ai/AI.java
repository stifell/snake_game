package ai;

import java.util.Random;

public class AI {
    float[] k = new float[7];
    AI(float[] k){
        for (int i = 0; i < 7; i++)
            this.k[i] = k[i];
    }
    AI(float delta, AI parent){
        Random rng =new Random();
        for (int i = 0; i < 7; i++)
            k[i] = parent.k[i] - delta + rng.nextFloat(delta) * 2;
    }
    int snake_go(float O_t, float O_r, float O_l, float x_h, float y_h,float x_a, float y_a) {
        if (k[0]*O_t + k[1]*O_r + k[2]*O_l + k[3]*x_h + k[4]*y_h + k[5]*x_a + k[6]*y_a < -50)
                return -1;
        if (k[0]*O_t + k[1]*O_r + k[2]*O_l + k[3]*x_h + k[4]*y_h + k[5]*x_a + k[6]*y_a > 50)
            return 1;
        return 0;
    }
    void output(){
        for (int i = 0; i < 7; i++){
            System.out.print(k[i] + "f,  ");
        }
        System.out.println();
    }
}