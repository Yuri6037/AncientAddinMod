package net.yuri6037.AncientAddinMod.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathUtilities {

    /**
     * Returns random integers between par1 and par2 (you need to specify witch random)
     */
    public static int generateRandomInteger(int par1, int par2, Random par3Random) {
        if (par1 > par2) {
            throw new IllegalArgumentException("SLDT_GAME_ENGINE_ERROR_003");
        }

        long range = (long) par2 - (long) par1 + 1;

        long fraction = (long) (range * par3Random.nextDouble());
        return (int) (fraction + par1);
    }

    /**
     * Returns if numToCheck is first or no
     */
    public static boolean isNumberFirst(int numToCheck){
        if (numToCheck == 1){
            return false;
        }

        int[] MFB = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        int divisor = 0;
        int i = 0;
        while ((divisor ^ 2) < numToCheck){
            if (i < MFB.length) {
                divisor = MFB[i];
            } else {
                divisor += 2;
            }
            if (divisor == numToCheck){
                i++;
                continue;
            }
            int rest = numToCheck % divisor;
            if (rest == 0){
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Returns first factors of a number
     */
    public static int[] decompileNumberInFactors(int numToDecompile){
        if (isNumberFirst(numToDecompile)){
            return new int[]{numToDecompile};
        }
        List<Integer> list = new ArrayList<Integer>();
        int divisor = 0;
        while (divisor < numToDecompile){
            divisor++;
            if (!isNumberFirst(divisor)){
                continue;
            }
            int rest = numToDecompile % divisor;
            if (rest == 0){
                list.add(divisor);
                numToDecompile = numToDecompile / divisor;
                divisor = 0;
            }
        }
        int[] ints = new int[list.size()];
        for (int j = 0 ; j < list.size() ; j++){
            ints[j] = list.get(j);
        }
        return ints;
    }

    /**
     * Returns integer part of a double
     */
    public static int getIntegerPart(double d){
        double fractionalPart = d % 1;
        return (int)(d - fractionalPart);
    }

    /**
     * Returns fractional part of a number
     */
    public static float getFractionalPart(double d){
        return (float)(d % 1);
    }
}
